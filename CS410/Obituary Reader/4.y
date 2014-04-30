%{
#include <stdio.h>
#include <string.h>

extern FILE *yyin;

int timeCount, dateCount, ageCount, relationCount, genderCount, stateCount,countyCount, townCount, fullNameCount, paperCount, yearCount, bornCount, diedCount = 0;

int timeFlag, fullNameFlag, dateFlag, ageFlag, relationshipFlag, genderFlag, stateFlag, countyFlag, townFlag, paperFlag, yearFlag, bornFlag, diedFlag = 0;

char time[50][10];
char date[50][20];
char age[50][20];
char relationship[50][20];
char gender[50][20];
char state[50][20];
char county[50][20];
char town[50][20];
char fullName[50][30];
char paper[50][20];
char year[50][20];
char born[50][20];
char died[50][20];

%}

%union {
	char *str;
	int num;	
}

%token <str> BORN
%token <str> TIME 
%token <str> FNAME
%token <str> FDATE 
%token <str> AGE
%token <str> RELATIONSHIP
%token <str> TOWN
%token <str> COUNTY
%token <str> STATES
%token <str> GENDER
%token <str> PAPER
%token <str> YEAR
%token <str> DIED
%token <str> STOP

%%


start : Grammar 
| start Grammar 
;

Grammar
: BORN {
	printf("Born-: %s \n",$1);
	strcpy(born[bornCount],$1);
	bornCount++;
	bornFlag = 1;
}

| DIED {
	printf("Died: %s\n",$1);
	strcpy(died[diedCount], $1);
	diedCount++;
	diedFlag = 1;
}

| FDATE {
	printf("Date: %s\n",$1);
	strcpy(date[dateCount], $1);
	dateCount++;
	dateFlag = 1;
}
 
| TIME {
	printf("Time: %s\n", $1);
	strcpy(time[timeCount], $1);
	timeCount++;
	timeFlag = 1;
}

| AGE {
	printf("Age: %s \n",$1);
	strcpy(age[ageCount], $1);
	ageCount++;
	ageFlag = 1;
}

| GENDER{
	if ((strcmp($1,"she") == 0) || (strcmp($1,"her") == 0)) { 
		strcpy(gender[genderCount], "female");
	}

	else if ((strcmp($1,"he") == 0) || (strcmp($1,"him") == 0)){ 
		strcpy(gender[genderCount], "male");
	}
	printf("Gender: %s\n", $1); 
	genderCount++;
	genderFlag = 1;
}

| RELATIONSHIP {
	printf("Relationship: %s \n",$1);
	strcpy(relationship[relationCount], $1);
	relationCount++;
	relationshipFlag = 1;
}

| TOWN{
	printf("Town: %s \n",$1);
	strcpy(town[townCount], $1);
	townCount++;
	townFlag = 1;
	countyFlag = 1;
	stateFlag = 1;

	if (strcmp($1,"Benbush")==0){ 
		strcpy(county[countyCount], "Tucker");
		strcpy(state[stateCount], "West Virginia");
	}

	else if (strcmp($1,"Brownsville")==0){ 
		strcpy(county[countyCount],"Cameron");
		strcpy(state[stateCount],"Texas");
	}

	else if (strcmp($1,"Cumberland")==0){ 
		strcpy(county[countyCount],"Allegany");
		strcpy(state[stateCount],"Maryland");
	}

	else if (strcmp($1,"Brushy Run")==0){ 
		strcpy(county[countyCount],"Pendleton");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Cabins")==0){ 
		strcpy(county[countyCount],"Grant");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Clarksburg")==0){ 
		strcpy(county[countyCount],"Harrison");
		strcpy(state[stateCount],"Maryland");
	}

	else if (strcmp($1,"Flinstone")==0){ 
		strcpy(county[countyCount],"Allegany");
		strcpy(state[stateCount],"Maryland");
	}

	else if (strcmp($1,"Fort Seybert")==0){ 
		strcpy(county[countyCount],"Pendleton");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Franklin")==0){ 
		strcpy(county[countyCount],"Pendleton");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Gormania")==0){ 
		strcpy(county[countyCount],"Grant");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Harman")==0){ 
		strcpy(county[countyCount],"Randolph");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Kearneysville")==0){ 
		strcpy(county[countyCount],"Jefferson");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Landes")==0){ 
		strcpy(county[countyCount],"Grant");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Petersburg")==0){ 
		strcpy(county[countyCount],"Grant");
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Seoul")==0){ 
		printf("South Korea");
		countyCount--;
		stateCount--;
		countyFlag = 0;
		stateFlag = 0;
	}
	countyCount++;
	stateCount++;
}

| COUNTY {
	printf("County: %s \n",$1);
	countyFlag = 1;
	if (strcmp($1,"Tucker")==0){ 
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Cameron")==0){ 
		strcpy(state[stateCount],"Texas");
	}

	else if (strcmp($1,"Pendleton")==0){ 
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Grant")==0){ 
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Harrison")==0){ 
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Allegany")==0){ 
		strcpy(state[stateCount],"Maryland");
	}

	else if (strcmp($1,"Randolph")==0){ 
		strcpy(state[stateCount],"West Virginia");
	}

	else if (strcmp($1,"Jefferson")==0){ 
		strcpy(state[stateCount],"West Virginia");
	}
	stateCount++;
}

