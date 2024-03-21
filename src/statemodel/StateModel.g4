grammar StateModel;

// first parser rules
// states block
program: 'states' '{' states '}' 'transitions' '{' transitions '}';


states : state+; // will be more states after the initial state
state: stateType? stateName labels; // stateName assigned labels
stateType: 'initial' | 'error'; // explicitly indicatiing initial and error type
stateName: CapitalLetter Statenumbers; // contain capital letter and numbers
labels: '{' label* '}'; // any number of labels even if it's none
label: labelName '{' labelContent '}';
labelName: LowercaseLetter Labelnumbers; //indicating labelName starts with LowercaseLetter
labelContent: AnyCharacters; //here something extra


// transitions block
transitions: transitionPattern+ transition+; // specify transitions composed by a pattern and a transition flow of states
transitionPattern: 'trans' transitionType transitionName;
transitionType: 'normal' | 'error';
transitionName: LowercaseLetter; //specify that the transition initiates with lowercase letter
transition: transitionPattern ';' stateName '->' stateName;


// Lexer rules
CapitalLetter: [A-Z];
LowercaseLetter: [a-z];
Statenumbers: (StateNameNumber)*;
Labelnumbers: (LabelNameNumber)*;
AnyCharacters: .;


fragment StateNameNumber : [0-9];
fragment LabelNameNumber : [0-9];


// skip white space and some characters
WS: (' '| '\t' | '\n' | '\r') -> skip;
LPAREN:'(';
RPAREN:')';
LCURLY:'{';
RCURLY:'}';
EOS:';';
COMMA:',';

EQ:'==';
NEQ:'!=';
NEG:'!';
LT:'<';
GT:'>';
LTE:'<=';
GTE:'>=';

ASSIGN:'=';
PLUS:'+';
MINUS:'-';
MUL:'*';
DIV:'/';

IF:'if';
ELSE:'else';
WHILE:'while';
VAR:'var';
MAIN:'main';
RETURN:'return';
VOID:'void';

NULL:'null';
TRUE:'true';
FALSE:'false';
STRING:'"' (~[\r\n"])* '"';
INT: [0-9]+;

ID: [a-zA-Z][a-zA-Z0-9_]*;
COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

