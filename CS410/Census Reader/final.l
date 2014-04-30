%{
#include <stdio.h>

int i = 0;
int j =0;
int k = 0;
int t = 0;
int h = 0;
int z = 0;
int ac = 0;
int counter = 0;
double hCalR = 0;
double hCalL = 0;
double hNew = 0 ;
int hCount = 0;
int new = 0;
int hZ = 0;
struct person{
char lineNumber[50];
char lastName[25];
char firstName[25];
char birthPlace[25];
char relationship[25];
char relationshipStatus[10];
char establishment[25];
char profession[25];
char employeeType[25];
char attendedSchool[10];
char immigrationYear[10];
char naturalizedAlien[10];
char naturalizationYear[10];
char mothersTongue[10];
char fathersBirthPlace[50];
char fathersMothersTongue[50];
char mothersBirthPlace[50];
char mothersMothersTongue[50];
char age[10];
char sex[10];
char race[20];
char read[10];
char write[10];
char english[10];
char houseNumber[10];
char dwellingNumber[10];
char familyNumber[10];
char houseLocation[50];
char freeOrRent[10];
char ownOrRent[10];
char numberFarmSchedule[20];
};

int printNum;
int germanyCounter = 0;
int irelandCounter= 0;
int englandCounter= 0;
int westVirginaCounter= 0;
int ohioCounter = 0;
int indianaCounter = 0;
int michiganCounter = 0;
int noDataCounter = 0;
int otherCounter = 0;
int newYorkCounter = 0;
char lexArray[500][500][100];
char bufferArray[50];
struct person variable[50];
char throwAway[2];
char empty[20] = "No data";
struct person makePerson();
void printPerson(int k);
void question1();
void question2();
void question3();
void question4();
int printNum= 0;
int louisianaCounter = 0;
int belguimCounter = 0;
int monthCounter = 0;
int wifeJobCounter = 0;
int houseNum[100];
double ordered[100];
int houseC =0; 
int houseP = 0;
int houseN = 0;
int ages;
int temp = 0;
int noRailRoadersCounter, someRailRoadersCounter, allRailRoadersCounter = 0;
int month10Counter, month11Counter, month12Counter, month13Counter, month14Count, month15Counter = 0;
int month16Counter, month17Counter, month26Counter,
month30counter, month36Counter, month37Count, month47Counter, month50Counter, month53Counter = 0;
int commaTracker = 0;
%}
Letter ([aA-zZ])
TwoWords([aA-zZ]+[ ][aA-zZ]+)
String ([aA-zZ]+)
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
Dashes(-)
Spaces([ ]*)

