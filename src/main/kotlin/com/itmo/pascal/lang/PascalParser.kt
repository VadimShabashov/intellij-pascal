package com.itmo.pascal.lang

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

/**
 * Source: https://www.freepascal.org/docs-html/current/ref/ref.html
 *
 * PROGRAM:
 *   | PROGRAM_HEADER ';' USES_CLAUSE? BLOCK '.'
 *
 * PROGRAM_HEADER:
 *   | 'PROGRAM' IDENTIFIER PROGRAM_PARAMETERS?
 *
 * PROGRAM_PARAMETERS:
 *   | '(' IDENTIFIER_LIST ')'
 *   | '(' ')'
 *
 * IDENTIFIER_LIST:
 *   | IDENTIFIER ',' IDENTIFIER_LIST
 *   | IDENTIFIER
 *
 * USES_CLAUSE:
 *   | 'USES' CLAUSE_LIST ';'
 *
 * CLAUSE_LIST:
 *   | CLAUSE ',' CLAUSE_LIST
 *   | CLAUSE
 *
 * CLAUSE:
 *   | IDENTIFIER
 *   | IDENTIFIER 'IN' STRING
 *
 * BLOCK:
 *   | DECLARATION_PART? STATEMENT_PART
 *
 * DECLARATION_PART:
 *   | LABEL_DECLARATION_PART DECLARATION_PART
 *   | VARIABLE_DECLARATION_PART DECLARATION_PART
 *   | PROCEDURE_DECLARATION_PART DECLARATION_PART
 *   | FUNCTION_DECLARATION_PART DECLARATION_PART
 *
 * PROCEDURE_DECLARATION_PART:
 *   | 'PROCEDURE' IDENTIFIER ';' BLOCK ';'
 *
 * FUNCTION_DECLARATION_PART:
 *   | 'FUNCTION' IDENTIFIER ';' BLOCK ';'
 *
 * VARIABLE_DECLARATION_PART:
 *   | 'VAR' VARIABLE_DECLARATION_LIST
 *
 * VARIABLE_DECLARATION_LIST:
 *   | VARIABLE_DECLARATION VARIABLE_DECLARATION_LIST
 *   | VARIABLE_DECLARATION
 *
 * VARIABLE_DECLARATION:
 *   | IDENTIFIER ':' TYPE ('=' EXPRESSION)? ';'
 *
 * TYPE:
 *   | 'INTEGER'
 *   | 'REAL'
 *   | 'BOOLEAN'
 *   | 'CHAR'
 *
 * LABEL_DECLARATION_PART:
 *   | 'LABEL' LABEL_DECLARATION_LIST ';'
 *
 * LABEL_DECLARATION_LIST:
 *   | LABEL_DECLARATION ',' LABEL_DECLARATION_LIST
 *   | LABEL_DECLARATION
 *
 * LABEL_DECLARATION:
 *   | IDENTIFIER
 *   | UNSIGNED_INTEGER
 *
 * STATEMENT_PART:
 *   | COMPOUND_STATEMENT
 *
 * COMPOUND_STATEMENT:
 *   | 'BEGIN' STATEMENT_LIST 'END'
 *
 * STATEMENT_LIST:
 *   | STATEMENT ';' STATEMENT_LIST
 *   | STATEMENT
 *
 * STATEMENT:
 *   | LABEL ':' SIMPLE_STATEMENT
 *   | LABEL ':' STRUCTURED_STATEMENT
 *   | SIMPLE_STATEMENT
 *   | STRUCTURED_STATEMENT
 *
 * LABEL:
 *   | IDENTIFIER
 *   | UNSIGNED_INTEGER
 *
 * SIMPLE_STATEMENT:
 *   | ASSIGNMENT_STATEMENT
 *   | PROCEDURE_STATEMENT
 *
 * ASSIGNMENT_STATEMENT:
 *   | VARIABLE_REFERENCE ASSIGNMENT_OPERATOR EXPRESSION
 *
 * ASSIGNMENT_OPERATOR:
 *   | ':='
 *   | '+='
 *   | '+='
 *   | '-='
 *   | '*='
 *   | '/='
 *
 * PROCEDURE_STATEMENT:
 *   | PROCEDURE_IDENTIFIER PARAMETERS_LIST?
 *
 * STRUCTURED_STATEMENT:
 *   | COMPOUND_STATEMENT
 *   | CONDITIONAL_STATEMENT
 *   | REPETITIVE_STATEMENT
 *
 * CONDITIONAL_STATEMENT:
 *   | 'IF' EXPRESSION 'THEN' STATEMENT
 *   | 'IF' EXPRESSION 'THEN' STATEMENT 'ELSE' STATEMENT
 *
 * REPETITIVE_STATEMENT:
 *   | FOR_STATEMENT
 *   | REPEAT_STATEMENT
 *   | WHILE_STATEMENT
 *
 * FOR_STATEMENT:
 *   | 'FOR' VARIABLE_IDENTIFIER ':=' EXPRESSION 'TO' EXPRESSION 'DO' STATEMENT
 *   | 'FOR' VARIABLE_IDENTIFIER ':=' EXPRESSION 'DOWNTO' EXPRESSION 'DO' STATEMENT
 *
 * REPEAT_STATEMENT:
 *   | 'REPEAT' STATEMENT_LIST 'UNTIL' EXPRESSION
 *
 * STATEMENT_LIST:
 *   | STATEMENT ';' STATEMENT_LIST
 *   | STATEMENT
 *
 * WHILE_STATEMENT:
 *   | 'WHILE' EXPRESSION 'DO' STATEMENT
 *
 * EXPRESSION:
 *   | SIMPLE_EXPRESSION SIMPLE_EXPRESSION_OPERATOR SIMPLE_EXPRESSION
 *   | SIMPLE_EXPRESSION
 *
 * SIMPLE_EXPRESSION_OPERATOR:
 *   | '<'
 *   | '<='
 *   | '>'
 *   | '>='
 *   | '='
 *   | '<>'
 *   | 'IN'
 *
 * SIMPLE_EXPRESSION:
 *   | TERM TERM_OPERATOR SIMPLE_EXPRESSION
 *   | TERM
 *
 * TERM_OPERATOR:
 *   | '+'
 *   | '-'
 *   | 'OR'
 *   | 'XOR'
 *
 * TERM:
 *   | FACTOR TERM_OPERATOR TERM
 *   | FACTOR
 *
 * FACTOR_OPERATOR:
 *   | '*'
 *   | '/'
 *   | 'DIV'
 *   | 'MOD'
 *   | 'AND'
 *
 * FACTOR:
 *   | '(' EXPRESSION ')'
 *   | FUNCTION_CALL
 *   | VARIABLE_REFERENCE
 *   | 'NOT' FACTOR
 *   | SIGN FACTOR
 *   | UNSIGNED_CONSTANT
 *
 * VARIABLE_REFERENCE:
 *   | IDENTIFIER
 *
 * SIGN:
 *   | '+'
 *   | '-'
 *
 * UNSIGNED_CONSTANT:
 *   | UNSIGNED_INTEGER
 *   | STRING
 *
 * FUNCTION_CALL:
 *   | IDENTIFIER PARAMETER_LIST
 *
 * PARAMETER_LIST:
 *   | '(' PARAMETERS ')'
 *   | '(' ')'
 *
 * PARAMETERS:
 *   | EXPRESSION ',' PARAMETERS
 *   | EXPRESSION
 */

