package com.itmo.pascal.lang

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase


/**
 * Все переменные, функции и процедуры обязаны быть объявлены в начале файла, поэтому для референса достаточно найти их
 * объявления в DECLARATION_PART
 */
class PascalRefIdentifier(node: ASTNode) : PascalPsiElement(node) {
	private fun findFileNode() :ASTNode {
		var parent = node
		while (parent.treeParent != null) {
			parent = parent.treeParent
		}

		return parent
	}

	private fun findDeclarations() : ASTNode? {
		var tempNode = findFileNode()
		tempNode = tempNode.findChildByType(PascalElementType.BLOCK) ?: return null
		return tempNode.findChildByType(PascalElementType.DECLARATION_PART)
	}

	private fun lookInDeclaredEntities(declarations: ASTNode) : ASTNode? {
		var identifier: ASTNode? = null

		for (declaredEntity in declarations.getChildren(null)) {
			when (declaredEntity.elementType) {
				PascalElementType.VARIABLE_DECLARATION_PART ->
					identifier = lookInDeclaredVars(declaredEntity)
				PascalElementType.PROCEDURE_DECLARATION ->
					identifier = lookInDeclaredFunctionsOrProcedures(declaredEntity)
				PascalElementType.FUNCTION_DECLARATION ->
					identifier = lookInDeclaredFunctionsOrProcedures(declaredEntity)
			}

			if (identifier != null) {
				return identifier
			}
		}

		return null
	}

	private fun lookInDeclaredVars(varsDeclaration: ASTNode) : ASTNode? {
		var identifier: ASTNode?

		for (child in varsDeclaration.getChildren(null)) {
			identifier = child.findChildByType(PascalElementType.IDENTIFIER)

			if (identifier != null && identifier.text == node.text) {
				return identifier
			}
		}

		return null
	}

	private fun lookInDeclaredFunctionsOrProcedures(functionDeclaration: ASTNode) : ASTNode? {
		val identifier: ASTNode = functionDeclaration.findChildByType(PascalElementType.IDENTIFIER) ?: return null

		if (identifier.text == node.text) {
			return identifier
		} else {
			return null
		}
	}

	override fun getReference(): PsiReferenceBase<PascalRefIdentifier>? {
		// Получаем PsiElement ноды и какой-то отступ
		val ref = node.psi
		val rangeInElement = ref.textRangeInParent.shiftRight(ref.startOffsetInParent)

		// Находим в дереве BLOCK, в котором содержатся все определения
		val declarations = findDeclarations() ?: return null

		// Ищем определение в определениях
		val foundRef = lookInDeclaredEntities(declarations) ?: return null

		return object : PsiReferenceBase<PascalRefIdentifier>(this, rangeInElement) {
			override fun resolve(): PsiElement {
				return PascalNamedNode(foundRef)
			}
		}
	}
}
