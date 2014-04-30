%{
#include <stdio.h>
#include <string.h>

extern FILE *yyin;
void lookAt(char*, char*);
void talkTo(char*,char*);
void spaceToContinue();
void resetFlags();

/* Arrays */
char name[1][20];
char decision[20][4];
int decisions[5][2];

/* Counters */
int age = 0;
int decisionCount = 0;

/* Decision Flags */
int kiddieGame = 0;
int intenseGame = 0;
int commandCenter = 0;
int yesOrNo = 0;
int enterOrExit = 0;
int examine = 0;
int enter = 0; 
int talk = 0;
int object = 0;
int man = 0;
int creature = 0;
int run = 0;
int wait = 0;
int leave = 0;
int cafeteria = 0;
int barracks = 0;
int cafeteria2 = 0;
int barracks2 = 0;
int open = 0;
int attack = 0;
int sneak = 0;
int companionFlag = 0;
int oneToken = 0;
int pickUp = 0;
%}

%union {
	char *str;
	int num;	
}

%token <str> BORN
%token <str> LEAVE
%token <str> ENTER
%token <str> OPEN
%token <str> CLOSE
%token <str> SNEAK
%token <str> RUN
%token <str> EXAMINE
%token <str> PICKUP
%token <str> PUTDOWN
%token <str> TALK
%token <str> COMMANDS
%token <str> YES
%token <str> NO
%token <str> OTHER
%token <str> CROSS
%token <str> SWIM
%token <str> CREATURE
%token <str> MAN
%token <str> WAIT
%token <str> ATTACK
%token <str> CAFETERIA
%token <str> BARRACKS
%token <str> CONNECT
%%

start : Grammar 
| start Grammar 
;

Grammar
: LEAVE {
if (yesOrNo == 1){
YYACCEPT;
}
leave = 1;
YYACCEPT;
}

| ENTER {
if (yesOrNo == 1){
YYACCEPT;
}
enter = 1;
YYACCEPT;
}

| ATTACK {
if (yesOrNo == 1){
YYACCEPT;
}
attack = 1;
YYACCEPT;
}

| CAFETERIA {
if (yesOrNo == 1){
YYACCEPT;
}
cafeteria = 1;
YYACCEPT;
}

| BARRACKS {
if (yesOrNo == 1){
YYACCEPT;
}

barracks = 1;
YYACCEPT;
}

| OPEN {
if (yesOrNo == 1){
YYACCEPT;
}
open = 1;
YYACCEPT;
}

| CLOSE {
if (yesOrNo == 1){
YYACCEPT;
}
YYACCEPT;
}

| SNEAK {
if (yesOrNo == 1){
YYACCEPT;
}
sneak = 1;
YYACCEPT;
}

| RUN {
if (yesOrNo == 1){
YYACCEPT;
}
run = 1;
YYACCEPT;
}

| WAIT {
if (yesOrNo == 1){
YYACCEPT;
}
wait = 1;
YYACCEPT;
}

|EXAMINE {
if (yesOrNo == 1){
YYACCEPT;
}
if (oneToken == 1){
examine = 1;
YYACCEPT;
}
examine = 1;
}

| CONNECT{

}

| PICKUP { 
if (yesOrNo == 1){
YYACCEPT;
}
pickUp = 1;
YYACCEPT;
}

| PUTDOWN {
if (yesOrNo == 1){
YYACCEPT;
}
YYACCEPT;
}

|TALK {
if (oneToken == 1){
talk = 1;
YYACCEPT;
}
if (yesOrNo == 1){
YYACCEPT;
}
talk = 1;
}

|MAN {
object = 1;
man = 1;
if (yesOrNo == 1){
YYACCEPT;
}
YYACCEPT;
}

| CREATURE {
if (yesOrNo == 1){
YYACCEPT;
}
object = 1;
creature = 1;
YYACCEPT;
}

