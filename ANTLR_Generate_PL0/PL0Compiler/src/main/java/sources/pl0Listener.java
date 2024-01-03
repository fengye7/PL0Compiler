package sources;

// Generated from E:/Exercise/Courses/PL0_Project/ANTLR_Generate_PL0/PL0Compiler/src/main/java/pl0.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link pl0Parser}.
 */
public interface pl0Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link pl0Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(pl0Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(pl0Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(pl0Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(pl0Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaration(pl0Parser.ConstantDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#constantDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaration(pl0Parser.ConstantDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstantDefinition(pl0Parser.ConstantDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#constantDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstantDefinition(pl0Parser.ConstantDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(pl0Parser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(pl0Parser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(pl0Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(pl0Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(pl0Parser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(pl0Parser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(pl0Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(pl0Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(pl0Parser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(pl0Parser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(pl0Parser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(pl0Parser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void enterConditionStatement(pl0Parser.ConditionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#conditionStatement}.
	 * @param ctx the parse tree
	 */
	void exitConditionStatement(pl0Parser.ConditionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(pl0Parser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(pl0Parser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(pl0Parser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(pl0Parser.RelationalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(pl0Parser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(pl0Parser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(pl0Parser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(pl0Parser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(pl0Parser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(pl0Parser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(pl0Parser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(pl0Parser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link pl0Parser#unsignedInteger}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedInteger(pl0Parser.UnsignedIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link pl0Parser#unsignedInteger}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedInteger(pl0Parser.UnsignedIntegerContext ctx);
}