#include "../headers/LexicalAnalysis.h"

Token LexicalAnalysis::getNextToken()
{
    char c;
    string lexeme;

    // 如果读取到文件末尾
    if (!inputFile)
    {
        currentToken.type = END_OF_FILE;
        currentToken.lexeme = "";
        return currentToken;
    }

    // 忽略空格、制表符、换行符
    while (inputFile.get(c) && (c == ' ' || c == '\t' || c == '\n' || c == '\r'))
        ;

    // 是a-z/A-Z
    if (isalpha(c))
    {
        lexeme += c;
        while (inputFile.get(c) && (isalnum(c) || c == '_')) // 读取完当前符号，限定字母开头
        {
            lexeme += c;
        }
        inputFile.putback(c); // 上面的循环结束多读取了一个字母

        // 判断关键字
        if (lexeme == "PROGRAM")
        {
            currentToken.type = KEYWORD_PROGRAM;
        }
        else if (lexeme == "BEGIN")
        {
            currentToken.type = KEYWORD_BEGIN;
        }
        else if (lexeme == "END")
        {
            currentToken.type = KEYWORD_END;
        }
        else if (lexeme == "CONST")
        {
            currentToken.type = KEYWORD_CONST;
        }
        else if (lexeme == "VAR")
        {
            currentToken.type = KEYWORD_VAR;
        }
        else if (lexeme == "WHILE")
        {
            currentToken.type = KEYWORD_WHILE;
        }
        else if (lexeme == "DO")
        {
            currentToken.type = KEYWORD_DO;
        }
        else if (lexeme == "IF")
        {
            currentToken.type = KEYWORD_IF;
        }
        else if (lexeme == "THEN")
        {
            currentToken.type = KEYWORD_THEN;
        }
        else
        { // 除了关键字就是 声明
            currentToken.type = IDENTIFIER;
            currentToken.lexeme = lexeme;
        }
    }
    else if (isdigit(c))
    { // 完成读取整个数字
        lexeme += c;
        while (inputFile.get(c) && isdigit(c))
        {
            lexeme += c;
        }
        inputFile.putback(c);

        currentToken.type = INTEGER;
        currentToken.lexeme = lexeme;
    }
    else if (c == EOF || (c == ' ' || c == '\t' || c == '\n' || c == '\r'))
    {
        currentToken.type = END_OF_FILE;
        currentToken.lexeme = "";
    }
    else
    { // 不是数字和关键字、声明就是操作符
        lexeme += c;
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == ';' || c == ',')
        {
            currentToken.type = DELIMITER;
        }
        else if (c == ':' && inputFile.get(c))
        {
            if (c == '=')
            {
                lexeme += c;
                currentToken.type = OPERATOR;
            }
            else
            {
                inputFile.putback(c);
            }
        }
        else if (c == '<' && inputFile.get(c))
        {
            if (c == '>' || c == '=')
            {
                lexeme += c;
            }
            else
            {
                inputFile.putback(c);
            }
            currentToken.type = OPERATOR;
        }
        else if (c == '>' && inputFile.get(c))
        {
            if (c == '=')
            {
                lexeme += c;
            }
            else
            {
                inputFile.putback(c);
            }
            currentToken.type = OPERATOR;
        }
        else if (c == '=')
        {
            currentToken.type = OPERATOR;
        }
        else
        {
            // Error: Invalid character
            cout << tokenTypeToString(currentToken.type) << ' ' << currentToken.lexeme << endl;
            error("Invalid character: " + lexeme + ' ' + c);
        }
    }

    currentToken.lexeme = lexeme;
    tokenList.push_back(currentToken);
    return currentToken;
}

void LexicalAnalysis::outputToFile(string &filename)
{
    ofstream outputFile(filename);
    Token token = getNextToken();
    while (token.type != END_OF_FILE)
    {
        outputFile << tokenTypeToString(token.type) << ' ' << token.lexeme << '\n';
        token = getNextToken();
    }
}
void LexicalAnalysis::outputToScreen()
{
    Token token = getNextToken();
    while (token.type != END_OF_FILE)
    {
        cout << tokenTypeToString(token.type) << ' ' << token.lexeme << '\n';
        token = getNextToken();
    }
}

void LexicalAnalysis::outputTokenList()
{
    for (auto &token : tokenList)
    {
        if (token.type != END_OF_FILE)
        {
            cout << tokenTypeToString(token.type) << ' ' << token.lexeme << '\n';
        }
    }
}