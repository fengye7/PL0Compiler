/*
 * @Author: fengye7 zcj2518529668@163.com
 * @Date: 2023-12-31 21:23:49
 * @LastEditors: fengye7 zcj2518529668@163.com
 * @LastEditTime: 2024-01-01 15:33:43
 * @FilePath: \PL0MiniCompiler\src\headers\parser.h
 * @Description:
 *
 * Copyright (c) 2023 by ${git_name_email}, All Rights Reserved.
 */
#ifndef PARSER_H
#define PARSER_H

#include "../headers/LexicalAnalysis.h"

class Parser
{
public:
    Parser(const string &inputFileName, const string &outputFileName) : inputFile(inputFileName), outputFile(outputFileName), lexicalAnalyzer(inputFileName) {}

    void compile() // 编译程序
    {
        currentToken = lexicalAnalyzer.getNextToken();
        program();
    }

    void outputLexicalAnalyseResult()
    {
        lexicalAnalyzer.outputTokenList();
    }

    void outputIntermediateCode()
    {
        for (auto &it : quadrupleList)
            it.outputQuadruplet();
    }

private:
    ifstream inputFile;
    ofstream outputFile;
    Token currentToken;
    int tempVarCount = 0;             // 用于生成临时变量的计数器
    int labelCount = 0;               // 用于生成唯一标签的计数器
    vector<Quadruplet> quadrupleList; // 四元式列表

    LexicalAnalysis lexicalAnalyzer; // 词法分析子程序

    void emit(OpType op, string op1, string op2, string result); // 生成中间代码
    void match(TokenType expectedType, const string &lexeme);    // 匹配符号
    // 下面是递归下降翻译程序
    void program();
    void programHeader();
    void subprogram();
    void constantDeclaration();
    void variableDeclaration();
    void statement();
    void assignmentStatement();
    string expression();
    string term();
    string factor();
    void conditionalStatement();
    void conditions(string trueLabel, string falseLabel, bool isEmitted);
    void loopStatement();
    void compoundStatement();

    string generateTempVariable();
    string generateNewLabel();
};

#endif