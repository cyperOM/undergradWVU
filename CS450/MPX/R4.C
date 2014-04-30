#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <dos.h>
#include "mpx_supt.h"
#include "R1.h"
#include "R2.h"
#include "R3.h"
#include "R4.h"

extern Queue *readyQueue;
extern Queue *blockedQueue;
extern Queue *suspendedReadyQueue;
extern Queue *suspendedBlockedQueue;

PCB* CurrentlyRunningProcess;
context* cp;
int start_offset_p;

void LoadProgram(char FileName[128], int priority){
	//Declaration of variables
	int error=0;
	int fileSize;
	start_offset_p = 0;
	//Checks priority ensure proper priority
	if(priority > -129 && priority < 128){
		//If PCB is not found
		if (findPCB(FileName)==NULL){
			//Checks the program
			error =  sys_check_program("C:\\MPX\\MPXFiles\\", FileName,&fileSize, &start_offset_p);
			if(error == 0){
				//Sets up the PCB with the file name and priority
				if(strcmp(FileName,"IDLE")==0){
					CurrentlyRunningProcess = setupPCB(FileName,0,priority);
				}else{
					CurrentlyRunningProcess = setupPCB(FileName,1,priority);
				}
				//Sets the PCB Memory for context switch
				SetPCBMemoryValues(fileSize);
				//Loads the program
				error = sys_load_program(CurrentlyRunningProcess->load_address,CurrentlyRunningProcess->memory_size,"C:\\MPX\\MPXFiles\\",FileName);
				if(error == 0){
					//If no errors our add to readyQueue with priority sorting
					//print("Program Loaded Successfully!\n");
					PriorityEnqueue(readyQueue,CurrentlyRunningProcess);
					fileSize =0;
				}else if(error == -117){
					//File Not Found Error
					//print("Error: File Not Found\n");
					fileSize =0;
				}else{
					//Error with loading the program
					//print("Error: Loading Program: ");
					//printNumber(error);
					fileSize =0;
				}
			}else if(error == -117){
				//File Not Found Error
				print("File Not Found\n");
				fileSize =0;
			}else{
				//Error with checking the program
				print("Error Checking Program: \n");
				printNumber(error);
				fileSize =0;
			}
		}else{
			//Error Same PCB Name
			print("Error: PCB already created with the same name\n");
		}
	}else{
		//Error Incorrect Priority
		print("Error: Incorrect Priority\n");
	}	
}

void SetPCBMemoryValues(int fileSize){
	//Sets the memory size of the PCB to the size of File
	CurrentlyRunningProcess->memory_size= fileSize;
	//sets the load address of the PCB
	CurrentlyRunningProcess->load_address = (unsigned char*)sys_alloc_mem(CurrentlyRunningProcess->memory_size);
	//sets the execution address of the PCB
	CurrentlyRunningProcess->exec_address = CurrentlyRunningProcess->load_address + start_offset_p;
	//Sets the context equal to the Currently Running Process's stack and sets the context up for a context switch
	cp = (context*)CurrentlyRunningProcess->stack;
	cp -> IP = FP_OFF(CurrentlyRunningProcess->exec_address);
	cp -> CS = FP_SEG(CurrentlyRunningProcess->exec_address);
	cp -> FLAGS = 0x200;
	cp -> DS = _DS;
	cp -> ES = _ES;
}
//Removes PCB from System
void TerminateProcess(char processName[128]){
	PCB *pcbPointer;
	//Finds the PCB's location
	pcbPointer = findPCB(processName);
	if(pcbPointer==NULL){
		//Error cannot find process
		print("Error: Process is not in the MPX System.\n");
	}else if(pcbPointer->PCBclass==0){
		//Error cannot remove system process
		print("Error: Process is a System Process and cannot be removed.\n");
	}else{
		//Deletes PCB
		removePCB(pcbPointer);
		freePCB(pcbPointer);
	}
}

void loadAllProcess(){
	//Use version to load all processes
	LoadProgram("CPUCOM",0);
	LoadProgram("CPUTERM",0);
	LoadProgram("IOCOM25",0);
	LoadProgram("IOMULTI",0);
	LoadProgram("IOTERM",0);
	LoadProgram("IOCOM",0);
}
void LoadIdle(){
	LoadProgram("IDLE",-127);
	
}