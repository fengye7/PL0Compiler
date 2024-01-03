package sources;

import java.util.ArrayList;
import java.util.List;

class Quadruplet {
    public String operator;
    public String op1;
    public String op2;
    public String result;
}

public class QuadrupleGenerator {
    private int tempCount = 1;
    private int labelCount = 1;
    List<Quadruplet> quadrupleList = new ArrayList<>();

    public List<String> generateQuadruples(pl0Parser.ProgramContext program) {
        generateQuadruples(program.getBlock());
        return quadruples;
    }

    private void generateQuadruples(pl0Parser.BlockContext block) {
        generateQuadruples(block.getVariableDeclaration());
        generateQuadruples(block.getStatements());
    }

    private void generateQuadruples(VariableDeclarationContext variableDeclaration) {
        // 处理变量声明
        // 这里可以生成相应的四元式
    }

    private void generateQuadruples(StatementsContext statements) {
        for (StatementContext statement : statements.getStatement()) {
            generateQuadruples(statement);
        }
    }

    private void generateQuadruples(StatementContext statement) {
        if (statement instanceof AssignmentStatementContext) {
            generateQuadruples((AssignmentStatementContext) statement);
        } else if (statement instanceof LoopStatementContext) {
            generateQuadruples((LoopStatementContext) statement);
        } else if (statement instanceof ConditionStatementContext) {
            generateQuadruples((ConditionStatementContext) statement);
        }
        // 处理其他类型的语句
    }

    private void generateQuadruples(AssignmentStatementContext assignmentStatement) {
        String target = assignmentStatement.getIdentifier().getText();
        String source = generateExpressionQuadruple(assignmentStatement.getExpression());
        quadruples.add(target + " = " + source);
    }

    private void generateQuadruples(LoopStatementContext loopStatement) {
        String loopStartLabel = generateLabel();
        String loopEndLabel = generateLabel();

        quadruples.add(loopStartLabel + ":");
        String conditionResult = generateExpressionQuadruple(loopStatement.getCondition());
        quadruples.add("if " + conditionResult + " goto " + loopEndLabel);

        generateQuadruples(loopStatement.getStatement());

        quadruples.add("goto " + loopStartLabel);
        quadruples.add(loopEndLabel + ":");
    }

    private void generateQuadruples(ConditionStatementContext conditionStatement) {
        String conditionResult = generateExpressionQuadruple(conditionStatement.getCondition());
        String trueLabel = generateLabel();
        String falseLabel = generateLabel();
        String endLabel = generateLabel();

        quadruples.add("if " + conditionResult + " goto " + trueLabel);
        quadruples.add("goto " + falseLabel);
        quadruples.add(trueLabel + ":");

        generateQuadruples(conditionStatement.getTrueStatement());

        quadruples.add("goto " + endLabel);
        quadruples.add(falseLabel + ":");

        generateQuadruples(conditionStatement.getFalseStatement());

        quadruples.add(endLabel + ":");
    }

    private String generateExpressionQuadruple(ExpressionContext expression) {
        if (expression instanceof TermContext) {
            return generateTermQuadruple((TermContext) expression);
        }
        // 处理其他类型的表达式
        return null;
    }

    private String generateTermQuadruple(TermContext term) {
        if (term instanceof FactorContext) {
            return generateFactorQuadruple((FactorContext) term);
        }
        // 处理其他类型的项
        return null;
    }

    private String generateFactorQuadruple(FactorContext factor) {
        if (factor instanceof IdentifierContext) {
            return ((IdentifierContext) factor).getText();
        } else if (factor instanceof UnsignedIntegerContext) {
            return ((UnsignedIntegerContext) factor).getText();
        }
        // 处理其他类型的因子
        return null;
    }

    private String generateLabel() {
        return "L" + labelCount++;
    }
}