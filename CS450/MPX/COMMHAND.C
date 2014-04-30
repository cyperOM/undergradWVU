#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "mpx_supt.h"
#include "R1.h"
#include "R2.h"
#include "R3.h"
#include "R4.h"

void commhand(){
	int day,month,year,PCBclass,priority;
	unsigned int core;
	char commands[23][128],commandBreakDown[20][128];
	int Welcomelen,len, i,errorlen,parameterCheck;
	char Welcome[128], error[128];
	char userInput[128],prompt[128];
	int command;
	char History[128][128];
	int terminateint = 0;
	//Grabs all of the commands in the MPX system.
	makeCommandList(commands);
	print("Welcome to Combustible Lemonade\n");
	//Loop till termination
	while(terminateint == 0){
		readFile(prompt);
		print(prompt);
		len=129;
		command = -1;
		//Takes in user input
		sys_req(READ,TERMINAL,userInput, &len);
		
		//Null Terminates the string
		userInput[strlen(userInput)]= '\0';
		if(userInput[0]=='\n'){
			continue;
		}
		//Breaks down the userinput into an array
		BreakCommandApart(userInput,commandBreakDown);
		
		//Checks to see if the command is a valid command of the MPX system
		for(i=0;i<22;i++){
			//printf("1. %s  2. %s\n", commandBreakDown[0],commands[i]);
			if(strcmp(commandBreakDown[0],commands[i])==0){
				command =i;
				//printf("The command was %d",i);
			}
		}
		//Checks the parameters of the function that the user is attempting to run
		parameterCheck =checkParameters(commandBreakDown,commands);
		
		if(command != -1 && parameterCheck ==0){
			//Command was found but Parameters were incorrect
			command =-2;
		}
		
		switch(command){
			case -2:
				//Parameters were incorrect
				print("Error: The parameters are incorrect.\nPlease use Help function.\n");
				break;
			case -1:
				//Command not found
				print("Error: The command was not found within Combustible Lemonade.\nPlease use Help function.\n");
				userInput[0]= '\0';
				break;
			case 0:
				//Display Date Command
				DisplayDate();
				break;
			case 1:
				//Change Date Command
				day = atoi(commandBreakDown[1]);
				month = atoi(commandBreakDown[2]);
				year = atoi(commandBreakDown[3]);
				ChangeDate(day,month,year);
				break;
			case 2:
				//Display Directory Command
				DisplayDir();
				break;
			case 3:
				//Version Command
				Version();
				loadAllProcess();
				break;
			case 4:
				//Help Command
				if(strcmp(commandBreakDown[1],NULL)==0||strlen(commandBreakDown[1])==0){
					Help("none");
				}else{
					Help(commandBreakDown[1]);
				}
				break;
			case 5:
				//Terminate Command
				terminateint = Term();
				printNumber(terminateint);
				break;
			case 6:
				PCBclass = atoi(commandBreakDown[2]);
				priority = atoi(commandBreakDown[3]);
				createPCB(commandBreakDown[1], PCBclass, priority);
				break;
			case 7:
				//Delete PCB Command
				deletePCB(commandBreakDown[1]);
				break;
			case 8:
				blockPCB(commandBreakDown[1]);
				break;
			case 9:
				unblockPCB(commandBreakDown[1]);
				break;
			case 10:
				suspendPCB(commandBreakDown[1]);
				break;
			case 11:
				resumePCB(commandBreakDown[1]);
				break;
			case 12:
				priority = atoi(commandBreakDown[2]);
				setPCBpriority(commandBreakDown[1],priority);
				break;
			case 13:
				showPCB(commandBreakDown[1]);
				break;
			case 14:
				showAll();
				break;
			case 15:
				showReady();
				break;
			case 16:
				showBlocked();
				break;
			case 17:
				load_proc();
				break;
			case 18:
				//load_proc();
				//delay(3000);
				dispatch();
				break;
			case 19:
				ChangePrompt(commandBreakDown[1]);
				break;
			case 20:
				priority = atoi(commandBreakDown[2]);
				LoadProgram(commandBreakDown[1],priority);
				break;
			case 21:
				TerminateProcess(commandBreakDown[1]);
				break;
			default:
				//Default case to ensure no self destruction of program
				print("Error: Unknown\n");
				break;
		}
		//Checks to see if the MPX is leaking memory
		//Needs to be verified by user if the numbers are correct each time a command has been ran
		core = coreleft();
		printf("Core: %uld",core);
		print("\n");
	}
}

