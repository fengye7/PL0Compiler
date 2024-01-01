#include <iostream>
#include <fstream>
#include <string>
#include <vector>

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

class Compiler
{
public:
    Compiler(const string &inputFileName, const string &outputFileName)
        : inputFile(inputFileName), outputFile(outputFileName) {}

    void compile()
    {
        getNextToken();
        program();
        emit("HALT");
        saveIntermediateCode();
    }

private:
    ifstream inputFile;
    ofstream outputFile;
    Token currentToken;

    void getNextToken()
    {
        char c;
        string lexeme;

        // Skip whitespace
        while (inputFile.get(c) && (c == ' ' || c == '\t' || c == '\n'))
            ;

        if (inputFile.eof())
        {
            currentToken.type = END_OF_FILE;
            currentToken.lexeme = "";
            return;
        }

        if (isalpha(c))
        {
            lexeme += c;
            while (inputFile.get(c) && (isalnum(c) || c == '_'))
            {
                lexeme += c;
            }
            inputFile.putback(c);

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
            {
                currentToken.type = IDENTIFIER;
                currentToken.lexeme = lexeme;
                return;
            }
        }
        else if (isdigit(c))
        {
            lexeme += c;
            while (inputFile.get(c) && isdigit(c))
            {
                lexeme += c;
            }
            inputFile.putback(c);

            currentToken.type = INTEGER;
            currentToken.lexeme = lexeme;
            return;
        }
        else
        {
            lexeme += c;
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == ';' || c == ',')
            {
                currentToken.type = DELIMITER;
            }
            else if (c == ':' && inputFile.get(c) && c == '=')
            {
                lexeme += c;
                currentToken.type = OPERATOR;
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
            else if (c == '>' && inputFile.get(c) && c == '=')
            {
                lexeme += c;
                currentToken.type = OPERATOR;
            }
            else if (c == '=')
            {
                currentToken.type = OPERATOR;
            }
            else
            {
                // Error: Invalid character
                error("Invalid character: " + lexeme);
            }
        }

        currentToken.lexeme = lexeme;
    }

    void emit(const string &code)
    {
        outputFile << code << endl;
    }

    void match(TokenType expectedType)
    {
        if (currentToken.type == expectedType)
        {
            getNextToken();
        }
        else
        {
            error("Unexpected token: " + currentToken.lexeme);
        }
    }

    void error(const string &message)
    {
        cerr << "Error: " << message << endl;
        // You can handle error recovery here if desired
        exit(1);
    }

    void saveIntermediateCode()
    {
        // Implement your code to save the intermediate code to a file
    }

    // Grammar rules implementation

    void program()
    {
        match(KEYWORD_PROGRAM);
        match(IDENTIFIER);
        // Generate code for program header if needed

        subprogram();
        match(KEYWORD_BEGIN);

        statement();
        while (currentToken.type == DELIMITER && currentToken.lexeme == ";")
        {
            match(DELIMITER);
            statement();
        }

        match(KEYWORD_END);
        // Generate code for program end if needed
    }

    void subprogram()
    {
        if (currentToken.type == KEYWORD_CONST)
        {
            constantDeclaration();
        }

        if (currentToken.type == KEYWORD_VAR)
        {
            variableDeclaration();
        }

        // Generate code for subprogram if needed
    }

    void constantDeclaration()
    {
        match(KEYWORD_CONST);
        do
        {
            match(IDENTIFIER);
            match(OPERATOR);
            match(INTEGER);
            // Generate code for constant declaration if needed
        } while (currentToken.type == DELIMITER && currentToken.lexeme == ",");
        match(DELIMITER);
    }

    void variableDeclaration()
    {
        match(KEYWORD_VAR);
        do
        {
            match(IDENTIFIER);
            // Generate code for variable declaration if needed
        } while (currentToken.type == DELIMITER && currentToken.lexeme == ",");
        match(DELIMITER);
    }

    void statement()
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
        {
            // Handle empty statement or other cases
        }
    }

    void assignmentStatement()
    {
        match(IDENTIFIER);
        match(OPERATOR);
        expression();
        // Generate code for assignment statement
    }

    void expression()
    {
        if (currentToken.type == OPERATOR && (currentToken.lexeme == "+" || currentToken.lexeme == "-"))
        {
            match(OPERATOR);
        }
        term();
        while (currentToken.type == OPERATOR && (currentToken.lexeme == "+" || currentToken.lexeme == "-"))
        {
            match(OPERATOR);
            term();
            // Generate code for addition or subtraction
        }
    }

    void term()
    {
        factor();
        while (currentToken.type == OPERATOR && (currentToken.lexeme == "*" || currentToken.lexeme == "/"))
        {
            match(OPERATOR);
            factor();
            // Generate code for multiplication or division
        }
    }

    void factor()
    {
        if (currentToken.type == IDENTIFIER)
        {
            match(IDENTIFIER);
            // Generate code for identifier factor
        }
        else if (currentToken.type == INTEGER)
        {
            match(INTEGER);
            // Generate code for integer factor
        }
        else if (currentToken.type == DELIMITER && currentToken.lexeme == "(")
        {
            match(DELIMITER);
            expression();
            match(DELIMITER);
        }
        else
        {
            error("Invalid factor");
        }
    }

    void
    conditionalStatement()
    {
        match(KEYWORD_IF);
        expression();
        match(KEYWORD_THEN);
        statement();
        // Generate code for conditional statement
    }

    void loopStatement()
    {
        match(KEYWORD_WHILE);
        expression();
        match(KEYWORD_DO);
        statement();
        // Generate code for loop statement
    }

    void compoundStatement()
    {
        match(KEYWORD_BEGIN);
        statement();
        while (currentToken.type == DELIMITER && currentToken.lexeme == ";")
        {
            match(DELIMITER);
            statement();
        }
        match(KEYWORD_END);
    }
};

int main()
{
    Compiler compiler("input.pl0", "output.txt");
    compiler.compile();

    return 0;
}