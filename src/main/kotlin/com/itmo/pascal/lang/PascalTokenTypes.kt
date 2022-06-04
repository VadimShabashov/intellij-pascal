package com.itmo.pascal.lang

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NonNls


class PascalTokenType(@NonNls debugName: String) : IElementType(debugName, PascalLanguage) {
    override fun toString(): String {
        return "PascalTokenType." + super.toString()
    }

    companion object {
        val STRING = PascalTokenType("STRING")
        val UNSIGNED_INTEGER = PascalTokenType("UNSIGNED_INTEGER")

        val COMMENT = PascalTokenType("COMMENT")

        val AND = PascalTokenType("AND")
        val BEGIN = PascalTokenType("BEGIN")
        val END = PascalTokenType("END")
        val DIV= PascalTokenType("DIV")
        val DO = PascalTokenType("DO")
        val DOWNTO = PascalTokenType("DOWNTO")
        val ELSE = PascalTokenType("ELSE")
        val FOR = PascalTokenType("FOR")
        val FUNCTION = PascalTokenType("FUNCTION")
        val IF = PascalTokenType("IF")
        val IN = PascalTokenType("IN")
        val LABEL = PascalTokenType("LABEL")
        val MOD = PascalTokenType("MOD")
        val NOT = PascalTokenType("NOT")
        val OR = PascalTokenType("OR")
        val PROCEDURE = PascalTokenType("PROCEDURE")
        val PROGRAM = PascalTokenType("PROGRAM")
        val REPEAT = PascalTokenType("REPEAT")
        val THEN = PascalTokenType("THEN")
        val TO = PascalTokenType("TO")
        val UNTIL = PascalTokenType("UNTIL")
        val USES = PascalTokenType("USES")
        val VAR = PascalTokenType("VAR")
        val WHILE = PascalTokenType("WHILE")
        val XOR = PascalTokenType("XOR")


        val INTEGER = PascalTokenType("INTEGER")
        val REAL = PascalTokenType("REAL")
        val BOOLEAN = PascalTokenType("BOOLEAN")
        val CHAR = PascalTokenType("CHAR")

        val IDENTIFIER = PascalTokenType("IDENTIFIER")

        val PLUS = PascalTokenType("PLUS")
        val MINUS = PascalTokenType("MINUS")
        val ASTERISK = PascalTokenType("ASTERISK")
        val SLASH = PascalTokenType("SLASH")
        val EQ = PascalTokenType("EQ")
        val LT = PascalTokenType("LT")
        val GT = PascalTokenType("GT")
        val DOT = PascalTokenType("DOT")
        val COMMA = PascalTokenType("COMMA")
        val LPAREN = PascalTokenType("LPAREN")
        val RPAREN = PascalTokenType("RPAREN")
        val COLON = PascalTokenType("COLON")
        val SEMICOLON = PascalTokenType("SEMICOLON")


        val LT_GT = PascalTokenType("LT_GT")
        val LQ = PascalTokenType("LQ")
        val GQ = PascalTokenType("GQ")
        val ASSIGN = PascalTokenType("ASSIGN")
        val PLUS_ASSIGN = PascalTokenType("PLUS_ASSIGN")
        val MINUS_ASSIGN = PascalTokenType("MINUS_ASSIGN")
        val ASTERISK_ASSIGN = PascalTokenType("ASTERISK_ASSIGN")
        val DIVIDE_ASSIGN = PascalTokenType("DIVIDE_ASSIGN")

        val KEYWORDS = TokenSet.create(DO, DOWNTO, ELSE, FOR, IF, IN, LABEL,
            PROGRAM, REPEAT, THEN, TO, UNTIL, USES, VAR, WHILE)
        val PROCEDURES = TokenSet.create(PROCEDURE, FUNCTION, BEGIN, END)


        val TYPES = TokenSet.create(INTEGER, REAL, BOOLEAN, CHAR)

        val COMMENTS = TokenSet.create(COMMENT)
        val STRINGS = TokenSet.create(STRING)

        val SIMPLE_EXPRESSION_OPERATORS = TokenSet.create(EQ, LQ, GQ, LT_GT, GT, LT)
        val TERM_OPERATORS = TokenSet.create(PLUS, MINUS, OR, XOR)
        val FACTOR_OPERATORS = TokenSet.create(ASTERISK, SLASH, DIV, MOD, AND)
        val ASSIGNMENTS = TokenSet.create(ASSIGN, PLUS_ASSIGN, MINUS_ASSIGN, ASTERISK_ASSIGN, DIVIDE_ASSIGN)
    }
}

