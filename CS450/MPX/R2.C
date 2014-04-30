#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "mpx_supt.h"
#include "R2.h"
#include "R3.h"
#include "R4.h"
#define SYS_STACK_SIZE 1024

#define SYS 0
#define APP 1

//Global declarations of Queues 
extern Queue *readyQueue;
extern Queue *blockedQueue;
extern Queue *suspendedReadyQueue;
extern Queue *suspendedBlockedQueue;
extern PCB *COP;

//Sets up the PCB by setting the structure to the information necessary 
PCB *setupPCB(char* processName, int PCBclass, int priority){
    PCB *newPCB = allocatePCB();
	strcpy(newPCB->processName, processName);
	newPCB->PCBclass = PCBclass;
	newPCB->priority = priority;
	newPCB->suspended = 0;
	newPCB->state = 2;
	return newPCB;
}
//Allocates the memory for the PCB
PCB *allocatePCB(char* processName){
	PCB *thePcb;
	thePcb = sys_alloc_mem(sizeof(PCB));
	if(strcmp(processName,"COMMHAND")==0){
		thePcb->stackBottom = (unsigned char*)sys_alloc_mem(4096);
	}else{
		thePcb->stackBottom = (unsigned char*)sys_alloc_mem(1024);
	}
	thePcb->stack = thePcb->stackBottom+SYS_STACK_SIZE-sizeof(context);
	return thePcb;
}

//Frees the memory of the PCB
void freePCB(PCB *pcb){
	sys_free_mem(pcb);
	sys_free_mem(pcb->stackBottom);
}

PCB *findPCB(char PCBName[20]){
	PCB *pointerPCB;
	Node *current;
	int PCBLoc = PCBLocation(PCBName);
	if (PCBLoc==1){
		current = readyQueue->head;
	}
	else if (PCBLoc==2){
		current = blockedQueue->head;
	}
	else if (PCBLoc==3){
		current = suspendedReadyQueue->head;
	}
	else if (PCBLoc==4){
		current = suspendedBlockedQueue->head;
	}
	if(PCBLoc ==0){
		return NULL;
	}
		while(current!=NULL){
			if(strcmp(current->pcb->processName,PCBName)==0){
				pointerPCB = current->pcb;
			}
			current = current->next;
		}
		if(pointerPCB==NULL){
			print("Error: PCB Not Found\n");
		}
		return pointerPCB;
} 

void insertPCB(PCB *pcb){
	char error[128];
	int len;
	if(pcb->suspended == 1 && pcb->state == 0){
		PriorityEnqueue(suspendedBlockedQueue,pcb);
	}
	else if(pcb->suspended == 1 && pcb->state == 2){
		PriorityEnqueue(suspendedReadyQueue,pcb);
	}
	else if(pcb->suspended == 0 && pcb->state == 0){
		PriorityEnqueue(blockedQueue,pcb);
	}
	else if(pcb->suspended == 0 && pcb->state == 2){
		PriorityEnqueue(readyQueue,pcb);
	}
	else {
		strcpy(error, "Error: Doesn't belong to queue\n");
		len = strlen(error);
		sys_req(WRITE,TERMINAL,error, &len);
	}
}

void removePCB(PCB *pcb){
	if(pcb->suspended == 0 && pcb->state == 2){
		DequeueName(readyQueue, pcb->processName);
	}
	else if(pcb->suspended == 0 && pcb->state == 0){
		DequeueName(blockedQueue,pcb->processName);
	}
	else if(pcb->suspended == 1 && pcb->state == 2){
		DequeueName(suspendedReadyQueue,pcb->processName);
	}
	else if(pcb->suspended == 1 && pcb->state == 0){
		DequeueName(suspendedBlockedQueue,pcb->processName);
	}
	else {
		print("Error: Not in a queue\n");
	}
}

