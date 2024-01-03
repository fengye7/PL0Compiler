grammar grammer;

program: 'PROGRAM' identifier block ;

block: constantDeclaration? variableDeclaration? statement ;

constantDeclaration: 'CONST' constantDefinition (',' constantDefinition)* ';' ;

constantDefinition: identifier ':=' unsignedInteger ;

variableDeclaration: 'VAR' identifier (',' identifier)* ';' ;

statement: assignmentStatement
         | conditionStatement
         | loopStatement
         | compoundStatement
         | emptyStatement ;

assignmentStatement: identifier ':=' expression ;

expression: ('+' | '-')? term
          | expression '+' term
          | expression '-' term ;

term: factor
    | term '*' factor
    | term '/' factor ;

factor: identifier
      | unsignedInteger
      | '(' expression ')' ;

conditionStatement: 'IF' condition 'THEN' statement ;

condition: expression relationalOperator expression ;

relationalOperator: '='
                  | '<>'
                  | '<'
                  | '<='
                  | '>'
                  | '>=' ;

loopStatement: 'WHILE' condition 'DO' statement ;

compoundStatement: 'BEGIN' statement ( ';' statement )* 'END' ;

emptyStatement: ;

identifier: LETTER ( LETTER | DIGIT )* ;
unsignedInteger: DIGIT+ ;

LETTER: [a-zA-Z] ;
DIGIT: [0-9] ;

WS: [ \t\r\n]+ -> skip ;