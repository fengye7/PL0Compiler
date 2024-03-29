package sources;

import java.util.*;

import org.antlr.v4.runtime.Token;

import sources.pl0Parser.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Symbol {
    public String name;
    public String type;
    public String address;

    public Symbol(String name, String type, String addressInt) {
        this.name = name;
        this.type = type;
        this.address ="ADDR" + addressInt;
    }
}

class Quadruple {
    public String operator;
    public String op1;
    public String op2;
    public String result;

    public Quadruple(String operator, String op1, String op2, String result) {
        this.operator = operator;
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
    }

    public void setOperand3(String operand3) {
        this.result = operand3;
    }
}

public class QuadrupleGenerator extends pl0BaseVisitor<Void> {
    private List<Quadruple> quadruples;
    private Map<String, Symbol> symbolTable;
    private int tempCount;
    private int labelCount;
    private int addressCount;

    public QuadrupleGenerator() {
        quadruples = new ArrayList<>();
        symbolTable = new HashMap<>();
        tempCount = 0;
        labelCount = 0;
        addressCount =0;
    }

    @Override
    public Void visitProgram(ProgramContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        Token programToken = ctx.getStart();
        match(programToken.getText(),"PROGRAM");
        String programName = ctx.identifier().getText();

        System.out.println("Program Name: " + programName);
        symbolTable.put(programName, new Symbol(programName, "PROCEDURE", Integer.toString(addressCount++)));
        visitBlock(ctx.block());
//        generateQuadruple("OPR", "0", "0", "0"); // Halt operation
        return null;
    }

    @Override
    public Void visitBlock(BlockContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        if (ctx.constantDeclaration() != null) {
            visitConstantDeclaration(ctx.constantDeclaration());
        }
        if (ctx.variableDeclaration() != null) {
            visitVariableDeclaration(ctx.variableDeclaration());
        }
        visitStatement(ctx.statement());

        // 生成四元式中间代码
        generateQuadruple("END", "", "", ""); // 结束程序块
        return null;
    }

    @Override
    public Void visitConstantDeclaration(ConstantDeclarationContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        for (ConstantDefinitionContext defCtx : ctx.constantDefinition()) {
            visitConstantDefinition(defCtx);
        }
        return null;
    }

    @Override
    public Void visitConstantDefinition(ConstantDefinitionContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        String name = ctx.identifier().getText();
        if (symbolTable.containsKey(name)) {
            error("Error: Constant " + name + " is already defined.");
        }

        String value = ctx.unsignedInteger().getText();
        generateQuadruple("CONST", value, null, name);
        symbolTable.put(name, new Symbol(name, "CONST", Integer.toString(addressCount++)));
        return null;
    }

    @Override
    public Void visitVariableDeclaration(VariableDeclarationContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        for (IdentifierContext identifier : ctx.identifier()) {
            if (symbolTable.containsKey(identifier.getText())) {
                error("Error: VAR " + identifier.getText() + " is already defined.");
            }
            generateQuadruple("VAR", identifier.getText(),null, null);
            symbolTable.put(identifier.getText(), new Symbol(identifier.getText(), "VAR", Integer.toString(addressCount++)));
        }
        return null;
    }

    @Override
    public Void visitAssignmentStatement(AssignmentStatementContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        String variable = ctx.identifier().getText();
        if (!symbolTable.containsKey(variable)) {
            error("Error: Undeclared identifier: " + variable);
        }
        String expressionResult = visitExpression(ctx.expression());

        generateQuadruple(":=", expressionResult, null, variable);

        return null;
    }

    @Override
    public String visitExpression(ExpressionContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        String termResult = visitTerm(ctx.term());

        if (ctx.getChildCount() > 1) {
            String operator = ctx.getChild(1).getText();
            String expressionResult = visitExpression(ctx.expression());
            String result = generateTempVariable();
            symbolTable.put(result, new Symbol(result, "VAR", Integer.toString(addressCount++)));

            Quadruple quadruple;

            if (operator.equals("+")) {
                quadruple = new Quadruple("+", termResult, expressionResult, result);
            } else {
                quadruple = new Quadruple("-", termResult, expressionResult, result);
            }

            quadruples.add(quadruple);

            return result;
        }

        return termResult;
    }

    @Override
    public String visitTerm(TermContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        String factorResult = visitFactor(ctx.factor());

        if (ctx.getChildCount() > 1) {
            String operator = ctx.getChild(1).getText();
            String termResult = visitTerm(ctx.term());
            String result = generateTempVariable();
            symbolTable.put(result, new Symbol(result, "VAR", Integer.toString(addressCount++)));

            Quadruple quadruple;

            if (operator.equals("*")) {
                quadruple = new Quadruple("*", factorResult, termResult, result);
            } else {
                quadruple = new Quadruple("/", factorResult, termResult, result);
            }

            quadruples.add(quadruple);

            return result;
        }

        return factorResult;
    }

