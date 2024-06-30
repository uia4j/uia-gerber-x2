grammar GerberX2;

options {
	language=Java;
}

@header {
}

@members {
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
 
gerberX2    :   (statement)* M02 EOB EOF
            ; 
 
statement   :	cmd EOB 
			|	PARAM extCmd EOB PARAM
            ;

cmd  		:  	g04
			|	g75
			|	g01
			|	g02
			|	g03
			| 	d01
			| 	d02
			| 	d03
			| 	dnn 
			| 	g36
            ;

extCmd      :	mo 
			|	fs
			|	ad
			| 	lp
			| 	lm 
			| 	lr 
			| 	ls
			|	tf
			|	ta
			|	to
			|	td
			|	sr
			|	ab
            ;

mo          :   MO unit=('MM' | 'IN'); 									// Mode, MM: millimeter, IN: inch
fs          :   FS 'LA' 'X' x=DECIMAL 'Y' y=DECIMAL;					//
lp          :   LP polarity=('C' | 'D');
lm          :   LM mirror=('N' | 'X' | 'Y' | 'XY');
lr          :   LR degree=DECIMAL;
ls          :   LS scale=DECIMAL;

g01			: 	G01; 
g02			: 	G02;
g03			: 	G03;
g04         :   G04 comment=string; 
g36			:	G36 EOB (g36Region)+ G37; 
g36Region	:	d02 EOB ((d01 | g01 | g02 | g03) EOB)+; 
g54			:	G54; 
g75			: 	G75; 

d01			:	g=(G01 | G02 | G03)?
				('X' x=DECIMAL)? ('Y' y=DECIMAL)? ('I' i=DECIMAL)? ('J' j=DECIMAL)? D01		// create a linear and circular line
			;
d02			:	('X' x=DECIMAL)? ('Y' y=DECIMAL)? D02;										// move the current point
d03			:	('X' x=DECIMAL)? ('Y' y=DECIMAL)? D03;										// create a flash object
dnn			:	d=DNN;																		// aperture definition

ad          :   AD d=DNN
				template=('C' | 'R' | 'O' | 'P') (',' DECIMAL ('X' DECIMAL)*)? ; 

tf			:	t=TF (',' field)*;  
ta			:	t=TA (',' field)*; 
to			:	t=TO (',' field)*; 
td			:	t=TD; 

sr			:	SR ('X' x=DECIMAL 'Y' y=DECIMAL 'I' i=DECIMAL 'J' j=DECIMAL)?
			; 
ab			:	AB (d=DNN)?
			;

string      :   (~(EOB | PARAM) | ' ')+;
field		:   (~(EOB | PARAM | ','))+;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
 
DECIMAL	:	'-'? (DIGIT)+ ('.' (DIGIT)+)?;
EOB     :   '*';
PARAM   :   '%';

G04     :   'G04'; 		// Comment code
MO      :   'MO'; 
FS      :   'FS';
AD      :   'AD';
AM      :   'AM';
DNN     :   'D' ('0'..'9')? '1'..'9' ('0'..'9')+;
G75     :   'G75'; 		// Set quadrant mode to ‟Multi quadrant‟
G01     :   'G01';		// Linear interpolation interpolation
G02     :   'G02';		// Clockwise circular interpolation 
G03     :   'G03';		// Counterclockwise circular interpolation
G36     :   'G36'; 		// Set region mode on 
G37     :   'G37'; 		// Set region mode off
G54		:	'G54D10';

D01     :   'D01';
D02     :   'D02';
D03     :   'D03';

LP      :   'LP';
LM      :   'LM';
LR      :   'LR';
LS      :   'LS';
AB		:	'AB'; 
SR		:	'SR';
TF		:   'TF' CH (CH | ('0'..'9'))*;
TA		:   'TA' CH (CH | ('0'..'9'))*;
TO		:   'TO' CH (CH | ('0'..'9'))*;
TD		:   'TD' CH (CH | ('0'..'9'))*;

M02     :   'M02';

WS		: 	('\t' | '\u000C' | '\r' | '\n')+ -> channel(HIDDEN);

fragment

DIGIT   :	'0'..'9';
CH		:	[._a-zA-Z$];
