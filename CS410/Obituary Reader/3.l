%{
#include "y.tab.h"
extern FILE * yyin;
%}
Paper("Grant County Press"|"Cumberland Times-News"|"Pendleton Times"|"Elkins Intermountain"|"The Evening Post Newspaper")

FullName([A-Z][a-z]*" "[A-Z][a-z]*" "[A-Z][a-z]*|[A-Z][a-z]*" "[A-Z][.]" "[A-Z][a-z]*|[A-Z][a-z]*" "[A-Z][a-z]*" "[(][A-Z][a-z]*[)]" "[A-Z][a-z]*|"Mrs."" "[A-Z][a-z]*" "[A-Z][a-z]*|"Mr."" "[A-Z][a-z]*" "[A-Z][a-z]*)

Month("December"|"January"|"Febuary"|"March"|"April"|"May"|"June"|"July"|"August"|"September"|"October"|"November"|"Dec."|"Jan."|"Feb."|"Aug."|"Sept."|"Oct."|"Nov.")


Born("Born Sept. "[0-3][0-9]", "[1-9][0-9][0-9][0-9])

Died("died Friday, Dec. 4, 1998")

FullDate([0-1][0-9][/][1-3][0-9][/][[1-9][0-9][0-9][0-9])

Year([1-2][0-9][0-9][0-9])

Time([1-9]"a.m."|[1][0-2]"a.m"|[1-9]"p.m."|[1][0-2]"p.m")

Relationship("husband"|"wife"|"father"|"mother"|"son"|"daughter"|"sister"|"brother"|"step-grandson"|"son-in-law"|"daughter-in-law"|"step-great-grandchild"|"step-great-great-grandchildren"|"stepmother"|"stepfather"|"friend")

Town("Benbush"|"Brownsville"|"Brushy Run"|"Cabins"|"Clarksburg"|"Cumberland"|"Flinstone"|"Fort Seybert"|"Franklin"|"Gormania"|"Harman"|"Kearneysville"|"Landes"|"Washington"|"Wellsburg"|"West Jefferson"|"Winchester")

County("Tucker"|"Cameron"|"Pendleton"|"Grant"|"Harrison"|"Allegany"|"Randolph"|"Jefferson"|"Brooks"|"Madison"|"Federick")

States("West Virginia"|"Texas"|"Maryland"|"South Korea"|"Ohio"|"District of Columbia")

Gender("he"|"she"|"him"|"her")

Age([0-9][0-9]"years"|[0-9][0-9]" years"|[0-9][0-9])

Letter [aA-zZ]

TwoWords([aA-zZ]+" "[aA-zZ]+)

String [aA-zZ]+

ShortString ([aA-zZ]+[.]+)

FractionWithInteger ([0-9]*[-]+[0-9]+[/][0-9]*)

Integer ([0-9]*)

Comma (,)

NewLine(\n)

Fraction ([0-9]+[/][0-9]*)

Decimal ([0-9]+[.][0-9]*)

Other ([/(?)/])

Quotes ([/"/]*)

SingleQuotes ([/'/]*)

Dashes (-)

Spaces([ ]*)


Stop("STOP")
%%
{Paper} {yylval.str = strdup(yytext); return PAPER;}
{Born}  {yylval.str = strdup(yytext); return BORN;}
{Died}  {yylval.str = strdup(yytext); return DIED;}
{Year} {yylval.str = strdup(yytext); return YEAR;}
{Age} {yylval.str = strdup(yytext); return AGE;}
{Gender} {yylval.str = strdup(yytext); return GENDER;}
{FullName} {yylval.str = strdup(yytext); return FNAME; }
{FullDate} {yylval.str = strdup(yytext); return FDATE; }
{Time} {yylval.str = strdup(yytext); return TIME;}
{Relationship} {yylval.str = strdup(yytext); return RELATIONSHIP;}
{Town} {yylval.str = strdup(yytext); return TOWN;}
{County} {yylval.str = strdup(yytext); return COUNTY;}
{States} {yylval.str = strdup(yytext); return STATES;}
{Stop} {yylval.str = strdup(yytext); return STOP;}
. ;
%%