%%
{NewLine} { 
if ( i > 1) {
strcpy(variable[k].lineNumber, lexArray[i][0]);
strcpy(variable[k].houseNumber, lexArray[i][1]);
strcpy(variable[k].dwellingNumber, lexArray[i][2]);
strcpy(variable[k].familyNumber, lexArray[i][3]);
strcpy(variable[k].lastName, lexArray[i][4]);
strcpy(variable[k].firstName, lexArray[i][5]);  
strcpy(variable[k].relationship, lexArray[i][6]);
strcpy(variable[k].ownOrRent, lexArray[i][7]);
strcpy(variable[k].freeOrRent, lexArray[i][8]);
strcpy(variable[k].sex, lexArray[i][9]);
strcpy(variable[k].race, lexArray[i][10]);
strcpy(variable[k].age, lexArray[i][11]);
strcpy(variable[k].relationshipStatus, lexArray[i][12]);
strcpy(variable[k].immigrationYear, lexArray[i][13]);
strcpy(variable[k].naturalizedAlien, lexArray[i][14]);
strcpy(variable[k].naturalizationYear, lexArray[i][15]);
strcpy(variable[k].attendedSchool, lexArray[i][16]);
strcpy(variable[k].read, lexArray[i][17]);
strcpy(variable[k].write, lexArray[i][18]);
strcpy(variable[k].birthPlace, lexArray[i][19]);
strcpy(variable[k].mothersTongue, lexArray[i][20]);
strcpy(variable[k].fathersBirthPlace, lexArray[i][21]);
strcpy(variable[k].fathersMothersTongue, lexArray[i][22]);
strcpy(variable[k].mothersBirthPlace, lexArray[i][23]);
strcpy(variable[k].mothersMothersTongue, lexArray[i][24]);
strcpy(variable[k].english, lexArray[i][25]);
strcpy(variable[k].profession, lexArray[i][26]);
strcpy(variable[k].establishment, lexArray[i][27]);
strcpy(variable[k].employeeType, lexArray[i][28]);
strcpy(variable[k].numberFarmSchedule , lexArray[i][29]);

if (strcmp(variable[k].relationship, "wife") == 0){
if (strcmp(variable[k].profession, "none") == 0){}
else if (strcmp(variable[k].profession, "None") == 0) {}
else if (strcmp(variable[k].profession, "No data") == 0) {}
else {
wifeJobCounter++; 
}
} // end wife job check

if (strcmp(variable[k].relationship, "daughter") == 0 || strcmp(variable[k].relationship, "son") == 0 || strcmp(variable[k].relationship, "granddaughter") == 0 || strcmp(variable[k].relationship, "grandson") == 0){

if (strcmp(variable[k].age, "4-2/12") == 0){
month50Counter++;
}
else if (strcmp(variable[k].age, "10/12") == 0){
month10Counter++;
}
else if (strcmp(variable[k].age, "1-2/12") == 0){
month14Count++;
}
else if (strcmp(variable[k].age, "1-5/12") == 0){
month17Counter++;
}
else if (strcmp(variable[k].age, "3") == 0){
month36Counter++;
}
else if (strcmp(variable[k].age, "4-5/12") == 0){
month53Counter++;
}
else if (strcmp(variable[k].age, "3-1/12") == 0){
month37Count++;
}
else if (strcmp(variable[k].age, "2-6/12") == 0){
month30counter++;
}
else if (strcmp(variable[k].age, "3-11/12") == 0){
month47Counter++;
}
else if (strcmp(variable[k].age, "2-2/12") == 0){
month26Counter++;
}
else if (strcmp(variable[k].age, "1-1/12") == 0){
month13Counter++;
}
} // end monthcheck
 
if (strcmp(variable[k].birthPlace, "Germany") == 0) {
germanyCounter++;
} 
else if (strcmp(variable[k].birthPlace,"Belgium") == 0) {
belguimCounter++;
}
else if (strcmp(variable[k].birthPlace,"Louisiana") == 0) {
louisianaCounter++;
}
else if (strcmp(variable[k].birthPlace,"England") == 0) {
englandCounter++;
}
else if (strcmp(variable[k].birthPlace, "Ireland") == 0) {
irelandCounter++;
}
else if (strcmp(variable[k].birthPlace, "West Virginia") == 0) {
westVirginaCounter++;
}
else if (strcmp(variable[k].birthPlace, "Ohio") == 0) {
ohioCounter++;
}
else if (strcmp(variable[k].birthPlace,"Indiana") == 0) {
indianaCounter++;
}
else if (strcmp(variable[k].birthPlace, "Michigan") == 0) {
michiganCounter++;
}
else if (strcmp(variable[k].birthPlace, "New York") == 0) {
newYorkCounter++;
}
else if (strcmp(variable[k].birthPlace, "No data") == 0) {
noDataCounter++;
} 
else {
otherCounter++;
} 


if (k == 69){
for (h = 0; h < 70; h++){
if (strcmp(variable[h].relationship, "head") == 0 && (strcmp(variable[h].establishment, "Railroad shop") == 0 || strcmp(variable[h].establishment, "Steam Railroad") == 0 || strcmp(variable[h].establishment, "Railroad yard") == 0 || strcmp(variable[h].establishment, "Steam Rairoad") == 0)) {

ordered[hZ] = atof(variable[h].houseNumber); 
hZ++; 

if (h == 68){
for(new = 0; new < 14; new++){
if (ordered[new] > ordered[new+1]){
temp = ordered[new];
ordered[new] = ordered[new+1];
ordered[new+1] = temp;
new = 0;
}
}

for (hCount = 0; hCount < 15; hCount++){
if (hCount == 0){ 
someRailRoadersCounter++;
}

 else if (hCount == 1 && ordered[hCount] == 0){
someRailRoadersCounter++;
}

else  if (hCount > 1){
hCalR = ordered[hCount+1] - ordered[hCount];
hCalL = ordered[hCount-1] - ordered[hCount];
 if ( (-4.0 < hCalR && hCalR < 4) && (-4.0 < hCalL && hCalL < 4) && (hCalL != 0 || hCalR != 0) ){
allRailRoadersCounter++;
}

else if (((-4 < hCalR  && hCalR < 4) || (-4 < hCalL && hCalL < 4)) && (hCalL != 0 || hCalR != 0) ) {
someRailRoadersCounter++;
}

 else { 
noRailRoadersCounter = 1;
}
}
}// end if
}// end for
}// end if
}
}
k ++; 
} 
i++;

if ( j = 29) {
strcpy(bufferArray, empty);
strcpy(lexArray[i][j], bufferArray);
}
j = 0;
}