| COMMANDS {
if (yesOrNo = 1){
YYACCEPT;
}
printf("\n");
printf("Here is the list of commands you can use:\n");
printf("leave: leave, exit, walk out\n");
printf("enter: enter,walk in\n");
printf("open: open\n");
printf("close: close, shut\n");
printf("sneak: creep, crawl, stealth, tiptoe, sneak\n");
printf("run: book it, run, jog\n");
printf("examine: look at, examine, analyze, look around\n");
printf("pickUp: pick up, take, grab\n");
printf("putDown: throw away, put down, remove from inventory\n");
printf("talk: talk to, speak, talk, communicate\n");
printf("commands: don't know what I'm doing, help, SOS, sos, commands, command\n");
printf("yes: yes, yeah, yea, yep, sure, why not?, why not, of course\n");
printf("no: no, nah, nope, of course not, negative\n");
printf("cross: bridge, cross bridge\n");
printf("swim: swim, swim across stream, stream \n");
printf("Anything else that you may enter will be counter as unidentified and not used.\n");
printf("\n");
YYACCEPT;
}

| CROSS {
if (yesOrNo = 1){
YYACCEPT;
}
printf("You have chosen to cross the bridge.\n");
/*strcpy(decision[decisionCount], "yes");
decisionCount++; */ 
YYACCEPT;
}

| SWIM {
if (yesOrNo = 1){
YYACCEPT;
}
printf("You have chosen to swim across the stream.\n");
/*strcpy(decision[decisionCount], "yes");
decisionCount++; */ 
YYACCEPT;
}

| OTHER {
if (yesOrNo = 1){
YYACCEPT;
}

}


| NO {
printf("You declined.\n");
strcpy(decision[decisionCount], "no");
decisionCount++;
YYACCEPT;
}

| YES {
printf("You accepted.\n");
strcpy(decision[decisionCount], "yes");
decisionCount++;
YYACCEPT;
}
%%

