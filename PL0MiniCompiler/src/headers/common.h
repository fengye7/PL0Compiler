#ifndef COMMON_H
#define COMMON_H

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

enum TokenType
{
    KEYWORD_PROGRAM,
    KEYWORD_BEGIN,
    KEYWORD_END,
    KEYWORD_CONST,
    KEYWORD_VAR,
    KEYWORD_WHILE,
    KEYWORD_DO,
    KEYWORD_IF,
    KEYWORD_THEN,
    IDENTIFIER,
    INTEGER,
    OPERATOR,
    DELIMITER,
    END_OF_FILE
};

struct Token
{
    TokenType type;
    string lexeme;
};

// 四元式类型
enum OpType
{
    OP_ADD,
    OP_SUB,
    OP_MUL,
    OP_DIV,
    OP_ASSIGN,
    OP_LOAD,
    OP_LOAD_CONST,
    OP_LABEL,  // 设置标签
    OP_BEQ,    // 相等跳转
    OP_BNE,    // 不相等跳转
    OP_BLT,    // 小于跳转
    OP_BLE,    // 小于等于跳转
    OP_BGT,    // 大于跳转
    OP_BGE,    // 大于等于跳转
    OP_JUMP,   // 无条件跳转
    OP_JMP,    // 跳转
    OP_INVALID // 非法操作符
};

// common函数声明 =================================================================
void error(const string &message);

string tokenTypeToString(TokenType type);

string opTypeToString(OpType op);

OpType opToOpType(string op);

// 四元式类
class Quadruplet
{
public:
    // 构造函数
    Quadruplet(OpType op, string op1, string op2, string result)
    {
        this->op = op;
        this->op1 = op1;
        this->op2 = op2;
        this->result = result;
    }

    void outputQuadruplet()
    {
        cout << opTypeToString(op) << " " << op1 << " " << op2 << " " << result << endl;
    }

private:
    // 成员变量
    OpType op;
    string op1;
    string op2;
    string result;
};

enum class SymbolType
{
    PROGRAM,
    CONSTANT,
    VARIABLE
};

class SymbolTable
{
private:
    std::unordered_map<std::string, SymbolType> symbolTable;
    std::unordered_map<std::string, std::string> symbolAddresses;
    int addressCounter;

public:
    SymbolTable() : addressCounter(0) {}

    void addIdentifier(const std::string &identifier, SymbolType type)
    {
        symbolTable[identifier] = type;
        std::string address = "ADDR" + std::to_string(addressCounter++);
        symbolAddresses[identifier] = address;
    }

    bool isIdentifierDeclared(const std::string &identifier)
    {
        return symbolTable.count(identifier) > 0;
    }

    SymbolType getIdentifierType(const std::string &identifier)
    {
        return symbolTable[identifier];
    }

    void printToFile(const std::string &filename)
    {
        std::ofstream outputFile(filename);
        if (outputFile.is_open())
        {
            for (const auto &entry : symbolTable)
            {
                outputFile << entry.first << ": ";
                switch (entry.second)
                {
                case SymbolType::PROGRAM:
                    outputFile << "PROGRAM";
                    break;
                case SymbolType::CONSTANT:
                    outputFile << "CONSTANT";
                    break;
                case SymbolType::VARIABLE:
                    outputFile << "VARIABLE";
                    break;
                }
                outputFile << " (Address: " << symbolAddresses[entry.first] << ")" << std::endl;
            }
            outputFile.close();
            std::cout << "Symbol table has been printed to " << filename << std::endl;
        }
        else
        {
            std::cerr << "Unable to open file: " << filename << std::endl;
        }
    }
};

#endif