{Letter} {strcpy(bufferArray, yytext);
}
{TwoWords} {strcpy(bufferArray, yytext);}
{ShortString} {strcpy(bufferArray, yytext);} 
{String} {strcpy(bufferArray, yytext);}
{Decimal}  {strcpy(bufferArray, yytext);}
{FractionWithInteger} {strcpy(bufferArray, yytext);}
{Fraction} {strcpy(bufferArray, yytext);}
{Integer} {strcpy(bufferArray, yytext);
}

{Comma} {
if ( i > 1){
strcpy(lexArray[i][j], bufferArray);
strcpy(bufferArray, empty);
j++;
}
else {strcpy(throwAway, bufferArray);}
}

{Quotes} {strcpy(throwAway, yytext);}
{Other} {
strcpy(throwAway, yytext);}
{SingleQuotes} {strcpy(throwAway, yytext);}
{Dashes} {strcpy(throwAway, yytext);}
{Spaces}{1} {strcpy(throwAway, yytext);}
%%

main(){
FILE *filePointer = fopen("census.txt", "r"); 
yyin = filePointer;  
yylex(); 
printf("Which line number would you like to scan in (starting at 0).\n");
scanf("%d", &printNum);

printPerson(printNum); 
printf("\n---------------------------------------\n");
printf("-------------Question 1----------------\n");
printf("---------------------------------------\n");
question1();
printf("\n---------------------------------------\n");
printf("-------------Question 2-----------------\n");
printf("---------------------------------------\n");
question2();
printf("\n---------------------------------------\n");
printf("-------------Question 3----------------\n");
printf("---------------------------------------\n");
question3();
printf("\n---------------------------------------\n");
printf("-------------Question 4----------------\n");
printf("---------------------------------------\n");
question4();
}// end main 



