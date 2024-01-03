package sources;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import sources.pl0Lexer;
import sources.pl0Parser;

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
            pl0Lexer lexer = new pl0Lexer(inputStream);

            // 创建词法符号流
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            // 创建PL0Parser语法分析器
            pl0Parser parser = new pl0Parser(tokenStream);

            // 解析抽象语法树
            pl0Parser.ProgramContext programContext = parser.program();

            // 打印抽象语法树
            printParseTree(programContext, "--");

            // 创建中间代码生成器
            QuadrupleGenerator quadrupleGenerator = new QuadrupleGenerator();
            // 遍历抽象语法树，生成中间代码
            quadrupleGenerator.visit(programContext);
            // 获取生成的四元式中间代码
            List<Quadruple> quadruples = quadrupleGenerator.getQuadruples();

            // 将中间代码写入输出文件
            writeQuadruplesToFile(quadruples, outputFile);

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

    public static void writeQuadruplesToFile(List<Quadruple> quadruples, String outputFile) {
        // TODO: 将四元式中间代码写入输出文件
        // 这里你需要根据你的需求和四元式中间代码生成类的设计来实现将中间代码写入文件的逻辑
    }
}