void makeCommandList(char commands[][128]){
//Creates a list of every possible command that the MPX system can preform
	strcpy(commands[0],"displayDate");
	strcpy(commands[1],"changeDate");
	strcpy(commands[2],"displayDir");
	strcpy(commands[3],"version");
	strcpy(commands[4],"help");
	strcpy(commands[5],"terminate");
	strcpy(commands[6],"createPCB");
	strcpy(commands[7],"deletePCB");
	strcpy(commands[8],"block");
	strcpy(commands[9],"unblock");
	strcpy(commands[10],"suspend");
	strcpy(commands[11],"resume");
	strcpy(commands[12],"setPriority");
	strcpy(commands[13],"showPCB");
	strcpy(commands[14],"showAll");
	strcpy(commands[15],"showReady");
	strcpy(commands[16],"showBlocked");
	strcpy(commands[17],"loadProc");
	strcpy(commands[18],"dispatch");
	strcpy(commands[19],"changePrompt");
	strcpy(commands[20],"loadProgram");
	strcpy(commands[21],"terminateProc");
}

void BreakCommandApart(char userInput[128], char command[][128]){
//Breaks down the user input into an array  
	int charCount,strCount,indexCount,c;
	strCount=0;
	charCount=0; 
	indexCount=0;
	//Goes through the user input one character at a time
	if(strstr(userInput,"(")!=NULL && strstr(userInput,");")!=NULL){
		while(charCount!=strlen(userInput)){
			//Checks to make sure that the character does not belong to the syntax of MPX
			if(userInput[charCount] == ' '){
				charCount++;
			}else{
				if(userInput[charCount] != '('&&userInput[charCount] != ')'&&userInput[charCount] != ','){
					command[strCount][indexCount] = userInput[charCount];
				}else{
					//Adds that character to the broken down array
					command[strCount][indexCount]= '\0';
					strCount++;
					indexCount=-1;
				}  
				charCount++;
				indexCount++;
			}
		}
	}
	//Terminates the strings, in order to not read in memory
	for(c =strCount;c<20;c++){
	command[c][0]= '\0';
  }
}

int checkParameters(char commandBreakDown[][128],char commands[][128]){
//Checks if the parameter count and type are correct
//Return 1 if correct, 0 if incorrect
	int count = checkParameterCount(commandBreakDown,commands);
	int type = checkParameterType(commandBreakDown,commands);
	//printf("type: %d count %d\n", type, count);
	if(count ==1 && type ==1){
		return 1;
	}else{
		return 0;
	}
}

int checkParameterCount(char commandBreakDown[][128],char commands[][128]){
//Checks to ensure that the correct number of parameters are inserted
//Return 1 if correct, 0 if incorrect
	int parameter[] = {0,3,0,0,0,0,3,1,1,1,1,1,2,1,0,0,0,0,0,1,2,1};
	int overLoad[] = {0,3,0,0,1,0,3,1,1,1,1,1,2,1,0,0,0,0,0,0,1,1};
	int i,z;
	int variableCount = 0;
	for(i=0;i<22;i++){
			//Checks to see if the command is valid
			if(strcmp(commandBreakDown[0],commands[i])==0){
				for(z =1;z<20;z++){
					//Checks to see how many parameters are in the user input
					if(strcmp(commandBreakDown[z],NULL)==0||strlen(commandBreakDown[z])==0){
						break;
					}else{
						variableCount++;
					}
				}
				//Checks to see if the number of parameters is acceptable
				if(variableCount==parameter[i] || variableCount==overLoad[i] ){
					//printf("%d \n",i);
					//printf("%d  %d\n",parameter[i],overLoad[i]);
					return 1;
				}else{
					return 0;
				}
			}
	}
	return 0;
}

