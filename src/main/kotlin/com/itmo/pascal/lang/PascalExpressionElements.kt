package com.itmo.pascal.lang

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase


class PascalRefIdentifier(node: ASTNode) : PascalPsiElement(node) {
	private fun findDeclarations() : ASTNode? {
		var parent = node.treeParent
		while (parent != null) {
			if (parent.elementType == PascalElementType.BLOCK) {
				break
			} else {
				parent = parent.treeParent
			}
		}

		if (parent != null) {
			return parent.findChildByType(PascalElementType.DECLARATION_PART)
		} else {
			return null
		}
	}

	private fun lookInDeclaredEntities(declarations: ASTNode) : ASTNode? {
		var identifier: ASTNode?

		for (declaredEntity in declarations.getChildren(null)) {
			for (child in declaredEntity.getChildren(null)) {
				identifier = child.findChildByType(PascalElementType.IDENTIFIER)
				if (identifier != null && identifier.text == node.text) {
					return identifier
				}
			}
		}

		return null
	}

	override fun getReference(): PsiReferenceBase<PascalRefIdentifier>? {
		// Получаем PsiElement ноды и какой-то отступ
		val ref = node.psi
		val rangeInElement = ref.textRangeInParent.shiftRight(ref.startOffsetInParent)

		// Идем по дереву до BLOCK, в котором считаем, что содержатся все определения
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
