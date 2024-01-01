#include "../headers/common.h"

void error(const string &message)
{
    cerr << "Error: " << message << endl;
    // You can handle error recovery here if desired
    exit(1);
}

string tokenTypeToString(TokenType type)
{
    string str;
    switch (type)
    {
    case TokenType::KEYWORD_PROGRAM:
        str = "KEYWORD_PROGRAM";
        break;
    case TokenType::KEYWORD_BEGIN:
        str = "KEYWORD_BEGIN";
        break;
    case TokenType::KEYWORD_END:
        str = "KEYWORD_END";
        break;
    case TokenType::KEYWORD_CONST:
        str = "KEYWORD_CONST";
        break;
    case TokenType::KEYWORD_VAR:
        str = "KEYWORD_VAR";
        break;
    case TokenType::KEYWORD_WHILE:
        str = "KEYWORD_WHILE";
        break;
    case TokenType::KEYWORD_DO:
        str = "KEYWORD_DO";
        break;
    case TokenType::KEYWORD_IF:
        str = "KEYWORD_IF";
        break;
    case TokenType::KEYWORD_THEN:
        str = "KEYWORD_THEN";
        break;
    case TokenType::IDENTIFIER:
        str = "IDENTIFIER";
        break;
    case TokenType::INTEGER:
        str = "INTEGER";
        break;
    case TokenType::OPERATOR:
        str = "OPERATOR";
        break;
    case TokenType::DELIMITER:
        str = "DELIMITER";
        break;
    case TokenType::END_OF_FILE:
        str = "END_OF_FILE";
        break;
    default:
        str = "UNKNOWN";
    }
    return str;
}

string opTypeToString(OpType op)
{
    switch (op)
    {
    case OP_ADD:
        return "OP_ADD";
    case OP_SUB:
        return "OP_SUB";
    case OP_MUL:
        return "OP_MUL";
    case OP_DIV:
        return "OP_DIV";
    case OP_ASSIGN:
        return "OP_ASSIGN";
    case OP_LOAD:
        return "OP_LOAD";
    case OP_LOAD_CONST:
        return "OP_LOAD_CONST";
    case OP_LABEL:
        return "OP_LABEL";
    case OP_BEQ:
        return "OP_BEQ";
    case OP_BNE:
        return "OP_BNE";
    case OP_BLT:
        return "OP_BLT";
    case OP_BLE:
        return "OP_BLE";
    case OP_BGT:
        return "OP_BGT";
    case OP_BGE:
        return "OP_BGE";
    case OP_JUMP:
        return "OP_JUMP";
    case OP_JMP:
        return "OP_JMP";
    default:
        return "Unknown";
    }
}

OpType opToOpType(string op)
{
    if (op == "+")
    {
        return OP_ADD;
    }
    else if (op == "-")
    {
        return OP_SUB;
    }
    else if (op == "*")
    {
        return OP_MUL;
    }
    else if (op == "/")
    {
        return OP_DIV;
    }
    else if (op == ":=")
    {
        return OP_ASSIGN;
    }
    else if (op == "=")
    {
        return OP_BEQ;
    }
    else if (op == "<>")
    {
        return OP_BNE;
    }
    else if (op == "<")
    {
        return OP_BLT;
    }
    else if (op == "<=")
    {
        return OP_BLE;
    }
    else if (op == ">")
    {
        return OP_BGT;
    }
    else if (op == ">=")
    {
        return OP_BGE;
    }
    else
    {
        error("Unknown OP");
        return OP_INVALID;
    }
}