main(){
/*************************************/
/*  Introduction of the Game */
/***********************************/
printf("Welcome to The Blood Wrenching Horror Survival Game.\n");
sleep(1);
printf("A weak tired hand slaps down on the stasis pod id reader.\n");
sleep(1);
printf("[computer voice]: Unfreezing...Name:\n");
scanf("%s", &name);
printf("[computer voice]: Age:\n");
scanf("%d", &age);
printf("[computer voice]: Occupation: Janitor\n");
sleep(1);
printf("[computer voice]: Rank: 8\n");
sleep(1);
printf("[Computer Voice]: Unfreezing Complete.\n");
sleep(1);
printf("The pod opened up.\n");
//spaceToContinue();
printf("[????]: *cackle cackle*\n");
sleep(1);
printf("The noise is coming from a blood covered man slumped on the floor who is cackling and coughing up blood.\n");
sleep(2);
printf("In the corner you see a cowering fluffy creature in the corner.\n");
sleep(1);
printf("[computer voice]: The inventory pod is opening up.\n");
sleep(1);
printf("[game prompt] would you like to look at the man or the fluffy creature\n");
yyparse();

if (examine == 1 && object == 1) {
		if(man == 1){
		lookAt("1", "man");
		}
		else {
		lookAt("1", "creature");
		}
	}
	else {
		while (examine == 0){
		printf("Please add more detail to your response.");
		resetFlags();
		yyparse();
		}
		if(man == 1){
		lookAt("1", "man");
		}
		else {
		lookAt("1", "creature");
		}
	} // end decision one

if (age < 18){
kiddieGame = 1; 
}
else {
intenseGame = 1; 
}

/*************************************/
/* Kiddie(Demo) Verison of the Game */
/***********************************/
if (kiddieGame == 1){
sleep(2);
printf("You decide this is too much for you. You climb back into the pod and enter back into stasis.\n");
return 1;
}

else if (intenseGame == 1){
sleep(2);
printf("You walk out dazed and confused. There is a guy on the ground bleeding profusely.\n");
sleep(1);
resetFlags();
	while (talk == 0){
	printf("[game prompt]: You may talk to the man, examine the man, or examine the creature.\n");
	yyparse();
		if (examine == 1 && object == 1) {
			if(creature == 1){
			lookAt("1", "creature");
				}
			else if (man == 1) {
			lookAt("1", "man");
		}
	resetFlags();
	}

	else if(talk == 1 && object == 1){ 
	talkTo("1", "man");
	}
} // end while 
printf("[%s]: Are you okay?\n",name[0]);
	printf("[bloody man]: *cackle cackle* Just a janitor... *cackle cackle*\n");
	sleep(1);
	printf("[%s]: Sir, are you okay?\n",name[0]);
	sleep(1);
	printf("The man slowly raises his head and looks at you.\n");
	sleep(1);
	printf("[bloody man]: I don't have much time.\n");
	sleep(1);
	printf("[bloody man]: Everybody is dying...\n");
	sleep(1);
	printf("[bloody man]: *coughs up blood*\n");
	sleep(1);
//spaceToContinue();
	printf("[computer system]: It's normal to feel a little whoozy when waking up from stasis.\n");
	sleep(1);
	printf("[bloody man]: You must find Dr. Cohen and give him the creature.\n");
	sleep(1);
	printf("[%s]: What's happening? What happened to you?\n",name[0]);
	sleep(1);
	printf("[bloody man]: There is no time for that...\n");
	sleep(1);
	printf("[bloody man]: I'm about to die and you won't be able to use my hand chip\n");
	sleep(1);
	printf("[bloody man]: If that happens you'll have to find someone who has\n");
	sleep(1);
	printf("[bloody man]: clearance to open Dr. Cohen's pod\n");
	sleep(1);
	printf("[bloody man]: You'll have to go to the command center...A map is in my sack.\n");
//spaceToContinue();
	printf("The sack is laying next to him.\n");
	sleep(1);
	printf("I grab a map from the sack.\n");
	sleep(1);
	printf("[bloody man]: You'll have to meet Lieutenant Ryan\n"); 
	sleep(1);
	printf("[bloody man]: Tell him what happened to me...\n");
	sleep(1);
	printf("[%s]: What happened?\n", name[0]);
	sleep(1);
	printf("[bloody man]: *coughs up blood* I was attacked by our crew...\n");
	sleep(1);
	printf("You can see the horror on his face\n");
	sleep(1);
	printf("He then collapses to the floor.\n");
	sleep(1);
	printf("You check the man's pulse and realize he is dead.\n");
	sleep(1);
	printf("The creature comes out from the corner and begins rubbing up against you.\n");
	sleep(1);
	printf("[computer system]: Please retrieve your belongings from the locker 4123.\n");
	printf("Locker 4123 opens up.\n");
//spaceToContinue();
	printf("Looking down, you now realize you're naked.\n");
	sleep(1);
	printf("You go to the locker and put on your janitor outfit. As you reach for your mop you hear a groning in the background.\n");
	sleep(1);
	printf("Looking back you see the bloody man is starting to move again.\n");
	sleep(1);
	printf("You grab ahold of your mop.\n");
	sleep(1);
while(run == 0 && wait == 0){
	printf("[game prompt]: Would you like to run or wait?\n");
	resetFlags();
	yyparse();
	}

	if (run == 1){
	printf("You run out of the room and the bunny creature follows and you slam the door shut.\n");
	sleep(1);
	}

else if (wait == 1){
	printf("The bloody man looks around the room with cloudy eye. He no longer appears in pain.\n");
	sleep(1);
	printf("[%s]: sir...\n", name[0]);
	sleep(1);
	printf("When you speak he looks at you and begins getting up\n");
	sleep(1);
	resetFlags();
	while(run == 0 && wait == 0){
	printf("[game prompt]: Would you like to run or wait?\n");
	yyparse();
	}
sleep(1);
	if (run == 1){
	printf("You run out of the room and the bunny creature follows and you slam the door shut.\n");
	sleep(1);
	}
	else if (wait ==1){
	printf("He starts snarling and you hold out your mop as he runs into it.\n");
	sleep(1);
	printf("It forces you back. You recollect your senses and push him backwards.\n");
sleep(1);
	printf("You run out of the room and the bunny creature follows and you slam the door shut and lean against it.\n");
	}

	printf("You hear a snarling and feel a slam against the door. You quickly do a scan of the room. It shoves you forward.\n");
	sleep(1);
printf("You immediately slam it closed again and bolt the door.\n");
}	

printf("You find yourself in a hallway with a door at the end of it.\n");
sleep(1);
// correct to this point
	while(examine == 0 && leave == 0){
	printf("[game prompt]: Would you like to look at the map or go out the door\n");
	resetFlags();
	yyparse();
}
	if (examine == 1) {
	printf("There are large X's on different portions of the map.\n");
	printf("One portion of the map is the barracks.\n");
	sleep(1);
	}

	else if (leave == 1){
	printf("You see a sign that says '<--- Barracks to the left. Cafeteria to the right. -----> Command center either direction.\n");
	}

while(cafeteria == 0 && barracks == 0){
printf("[game prompt]: Would you like to go to the barracks or the cafeteria\n");
resetFlags();
yyparse();
}

if(barracks == 1){
resetFlags();
 barracks2 = 1;
printf("As you approach the barracks. You notice a stream of blood leading to the door.\n");
while(attack == 0 && sneak == 0 && run == 0){
printf("[game prompt]: would you like to charge in or sneak in?\n");
resetFlags();
yyparse();
}
 if (attack ==1 || run == 1) {
resetFlags();
printf("You rush in and take a zombie down with a few good blows to the chest. Two more zombies rush at you and you manage to dodge them.\n");
sleep(1);
printf("As you look around for an exit, you discover you're surrounded....\n");
sleep(1);
printf("Game over\n");
return 0;
}

else if(sneak == 1){
resetFlags();
printf("You see a room full of zombies. There is a machine gun laying on the ground.\n");
sleep(1);
while(pickUp == 0 && attack == 0){
printf("[game prompt]: Would you like to pick it up or go in swinging?\n");
resetFlags();
yyparse();
}
if(pickUp ==1){
printf("You obtain a machine gun!\n");
printf("You charge into the room while fireing randomly in all directions.\n");
printf("You spot an exit and start clearing a path to the door.\n");
printf("You run out of ammo and toss the gun on the ground and make a break for the door.\n");
printf("A zombie charges at you! Just as he snaps at you, the creature attacks the zombie. You continue running towards the door.");
}

else if (attack ==1) {
printf("You rush in and take a zombie down with a few good blows to the chest. Two more zombies rush at you and you manage to dodge them.\n");
sleep(1);
printf("As you look around for an exit, you discover you're surrounded....\n");
sleep(1);
printf("Game over\n");
return 0;
}}
barracks = 1;
} // end barracks

if (cafeteria == 1){ 
printf("As you approach the cafeteria door, there is a window\n");
sleep(1);
while(leave == 0 && open ==0 && examine == 0){
printf("[game prompt]: would you like to look through the window or go through the door.\n");
resetFlags();
oneToken = 1;
yyparse();
}

if (leave == 1 || open == 1){
printf("You open the door and the three men hunched over the boody look back at you and just stare....\n");
sleep(1);
printf("You can see that their faces are covered in blood and one has portions of a liver sticking out of its mouth\n");
sleep(1);
printf("You see a door across the room and make a break for it\n");
sleep(1);
printf("The zombies seeing this begin to get up. One zombie slips in the pool of blood.\n");
sleep(1);
while(attack == 0 && run == 0){
printf("[game prompt]: you can swing at the two zombies or run faster\n");
resetFlags();
yyparse();
}
if (attack == 1) {
printf("You push a zombie over the zombie who slipped in the blood.\n");
printf("The remaining zombie strikes at you.\n");
printf("Out of nowhere, the creature attacks the zombie.\n"); 
printf("This allows you to run to the door.\n");
}
else if (run == 1){
printf("You trip and die.. (anticlimactic, I know...)\n");
return 1;
}
}
else if (examine == 1) { 
printf("You look through the window and notice a body in the center of the room\n");
sleep(1);
printf("in a pool of blood. Three men are hunched around it\n");

printf("They appear to be eating something but you can't see enough from this angle\n");
sleep(1);
while(sneak == 0 && attack == 0){
printf("[game prompt]: Would you like to sneak past or make a run for it\n"); 
resetFlags();
yyparse();
}
if (sneak == 1) {
printf("You quietly open the door and crawl through the door.\n");
sleep(1);
printf("You take a closer look at the hunched men and notice that they're feeding on the man\n");
sleep(1);
printf("You notice a meat cleaver on a counter\n");
sleep(1);
resetFlags();
while(attack == 0 && sneak == 0){
printf("[game [prompt]: You can take them out or sneak by.\n");
resetFlags();
yyparse();
}
if (attack == 1){ 
printf("You're out numbered and die.\n");
return 1;
}
printf("A zombie notices you and runs at you.\n");
printf("The creature attacks the zombie and allows you to get to the door. \n");
cafeteria2 = 1;
}
}
 }// end cafe

/*************************/
/*     Chapter 2        */
/************************/

printf("You get to the door. After the creature bolts through the door, you slam it behind you. Your heart feels as if it's beating out of your chest.\n");
sleep(3);
printf("You're shocked by the creatures reaction to save you.\n");
sleep(1);
printf("You bend down and pet the creature.\n");
printf("The creature jumps around excitedly\n");
printf("You look around the room and realized you're in the command center!\n");
commandCenter = 1;

if(commandCenter ==1){
resetFlags();
printf("You look around the command center and can tell that it has been over ran with zombies.\n");
printf("There are piles of zombies piled up around the room.\n");
printf("As you examine the room you see a man sitting at one of the desks.\n");
while(sneak == 0 && talk == 0){
printf("[game prompt]: Would you like to walk up to the man or call out to him?\n");
resetFlags();
oneToken = 1;
yyparse();
}

if(sneak == 1){
printf("As you make you way towards him, he gets startled and shoots you.\n");
printf("You don't sneak up on someone during a zombie invasion... Game over.\n");
return 0;
}
else if (talk == 1){
//spaceToContinue();
printf("[%s]: Sir...\n", name);
 printf("The man's gaze snaps up at you.\n");
sleep(1);
printf("[???]: Have you been bitten?\n");
sleep(1);
printf("[%s]: No.. Have you seen Lieutenant Ryan?\n", name);
sleep(1);
printf("[%s]: It's urgent.\n", name);
sleep(3);
printf("[Lieutenant Ryan]: I'm Ryan. Who are you?\n");
sleep(1);
printf("[%s]: I'm %s. A scientist opened up my stasis pod by mistake.\n", name, name);
sleep(1);
printf("[%s]: He was pyschotic at that point but he said to find you.\n", name);
sleep(1);
printf("[%s]: He also said that we need to take this.. creature.. to Dr. Cohen?\n", name); 

//spaceToContinue();
printf("You point out the creature to Ryan.\n");
sleep(1);
printf("[%s]: It has been following me willingly ever since...\n", name);
sleep(3);
printf("Ryan walks towards the creature and the creature cowers behind your leg.\n");
sleep(1);
printf("[Ryan]: Huh, it must not like me.\n");
sleep(1);
printf("[Ryan]: Did he say anything else?\n");
sleep(1);
printf("[%s]: No.. Dr. Cohen should be in the room where my stasis pod was.\n", name);
sleep(3);
printf("[%s]: Apparently ours got mixed up.\n", name);
sleep(1);
printf("[Ryan]: If you know where he is, why didn't you open the pod?\n");
sleep(3);
printf("Ryan glances at your clothes..\n");
//spaceToContinue();
printf("[Ryan]: Seriously?..You're a janitor?\n");
sleep(1);
printf("[Ryan]: How did you make it here alive?\n");
sleep(1);
printf("[%s]: We don't have time for this..\n", name);
sleep(1);
printf("[Ryan]: You're right we should head back to the stasis pod now.. Which way would you suggest?\n");
sleep(1);
 }// end talk with ryan
if(barracks == 1){
printf("[%s]: I just came from the barracks and it's not safe. We should go through the cafeteria.\n", name);
barracks2 = 1;
}

if(barracks2 == 1){
printf("As you approach the cafeteria door, there is a window\n");
sleep(1);
while(leave == 0 && open ==0 && examine == 0){
printf("[game prompt]: would you like to look through the window or go through the door.\n");
resetFlags();
oneToken = 1;
yyparse();
}

if (leave == 1 || open == 1){
printf("You open the door and the three men hunched over the boody look back at you and just stare....\n");
sleep(1);
printf("You can see that their faces are covered in blood and one has portions of a liver sticking out of its mouth\n");
sleep(1);
printf("You see a door across the room and make a break for it\n");
sleep(1);
printf("The zombies seeing this begin to get up. One zombie slips in the pool of blood.\n");
printf("You make a run for it.");
sleep(1);
while(attack == 0 && run == 0){
printf("[game prompt]: you can swing at the two zombies or run faster\n");
resetFlags();
yyparse();
}
if (attack == 1) {
printf("You knock zombie into another zombie and they flip over the zombie who slipped in the blood\n");
}
else if (run == 1){
printf("You trip and die.. (anticlimactic, I know...)\n");
return 1;
}
}
else if (examine == 1) { 
printf("You look through the window and notice a body in the center of the room\n");
sleep(1);
printf("in a pool of blood. Three men are hunched around it\n");

printf("They appear to be eating something but you can't see enough from this angle\n");
sleep(1);
while(sneak == 0 && attack == 0){
printf("[game prompt]: Would you like to sneak past or make a run for it\n"); 
resetFlags();
yyparse();
}
if (sneak == 1) {
printf("You quietly open the door and crawl through the door.\n");
sleep(1);
printf("You take a closer look at the hunched men and notice that they're feeding on the man.\n");
sleep(1);
printf("You notice a meat cleaver on a counter\n");
sleep(1);
while(attack == 0 && sneak == 0){
printf("[game [prompt]: You can take them out or sneak by.\n");
resetFlags();
yyparse();
}
if (attack == 1){ 
printf("You're out numbered and die.\n");
return 1;
}
}
}
printf("A zombie notices you and runs at you.\n");
printf("The creature attacks the zombie and allows you and Ryan  to get to the door. \n");
} // end barracks
if(cafeteria ==1){
printf("[%s]: The cafeteria isn't a wise choice... Lets go through the barracks.\n", name);
cafeteria2 = 1;
}

if(cafeteria2 == 1){
printf("As you approach the barracks. You notice a stream of blood leading to the door.\n");
while(attack == 0 && sneak == 0 && run == 0){
printf("[game prompt]: would you like to charge in or sneak in?\n");
resetFlags();
yyparse();
}
 if (attack ==1 || run == 1) {
resetFlags();
printf("You rush in and take a zombie down with a few good blows to the chest. Two more zombies rush at you and you manage to dodge them.\n");
sleep(1);
printf("As you look around for an exit, you discover you're surrounded....\n");
sleep(1);
printf("Game over\n");
return 0;
}

else if(sneak == 1){
resetFlags();
printf("You see a room full of zombies. There is a machine gun laying on the ground.\n");
sleep(1);
while(pickUp == 0 && attack == 0){
printf("[game prompt]: Would you like to pick it up or go in swinging?\n");
resetFlags();
yyparse();
}
if(pickUp ==1){
printf("You obtain a machine gun!\n");
printf("You charge into the room while fireing randomly in all directions.\n");
printf("You spot an exit and start clearing a path to the door.\n");
printf("You run out of ammo and toss the gun on the ground and make a break for the door.\n");
 printf("A zombie charges at you!\n");
 printf("The bunny creature dives into the zombie and knocks it down.\n");
 printf("This allows you and Ryan to make it to the door.\n");
 printf("While the zombie is getting back up, the creature runs through the door.\n");
}

else if (attack ==1) {
printf("You rush in and take a zombie down with a few good blows to the chest. Two more zombies rush at you and you manage to dodge them.\n");
sleep(1);
printf("As you look around for an exit, you discover you're surrounded....\n");
sleep(1);
printf("Game over\n");
return 0;
 }}}// end cafe
sleep(1);
printf("[Ryan]: *sighs* That was close.\n");
sleep(1);
printf("[Ryan]: That creature has your back...\n");
sleep(1);
printf("[%s]: That's not the first time this little guy has pulled through for me.\n", name);
sleep(3);
printf("You bend down to pet the creature and he cuddles up to you and purrs.\n");
sleep(1);
printf("[Ryan]: Huh.. Okay, well lets keep going.\n");
sleep(1);
printf("[%s]: *silence*\n", name);
sleep(2);
printf("[Ryan]: What is it?\n");
sleep(1);
printf("[%s]: The scientist is still in there...\n", name);
sleep(2);
printf("[Ryan]: Between the two of us we should be fine.\n");
sleep(3);
 printf("Ryan opens the door slowly..\n");
sleep(1);
 printf("There is no sign of the zombie.\n");
sleep(1);
 printf("[Ryan]: He must be further in the room..\n");
sleep(1);
 printf("You make your way into the room and notice your mop from before.\n");
resetFlags();
while(pickUp == 0 && sneak == 0){
printf("[Game Prompt]: Would you like to pick up the mop or sneak through the room.\n");
resetFlags();
yyparse();
}
 if (sneak == 1){
   printf("You make your way slowly through the room.\n");
sleep(1);
   printf("As you walk through a row of stasis pods, the zombie grabs you.\n");
sleep(1);
   printf("Your creature is too far away to interject this time.\n");
sleep(1);
   printf("Game over....");
 }
 else if (pickUp == 1){
   printf("You grab your mop.\n");
sleep(1);
   printf("Just as you head towards the stasis pods, the zombie jumps out.\n");
sleep(1);
   printf("The zombie charges at you and you use the broom to shield yourself.\n");
sleep(2);
   printf("The broom ends up snapping, leaving a pointy edge.\n");
sleep(3);
   printf("You quickly stab the zombie...\n");
sleep(1);
   printf("The zombie falls to the floor.\n");
sleep(2);
   printf("Ryan quickly rushes over and snatches your broom.\n");
sleep(1);
   printf("He repeatedly stabs the zombie in the face before throwing the broom to the floor.\n");
sleep(3);
   printf("[Ryan]: *panting* You need to make sure you kill that one....\n");
sleep(1);
   printf("[%s]: We need to find Dr. Cohen..\n", name);
sleep(1);
   printf("You and Ryan end up finding and opening up Dr. Cohen's stasis pod.\n", name);
sleep(2);
   printf("You both fill him in on all that he has missed.\n");
sleep(1);
   printf("The three of you decide that a few tests on the bunny creature is neccessary to determine its importance.\n");
sleep(3);
   printf("Dr. Cohen goes to draw blood from the creature and it freaks out and attacks Ryan.");
sleep(2);
   printf("Ryan immediately reacts to the bite and passes out.\n");
sleep(1);
   printf("As you and Dr. Cohen try to wake him up, it dawns on you...\n");
sleep(1);
   printf("The creatures bite had the same effect on Ryan as the zombie bite did on the scientist that woke you up...\n");
sleep(1);
   printf("Without filling Dr. Cohen in on the discovery, you grab your mop.\n");

   resetFlags();
   while (creature == 0 && man == 0){
   printf("[Game Prompt]: Would you like to kill Dr. Cohen and Ryan so they don't kill the creature that has saved you multiple times\n");
   printf("or would you like to kill the bunny creature cowering in the corner because of the needle?\n");
     resetFlags();
     yyparse();
   }
     if(man == 1){
       printf("You stab Dr. Cohen with the jagged broom end before stabbing Ryan through the face.\n");
       sleep(1);
       printf("Together, you and the creature clear the zombies out of the base.\n");
       sleep(1);
       printf("There is enough food and resources for you to live comfortably.\n");
	 printf("Game over!\n");
       return 1;
     }
     else if(creature == 1){
       printf("You stab the creature.\n");
       sleep(1);
       printf("Dr. Cohen experiments on the dead creature and finds a cure.\n");
       sleep(1);       
printf("You start hunting down zombies and injecting them with the cure.\n");
       printf("Game OVer.");
       return 1;
     }
 }
 }// end command center
 }// end intense game
}// end main


int yyerror (const char *msg) {
	return fprintf (stderr, "YACC: %s\n", msg);
	}



















/**********************/
/*   Methods         */
/********************/
void lookAt(char* input1, char* input2){

if (input1 == "1" && input2 == "man"){
printf("The man appears to be in a hysterical state and is covered in blood that is coming from large gashes on his body..\n");
}

if (input1 == "1" && input2 == "creature"){
printf("The creature is furry, has the head and fur of a bunny, and the body of a small dog.\n");
}
}

void talkTo(char* input1, char* input2){
if (input1 == "1" && input2 == "man"){
}
}

void spaceToContinue(){
printf("[game prompt]: Please, press space to continue.\n");
while(getchar() != ' '){
sleep(100);
}
}

void resetFlags(){
  pickUp = 0;
attack = 0;
sneak = 0;
yesOrNo = 0;
enterOrExit = 0;
examine = 0;
leave = 0;
talk = 0;
object = 0;
man = 0;
creature = 0;
run = 0;
wait = 0;
oneToken = 0; 
}

