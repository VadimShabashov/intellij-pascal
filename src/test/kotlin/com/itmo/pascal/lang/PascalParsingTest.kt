package com.itmo.pascal.lang

import com.intellij.testFramework.ParsingTestCase

abstract class PascalParsingTestBase(baseDir: String) : ParsingTestCase(baseDir, "pascal", true, PascalParserDefinition()) {
  override fun getTestDataPath() = "src/test/data"
}

class PascalParsingTest : PascalParsingTestBase("parser") {

  fun testHello() = doTest(true)
  fun testExample1() = doTest(true)
  fun testExample2() = doTest(true)
  fun testExample3() = doTest(true)
}
