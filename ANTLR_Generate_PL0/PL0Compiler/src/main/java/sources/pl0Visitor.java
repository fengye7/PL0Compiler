package sources;

// Generated from E:/Exercise/Courses/PL0_Project/ANTLR_Generate_PL0/PL0Compiler/src/main/java/pl0.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import sources.pl0Parser;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link pl0Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface pl0Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link pl0Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(pl0Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(pl0Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(pl0Parser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#constantDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinition(pl0Parser.ConstantDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(pl0Parser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(pl0Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(pl0Parser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(pl0Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(pl0Parser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(pl0Parser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#conditionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionStatement(pl0Parser.ConditionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(pl0Parser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(pl0Parser.RelationalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(pl0Parser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(pl0Parser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(pl0Parser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(pl0Parser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link pl0Parser#unsignedInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedInteger(pl0Parser.UnsignedIntegerContext ctx);
}