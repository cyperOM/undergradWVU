#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <dos.h>
#include "mpx_supt.h"
#include "R1.h"
#include "R2.h"
#include "R3.h"
#include "R4.h"
#include "procs-r3.h"
#define SYS_STACK_SIZE 1024

//external queues from r2.c
extern Queue *readyQueue;
extern Queue *blockedQueue;
extern Queue *suspendedReadyQueue;
extern Queue *suspendedBlockedQueue;
IOCB *TerminalQueue;
IOCB *Com_PortQueue;

//Temporary storage for ss and sp
unsigned short ss_save;
unsigned short sp_save;
unsigned short new_ss;
unsigned short new_sp;
unsigned short ss_save2;
unsigned short sp_save2;

PCB *COP; //Currently Running Process
char sys_stack[SYS_STACK_SIZE]; //stack for context switch
context *aContext; //Context switch variable
params *param_ptr; //New parameters for process
	
void interrupt dispatch(){

	if(ss_save == NULL){
		ss_save = _SS;
		sp_save = _SP;
	}
	COP = dequeueFromNode(readyQueue);
	if(COP == NULL){
		new_ss = ss_save;
		new_sp = sp_save;
		sp_save = NULL;
		ss_save = NULL;
	}else{
		COP->state=1;
		//Sets _ss and _sp to the cops stack
		new_ss = FP_SEG(COP->stack);
		new_sp = FP_OFF(COP->stack);
	}
	_SS = new_ss;
	_SP = new_sp;
}

void load_proc() { 
	//Creates Process1 that contains the test process test1_R3 then adds it to the ready readyQueue
	if (findPCB("Process1")==NULL){
		COP = setupPCB("Process1", 1 ,0); 
		aContext = (context*)COP -> stack;
		aContext -> IP = FP_OFF(&test1_R3);
		aContext -> CS = FP_SEG(&test1_R3);
		aContext -> FLAGS = 0x200;
		aContext -> DS = _DS;
		aContext -> ES = _ES;
		PriorityEnqueue(readyQueue, COP);
		print("Process1 is now Loaded!\n");
	}else{
		//Error PCB is already in system
		print("Error: PCB with the name 'Process1' is already in system\n");	
	}
	//Creates Process2 that contains the test process test2_R3 then adds it to the ready readyQueue
	if(findPCB("Process2")==NULL){
		COP = setupPCB("Process2", 1 ,0); 
		aContext = COP -> stack;
		aContext -> IP = FP_OFF(&test2_R3);
		aContext -> CS = FP_SEG(&test2_R3);
		aContext -> FLAGS = 0x200;
		aContext -> DS = _DS;
		aContext -> ES = _ES;
		PriorityEnqueue(readyQueue, COP); 
		print("Process2 is now Loaded!\n");
	}else{
		//Error PCB is already in system
		print("Error: PCB with the name 'Process2' is already in system\n");	
	}
	
	//Creates Process3 that contains the test process test3_R3 then adds it to the ready readyQueue
	if(findPCB("Process3")==NULL){
		COP = setupPCB("Process3", 1 ,0); 
		aContext = COP -> stack;
		aContext -> IP = FP_OFF(&test3_R3);
		aContext -> CS = FP_SEG(&test3_R3);
		aContext -> FLAGS = 0x200;
		aContext -> DS = _DS;
		aContext -> ES = _ES;
		PriorityEnqueue(readyQueue, COP);
		print("Process3 is now Loaded!\n");		
	}else{
		//Error PCB is already in system
		print("Error: PCB with the name 'Process3' is already in system\n");	
	}
	
	//Creates Process4 that contains the test process test4_R3 then adds it to the ready readyQueue
	if(findPCB("Process4")==NULL){
	COP = setupPCB("Process4", 1 ,0); 
		aContext = COP -> stack;
		aContext -> IP = FP_OFF(&test4_R3);
		aContext -> CS = FP_SEG(&test4_R3);
		aContext -> FLAGS = 0x200;
		aContext -> DS = _DS;
		aContext -> ES = _ES;
		PriorityEnqueue(readyQueue, COP); 
		print("Process4 is now Loaded!\n");
	}else{
		//Error PCB is already in system
		print("Error: PCB with the name 'Process4' is already in system\n");	
	}
	
	//Creates Process5 that contains the test process test5_R3 then adds it to the ready readyQueue
	if(findPCB("Process5")==NULL){
		COP = setupPCB("Process5", 1 ,0); 
		aContext = COP -> stack;
		aContext -> IP = FP_OFF(&test5_R3);
		aContext -> CS = FP_SEG(&test5_R3);
		aContext -> FLAGS = 0x200;
		aContext -> DS = _DS;
		aContext -> ES = _ES;
		PriorityEnqueue(readyQueue, COP);
		print("Process5 is now Loaded!\n");
	}else{
		//Error PCB is already in system
		print("Error: PCB with the name 'Process5' is already in system\n");
		
	}
 }
 
