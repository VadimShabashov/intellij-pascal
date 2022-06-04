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
UNSIGNED_INTEGER = {DEC_DIGIT}+

LINE_WS = [\ \t\f]+
EOL = "\r"|"\n"|"\r\n"
WHITE_SPACE=({LINE_WS}|{EOL})+

IDENTIFIER = [a-zA-Z] [a-zA-Z0-9_]*

STRING = (\'([^\'\r\\]|\\.)*\')

COMMENT = (\(\*(.|\n)*\*\)|\/\/.*\n|\{(.|\n)*\})


%%

{WHITE_SPACE}          { return WHITE_SPACE; }
{STRING}               { return PascalTokenType.Companion.getSTRING(); }
{UNSIGNED_INTEGER}     { return PascalTokenType.Companion.getUNSIGNED_INTEGER(); }

{COMMENT}              { return PascalTokenType.Companion.getCOMMENT(); }

"AND"                  { return PascalTokenType.Companion.getAND(); }
"BEGIN"                { return PascalTokenType.Companion.getBEGIN(); }
"END"                  { return PascalTokenType.Companion.getEND(); }
"DIV"                  { return PascalTokenType.Companion.getDIV(); }
"DO"                   { return PascalTokenType.Companion.getDO(); }
"DOWNTO"               { return PascalTokenType.Companion.getDOWNTO(); }
"ELSE"                 { return PascalTokenType.Companion.getELSE(); }
"FOR"                  { return PascalTokenType.Companion.getFOR(); }
"FUNCTION"             { return PascalTokenType.Companion.getFUNCTION(); }
"IF"                   { return PascalTokenType.Companion.getIF(); }
"IN"                   { return PascalTokenType.Companion.getIN(); }
"LABEL"                { return PascalTokenType.Companion.getLABEL(); }
"MOD"                  { return PascalTokenType.Companion.getMOD(); }
"NOT"                  { return PascalTokenType.Companion.getNOT(); }
"OR"                   { return PascalTokenType.Companion.getOR(); }
"PROCEDURE"            { return PascalTokenType.Companion.getPROCEDURE(); }
"PROGRAM"              { return PascalTokenType.Companion.getPROGRAM(); }
"REPEAT"               { return PascalTokenType.Companion.getREPEAT(); }
"THEN"                 { return PascalTokenType.Companion.getTHEN(); }
"TO"                   { return PascalTokenType.Companion.getTO(); }
"UNTIL"                { return PascalTokenType.Companion.getUNTIL(); }
"USES"                 { return PascalTokenType.Companion.getUSES(); }
"VAR"                  { return PascalTokenType.Companion.getVAR(); }
"WHILE"                { return PascalTokenType.Companion.getWHILE(); }
"XOR"                  { return PascalTokenType.Companion.getXOR(); }


"INTEGER"              { return PascalTokenType.Companion.getINTEGER(); }
"REAL"                 { return PascalTokenType.Companion.getREAL(); }
"BOOLEAN"              { return PascalTokenType.Companion.getBOOLEAN(); }
"CHAR"                 { return PascalTokenType.Companion.getCHAR(); }

{IDENTIFIER}           { return PascalTokenType.Companion.getIDENTIFIER(); }

"+"           { return PascalTokenType.Companion.getPLUS(); }
"-"           { return PascalTokenType.Companion.getMINUS(); }
"*"           { return PascalTokenType.Companion.getASTERISK(); }
"/"           { return PascalTokenType.Companion.getSLASH(); }
"="           { return PascalTokenType.Companion.getEQ(); }
"<"           { return PascalTokenType.Companion.getLT(); }
">"           { return PascalTokenType.Companion.getGT(); }
"."           { return PascalTokenType.Companion.getDOT(); }
","           { return PascalTokenType.Companion.getCOMMA(); }
"("           { return PascalTokenType.Companion.getLPAREN(); }
")"           { return PascalTokenType.Companion.getRPAREN(); }
":"           { return PascalTokenType.Companion.getCOLON(); }
";"           { return PascalTokenType.Companion.getSEMICOLON(); }


"<>"           { return PascalTokenType.Companion.getLT_GT(); }
"<="           { return PascalTokenType.Companion.getLQ(); }
">="           { return PascalTokenType.Companion.getGQ(); }
":="           { return PascalTokenType.Companion.getASSIGN(); }
"+="           { return PascalTokenType.Companion.getPLUS_ASSIGN(); }
"-="           { return PascalTokenType.Companion.getMINUS_ASSIGN(); }
"*="           { return PascalTokenType.Companion.getASTERISK_ASSIGN(); }
"/="           { return PascalTokenType.Companion.getDIVIDE_ASSIGN(); }


[^] { return BAD_CHARACTER; }
