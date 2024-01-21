lexer grammar MiniJavaLexer;

CLASS: 'class';
PUBLIC: 'public';
STATIC: 'static';
VOID: 'void';
MAIN: 'main';
STRING: 'String';
EXTENDS: 'extends';
RETURN: 'return';
INT: 'int';
BOOLEAN: 'boolean';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
TRUE: 'true';
FALSE: 'false';
THIS: 'this';
NEW: 'new';

ID: [a-zA-Z_][a-zA-Z0-9_]*;
INTEGER_LITERAL: [0-9]+;
BIN_OP: '+' | '&&' | '<' | '-' | '*';
EQ: '=';

LBRACE: '{';
RBRACE: '}';
LBRACKET: '[';
RBRACKET: ']';
LPAREN: '(';
RPAREN: ')';
SEMI: ';';
COMMA: ',';
DOT: '.';
BANG: '!';

COMMENT: (('//' .*? '\n') | ('/*' .*? '*/')) -> skip;
WHITESPACE: (' ' | '\t' | '\n')+ -> skip;