void interrupt sys_call(void){

	static IOD *temp;
	
	//Saves the ss and sp register to a temp storage
	ss_save2 = _SS;
	sp_save2 = _SP;
	COP->stack = (unsigned char *)MK_FP(ss_save2,sp_save2);
	//Updates the stacktop for context switch
	
	
	//Access Parameters that sys_req placed on the stack
	param_ptr = (params*)(COP->stack+sizeof(context));
	
	//Switching to a temp stack
	new_ss = FP_SEG(sys_stack);
	new_sp = FP_OFF(sys_stack)+ SYS_STACK_SIZE;
	_SS = new_ss;
	_SP = new_sp;
	
	trm_getc();
	
	if(TerminalQueue->event_flag==1 && TerminalQueue->count > 0){
		TerminalQueue->event_flag = 0;
		temp = TerminalQueue->head;
		TerminalQueue->head = TerminalQueue->head->next;
		unblockPCB(temp->ppt->processName);
		sys_free_mem(temp);
		TerminalQueue->count--;
		if(TerminalQueue->count!=0){
			ProcessIORequest(1);
		}
	}
	if(Com_PortQueue->event_flag==1 && Com_PortQueue->count > 0){
		Com_PortQueue->event_flag = 0;
		temp = Com_PortQueue->head;
		Com_PortQueue->head = Com_PortQueue->head->next;
		unblockPCB(temp->ppt->processName);
		sys_free_mem(temp);
		Com_PortQueue->count--;
		if(Com_PortQueue->count!=0){
			ProcessIORequest(1);
		}
	}
	
	//Process is idle
	if (param_ptr -> op_code == IDLE){
		//Sets state to running and adds it back into the readyQueue
		COP -> state = 2; 
		PriorityEnqueue(readyQueue,COP);
	}
	//Process is completed and ready for deletion 
	if (param_ptr -> op_code == EXIT){
		//Frees the memory of the PCB and sets the PCB to NULL
		freePCB(COP);
		COP = NULL;
	}
	if (param_ptr -> op_code == READ ||param_ptr -> op_code == WRITE ||param_ptr -> op_code == CLEAR ||param_ptr -> op_code == GOTOXY ){
		IOScheduler();
	}
	//Dispatches the next process in the readyQueue
	dispatch();
}
void InstallCommhand(){
	if (findPCB("COMMHAND")==NULL){
		COP = setupPCB("COMMHAND", 0 ,127); 
		aContext = (context*)COP -> stack;
		aContext -> IP = FP_OFF(&commhand);
		aContext -> CS = FP_SEG(&commhand);
		aContext -> FLAGS = 0x200;
		aContext -> DS = _DS;
		aContext -> ES = _ES;
		PriorityEnqueue(readyQueue, COP);
	}else{
		//Error PCB is already in system
		print("Error: PCB with the name 'COMMHAND' is already in system\n");	
	}
}
void IOScheduler(){
	if(param_ptr->device_id==TERMINAL||param_ptr->device_id==COM_PORT){
		IOD *Descriptor = sys_alloc_mem(sizeof(IOD));
		Descriptor->ppt = COP;
		strcpy(Descriptor->PCBName,COP->processName);
		Descriptor->buffer = param_ptr->buf_addr;
		Descriptor->count = param_ptr->count_addr;
		Descriptor->requestType = param_ptr->op_code;
		if(param_ptr->device_id==TERMINAL){
			if(TerminalQueue-> count == 0){
				TerminalQueue->head = Descriptor;
				TerminalQueue->tail = Descriptor;
				TerminalQueue->count = 1;
				ProcessIORequest(1);
			}else{
				TerminalQueue->tail->next = Descriptor;
				TerminalQueue->tail = TerminalQueue->tail->next;
				TerminalQueue->count++;
			}
		}else if(param_ptr->device_id==COM_PORT){
			if(Com_PortQueue-> count == 0){
				Com_PortQueue->head = Descriptor;
				Com_PortQueue->tail = Descriptor;
				Com_PortQueue->count = 1;
				ProcessIORequest(2);
			}else{
				Com_PortQueue->tail->next = Descriptor;
				Com_PortQueue->tail = Com_PortQueue->tail->next;
				Com_PortQueue->count++;
			}
		}
		COP->state = 0;
		insertPCB(COP);
		//blockPCB(COP->processName);
	}
}
void CreateIOCB(){
	TerminalQueue = sys_alloc_mem(sizeof(IOCB));
	Com_PortQueue = sys_alloc_mem(sizeof(IOCB));
	trm_open(&TerminalQueue->event_flag);
	com_open(&Com_PortQueue->event_flag,1200);
}

