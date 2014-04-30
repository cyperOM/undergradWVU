%{
#include "y.tab.h"
extern FILE * yyin;
%}
leave("leave"|"exit"|"walk out"|"go out the door"|"go out door"|"door"|"go through")
enter("enter"|"walk in")
open("open"|"door")
close("close"|"shut")
sneak("creep"|"crawl"|"stealth"|"tiptoe"|"sneak"|"walk up to"|"walk up")
run("book it"|"run"|"jog"|"charge")
wait("stay"|"wait"|"halt")
examine("look at"|"examine"|"analyze"|"look around"|"look through"|"window"|"look")
pickUp("pick up"|"take"|"grab"|"pick it up")
putDown("throw away"|"put down"|"remove from inventory")
talk("talk to"|"speak"|"talk"|"communicate"|"call out"|"call"|"yell")
commands("don't know what I'm doing"|"help"|"SOS"|"sos"|"commands"|"command")
yes("yes"|"yeah"|"yea"|"yep"|"sure"|"why not?"|"why not"|"of course")
no("no"|"nah"|"nope"|"of course not"|"negative")
cross("bridge"|"cross bridge")
swim("swim"|"swim across stream"|"stream")
man("man"|"guy"|"mister"|"fello"|"dude"|"sir"|"Cohen"|"Dr. Cohen"|"Dr"|"cohen"|"dr cohen"|"dr. cohen")
creature("creature"|"fluff ball"|"bunny"|"dog"|"fluff"|"fluffy")
attack("swing"|"hit"|"fight"|"take them out"|"attack"|"swinging")
barracks("barracks"|"left"|"go to barracks"|"baracks"|"go to baracks")
cafeteria("cafeteria"|"right"|"cafe"|"lunch room")
connect("a"|"the")
other([0-9]|[a-z]|[A-Z]|[a-z]*|[A-Z]*|[0-9]*)

%%
{connect} {yylval.str = strdup(yytext); return CONNECT;}
{no} {yylval.str = strdup(yytext); return NO;}
{yes} {yylval.str = strdup(yytext); return YES;}
{commands} {yylval.str = strdup(yytext); return COMMANDS;}
{leave} {yylval.str = strdup(yytext); return LEAVE;}
{enter} {yylval.str = strdup(yytext); return ENTER;}
{open} {yylval.str = strdup(yytext); return OPEN;}
{close} {yylval.str = strdup(yytext); return CLOSE;}
{sneak} {yylval.str = strdup(yytext); return SNEAK;}
{run} {yylval.str = strdup(yytext); return RUN;}
{barracks} {yylval.str = strdup(yytext); return BARRACKS;}
{cafeteria} {yylval.str = strdup(yytext); return CAFETERIA;}
{examine} {yylval.str = strdup(yytext); return EXAMINE;}
{pickUp} {yylval.str = strdup(yytext); return PICKUP;}
{putDown} {yylval.str = strdup(yytext); return PUTDOWN;}
{talk} {yylval.str = strdup(yytext); return TALK;}
{wait} {yylval.str = strdup(yytext); return WAIT;}
{man} {yylval.str = strdup(yytext); return MAN;}
{creature} {yylval.str = strdup(yytext); return CREATURE;}
{attack} {yylval.str = strdup(yytext); return ATTACK;}
{other} {yylval.str = strdup(yytext); return OTHER;}

. ; 
%%
