package sources;// Generated from E:/Exercise/Courses/PL0_Project/ANTLR_Generate_PL0/antlr_generate_pl0Compiler/src/main/java/grammer.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link grammerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface grammerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link grammerParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(grammerParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(grammerParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(grammerParser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#constantDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinition(grammerParser.ConstantDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(grammerParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(grammerParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(grammerParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(grammerParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(grammerParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(grammerParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#conditionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionStatement(grammerParser.ConditionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(grammerParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(grammerParser.RelationalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(grammerParser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(grammerParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(grammerParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(grammerParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link grammerParser#unsignedInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedInteger(grammerParser.UnsignedIntegerContext ctx);
}