| STATES {
	strcpy(state[stateCount], $1);
	printf("State: %s \n",$1);
	stateCount++;
	stateFlag = 1;
}

| FNAME {
	printf("Full Name: %s\n",$1);
	strcpy(fullName[fullNameCount], $1);
	fullNameCount++;
	fullNameFlag = 1;
}

| PAPER{
	printf("Paper: %s \n",$1);
	strcpy(paper[paperCount],$1);
	paperCount++;
	paperFlag =1;
}


| YEAR{
	printf("Year: %s \n",$1);
	strcpy(year[yearCount],$1);
	yearCount++;
	yearFlag = 1;
}

| STOP {
	printCrap();
}
%%

main(){
FILE *f1 = fopen("34.txt", "r");
	if(f1 == NULL){ 
		printf("Error in Opening File ");
 		return -1;
 	} 
	yyin = f1;
 do{ 
	yyparse(); 
}
while(!feof(yyin)); 
}

int yyerror (const char *msg) {
	return fprintf (stderr, "YACC: %s\n", msg);
	}

printCrap(){
int nameI = 0;
int ageI = 0;
int townI = 0;
int countyI = 0;
int stateI = 0;
int timeI = 0;
int relationI = 0;
int genderI = 0;
int paperI = 0;
int bornI = 0;
int diedI = 0;
int i = 0;


if ((fullNameFlag && ageFlag && townFlag && countyFlag && stateFlag && timeFlag) == 1){
	printf("%s died at the age of %s in %s, %s, %s at %s. ", fullName[nameI], age[ageI],town	[townI], county[countyI], state[stateI], time[timeI]);
	nameI++;
	ageI++;
	townI++;
	countyI++;
	stateI++;
	timeI++;
}

else if ((fullNameFlag && ageFlag && townFlag && countyFlag && stateFlag) == 1){
	printf("%s died at the age of %s in %s, %s, %s. ", fullName[nameI], age[ageI],town[townI], county[countyI], state[stateI]);
	nameI++;
	ageI++;
	townI++;
	countyI++;
	stateI++;
}

else if((fullNameFlag && ageFlag && timeFlag) == 1){
	printf("%s died at the age of %s at %s. ", fullName[nameI], age[ageI], time[timeI]);
	ageI++;
	nameI++;
	timeI++;
}

else if((fullNameFlag && ageFlag) == 1){
	printf("%s died at the age of %s. ", fullName[nameI], age[ageI]);
	ageI++;
	nameI++;
}

else if(fullNameFlag == 1){
	printf("%s died. ", fullName[nameI]);
	nameI++;
}


if (timeFlag == 1){
	printf("The time of death was %s. ",time[timeI]);
	timeI++;
}


if ((relationshipFlag && genderFlag) == 1){
	if (strcmp(gender[genderI], "male") == 0){
		for (i = 0; i < relationCount; i++){
			if (strcmp(relationship[relationI], "son") == 0 || strcmp(relationship	[relationI], "father")  == 0 || strcmp(relationship[relationI], "husband") == 0){
				printf("He was a %s. ", relationship[relationI]);
				relationI++;
				}
		}
			if(strcmp(relationship[relationI], "friend") == 0){
				printf("He was a %s. ", relationship[relationI]);
				relationI++;			
			}
	}

	else {
		for (i = 0; i < relationCount; i++){
			if (strcmp(relationship[relationI], "daughter") == 0 || strcmp(relationship[relationI], "mother")  == 0){
				printf("She was a %s. ", relationship[relationI]);
				relationI++;
			}
		}
	}

}

if(paperFlag == 1){
	printf("The original obituary was printed in the %s. ", paper[paperI]);
	paperI++;
}

if((bornFlag && genderFlag && diedFlag) == 1){
	if (strcmp(gender[genderI], "male") == 0){
		printf("He was born on %s and died on %s. ", born[bornI], died[diedI]);
		bornI++;
		diedI++;
	}
	else {
		printf("She was born on %s and died on %s. ", born[bornI], died[diedI]);
		bornI++;
		diedI;
	}
}


else if((bornFlag && genderFlag) == 1){
	if (strcmp(gender[genderI], "male") == 0){
		printf("He was born on %s. ", born[bornI]);
		bornI++;
	}
	else {
		printf("She was born on %s. ", born[bornI]);
		bornI++;
	}
}

else if((bornFlag && diedFlag )== 1){
	printf("The deceased was born on %s and died on %s. ", born[bornI], died[diedI]);
	bornI++;
	diedI++;
}

else if(bornFlag== 1){
	printf("The deceased was born on %s. ", born[bornI]);
	bornI++;
}

else if(diedFlag== 1){
	printf("The deceased died on %s. ", died[diedI]);
	diedI++;
}
else {
	
}
printf("\n--- Provided to you by Sam's Obituary Summarizer");
}