//Creates the PCB
void createPCB(char* processName, int PCBclass, int priority){
	char error[128];
	int len;
	PCB *pcbPointer;
	if (strlen(processName) > 8){
		//Name is too long
		print("Error: Incorrect Name to Many Characters \n");
	}else if (priority > 127){
		//Wrong priority
		print("Error: Incorrect Priority\n");
	}else if(priority < -128){
		//Wrong priority
		print("Error: Incorrect Priority\n");
	}else if ( PCBclass != 0 && PCBclass != 1){
		//Wrong Class
		print("Error: Incorrect Class\n");
	}else if (findPCB(processName)==NULL){
		//If no duplicate name
		pcbPointer = setupPCB(processName, PCBclass, priority);
		PriorityEnqueue(readyQueue,pcbPointer);
	}else{
		//If there is a duplicate name
		print("Error: Name is already in use.\n");
	}
}

//Tester Function
void QueueTest(){
	createPCB("THisisPCB7", 0, 4);
	createPCB("THisisPCB4", 0, 3);
	createPCB("THisisPCB5", 0, 5);
	//createPCB("THisisPCB2", 0, 1);
	//DequeueName(readyQueue,"THisisPCB3");
	printQueue(readyQueue);
}

void blockPCB(char PCBName[20]){
	PCB *process;
	int sus;//suspended
	int sta;//state
	process = findPCB(PCBName); //find PCB
	if(process==NULL){
		print("Error: PCB not found");	
	}
	else{//if found PCB
		//check state
		sus = (process->suspended);
		sta = (process->state);
		if(sta==0){//state is already blocked...
			print("Error: State is already blocked");
		}
		else if(sta==2){ //if PCB state is unblocked		
		
			if((sus==0)){ //if not suspended or suspended
			
				removePCB(process);//remove from readyQueue
				process->state = 0;//change state to blocked
				PriorityEnqueue(blockedQueue, process);//insert into blockedQueue

			}
			else if((sus==1)){
				removePCB(process);//remove from suspendedReadyQueue
				process->state = 0;//change state to blocked
				PriorityEnqueue(suspendedBlockedQueue, process);//insert into suspendedBlockedQueue

			}
			else{ //if invalid state
				print("Error suspended State is invalid");
			}
				
		}
		else if(sta==1){//if state is running		
			print("Error: PCB is running");		
		}
		else{
			print("Error: Suspended state invalid");
		}
	}
	
}

void unblockPCB(char PCBName[20]){
	PCB *process;
	int sus;//suspended
	int sta;//state
	process = findPCB(PCBName); //find PCB
	if(process==NULL){
		print("Error: PCB not found");	
	}
	else{//if found PCB
		//check state
		sus = (process->suspended);
		sta = (process->state);
		if(sta==2){//state is not blocked...
			print("Error: State is already blocked");
		}
		else if(sta==0){ //if PCB state is blocked		
		
			if((sus==0)){ //if not suspended or suspended
			
				removePCB(process);//remove from blockedQueue
				process->state = 2;//change state to unblocked
				PriorityEnqueue(readyQueue, process);//insert into readyQueue
			}
			else if((sus==1)){
				removePCB(process);//remove from suspendedBlockedQueue
				process->state = 2;//change state to unblocked
				PriorityEnqueue(suspendedReadyQueue, process);//insert into suspendedReadyQueue
			}
			else{ //if invalid state
				print("Error suspended State is invalid");
			}
				
		}
		else if(sta==1){//if state is running		
			print("Error: PCB is running");		
		}
		else{
			print("Error: Suspended state invalid");
		}
	}
}

void suspendPCB(char PCBName[20]){
	PCB *process;
	int sus;//suspended
	int sta;//state
	process = findPCB(PCBName); //find PCB
	if(process==NULL){
		print("Error: PCB not found");	
	}
	else{//if found PCB
	//check sus state
		sus = (process->suspended);
		sta = (process->state);
		if(sus==0){//if PCB is not suspended 
		
			if((sta==0)){ //if state is blocked
			
				removePCB(process);//remove from blockedQueue
				process->suspended = 1;//change state to suspended
				insertPCB(process);//insert into suspendedBlockedQueue

			}
			else if((sta==2)){
				removePCB(process);//remove from readyQueue
				process->suspended = 1;//change state to suspended
				insertPCB(process);//insert into suspendedReadyQueue

			}
			else if(sta==1){//if state is running		
				print("Error: PCB is running");		
			}
			else{//if state is invalid
				print("Error: PCB state is invalid");
			}
		}
		else if(sus==1){
			print("Error: Process is already suspended");
		}
		else{
			print("Error: Suspended state invalid");
		}
	}
}

