package com.itmo.pascal.lang

import com.intellij.lexer.FlexAdapter
import com.itmo.pascal.lexer._PascalLexer


class PascalLexer : FlexAdapter(_PascalLexer(null))