class PascalParser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        InnerParser(builder).parseFile()
        return builder.treeBuilt
    }

    class InnerParser(private val builder: PsiBuilder) {
        fun parseFile() {
            val mark = builder.mark()

            parseProgramHeader()
            expectAdvance(PascalTokenType.SEMICOLON, ";")

            if (builder.tokenType == PascalTokenType.USES) {
                parseUsesClause()
            }

            parseBlock()
            expectAdvance(PascalTokenType.DOT, ".")

            mark.done(PascalElementType.Pascal_FILE)
        }

        private fun parseProgramHeader() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.PROGRAM, "PROGRAM")
            parseIdentifier()
            if (builder.tokenType == PascalTokenType.LPAREN) {
                parseProgramParameters()
            }

            mark.done(PascalElementType.PROGRAM_HEADER)
        }


        private fun parseIdentifier() {
            val mark = builder.mark()
            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            mark.done(PascalElementType.IDENTIFIER)
        }


        private fun parseProgramParameters() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.LPAREN, "(")
            if (builder.tokenType == PascalTokenType.IDENTIFIER) {
                parseIdentifierList()
            }
            expectAdvance(PascalTokenType.RPAREN, ")")

            mark.done(PascalElementType.PROGRAM_PARAMETERS)
        }

        private fun parseIdentifierList() {
            parseIdentifier()
            while (builder.tokenType == PascalTokenType.COMMA) {
                expectAdvance(PascalTokenType.COMMA, ",")
                parseIdentifier()
            }
        }

        private fun parseUsesClause() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.USES, "USES")
            parseClauseList()
            expectAdvance(PascalTokenType.SEMICOLON, ";")

            mark.done(PascalElementType.USES_CLAUSE)
        }

        private fun parseClauseList() {
            parseClause()
            while (builder.tokenType == PascalTokenType.COMMA) {
                expectAdvance(PascalTokenType.COMMA, ",")
                parseClause()
            }
        }

        private fun parseClause() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            if (builder.tokenType == PascalTokenType.IN) {
                expectAdvance(PascalTokenType.IN, "IN")
                expectAdvance(PascalTokenType.STRING, "STRING")
            }

            mark.done(PascalElementType.CLAUSE)
        }

        private fun parseBlock() {
            val mark = builder.mark()

            parseDeclarationPart()
            parseStatementPart()

            mark.done(PascalElementType.BLOCK)
        }

        private fun parseDeclarationPart() {
            var flag = false
            val mark = builder.mark()

            while (builder.tokenType != PascalTokenType.BEGIN) {
                flag = true

                when (builder.tokenType) {
                    PascalTokenType.LABEL -> parseLabelDeclarationPart()
                    PascalTokenType.VAR -> parseVariableDeclarationPart()
                    PascalTokenType.FUNCTION -> parseFunctionDeclaration()
                    PascalTokenType.PROCEDURE -> parseProcedureDeclaration()
                    else -> errorAdvance("LABEL, VAR, FUNCTION, PROCEDURE")
                }
            }

            if (flag) {
                mark.done(PascalElementType.DECLARATION_PART)
            } else {
                mark.drop()
            }
        }

        private fun parseFunctionDeclaration() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.FUNCTION, "FUNCTION")
            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            expectAdvance(PascalTokenType.SEMICOLON, ";")
            parseBlock()

            mark.done(PascalElementType.FUNCTION_DECLARATION)
        }

        private fun parseProcedureDeclaration() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.PROCEDURE, "PROCEDURE")
            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            expectAdvance(PascalTokenType.SEMICOLON, ";")
            parseBlock()

            mark.done(PascalElementType.PROCEDURE_DECLARATION)
        }

        private fun parseLabelDeclarationPart() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.LABEL, "LABEL")
            parseLabelDeclarationList()

            expectAdvance(PascalTokenType.SEMICOLON, ";")

            mark.done(PascalElementType.LABEL_DECLARATION_PART)
        }

        private fun parseLabelDeclarationList() {
            parseLabel()
            while (builder.tokenType == PascalTokenType.COMMA) {
                expectAdvance(PascalTokenType.COMMA, ",")
                parseLabel()
            }
        }

        private fun parseVariableDeclarationPart() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.VAR, "VAR")
            parseVariableDeclarationList()

            mark.done(PascalElementType.VARIABLE_DECLARATION_PART)
        }

        private fun parseVariableDeclarationList() {
            parseVariableDeclaration()
            while (builder.tokenType == PascalTokenType.IDENTIFIER) {
                parseVariableDeclaration()
            }
        }

        private fun parseVariableDeclaration() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            expectAdvance(PascalTokenType.COLON, ":")
            when (builder.tokenType) {
                PascalTokenType.INTEGER -> expectAdvance(PascalTokenType.INTEGER, "INTEGER")
                PascalTokenType.REAL -> expectAdvance(PascalTokenType.REAL, "REAL")
                PascalTokenType.BOOLEAN -> expectAdvance(PascalTokenType.BOOLEAN, "BOOLEAN")
                PascalTokenType.CHAR -> expectAdvance(PascalTokenType.CHAR, "CHAR")
                else -> errorAdvance("INTEGER, REAL, BOOLEAN, CHAR")
            }
            if (builder.tokenType == PascalTokenType.EQ) {
                expectAdvance(PascalTokenType.EQ, "EQ")
                parseExpression()
            }

            expectAdvance(PascalTokenType.SEMICOLON, ";")

            mark.done(PascalElementType.VARIABLE_DECLARATION)
        }

        private fun parseExpression() {
            val mark = builder.mark()

            parseSimpleExpression()
            if (builder.tokenType in PascalTokenType.SIMPLE_EXPRESSION_OPERATORS) {
                parseSimpleExpressionOperator()
                parseSimpleExpression()
            }

            mark.done(PascalElementType.EXPRESSION)
        }

        private fun parseSimpleExpressionOperator() {
            val mark = builder.mark()

            when (builder.tokenType) {
                PascalTokenType.EQ -> {
                    expectAdvance(PascalTokenType.EQ, "=")
                }
                PascalTokenType.LQ -> {
                    expectAdvance(PascalTokenType.LQ, "<=")
                }
                PascalTokenType.GQ -> {
                    expectAdvance(PascalTokenType.GQ, ">=")
                }
                PascalTokenType.LT_GT -> {
                    expectAdvance(PascalTokenType.LT_GT, "<>")
                }
                PascalTokenType.GT -> {
                    expectAdvance(PascalTokenType.GT, ">")
                }
                PascalTokenType.LT -> {
                    expectAdvance(PascalTokenType.LT, "<")
                }
                PascalTokenType.IN -> {
                    expectAdvance(PascalTokenType.IN, "IN")
                }
                else -> errorAdvance("SIMPLE_EXPRESSION_OPERATOR")
            }

            mark.done(PascalElementType.SIMPLE_EXPRESSION_OPERATOR)
        }

        private fun parseSimpleExpression() {
            val mark = builder.mark()

            parseTerm()
            if (builder.tokenType in PascalTokenType.TERM_OPERATORS) {
                parseTermOperator()
                parseTerm()
            }

            mark.done(PascalElementType.SIMPLE_EXPRESSION)
        }

        private fun parseTermOperator() {
            val mark = builder.mark()

            when (builder.tokenType) {
                PascalTokenType.PLUS -> {
                    expectAdvance(PascalTokenType.PLUS, "+")
                }
                PascalTokenType.MINUS -> {
                    expectAdvance(PascalTokenType.MINUS, "-")
                }
                PascalTokenType.OR -> {
                    expectAdvance(PascalTokenType.OR, "OR")
                }
                PascalTokenType.XOR -> {
                    expectAdvance(PascalTokenType.XOR, "XOR")
                }
            }

            mark.done(PascalElementType.TERM_OPERATOR)
        }

        private fun parseTerm() {
            val mark = builder.mark()

            parseFactor()
            if (builder.tokenType in PascalTokenType.FACTOR_OPERATORS) {
                parseFactorOperator()
                parseFactor()
            }

            mark.done(PascalElementType.TERM)
        }

        private fun parseFactorOperator() {
            val mark = builder.mark()

            when (builder.tokenType) {
                PascalTokenType.ASTERISK -> {
                    expectAdvance(PascalTokenType.ASTERISK, "*")
                }
                PascalTokenType.DIV -> {
                    expectAdvance(PascalTokenType.DIV, "DIV")
                }
                PascalTokenType.SLASH -> {
                    expectAdvance(PascalTokenType.SLASH, "/")
                }
                PascalTokenType.MOD -> {
                    expectAdvance(PascalTokenType.MOD, "MOD")
                }
                PascalTokenType.AND -> {
                    expectAdvance(PascalTokenType.AND, "AND")
                }
            }

            mark.done(PascalElementType.FACTOR_OPERATOR)
        }

        private fun parseFactor() {
            val mark = builder.mark()

            when (builder.tokenType) {
                PascalTokenType.IDENTIFIER -> {
                    expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
                    if (builder.tokenType == PascalTokenType.LPAREN) {
                        parseParameterList()
                    }
                }
                PascalTokenType.NOT -> {
                    expectAdvance(PascalTokenType.NOT, "NOT")
                    parseFactor()
                }
                PascalTokenType.UNSIGNED_INTEGER -> {
                    expectAdvance(PascalTokenType.UNSIGNED_INTEGER, "UNSIGNED_INTEGER")
                }
                PascalTokenType.LPAREN -> {
                    expectAdvance(PascalTokenType.LPAREN, "(")
                    parseExpression()
                    expectAdvance(PascalTokenType.LPAREN, ")")
                }
                PascalTokenType.PLUS -> {
                    expectAdvance(PascalTokenType.PLUS, "+")
                    parseFactor()
                }
                PascalTokenType.MINUS -> {
                    expectAdvance(PascalTokenType.MINUS, "-")
                    parseFactor()
                }
                else -> parseUnsignedConstant()
            }

            mark.done(PascalElementType.FACTOR)
        }

        private fun parseParameterList() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.LPAREN, "(")
            while (builder.tokenType != PascalTokenType.RPAREN) {
                parseExpression()
                if (builder.tokenType == PascalTokenType.COMMA) {
                    expectAdvance(PascalTokenType.COMMA, ",")
                }
            }
            expectAdvance(PascalTokenType.RPAREN, ")")

            mark.done(PascalElementType.PARAMETER_LIST)
        }

        private fun parseUnsignedConstant() {
            val mark = builder.mark()

            when (builder.tokenType) {
                PascalTokenType.UNSIGNED_INTEGER -> expectAdvance(PascalTokenType.UNSIGNED_INTEGER, "UNSIGNED_NUMBER")
                PascalTokenType.STRING -> expectAdvance(PascalTokenType.STRING, "STRING")
                else -> errorAdvance("UNSIGNED_CONSTANT")
            }

            mark.done(PascalElementType.UNSIGNED_CONSTANT)
        }

        private fun parseStatementPart() {
            val mark = builder.mark()

            parseCompoundStatement()

            mark.done(PascalElementType.STATEMENT_PART)
        }

        private fun parseCompoundStatement() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.BEGIN, "BEGIN")
            parseStatementList()
            expectAdvance(PascalTokenType.END, "END")

            mark.done(PascalElementType.COMPOUND_STATEMENT)
        }

        private fun parseStatementList() {
            parseStatement()
            while (builder.tokenType == PascalTokenType.SEMICOLON) {
                expectAdvance(PascalTokenType.SEMICOLON, ";")
                parseStatement()
            }
        }

        private fun parseStatement() {
            val mark = builder.mark()

            if (builder.lookAhead(1) == PascalTokenType.COLON) {
                parseLabel()
                expectAdvance(PascalTokenType.COLON, ":")
            }

            if (!tryParseStructuredStatement()) {
                parseSimpleStatement()
            }

            mark.done(PascalElementType.STATEMENT)
        }

        private fun tryParseStructuredStatement(): Boolean {
            when (builder.tokenType) {
                PascalTokenType.BEGIN -> {
                    parseCompoundStatement()
                }
                PascalTokenType.IF -> {
                    parseConditionStatement()
                }
                PascalTokenType.FOR -> {
                    parseForStatement()
                }
                PascalTokenType.WHILE -> {
                    parseWhileStatement()
                }
                PascalTokenType.REPEAT -> {
                    parseRepeatStatement()
                }
                else -> {
                    return false
                }
            }

            return true
        }

        private fun parseForStatement() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.FOR, "FOR")
            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            expectAdvance(PascalTokenType.ASSIGN, ":=")
            parseExpression()

            when (builder.tokenType) {
                PascalTokenType.TO -> expectAdvance(PascalTokenType.TO, "TO")
                PascalTokenType.DOWNTO -> expectAdvance(PascalTokenType.DOWNTO, "DOWNTO")
                else -> errorAdvance("TO or DOWNTO")
            }
            parseExpression()

            expectAdvance(PascalTokenType.DO, "DO")
            parseStatement()

            mark.done(PascalElementType.FOR_STATEMENT)
        }

        private fun parseConditionStatement() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.IF, "IF")
            parseExpression()
            expectAdvance(PascalTokenType.THEN, "THEN")
            parseStatement()
            if (builder.tokenType == PascalTokenType.ELSE) {
                expectAdvance(PascalTokenType.ELSE, "ELSE")
                parseStatement()
            }

            mark.done(PascalElementType.CONDITION_STATEMENT)
        }

        private fun parseWhileStatement() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.WHILE, "WHILE")
            parseExpression()
            expectAdvance(PascalTokenType.DO, "DO")
            parseStatement()

            mark.done(PascalElementType.WHILE_STATEMENT)
        }


        private fun parseRepeatStatement() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.REPEAT, "REPEAT")
            parseStatement()
            while (builder.tokenType == PascalTokenType.SEMICOLON) {
                expectAdvance(PascalTokenType.SEMICOLON, ";")
                parseStatement()
            }

            expectAdvance(PascalTokenType.UNTIL, "UNTIL")
            parseExpression()

            mark.done(PascalElementType.REPEAT_STATEMENT)
        }

        private fun parseLabel() {
            val mark = builder.mark()

            when (builder.tokenType) {
                PascalTokenType.IDENTIFIER -> {
                    expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
                }
                PascalTokenType.UNSIGNED_INTEGER -> {
                    expectAdvance(PascalTokenType.UNSIGNED_INTEGER, "UNSIGNED_INTEGER")
                }
                else -> {
                    builder.error("IDENTIFIER or UNSIGNED_INTEGER")
                }
            }

            mark.done(PascalElementType.LABEL)
        }

        private fun parseSimpleStatement() {
            val mark = builder.mark()

            if (!tryParseAssignment()) {
                parseProcedureStatement()
            }

            mark.done(PascalElementType.SIMPLE_STATEMENT)
        }

        private fun tryParseAssignment(): Boolean {
            if (builder.lookAhead(1) in PascalTokenType.ASSIGNMENTS) {
                parseAssignment()
                return true
            } else {
                return false
            }
        }

        private fun parseAssignment() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            when (builder.tokenType) {
                PascalTokenType.ASSIGN -> {
                    expectAdvance(PascalTokenType.ASSIGN, ":=")
                }
                PascalTokenType.PLUS_ASSIGN -> {
                    expectAdvance(PascalTokenType.PLUS_ASSIGN, "+=")
                }
                PascalTokenType.MINUS_ASSIGN -> {
                    expectAdvance(PascalTokenType.MINUS_ASSIGN, "-=")
                }
                PascalTokenType.ASTERISK_ASSIGN -> {
                    expectAdvance(PascalTokenType.ASTERISK_ASSIGN, "*=")
                }
                PascalTokenType.DIVIDE_ASSIGN -> {
                    expectAdvance(PascalTokenType.DIVIDE_ASSIGN, "/=")
                }
                else -> {
                    builder.error("ASSIGNMENT_OPERATOR")
                }
            }
            parseExpression()

            mark.done(PascalElementType.ASSIGNMENT)
        }

        private fun parseProcedureStatement() {
            val mark = builder.mark()

            expectAdvance(PascalTokenType.IDENTIFIER, "IDENTIFIER")
            if (builder.tokenType == PascalTokenType.LPAREN) {
                parseParameterList()
            }

            mark.done(PascalElementType.PROCEDURE_STATEMENT)
        }

        private fun advance(): IElementType? {
            val result = builder.tokenType
            builder.advanceLexer()
			while (builder.tokenType == TokenType.BAD_CHARACTER) {
				val badMark = builder.mark()
				builder.advanceLexer()
				badMark.error("Unexpected character")
			}
            return result
        }

        private fun errorAdvance(expectedName: String) {
            val mark = builder.mark()
            advance()
            mark.error("Expected $expectedName")
        }

        private fun expectAdvance(expectedTt: PascalTokenType, expectedName: String): Boolean {
            if (builder.tokenType == expectedTt) {
                advance()
                return true
            }
            else {
                builder.error("Expected $expectedName")
                return false
            }
        }
    }
}

class PascalParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer {
        return PascalLexer()
    }

    override fun createParser(project: Project?): PsiParser {
        return PascalParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return PascalElementType.Pascal_STUB_FILE
    }

    override fun getCommentTokens(): TokenSet {
        return PascalTokenType.COMMENTS
    }

    override fun getStringLiteralElements(): TokenSet {
        return PascalTokenType.STRINGS
    }

    override fun createElement(node: ASTNode): PsiElement {
        return PascalElementType.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return PascalFile(viewProvider)
    }
}