void resumePCB(char PCBName[20]){
	PCB *process;
	int sus;//suspended
	int sta;//state
	process = findPCB(PCBName); //find PCB
	if(process==NULL){
		print("Error: PCB not found");	
	}
	else{//if found PCB
	//check sus state
		sus = (process->suspended);
		sta = (process->state);
		if(sus==1){//if PCB is suspended 
			if((sta==0)){ //if state is blocked
				removePCB(process);//remove from suspendedblockedQueue
				process->suspended = 0;//change state to not suspended
				insertPCB(process);//insert into blockedQueue
			}
			else if((sta==2)){
				removePCB(process);//remove from suspendedReadyQueue
				process->suspended = 0; //change state to not suspended
				insertPCB(process);//insert into readyQueue
			}
			else if(sta==1){//if state is running		
				print("Error: PCB is running");		
			}
			else{//if state is invalid
				print("Error: PCB state is invalid");
			}
		}
		else if(sus==0){
			print("Error: Process is already resumed");
		}else{
			print("Error: Suspended state invalid");
		}
	}
}

//Deletes the PCB from the MPX system
void deletePCB(char PCBName[20]){
	PCB *pcbPointer;
	pcbPointer = findPCB(PCBName);
	removePCB(pcbPointer);
	freePCB(pcbPointer);
}

void setPCBpriority(char PCBName[20], int inputPriority){
	PCB *process = findPCB(PCBName); //find PCB
	if((inputPriority < -128) || (inputPriority > 127)){ 
		//if incorrect input priority
		print("Error: Invalid Priority: Must be between -128 and +127\n");
	}
	else if(process==NULL){
		print("Error: PCB not found\n");
	}
	else{ 
		removePCB(process);
		process -> priority = inputPriority;
		insertPCB(process);
		
	}
}

void showPCB(char PCBName[20]){
	char printArray[10000];
	PCBToString(PCBName,printArray);
	print(printArray);
}
void PCBToString(char PCBName[20],char printArray[10000]){
		//shows the contains of the certain PCB that was inserted
	
	int pri; //priority
	int sus; //suspend
	int sta; //states
	int class1; 
	char statement[128]; 
	int len;
	char susWord[128];
	char staWord[128];
	char classWord[128];
	PCB *pcbPointer;
	char priString[4];
	
	//find all the properties and initializing to variables
	pcbPointer = findPCB(PCBName);
	pri = pcbPointer->priority;
	sus = pcbPointer->suspended;
	sta = pcbPointer->state;
	class1 =pcbPointer->PCBclass;
	
	//sets all numbers to the current associated words
	if(sus==0){
		strcpy(susWord,"Resumed");
	}
	else if(sus==1){
		strcpy(susWord,"Suspended");
	}
	else{
		strcpy(susWord,"No sus State found");
	}
	
	if(sta==0){
		strcpy(staWord,"Blocked");
	}
	else if(sta==1){
		strcpy(staWord,"Running");
	}
	else if(sta==2){
		strcpy(staWord,"Ready");
	}
	else{
		strcpy(staWord,"No state found");
	}
	
	if(class1==0){
		strcpy(classWord,"System");
	}
	else if(class1==1){
		strcpy(classWord,"Application");
	}
	else{
		strcpy(classWord,"No Class found");
	}
	sprintf(priString,"%d",pri);
	
	//prints to terminate
	strcpy(printArray,"Name: ");
	strcat(printArray,PCBName);
	strcat(printArray," \nClass: ");
	strcat(printArray,classWord);
	strcat(printArray," \nPriority: ");
	strcat(printArray,priString);
	strcat(printArray," \nSuspended: ");
	strcat(printArray,susWord);
	strcat(printArray," \nStates: ");
	strcat(printArray,staWord);
	strcat(printArray,"\n\n");
	//print(printArray);
}
void showReady(){
	//shows the ready state and also suspended ready state
	
	Queue *q1=readyQueue;
	Queue *q2=suspendedReadyQueue;
	Node *current;
	Node *currentSus;
	PCB *process;
	char PCBName[20];
	char line[10000];
	char temp[10000];
	int len;
	
	current = q1->head;
	currentSus = q2->head;
	
	strcpy(line,"\n");
	
	//prints if there are not PCBs that are ready state or suspended ready state
	if(currentSus==NULL && current==NULL){
		strcat(line,"No PCBs are in the Ready or Suspended Ready state\n");
	}
	
	//prints all ready states
	if(current!=NULL){
		strcat(line,"Ready State:\n");
		while(current!=NULL){
			process = current ->pcb;
			strcpy(PCBName,process->processName);
			PCBToString(PCBName,temp);
			strcat(line,temp);
			current = current->next;
		}
	}
	
	//prints all blocked states
	if(currentSus!=NULL){
		strcat(line,"Ready Suspended State:\n");
		while(currentSus!=NULL){
			process = currentSus ->pcb;
			strcpy(PCBName,process->processName);
			PCBToString(PCBName,temp);
			strcat(line,temp);
			currentSus = currentSus->next;
		}
	}
	pageinate(line);
}

