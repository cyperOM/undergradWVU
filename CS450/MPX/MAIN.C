#include "R1.h"
#include "R2.h"
#include "R3.h"

#include "mpx_supt.h"
#include "R5.H"

extern Queue *readyQueue;
extern Queue *blockedQueue;
extern Queue *suspendedReadyQueue;
extern Queue *suspendedBlockedQueue;
extern DCB *COM1;
extern IOCB *IOScheduleQueue;

void main(){
	//Starts the MPX system
	sys_init(MODULE_F);
	sys_set_vec(sys_call);
	CreateQueue();
	CreateDCB();
	CreateIOCB();	
	InstallCommhand();
	LoadIdle();
	//showAll();
	//delay(4000);
	dispatch();
	Cleanup();
	printf("Goodbye\n");
	delay(2000);
	sys_exit();
}