void ProcessIORequest(int device){
	if(device == 1){
		if(TerminalQueue->head->requestType == READ){
			trm_read(TerminalQueue->head->buffer, TerminalQueue->head->count);
		}else if(TerminalQueue->head->requestType == WRITE){
			trm_write(TerminalQueue->head->buffer, TerminalQueue->head->count);
		}else if(TerminalQueue->head->requestType == CLEAR){
			trm_clear();
		}else if(TerminalQueue->head->requestType == GOTOXY){
			trm_gotoxy(0,0);
		}
	}else if(device == 2){
		if(Com_PortQueue->head->requestType == READ){
			com_read(Com_PortQueue->head->buffer, Com_PortQueue->head->count);
		}else if(Com_PortQueue->head->requestType == WRITE){
			com_write(Com_PortQueue->head->buffer, Com_PortQueue->head->count);
		}
	}
}
void DeleteAllTheThings(){
	IOD *temp;
	while(TerminalQueue->head !=NULL){
		temp = TerminalQueue->head;
		sys_free_mem(temp);
		TerminalQueue->head = TerminalQueue->head->next;
	}
	TerminalQueue->tail = NULL;
	
	while(Com_PortQueue->head !=NULL){
		temp = Com_PortQueue->head;
		sys_free_mem(temp);
		Com_PortQueue->head = Com_PortQueue->head->next;
	}
	Com_PortQueue->tail = NULL;
	sys_free_mem(TerminalQueue);
	sys_free_mem(Com_PortQueue);
}
void Cleanup(){
	trm_close();
	com_close();
	DeleteAllQueues();
	DeleteAllTheThings();
}
void IODCleanup(int device){
	if(device == 1){
		TerminalQueue->head = TerminalQueue->head->next;
		TerminalQueue->count--;
		if(TerminalQueue->head == NULL && TerminalQueue->count == 0){
			TerminalQueue->tail = NULL;
		}
	}
	if(device == 2){
		Com_PortQueue->head = Com_PortQueue->head->next;
		Com_PortQueue->count--;
		if(Com_PortQueue->head == NULL && Com_PortQueue->count == 0){
			Com_PortQueue->tail = NULL;
		}
	}
}