int checkParameterType(char commandBreakDown[][128],char commands[][128]){
//Checks to see if parameters are correct.
//Return 1 if correct, 0 if incorrect
	char types[5]= {'x','x','x','x','x'};
	char overRide[5] = {'x','x','x','x','x'};
	char usertypes[5] = {'x','x','x','x','x'};
	int i;
	int isTypes = 0;
	getTypes(commandBreakDown[0],types,overRide);
	getUserTypes(commandBreakDown,usertypes);
	//No Parameters
	if(strlen(commandBreakDown[1])==0&&types[0]=='x'){
		return 1;
	}else{
		//checks the types of parameters
		i =0;
		//printf("%c\n%c\n%c\n",types[i],usertypes[i],overRide[i]);
		do{
			if(types[i]==usertypes[i]){
				isTypes= 1;
			}else if(usertypes[i]==overRide[i]){
				isTypes= 2;
			}else{
				return isTypes;
			}
			i++;
		}while(types[i]!=overRide[i]);
		
		for(i=0;i<6;i++){
			//printf("This is i: %d ",i);
			//printf("Types: %c  UserType: %c  Override: %c\n",usertypes[i],types[i],overRide[i]);
			if(isTypes==1){
				if(types[i]!=usertypes[i]){
					return 0;
				}
			}else if(isTypes==2){
				if(usertypes[i]!=overRide[i]){
					return 0;
				}
			}
		}
		return 1;
	}	
}

void getTypes(char command[128],char types[],char overRide[]){
// Gets the types for each command that needs parameters
// OverRide array is for commands with optional parameters 
// An 'i' indicates integer
// An 's' indicates string
// An 'x' indicates no parameter type found or Don't Care
	//changeDate Command
	if(strcmp(command,"changeDate")==0){
		types[0] = 'i';
		types[1] = 'i';
		types[2] = 'i';
	}
	//help Command
	else if(strcmp(command,"help")==0){
		types[0] = 's';
		overRide[0] = 'x';
	}else if(strcmp(command,"changePrompt")==0){
		types[0] = 's';
		overRide[0] = 'i';
	}else if(strcmp(command,"createPCB")==0){
		types[0] = 's';
		types[1] = 'i';
		types[2] = 'i';
	}else if(strcmp(command,"deletePCB")==0||strcmp(command,"terminateProc")==0||strcmp(command,"block")==0||strcmp(command,"unblock")==0||strcmp(command,"suspend")==0||strcmp(command,"resume")==0||strcmp(command,"showPCB")==0){
		types[0] = 's';
	}else if(strcmp(command,"setPriority")==0){
		types[0] = 's';
		types[1] = 'i';
	}else if(strcmp(command,"loadProgram")==0){
		types[0] = 's';
		types[1] = 'i';
		overRide[0] = 's';
	}
}

void getUserTypes(char commandBreakDown[][128],char usertypes[]){
// Checks the commandBreakDown (user input) for parameter types
// An 'i' indicates integer
// An 's' indicates string
// An 'x' indicates no parameter type found
	int i;
	int d = 0;
	int index = 0;
	int isNum;
	for(i=1;i<20;i++){
		isNum = 1;
		//If size is 0 then there is no type
		//print(commandBreakDown[i]);
		if(strlen(commandBreakDown[i])==0){
			break;
		}
		//if the commandBreakDown[i] is a number then 'i' is given
		while(commandBreakDown[i][d]!='\0'){
			if(isdigit(commandBreakDown[i][d])==0){
				isNum = 0;
				break;
			}
			d++;
		}
	    if(isNum==1){
			usertypes[index] = 'i';
		}
		//if the commandBreakDown[i] has quotes then 's' is given 
		else{
			usertypes[index] = 's';
		}
		// No parameter type found
		index++;
		
	}	
}

//SET A FLAG to disable methods
void disable(){

}