void printPerson(int l){
int k = l;
printf("The person can be found on line: %s.\n", variable[k].lineNumber);
printf("The person has the house number: %s.\n", variable[k].houseNumber);
printf("The person has the dwelling number: %s.\n", variable[k].dwellingNumber);
printf("The person has the family number: %s.\n", variable[k].familyNumber);
printf("The person's last name is: %s.\n", variable[k].lastName);
printf("The person's name is: %s.\n", variable[k].firstName);
printf("The person's relationship to the family is: %s.\n", variable[k].relationship);
printf("The person's house is: %s.\n", variable[k].ownOrRent);
printf("The person's house is: %s.\n", variable[k].freeOrRent);
printf("The person's gender is: %s.\n", variable[k].sex);
printf("The person's race is: %s.\n", variable[k].race);
printf("The person's age is: %s.\n", variable[k].age);
printf("The person's relationship status is: %s.\n", variable[k].relationshipStatus);
printf("The person's immigration year is: %s.\n", variable[k].immigrationYear);
printf("The person is: %s.\n", variable[k].naturalizedAlien);
printf("The person's naturalization year is: %s.\n", variable[k].naturalizationYear);
printf("The person has attended school: %s.\n", variable[k].attendedSchool);
printf("The person can read: %s.\n", variable[k].read);
printf("The person can write: %s.\n", variable[k].write);
printf("The person's birthplace is: %s.\n", variable[k].birthPlace);
printf("The person's mothers tongue: %s.\n", variable[k].mothersTongue);
printf("The person's father's birthplace: %s.\n", variable[k].fathersBirthPlace);
printf("The person's father's mother's tongue: %s.\n", variable[k].fathersMothersTongue);
printf("The person's mother's birthplace: %s.\n", variable[k].mothersBirthPlace);
printf("The person's mother's mother's tongue: %s.\n", variable[k].mothersMothersTongue);
printf("The person can speak english: %s.\n", variable[k].english);
printf("The person's profession: %s.\n", variable[k].profession);
printf("The person's establishment: %s.\n", variable[k].establishment);
printf("The person's employement type: %s.\n", variable[k].employeeType);
printf("The person's farm schedule: %s.\n", variable[k].numberFarmSchedule); 
}

void question1(){
printf("There are %d people who live in Germany.\n", germanyCounter);
printf("There are %d people who live in England.\n", englandCounter);
printf("There are %d people who live in Ireland.\n", irelandCounter); 
printf("There are %d people who live in West Virginia.\n", westVirginaCounter);
printf("There are %d people who live in Ohio.\n", ohioCounter);
printf("There are %d people who live in Indiana.\n", indianaCounter);
printf("There are %d people who live in Michigan.\n", michiganCounter);
printf("There are %d people who live in New York.\n", newYorkCounter);
printf("There are %d people who live in Belguim.\n", belguimCounter);
printf("There are %d people who live in Louisiana.\n", louisianaCounter);
printf("There are %d people who don't have a recorded birthplace.\n", noDataCounter);
printf("There are %d people who live in a different location.\n", otherCounter);
}


void question2(){

printf("There are %d children aged at 10 month.\n", month10Counter);
printf("There are %d children aged at 11 month.\n", month11Counter);
printf("There are %d children aged at 12 month.\n", month12Counter);
printf("There are %d children aged at 13 month.\n", month13Counter);
printf("There are %d children aged at 14 month.\n", month14Count);
printf("There are %d children aged at 15 month.\n", month15Counter);
printf("There are %d children aged at 16 month.\n", month16Counter);
printf("There are %d children aged at 17 month.\n", month17Counter);

printf("There are %d children aged at 26 month.\n", month26Counter);
printf("There are %d children aged at 30 month.\n", month30counter);
printf("There are %d children aged at 36 month.\n", month36Counter);
printf("There are %d children aged at 37 month.\n", month37Count);
printf("There are %d children aged at 47 month.\n", month47Counter);
printf("There are %d children aged at 50 month.\n", month50Counter);
printf("There are %d children aged at 53 month.\n", month53Counter);
}

void question3(){
printf("There are a total of %d households in which the head of the house is a railroader and all neighbor heads of households are railroaders.\n\n", allRailRoadersCounter);

printf("There are a total of %d households in which the head of the house is a railroader nand some neighbor heads of households are railroaders.\n\n", someRailRoadersCounter);

printf("There are a total of %d households in which the head of the house is a railroader and no neighbor heads of households are railroaders.\n\n", noRailRoadersCounter);
}


void question4(){
printf("There are %d wives that have jobs.\n", wifeJobCounter);

}

