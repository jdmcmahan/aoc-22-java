parser grammar MonkeyNotesParser;

options { tokenVocab=MonkeyNotesLexer; }

notes
   : monkeyNotes+
   ;

monkeyNotes
   : monkeyHeader monkeyStartingItems monkeyWorryOperation monkeyActionTest
   ;

monkeyHeader
   : MONKEY_LABEL id=monkeyIdentifier COLON
   ;

monkeyStartingItems
   : STARTING_ITEMS_LABEL COLON items=intList
   ;

monkeyWorryOperation
   : OPERATION_LABEL COLON NEW ASSIGN left=operand op=(PLUS | MINUS | MUL | DIV) right=operand
   ;

monkeyActionTest
   : TEST_LABEL COLON logic=monkeyActionTestLogic trueAction=monkeyActionIfTrue falseAction=monkeyActionIfFalse
   ;

monkeyActionTestLogic
   : DIVISIBLE_BY_TEST right=operand             # DivisibleByTest
   ;

monkeyActionIfTrue
   : TEST_TRUE_LABEL COLON action=monkeyAction
   ;

monkeyActionIfFalse
   : TEST_FALSE_LABEL COLON action=monkeyAction
   ;

monkeyAction
   : THROW_ACTION target=monkeyIdentifier        # ThrowAction
   ;

operand
   : OLD
   | literal
   ;

literal
   : intLiteral
   ;

intLiteral
   : INTEGER_LITERAL                             # IntegerLiteral
   ;

intList
   : intLiteral (COMMA intLiteral)*
   ;

monkeyIdentifier
   : literal
   ;