void showBlocked(){
	//shows blocked state and suspended blocked state
	
	Queue *q1=blockedQueue;
	Queue *q2=suspendedBlockedQueue;
	Node *current;
	Node *currentSus;
	PCB *process;
	char PCBName[20];
	char line[10000];
	char temp[10000];
	
	current = q1->head;
	currentSus = q2->head;
	
	strcpy(line,"\n");
	
	//
	if(currentSus==NULL && current==NULL){
		print("No PCBs are in the Blocked or Suspended Blocked state\n");
	}
	
	//prints all blocked states
	if(current!=NULL){
		strcat(line,"Blocked States:\n");
		while(current!=NULL){
			process = current ->pcb;
			strcpy(PCBName,process->processName);
			PCBToString(PCBName,temp);
			strcat(line,temp);
			current = current->next;
		}
	}
	
	//prints all suspended blocked states
	if(currentSus!=NULL){
		strcat(line,"Suspended Blocked States:\n");
		while(currentSus!=NULL){
			process = currentSus ->pcb;
			strcpy(PCBName,process->processName);
			PCBToString(PCBName,temp);
			strcat(line,temp);
			currentSus = currentSus->next;
		}
	}
	//pageinate needs to go here
	pageinate(line);
}

void showAll(){
	//shows all the states 
	
	//prints the ready/blocked state and suspended ready/blocked state
	showReady();
	showBlocked();
	
	if(COP!=NULL){
		print("Running PCB:\n");
		showPCB(COP->processName);
	}
	
	else{
		print("No Running PCBs\n");
	}
	
}
//Wrapper function for sys_rec
void print(char print[1700]){
	int len;
	len = strlen(print);
	sys_req(WRITE,TERMINAL,print, &len);
}

void printNumber(int number){
	char numbers[128];
	itoa(number, numbers, 10);
	print(numbers);
}

//Creates pagination for the MPX system
void pageinate(char printline[10000]){
	char page[1700] = {'\0'};
	char userInput[128];
	int len = 129;
	int newlineCount = 0;
	int i=0;
	int printIndex = 0;
	while(i<1700){
		//First page
		if(printline[printIndex] == '\n'){
			newlineCount++;
		}if(newlineCount ==22){
			break;
		}
		page[i] = printline[printIndex];
		i++;
		printIndex++;
	}
	print(page);
	while(i < strlen(printline)){
		print("\nPress enter key to continue....");
		sys_req(READ,TERMINAL,userInput, &len);
		//Asks for user input
		if(strlen(userInput)!=0){
			//Next Page
			i = 0;
			newlineCount = 0;
			while(i<1700){
				if(printline[printIndex] == '\n'){
					newlineCount++;
				}if(newlineCount ==22){
					break;
				}
				page[i] = printline[printIndex];
				i++;
				printIndex++;
			}
			page[i+1] = '\0';
			print(page);
		}
	}
}