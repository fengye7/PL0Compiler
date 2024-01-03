package sources;
// Generated from E:/Exercise/Courses/PL0_Project/ANTLR_Generate_PL0/PL0Compiler/src/main/java/grammer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link grammerParser}.
 */
public interface grammerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link grammerParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(grammerParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(grammerParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(grammerParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(grammerParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaration(grammerParser.ConstantDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaration(grammerParser.ConstantDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstantDefinition(grammerParser.ConstantDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstantDefinition(grammerParser.ConstantDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(grammerParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(grammerParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(grammerParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(grammerParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(grammerParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(grammerParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(grammerParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(grammerParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(grammerParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(grammerParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(grammerParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(grammerParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void enterConditionStatement(grammerParser.ConditionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void exitConditionStatement(grammerParser.ConditionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(grammerParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(grammerParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(grammerParser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(grammerParser.RelationalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(grammerParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(grammerParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(grammerParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(grammerParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(grammerParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(grammerParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(grammerParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(grammerParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link grammerParser#unsignedInteger}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedInteger(grammerParser.UnsignedIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link grammerParser#unsignedInteger}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedInteger(grammerParser.UnsignedIntegerContext ctx);
}