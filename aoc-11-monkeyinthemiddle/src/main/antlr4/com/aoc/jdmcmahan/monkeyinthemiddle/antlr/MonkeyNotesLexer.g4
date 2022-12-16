lexer grammar MonkeyNotesLexer;

MONKEY_LABEL         : 'Monkey';
STARTING_ITEMS_LABEL : 'Starting items';
OPERATION_LABEL      : 'Operation';
TEST_LABEL           : 'Test';
TEST_TRUE_LABEL      : 'If true';
TEST_FALSE_LABEL     : 'If false';

INTEGER_LITERAL      : Digits;

DIVISIBLE_BY_TEST    : 'divisible by';

THROW_ACTION         : 'throw to monkey';

COLON                : ':';
COMMA                : ',';

ASSIGN               : '=';
PLUS                 : '+';
MINUS                : '-';
MUL                  : '*';
DIV                  : '/';

NEW                  : 'new';
OLD                  : 'old';

WS                   : [ \t\r\n]+ -> channel(HIDDEN);

UNMATCHED            : .+?;

fragment Digits
    : [0-9]+
    ;
