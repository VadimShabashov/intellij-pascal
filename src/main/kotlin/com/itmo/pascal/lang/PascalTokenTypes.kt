package com.itmo.pascal.lang

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NonNls


class PascalTokenType(@NonNls debugName: String) : IElementType(debugName, PascalLanguage) {
    override fun toString(): String {
        return "PascalTokenType." + super.toString()
    }

    companion object {
        val NEW_LINE = PascalTokenType("NEW_LINE")
        val COMMENT = PascalTokenType("COMMENT")
        val STRING = PascalTokenType("STRING")
        val INT = PascalTokenType("INT")
        val HEX = PascalTokenType("HEX")

        val ABSOLUTE = PascalTokenType("ABSOLUTE")
        val AND = PascalTokenType("AND")
        val ASM = PascalTokenType("ASM")
        val BEGIN = PascalTokenType("BEGIN")
        val BREAK = PascalTokenType("BREAK")
        val CASE = PascalTokenType("CASE")
        val CONST = PascalTokenType("CONST")
        val CONSTRUCTOR = PascalTokenType("CONSTRUCTOR")
        val CONTINUE = PascalTokenType("CONTINUE")
        val DESTRUCTOR = PascalTokenType("DESTRUCTOR")
        val DIV= PascalTokenType("DIV")
        val DO = PascalTokenType("DO")
        val DOWNTO = PascalTokenType("DOWNTO")
        val ELSE = PascalTokenType("ELSE")
        val END = PascalTokenType("END")
        val FILE = PascalTokenType("FILE")
        val FOR = PascalTokenType("FOR")
        val FUNCTION = PascalTokenType("FUNCTION")
        val GOTO = PascalTokenType("GOTO")
        val IF = PascalTokenType("IF")
        val IMPLEMENTATION = PascalTokenType("IMPLEMENTATION")
        val IN = PascalTokenType("IN")
        val INHERITED = PascalTokenType("INHERITED")
        val INLINE = PascalTokenType("INLINE")
        val INTERFACE = PascalTokenType("INTERFACE")
        val LABEL = PascalTokenType("LABEL")
        val MOD = PascalTokenType("MOD")
        val NIL = PascalTokenType("NIL")
        val NOT = PascalTokenType("NOT")
        val OBJECT = PascalTokenType("OBJECT")
        val OF = PascalTokenType("OF")
        val ON = PascalTokenType("ON")
        val OPERATOR = PascalTokenType("OPERATOR")
        val OR = PascalTokenType("OR")
        val PACKED = PascalTokenType("PACKED")
        val PROCEDURE = PascalTokenType("PROCEDURE")
        val PROGRAM = PascalTokenType("PROGRAM")
        val RECORD = PascalTokenType("RECORD")
        val REPEAT = PascalTokenType("REPEAT")
        val SELF = PascalTokenType("SELF")
        val SET = PascalTokenType("SET")
        val SHL = PascalTokenType("SHL")
        val SHR = PascalTokenType("SHR")
        val THEN = PascalTokenType("THEN")
        val TO = PascalTokenType("TO")
        val TYPE = PascalTokenType("TYPE")
        val UNIT = PascalTokenType("UNIT")
        val UNTIL = PascalTokenType("UNTIL")
        val USES = PascalTokenType("USES")
        val VAR = PascalTokenType("VAR")
        val WHILE = PascalTokenType("WHILE")
        val WITH = PascalTokenType("WITH")
        val XOR = PascalTokenType("XOR")


        val INTEGER = PascalTokenType("INTEGER")
        val REAL = PascalTokenType("REAL")
        val BOOLEAN = PascalTokenType("BOOLEAN")
        val CHAR = PascalTokenType("CHAR")
        val ARRAY = PascalTokenType("ARRAY")


        val APOSTROPHE = PascalTokenType("APOSTROPHE")
        val PLUS = PascalTokenType("PLUS")
        val MINUS = PascalTokenType("MINUS")
        val ASTERISK = PascalTokenType("ASTERISK")
        val SLASH = PascalTokenType("SLASH")
        val EQ = PascalTokenType("EQ")
        val LT = PascalTokenType("LT")
        val GT = PascalTokenType("GT")
        val LBRACKET = PascalTokenType("LBRACKET")
        val RBRACKET = PascalTokenType("RBRACKET")
        val DOT = PascalTokenType("DOT")
        val COMMA = PascalTokenType("COMMA")
        val LPAREN = PascalTokenType("LPAREN")
        val RPAREN = PascalTokenType("RPAREN")
        val COLON = PascalTokenType("COLON")
        val CARET = PascalTokenType("CARET")
        val AT = PascalTokenType("AT")
        val DOLLAR = PascalTokenType("DOLLAR")
        val HASH = PascalTokenType("HASH")
        val AMP = PascalTokenType("AMP")
        val PERCENT = PascalTokenType("PERCENT")
        val SEMICOLON = PascalTokenType("SEMICOLON")


        val DOUBLE_LT = PascalTokenType("DOUBLE_LT")
        val DOUBLE_GT = PascalTokenType("DOUBLE_GT")
        val DOUBLE_ASTERISK = PascalTokenType("DOUBLE_ASTERISK")
        val LT_GT = PascalTokenType("LT_GT")
        val GT_LT = PascalTokenType("GT_LT")
        val LQ = PascalTokenType("LQ")
        val GQ = PascalTokenType("GQ")
        val ASSIGN = PascalTokenType("ASSIGN")
        val PLUS_ASSIGN = PascalTokenType("PLUS_ASSIGN")
        val MINUS_ASSIGN = PascalTokenType("MINUS_ASSIGN")
        val ASTERISK_ASSIGN = PascalTokenType("ASTERISK_ASSIGN")
        val DIVIDE_ASSIGN = PascalTokenType("DIVIDE_ASSIGN")
        val LPAREN_DOT = PascalTokenType("LPAREN_DOT")
        val RPAREN_DOT = PascalTokenType("RPAREN_DOT")

        val KEYWORDS = TokenSet.create(ABSOLUTE, AND, ASM, BREAK, CASE, CONST, CONSTRUCTOR, CONTINUE,
            DESTRUCTOR, DIV, DO, DOWNTO, ELSE, FILE, FOR, FUNCTION, GOTO, IF, IMPLEMENTATION,
            IN, INHERITED, LABEL, MOD, NIL, NOT, OBJECT, OF, ON, OPERATOR, OR, PACKED,
            PROGRAM, RECORD, REPEAT, SELF, SET, SHL, SHL, THEN, TO, TYPE, UNIT,
            UNTIL, USES, VAR, WHILE, WITH, XOR)
        var OPERATORS = TokenSet.create(PLUS, MINUS, ASTERISK, SLASH, CARET)
        var SPECIFIC_SYMBOLS = TokenSet.create(APOSTROPHE, AT, DOLLAR, HASH, AMP, PERCENT, LPAREN_DOT, RPAREN_DOT)
        val ASSIGNMENTS = TokenSet.create(EQ, ASSIGN, LQ, GQ, LT_GT, GT_LT, GT, LT, PLUS_ASSIGN,
            MINUS_ASSIGN, ASTERISK_ASSIGN, DIVIDE_ASSIGN)
        val PROCEDURES = TokenSet.create(PROCEDURE, BEGIN, END)
        val TYPES = TokenSet.create(INTEGER, REAL, BOOLEAN, CHAR, ARRAY)
    }

}

