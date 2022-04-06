package com.itmo.pascal.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.itmo.pascal.lang.PascalTokenType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
%%
%public
%class _PascalLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode


DEC_DIGIT = [0-9]
DEC_INTEGER = "-"? {DEC_DIGIT}+
HEX_DIGIT = [a-fA-F0-9]
HEX_INTEGER = "0x" ({HEX_DIGIT})*

LINE_WS = [\ \t\f]+
EOL = "\r"|"\n"|"\r\n"
WHITE_SPACE=({LINE_WS}|{EOL})+

STRING = (\'([^\'\r\\]|\\.)*\')

COMMENT = (\(\*(.|\n)*\*\)|\/\/.*\n|\{(.|\n)*\})

END = ("END"[;.])


%%

{WHITE_SPACE}    { return WHITE_SPACE; }
{STRING}         { return PascalTokenType.Companion.getSTRING(); }
{HEX_INTEGER}    { return PascalTokenType.Companion.getHEX(); }
{DEC_INTEGER}    { return PascalTokenType.Companion.getINT(); }

{END}            { return PascalTokenType.Companion.getEND(); }

{COMMENT}        { return PascalTokenType.Companion.getCOMMENT(); }

// Pascal tokens: http://cc.etsii.ull.es/ftp/antiguo/MTDPRG1/freepascal/manual/ref/node4.html

"ABSOLUTE"             { return PascalTokenType.Companion.getABSOLUTE(); }
"AND"                  { return PascalTokenType.Companion.getAND(); }
"ASM"                  { return PascalTokenType.Companion.getASM(); }
"BEGIN"                { return PascalTokenType.Companion.getBEGIN(); }
"BREAK"                { return PascalTokenType.Companion.getBREAK(); }
"CASE"                 { return PascalTokenType.Companion.getCASE(); }
"CONST"                { return PascalTokenType.Companion.getCONST(); }
"CONSTRUCTOR"          { return PascalTokenType.Companion.getCONSTRUCTOR(); }
"CONTINUE"             { return PascalTokenType.Companion.getCONTINUE(); }
"DESTRUCTOR"           { return PascalTokenType.Companion.getDESTRUCTOR(); }
"DIV"                  { return PascalTokenType.Companion.getDIV(); }
"DO"                   { return PascalTokenType.Companion.getDO(); }
"DOWNTO"               { return PascalTokenType.Companion.getDOWNTO(); }
"ELSE"                 { return PascalTokenType.Companion.getELSE(); }
"FILE"                 { return PascalTokenType.Companion.getFILE(); }
"FOR"                  { return PascalTokenType.Companion.getFOR(); }
"FUNCTION"             { return PascalTokenType.Companion.getFUNCTION(); }
"GOTO"                 { return PascalTokenType.Companion.getGOTO(); }
"IF"                   { return PascalTokenType.Companion.getIF(); }
"IMPLEMENTATION"       { return PascalTokenType.Companion.getIMPLEMENTATION(); }
"IN"                   { return PascalTokenType.Companion.getIN(); }
"INHERITED"            { return PascalTokenType.Companion.getINHERITED(); }
"INLINE"               { return PascalTokenType.Companion.getINLINE(); }
"INTERFACE"            { return PascalTokenType.Companion.getINTERFACE(); }
"LABEL"                { return PascalTokenType.Companion.getLABEL(); }
"MOD"                  { return PascalTokenType.Companion.getMOD(); }
"NIL"                  { return PascalTokenType.Companion.getNIL(); }
"NOT"                  { return PascalTokenType.Companion.getNOT(); }
"OBJECT"               { return PascalTokenType.Companion.getOBJECT(); }
"OF"                   { return PascalTokenType.Companion.getOF(); }
"ON"                   { return PascalTokenType.Companion.getON(); }
"OPERATOR"             { return PascalTokenType.Companion.getOPERATOR(); }
"OR"                   { return PascalTokenType.Companion.getOR(); }
"PACKED"               { return PascalTokenType.Companion.getPACKED(); }
"PROCEDURE"            { return PascalTokenType.Companion.getPROCEDURE(); }
"PROGRAM"              { return PascalTokenType.Companion.getPROGRAM(); }
"RECORD"               { return PascalTokenType.Companion.getRECORD(); }
"REPEAT"               { return PascalTokenType.Companion.getREPEAT(); }
"SELF"                 { return PascalTokenType.Companion.getSELF(); }
"SET"                  { return PascalTokenType.Companion.getSET(); }
"SHL"                  { return PascalTokenType.Companion.getSHL(); }
"SHR"                  { return PascalTokenType.Companion.getSHR(); }
"THEN"                 { return PascalTokenType.Companion.getTHEN(); }
"TO"                   { return PascalTokenType.Companion.getTO(); }
"TYPE"                 { return PascalTokenType.Companion.getTYPE(); }
"UNIT"                 { return PascalTokenType.Companion.getUNIT(); }
"UNTIL"                { return PascalTokenType.Companion.getUNTIL(); }
"USES"                 { return PascalTokenType.Companion.getUSES(); }
"VAR"                  { return PascalTokenType.Companion.getVAR(); }
"WHILE"                { return PascalTokenType.Companion.getWHILE(); }
"WITH"                 { return PascalTokenType.Companion.getWITH(); }
"XOR"                  { return PascalTokenType.Companion.getXOR(); }


"INTEGER"              { return PascalTokenType.Companion.getINTEGER(); }
"REAL"                 { return PascalTokenType.Companion.getREAL(); }
"BOOLEAN"              { return PascalTokenType.Companion.getBOOLEAN(); }
"CHAR"                 { return PascalTokenType.Companion.getCHAR(); }
"ARRAY"                { return PascalTokenType.Companion.getARRAY(); }



"'"           { return PascalTokenType.Companion.getAPOSTROPHE(); }
"+"           { return PascalTokenType.Companion.getPLUS(); }
"-"           { return PascalTokenType.Companion.getMINUS(); }
"*"           { return PascalTokenType.Companion.getASTERISK(); }
"/"           { return PascalTokenType.Companion.getSLASH(); }
"="           { return PascalTokenType.Companion.getEQ(); }
"<"           { return PascalTokenType.Companion.getLT(); }
">"           { return PascalTokenType.Companion.getGT(); }
"["           { return PascalTokenType.Companion.getLBRACKET(); }
"]"           { return PascalTokenType.Companion.getRBRACKET(); }
"."           { return PascalTokenType.Companion.getDOT(); }
","           { return PascalTokenType.Companion.getCOMMA(); }
"("           { return PascalTokenType.Companion.getLPAREN(); }
")"           { return PascalTokenType.Companion.getRPAREN(); }
":"           { return PascalTokenType.Companion.getCOLON(); }
"^"           { return PascalTokenType.Companion.getCARET(); }
"@"           { return PascalTokenType.Companion.getAT(); }
"$"           { return PascalTokenType.Companion.getDOLLAR(); }
"#"           { return PascalTokenType.Companion.getHASH(); }
"&"           { return PascalTokenType.Companion.getAMP(); }
"%"           { return PascalTokenType.Companion.getPERCENT(); }
";"           { return PascalTokenType.Companion.getSEMICOLON(); }


"<<"           { return PascalTokenType.Companion.getDOUBLE_LT(); }
">>"           { return PascalTokenType.Companion.getDOUBLE_GT(); }
"**"           { return PascalTokenType.Companion.getDOUBLE_ASTERISK(); }
"<>"           { return PascalTokenType.Companion.getLT_GT(); }
"><"           { return PascalTokenType.Companion.getGT_LT(); }
"<="           { return PascalTokenType.Companion.getLQ(); }
">="           { return PascalTokenType.Companion.getGQ(); }
":="           { return PascalTokenType.Companion.getASSIGN(); }
"+="           { return PascalTokenType.Companion.getPLUS_ASSIGN(); }
"-="           { return PascalTokenType.Companion.getMINUS_ASSIGN(); }
"*="           { return PascalTokenType.Companion.getASTERISK_ASSIGN(); }
"/="           { return PascalTokenType.Companion.getDIVIDE_ASSIGN(); }
"(."           { return PascalTokenType.Companion.getLPAREN_DOT(); }
".)"           { return PascalTokenType.Companion.getRPAREN_DOT(); }


[^] { return BAD_CHARACTER; }