    @Override
    public String visitFactor(FactorContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        if (ctx.identifier() != null) {
            return visitIdentifier(ctx.identifier());
        } else if (ctx.unsignedInteger() != null) {
            return visitUnsignedInteger(ctx.unsignedInteger());
        } else if (ctx.expression() != null) {
            return visitExpression(ctx.expression());
        }

        return null;
    }

    @Override
    public Void visitConditionStatement(ConditionStatementContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        String trueLabel = generateLabelVariable();
        String falseLabel = generateLabelVariable();
        String conditionResult = visitCondition(ctx.condition());

        generateQuadruple("JPC", conditionResult, null, trueLabel); // 条件为真时跳转执行语句
        generateQuadruple("JMP", null, null, falseLabel);

        generateQuadruple("LBL", trueLabel, null, null); // 添加条件为真时的标签
        visitStatement(ctx.statement());
        generateQuadruple("LBL", falseLabel, null, null); // 添加条件为假时的标签

        return null;
    }

    @Override
    public String visitCondition(ConditionContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        String expression1Result = visitExpression(ctx.expression(0));
        String expression2Result = visitExpression(ctx.expression(1));
        String operator = visitRelationalOperator(ctx.relationalOperator());

        String result = generateTempVariable();
        symbolTable.put(result, new Symbol(result, "VAR", Integer.toString(addressCount++)));

        Quadruple compareQuadruple = new Quadruple(operator, expression1Result, expression2Result, result);
        quadruples.add(compareQuadruple);

        return result;
    }

    @Override
    public String visitRelationalOperator(RelationalOperatorContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        if (ctx.getText().equals("=")) {
            return "==";
        } else if (ctx.getText().equals("<>")) {
            return "!=";
        } else {
            return ctx.getText();
        }
    }

    @Override
    public Void visitLoopStatement(LoopStatementContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        String trueLabel = generateLabelVariable();
        String falseLabel = generateLabelVariable();
        String conditionLabel = generateLabelVariable();

        generateQuadruple("LBL", conditionLabel, null, null);
        String conditionResult = visitCondition(ctx.condition());
        generateQuadruple("JPC", conditionResult, null, trueLabel);
        generateQuadruple("JMP", null, null, falseLabel); // 跳出循环

        generateQuadruple("LBL", trueLabel, null, null);
        visitStatement(ctx.statement());
        generateQuadruple("JMP", null, null, conditionLabel); // 跳回循环开始
        generateQuadruple("LBL", falseLabel, null, null);
        return null;
    }

    @Override
    public Void visitCompoundStatement(CompoundStatementContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        for (StatementContext statementCtx : ctx.statement()) {
            visit(statementCtx);
        }
        return null;
    }

    @Override
    public Void visitEmptyStatement(EmptyStatementContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        return null;
    }

    @Override
    public String visitIdentifier(IdentifierContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        if (!symbolTable.containsKey(ctx.getText())) {
            error("Error: Undeclared identifier: " + ctx.getText());
        }
        return ctx.getText();
    }

    @Override
    public String visitUnsignedInteger(UnsignedIntegerContext ctx) {
        if(ctx == null){
            System.out.println("ctx is null");
            return null;
        }
        return ctx.getText();
    }

    private void generateQuadruple(String operator, String operand1, String operand2, String operand3) {
        Quadruple quadruple = new Quadruple(operator, operand1, operand2, operand3);
        quadruples.add(quadruple);
    }

    private String generateTempVariable() {
        String tempVariable = "Temp" + tempCount;
        tempCount++;
        return tempVariable;
    }

    private String generateLabelVariable() {
        String tempVariable = "Label" + labelCount;
        labelCount++;
        return tempVariable;
    }

    public List<Quadruple> getQuadruples() {
        return quadruples;
    }

    private void error(String message) {
        System.err.println(message);
        System.exit(1);
    }

    private void match(String str, String expectedStr)
    {
        if (!Objects.equals(str, expectedStr))
        {
            error("Unexpected token: " + expectedStr + " <=> " + str);
        }
    }

    public void printToFile(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            for (Map.Entry<String, Symbol> entry : symbolTable.entrySet()) {
                String name = entry.getKey();
                Symbol symbol = entry.getValue();
                fileWriter.write("Name: " + name + ", Type: " + symbol.type+ ", Address: " + symbol.address +  "\n");
            }
            fileWriter.close();
            System.out.println("Symbol table has been printed to " + filename);
        } catch (IOException e) {
            System.err.println("Unable to open file: " + filename);
        }
    }
}