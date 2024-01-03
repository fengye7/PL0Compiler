package sources;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import sources.grammerLexer;
import sources.grammerParser;

public class PL0Compiler {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: PL0Analyzer <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            // 读取输入文件内容
            String inputText = new String(Files.readAllBytes(Paths.get(inputFile)), StandardCharsets.UTF_8);

            // 创建ANTLR输入流
            CharStream inputStream = CharStreams.fromString(inputText);

            // 创建PL0Lexer词法分析器
            grammerLexer lexer = new grammerLexer(inputStream);

            // 创建词法符号流
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            // 创建PL0Parser语法分析器
            grammerParser parser = new grammerParser(tokenStream);

            // 解析抽象语法树
            grammerParser.ProgramContext programContext = parser.program();

            // 打印抽象语法树
            printParseTree(programContext,"--");

            // 生成中间代码并写入输出文件
            // TODO: 根据需求编写生成中间代码的逻辑，并将结果写入输出文件

            System.out.println("Abstract syntax tree printed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printParseTree(ParseTree tree, String indent) {
        System.out.println(indent + tree.getClass().getSimpleName() + ": " + tree.getText());
        for (int i = 0; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);
            printParseTree(child, indent + "  ");
        }
    }
}