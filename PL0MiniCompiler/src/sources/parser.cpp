#include "../headers/parser.h"

// 在parser中emit四元式
void Parser::emit(OpType op, string op1, string op2, string result)
{
    Quadruplet quad(op, op1, op2, result);
    // 将四元式加入列表
    quadrupleList.push_back(quad);
    // 输入文件
    outputFile << "(  " << opTypeToString(op) << " , " << op1 << " , " << op2 << " , " << result << "  )" << endl;
}

void Parser::match(TokenType expectedType, const string &lexeme)
{
    if ((currentToken.type == expectedType && currentToken.lexeme == lexeme) || (currentToken.type == expectedType && currentToken.type == INTEGER) || (currentToken.type == expectedType && currentToken.type == IDENTIFIER))
    {
        currentToken = lexicalAnalyzer.getNextToken();
    }
    else
    {
        outputIntermediateCode();
        outputLexicalAnalyseResult();
        error("Unexpected token: " + tokenTypeToString(expectedType) + " <=> " + tokenTypeToString(currentToken.type) + " -> " + currentToken.lexeme);
    }
}

// 递归下降分析程序

void Parser::program()
{
    programHeader();
    subprogram();
}

void Parser::programHeader()
{
    match(KEYWORD_PROGRAM, "PROGRAM");
    match(IDENTIFIER, "_");
}

void Parser::subprogram()
{
    if (currentToken.type == KEYWORD_CONST)
    {
        constantDeclaration();
    }

    if (currentToken.type == KEYWORD_VAR)
    {
        variableDeclaration();
    }
    statement();
}

void Parser::constantDeclaration()
{
    int num = 0;

    match(KEYWORD_CONST, "CONST");
    do
    {
        if (num != 0)
            match(DELIMITER, ",");
        string identifier = currentToken.lexeme;
        match(IDENTIFIER, "_");
        match(OPERATOR, ":=");
        string value = currentToken.lexeme;
        match(INTEGER, "_");

        num++;

        emit(OP_LOAD_CONST, value, "_", identifier);
    } while (currentToken.type == DELIMITER && currentToken.lexeme == ",");
    match(DELIMITER, ";");
}

void Parser::variableDeclaration()
{
    int num = 0;

    match(KEYWORD_VAR, "VAR");
    do
    {
        if (num != 0)
            match(DELIMITER, ",");
        string identifier = currentToken.lexeme;
        match(IDENTIFIER, "_");
        num++;
        emit(OP_LOAD, identifier, "_", "_");
    } while (currentToken.type == DELIMITER && currentToken.lexeme == ",");
    match(DELIMITER, ";");
}

void Parser::statement()
{
    if (currentToken.type == IDENTIFIER)
    {
        assignmentStatement();
    }
    else if (currentToken.type == KEYWORD_IF)
    {
        conditionalStatement();
    }
    else if (currentToken.type == KEYWORD_WHILE)
    {
        loopStatement();
    }
    else if (currentToken.type == KEYWORD_BEGIN)
    {
        compoundStatement();
    }
    else
    { // <空语句>
    }
}

void Parser::assignmentStatement()
{
    string identifier = currentToken.lexeme;
    match(IDENTIFIER, "_");
    match(OPERATOR, ":=");
    string value = expression();

    emit(OP_ASSIGN, value, "_", identifier);
}

string Parser::expression()
{
    string left;
    if (currentToken.type == DELIMITER && (currentToken.lexeme == "+" || currentToken.lexeme == "-"))
    {
        string op = currentToken.lexeme;
        match(DELIMITER, currentToken.lexeme);
        if (op == "-")
            left = "-";
    }
    left += term();
    while (currentToken.type == DELIMITER && (currentToken.lexeme == "+" || currentToken.lexeme == "-"))
    {
        string op = currentToken.lexeme;
        match(DELIMITER, currentToken.lexeme);
        string right = term();
        string result = generateTempVariable();

        emit(op == "+" ? OP_ADD : OP_SUB, left, right, result);
        left = result;
    }
    return left;
}

string Parser::term()
{
    string left = factor();
    while (currentToken.type == DELIMITER && (currentToken.lexeme == "*" || currentToken.lexeme == "/"))
    {
        string op = currentToken.lexeme;
        match(DELIMITER, currentToken.lexeme);
        string right = factor();
        string result = generateTempVariable();

        emit(op == "*" ? OP_MUL : OP_DIV, left, right, result);
        left = result;
    }
    return left;
}

string Parser::factor()
{
    if (currentToken.type == IDENTIFIER)
    {
        string identifier = currentToken.lexeme;
        match(IDENTIFIER, "_");

        return identifier;
    }
    else if (currentToken.type == INTEGER)
    {
        string integer = currentToken.lexeme;
        match(INTEGER, "_");

        return integer;
    }
    else if (currentToken.type == DELIMITER && currentToken.lexeme == "(")
    {
        match(DELIMITER, "(");
        string result = expression();
        match(DELIMITER, ")");
        return result;
    }
    else
    {
        error("Invalid factor");
        return "Invalid factor";
    }
}

void Parser::conditionalStatement()
{
    match(KEYWORD_IF, "IF");
    string trueLabel = generateNewLabel();
    string falseLabel = generateNewLabel();

    conditions(trueLabel, falseLabel, false);
    match(KEYWORD_THEN, "THEN");

    emit(OP_LABEL, trueLabel, "_", "_"); // trueLabel
    statement();
    emit(OP_LABEL, falseLabel, "_", "_"); // falseLabel
}

void Parser::conditions(string trueLabel, string falseLabel, bool isEmitted)
{
    string left = expression();
    string operatorToken = currentToken.lexeme;
    match(OPERATOR, operatorToken); // 匹配关系运算符
    string right = expression();
    if (!isEmitted)
    {
        emit(opToOpType(operatorToken), left, right, trueLabel); // 生成条件的中间代码
        emit(OP_JUMP, "_", "_", falseLabel);
    }
}

void Parser::loopStatement()
{
    match(KEYWORD_WHILE, "WHILE");
    string conditionLabel = generateNewLabel();
    string trueLabel = generateNewLabel();
    string falseLabel = generateNewLabel();
    bool isEmitted = false;

    emit(OP_LABEL, conditionLabel, "_", "_"); // 条件标签
    conditions(trueLabel, falseLabel, isEmitted);
    isEmitted = true;

    match(KEYWORD_DO, "DO");
    emit(OP_LABEL, trueLabel, "_", "_"); // trueLabel
    statement();
    emit(OP_JUMP, "_", "_", conditionLabel); // 无条件跳转到条件标签
    emit(OP_LABEL, falseLabel, "_", "_");    // 循环体结束标签
}

void Parser::compoundStatement()
{
    match(KEYWORD_BEGIN, "BEGIN");
    statement();
    while (currentToken.type == DELIMITER && currentToken.lexeme == ";")
    {
        match(DELIMITER, ";");
        statement();
    }
    match(KEYWORD_END, "END");
}

string Parser::generateTempVariable()
{
    // 生成临时变量名称，例如：Temp0, Temp1, Temp2, ...
    string tempVarName = "Temp" + to_string(tempVarCount);
    tempVarCount++;

    return tempVarName;
}

string Parser::generateNewLabel()
{
    // 生成新的标签名称，例如：Label0, Label1, Label2, ...
    string newLabel = "Label" + to_string(labelCount);
    labelCount++;

    return newLabel;
}