package com.itmo.pascal.lang

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.PsiFileStub
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.IStubFileElementType
import org.jetbrains.annotations.NonNls


class PascalElementType(@NonNls debugName: String) : IElementType(debugName, PascalLanguage) {
    companion object {
        val Pascal_FILE = IFileElementType(PascalLanguage)
        val Pascal_STUB_FILE = IStubFileElementType<PsiFileStub<PascalFile>>(PascalLanguage)

        val PROGRAM_HEADER = PascalElementType("PROGRAM_HEADER")
        val USES_CLAUSE = PascalElementType("USES_CLAUSE")
        val PROGRAM_PARAMETERS = PascalElementType("PROGRAM_PARAMETERS")
        val IDENTIFIER = PascalElementType("IDENTIFIER")
        val CLAUSE = PascalElementType("CLAUSE")

        val BLOCK = PascalElementType("BLOCK")
        val DECLARATION_PART = PascalElementType("DECLARATION_PART")
        val STATEMENT_PART = PascalElementType("STATEMENT_PART")
        val COMPOUND_STATEMENT = PascalElementType("COMPOUND_STATEMENT")

        val LABEL_DECLARATION_PART = PascalElementType("LABEL_DECLARATION_PART")
        val VARIABLE_DECLARATION_PART = PascalElementType("VARIABLE_DECLARATION_PART")
        val VARIABLE_DECLARATION = PascalElementType("VARIABLE_DECLARATION")
        val STATEMENT = PascalElementType("STATEMENT")

        val EXPRESSION = PascalElementType("EXPRESSION")
        val SIMPLE_EXPRESSION = PascalElementType("SIMPLE_EXPRESSION")
        val SIMPLE_EXPRESSION_OPERATOR = PascalElementType("SIMPLE_EXPRESSION_OPERATOR")

        val TERM = PascalElementType("TERM")
        val TERM_OPERATOR = PascalElementType("TERM_OPERATOR")

        val FACTOR = PascalElementType("FACTOR")
        val FACTOR_OPERATOR = PascalElementType("FACTOR_OPERATOR")

        val LABEL = PascalElementType("LABEL")

        val PARAMETER_LIST = PascalElementType("PARAMETER_LIST")
        val UNSIGNED_CONSTANT = PascalElementType("UNSIGNED_CONSTANT")

        val SIMPLE_STATEMENT = PascalElementType("SIMPLE_STATEMENT")
        val ASSIGNMENT = PascalElementType("ASSIGNMENT")
        val PROCEDURE_STATEMENT = PascalElementType("PROCEDURE_STATEMENT")
        val FOR_STATEMENT = PascalElementType("FOR_STATEMENT")
        val WHILE_STATEMENT = PascalElementType("WHILE_STATEMENT")
        val REPEAT_STATEMENT = PascalElementType("REPEAT_STATEMENT")
        val CONDITION_STATEMENT = PascalElementType("CONDITION_STATEMENT")

        val FUNCTION_DECLARATION = PascalElementType("FUNCTION_DECLARATION")
        val PROCEDURE_DECLARATION = PascalElementType("PROCEDURE_DECLARATION")


        fun createElement(node: ASTNode): PsiElement {
            return when (node.elementType) {
                PROGRAM_HEADER -> PascalSimpleNode(node)
                USES_CLAUSE -> PascalSimpleNode(node)
                PROGRAM_PARAMETERS -> PascalSimpleNode(node)
                IDENTIFIER -> PascalRefIdentifier(node)
                CLAUSE -> PascalSimpleNode(node)

                BLOCK -> PascalSimpleNode(node)
                DECLARATION_PART -> PascalSimpleNode(node)
                STATEMENT_PART -> PascalSimpleNode(node)
                COMPOUND_STATEMENT -> PascalSimpleNode(node)

                LABEL_DECLARATION_PART -> PascalSimpleNode(node)
                VARIABLE_DECLARATION_PART -> PascalSimpleNode(node)
                VARIABLE_DECLARATION -> PascalSimpleNode(node)
                STATEMENT -> PascalSimpleNode(node)

                EXPRESSION -> PascalSimpleNode(node)
                SIMPLE_EXPRESSION -> PascalSimpleNode(node)
                SIMPLE_EXPRESSION_OPERATOR -> PascalSimpleNode(node)

                TERM -> PascalSimpleNode(node)
                TERM_OPERATOR -> PascalSimpleNode(node)

                FACTOR -> PascalSimpleNode(node)
                FACTOR_OPERATOR -> PascalSimpleNode(node)

                LABEL -> PascalSimpleNode(node)

                PARAMETER_LIST -> PascalSimpleNode(node)
                UNSIGNED_CONSTANT -> PascalSimpleNode(node)

                SIMPLE_STATEMENT -> PascalSimpleNode(node)
                ASSIGNMENT -> PascalSimpleNode(node)
                PROCEDURE_STATEMENT -> PascalSimpleNode(node)
                FOR_STATEMENT -> PascalSimpleNode(node)
                WHILE_STATEMENT -> PascalSimpleNode(node)
                REPEAT_STATEMENT -> PascalSimpleNode(node)
                CONDITION_STATEMENT -> PascalSimpleNode(node)

                FUNCTION_DECLARATION -> PascalSimpleNode(node)
                PROCEDURE_DECLARATION -> PascalSimpleNode(node)


                else -> throw IllegalArgumentException("Unknown elementType: " + node.elementType)
            }
        }
    }
}
