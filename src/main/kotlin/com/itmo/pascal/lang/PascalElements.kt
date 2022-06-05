package com.itmo.pascal.lang

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.lang.ASTNode
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.*


interface PascalNavigatableElement : NavigatablePsiElement {
}


class PascalFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, PascalLanguage), PascalNavigatableElement {
	override fun getFileType(): FileType = PascalFileType
}

abstract class PascalPsiElement(node: ASTNode) : ASTWrapperPsiElement(node) {
	override fun getContainingFile(): PascalFile? = super.getContainingFile() as? PascalFile
}

// Простая нода без имени
class PascalSimpleNode(node: ASTNode) : PascalPsiElement(node) {}

// Ниже будет именная нода для референсов
interface PascalNamedElement : PsiElement

abstract class PascalNamedPsiElement(node: ASTNode) : PascalPsiElement(node), PascalNamedElement {
	override fun getName(): String = node.text
}

class PascalNamedNode(node: ASTNode) : PascalNamedPsiElement(node) {}
