#include "./headers/parser.h"

void UsageWarning()
{
    cout << "Usage: " << endl;
    cout << "pl0 inputFilename outputFileName" << endl;
}

int main(int argc, char **argv)
{
    if (argc != 3)
        UsageWarning();
    else
    {
        Parser parser(argv[1], argv[2]);
        parser.compile();
        // parser.outputIntermediateCode();
        // parser.outputLexicalAnalyseResult();
    }
    return 0;
}