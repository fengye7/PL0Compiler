package sources;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import sources.pl0BaseVisitor;
import sources.pl0Parser;
import sources.pl0Parser.*;

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
}

public class QuadrupleGenerator extends pl0BaseVisitor<Void> {
    private List<Quadruple> quadruples;
    private int tempCount;
    private int labelCount;

    public QuadrupleGenerator() {
        quadruples = new ArrayList<>();
        tempCount = 0;
        labelCount = 0;
    }

    @Override
    public Void visitProgram(ProgramContext ctx) {
        visit(ctx.block());
        generateQuadruple("OPR", "0", "0", "0"); // Halt operation
        return null;
    }

    @Override
    public Void visitBlock(BlockContext ctx) {
        if (ctx.constantDeclaration() != null) {
            visit(ctx.constantDeclaration());
        }
        if (ctx.variableDeclaration() != null) {
            visit(ctx.variableDeclaration());
        }
        visit(ctx.statement());
        return null;
    }

    @Override
    public Void visitConstantDeclaration(ConstantDeclarationContext ctx) {
        for (ConstantDefinitionContext defCtx : ctx.constantDefinition()) {
            visit(defCtx);
        }
        return null;
    }

    @Override
    public Void visitConstantDefinition(ConstantDefinitionContext ctx) {
        String name = ctx.identifier().getText();
        String value = ctx.unsignedInteger().getText();
        generateQuadruple("CONST", value, null, name);
        return null;
    }

    @Override
    public Void visitVariableDeclaration(VariableDeclarationContext ctx) {
        for (TerminalNode identifier : ctx.identifier()) {
            generateQuadruple("VAR", null, null, identifier.getText());
        }
        return null;
    }

    @Override
    public Void visitAssignmentStatement(AssignmentStatementContext ctx) {
        String variable = ctx.Identifier().getText();
        String expressionResult = visit(ctx.expression());

        generateQuadruple(":=", expressionResult, null, variable);

        return null;
    }

    @Override
    public String visitExpression(ExpressionContext ctx) {
        String termResult = visit(ctx.term());
        String expressionPrimeResult = visit(ctx.expressionPrime());

        if (expressionPrimeResult != null) {
            String result = generateTempVariable();

            Quadruple addQuadruple = new Quadruple("+", termResult, expressionPrimeResult, result);
            quadruples.add(addQuadruple);

            return result;
        }

        return termResult;
    }

    @Override
    public String visitExpressionPrime(ExpressionPrimeContext ctx) {
        if (ctx.getChildCount() == 0) {
            return null;
        }

        String termResult = visit(ctx.term());
        String expressionPrimeResult = visit(ctx.expressionPrime());

        if (expressionPrimeResult != null) {
            String result = generateTempVariable();

            Quadruple addQuadruple = new Quadruple("+", termResult, expressionPrimeResult, result);
            quadruples.add(addQuadruple);

            return result;
        }

        return termResult;
    }

    @Override
    public String visitTerm(TermContext ctx) {
        String factorResult = visit(ctx.factor());
        String termPrimeResult = visit(ctx.termPrime());

        if (termPrimeResult != null) {
            String result = generateTempVariable();

            Quadruple multiplyQuadruple = new Quadruple("*", factorResult, termPrimeResult, result);
            quadruples.add(multiplyQuadruple);

            return result;
        }

        return factorResult;
    }

    @Override
    public String visitTermPrime(TermPrimeContext ctx) {
        if (ctx.getChildCount() == 0) {
            return null;
        }

        String factorResult = visit(ctx.factor());
        String termPrimeResult = visit(ctx.termPrime());

        if (termPrimeResult != null) {
            String result = generateTempVariable();

            Quadruple multiplyQuadruple = new Quadruple("*", factorResult, termPrimeResult, result);
            quadruples.add(multiplyQuadruple);

            return result;
        }

        return factorResult;
    }

    @Override
    public String visitFactor(FactorContext ctx) {
        if (ctx.Identifier() != null) {
            return ctx.Identifier().getText();
        } else if (ctx.UnsignedInteger() != null) {
            return ctx.UnsignedInteger().getText();
        } else if (ctx.expression()!= null) {
            return visit(ctx.expression());
        }

        return null;
    }

    @Override
    public Void visitConditionStatement(ConditionStatementContext ctx) {
        String conditionResult = visit(ctx.condition());

        generateQuadruple("JPC", conditionResult, null, null);

        int jumpQuadrupleIndex = quadruples.size() - 1; // Remember the index of the jump quadruple

        visit(ctx.statement());

        Quadruple jumpQuadruple = quadruples.get(jumpQuadrupleIndex);
        jumpQuadruple.setOperand3(String.valueOf(quadruples.size())); // Set the jump target to the current quadruple count

        return null;
    }

    @Override
    public String visitCondition(ConditionContext ctx) {
        String expression1Result = visit(ctx.expression(0));
        String expression2Result = visit(ctx.expression(1));
        String operator = visit(ctx.relationalOperator());

        String result = generateTempVariable();

        Quadruple compareQuadruple = new Quadruple(operator, expression1Result, expression2Result, result);
        quadruples.add(compareQuadruple);

        return result;
    }

    @Override
    public String visitRelationalOperator(RelationalOperatorContext ctx) {
        if (ctx.Equal() != null) {
            return "==";
        } else if (ctx.LessThan() != null) {
            return "<";
        } else if (ctx.LessThanOrEqual() != null) {
            return "<=";
        } else if (ctx.GreaterThan() != null) {
            return ">";
        } else if (ctx.GreaterThanOrEqual() != null) {
            return ">=";
        } else if (ctx.NotEqual() != null) {
            return "!=";
        }

        return null;
    }

    @Override
    public Void visitLoopStatement(LoopStatementContext ctx) {
        int loopStartIndex = quadruples.size(); // Remember the index of the loop start quadruple

        visit(ctx.statement());

        String conditionResult = visit(ctx.condition());

        generateQuadruple("JPC", conditionResult, null, String.valueOf(loopStartIndex)); // Jump back to the loop start

        return null;
    }

    @Override
    public Void visitCompoundStatement(CompoundStatementContext ctx) {
        for (StatementContext statementCtx : ctx.statement()) {
            visit(statementCtx);
        }
        return null;
    }

    @Override
    public Void visitEmptyStatement(EmptyStatementContext ctx) {
        return null;
    }

    @Override
    public String visitIdentifier(IdentifierContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitUnsignedInteger(UnsignedIntegerContext ctx) {
        return ctx.getText();
    }

    private void generateQuadruple(String operator, String operand1, String operand2, String operand3) {
        Quadruple quadruple = new Quadruple(operator, operand1, operand2, operand3);
        quadruples.add(quadruple);
    }

    private String generateTempVariable() {
        String tempVariable = "T" + tempCount;
        tempCount++;
        return tempVariable;
    }

    public List<Quadruple> getQuadruples() {
        return quadruples;
    }
}