package com.itmo.pascal.ide

import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import com.itmo.pascal.lang.PascalFileType
import com.itmo.pascal.lang.PascalLanguage
import com.itmo.pascal.lang.PascalLexer
import com.itmo.pascal.lang.PascalTokenType

enum class PascalTextAttributeKeys(humanName: String, fallback: TextAttributesKey) {
    COMMENT("Comment", DefaultLanguageHighlighterColors.DOC_COMMENT),
    STRING("String text", DefaultLanguageHighlighterColors.STRING),
    INT("Decimal", DefaultLanguageHighlighterColors.NUMBER),
    HEX("Hexadecimal", DefaultLanguageHighlighterColors.NUMBER),

    DOT("Dot", DefaultLanguageHighlighterColors.DOT),
    COMMA("Comma", DefaultLanguageHighlighterColors.COMMA),
    SEMICOLON("Semicolon", DefaultLanguageHighlighterColors.SEMICOLON),
    COLON("Colon", DefaultLanguageHighlighterColors.DOT),

    PARENTHESES("Parentheses", DefaultLanguageHighlighterColors.PARENTHESES),
    BRACKETS("Brackets", DefaultLanguageHighlighterColors.BRACKETS),

    ASSIGNMENTS("Signs", DefaultLanguageHighlighterColors.DOT),
    SPECIFIC_SYMBOLS("Specific symbols", DefaultLanguageHighlighterColors.DOT),
    OPERATORS("Mathematical operations", DefaultLanguageHighlighterColors.OPERATION_SIGN),
    KEYWORD("Keyword", DefaultLanguageHighlighterColors.KEYWORD),
    TYPES("Variables types", DefaultLanguageHighlighterColors.LABEL),

    PROCEDURES("Contract Procedure", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
	
	val key = TextAttributesKey.createTextAttributesKey("Pascal.$name", fallback)
    val descriptor = AttributesDescriptor(humanName, key)
}

class PascalColorSettingsPage : ColorSettingsPage {
	override fun getDisplayName() = PascalLanguage.displayName
	override fun getIcon() = PascalFileType.icon
	override fun getAttributeDescriptors() = PascalTextAttributeKeys.values().map { it.descriptor }.toTypedArray()
	override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
	override fun getHighlighter() = PascalSyntaxHighlighter()
	
	override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> {
		return PascalTextAttributeKeys.values().associateBy({ it.name }, { it.key })
	}

	private val DEMO_TEXT = CodeStyleAbstractPanel.readFromFile(PascalLanguage::class.java, "Sample.pas")
	override fun getDemoText(): String = DEMO_TEXT
}


class PascalSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val keys1 = HashMap<IElementType, TextAttributesKey>()

        init
        {
            fillMap(keys1, PascalTokenType.KEYWORDS, PascalTextAttributeKeys.KEYWORD.key)
            fillMap(keys1, PascalTokenType.ASSIGNMENTS, PascalTextAttributeKeys.ASSIGNMENTS.key)
            fillMap(keys1, PascalTokenType.OPERATORS, PascalTextAttributeKeys.OPERATORS.key)
            fillMap(keys1, PascalTokenType.SPECIFIC_SYMBOLS, PascalTextAttributeKeys.SPECIFIC_SYMBOLS.key)
            fillMap(keys1, PascalTokenType.PROCEDURES, PascalTextAttributeKeys.PROCEDURES.key)
            fillMap(keys1, PascalTokenType.TYPES, PascalTextAttributeKeys.TYPES.key)


            keys1[PascalTokenType.INT] = PascalTextAttributeKeys.INT.key
            keys1[PascalTokenType.HEX] = PascalTextAttributeKeys.HEX.key
            keys1[PascalTokenType.STRING] = PascalTextAttributeKeys.STRING.key

            keys1[PascalTokenType.COMMENT] = PascalTextAttributeKeys.COMMENT.key

            keys1[PascalTokenType.LPAREN] = PascalTextAttributeKeys.PARENTHESES.key
            keys1[PascalTokenType.RPAREN] = PascalTextAttributeKeys.PARENTHESES.key

            keys1[PascalTokenType.LBRACKET] = PascalTextAttributeKeys.BRACKETS.key
            keys1[PascalTokenType.RBRACKET] = PascalTextAttributeKeys.BRACKETS.key

            keys1[PascalTokenType.COMMA] = PascalTextAttributeKeys.COMMA.key
            keys1[PascalTokenType.DOT] = PascalTextAttributeKeys.DOT.key
            keys1[PascalTokenType.SEMICOLON] = PascalTextAttributeKeys.SEMICOLON.key

            keys1[PascalTokenType.COLON] = PascalTextAttributeKeys.COLON.key
        }
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<out TextAttributesKey> {
        return pack(keys1[tokenType])
    }

    override fun getHighlightingLexer(): Lexer {
        return PascalLexer()
    }
}

class PascalSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
        return PascalSyntaxHighlighter()
    }
}
