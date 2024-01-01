#ifndef LEXICALANALYSIS_H
#define LEXICALANALYSIS_H

#include "./common.h"

class LexicalAnalysis
{
private:
    ifstream inputFile;
    Token currentToken;
    vector<Token> tokenList;

public:
    LexicalAnalysis() {}
    LexicalAnalysis(const string &filename) : inputFile(filename) {} // 构造函数，设定输入文件
    Token getNextToken();                                            // 获取下一个token
    void outputToFile(string &filename);                             // 将词法分析结果输出到文件
    void outputToScreen();                                           // 将词法分析结果输出到console窗口
    void outputTokenList();                                          // 将已经读取完的tokenList打印到screen
};

#endif