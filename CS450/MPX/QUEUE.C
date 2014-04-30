#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "mpx_supt.h"
#include "R2.h"

Queue *readyQueue;
Queue *blockedQueue;
Queue *suspendedReadyQueue;
Queue *suspendedBlockedQueue;
extern PCB *COP;

//Adds to queue
void enqueue(Queue *q1, PCB *pcb){ 
  Node *newPtr;
  int len;
  char error[128];
  newPtr = sys_alloc_mem(sizeof(Node)); 
  
  if(newPtr != NULL){	
    newPtr->pcb= pcb;
    newPtr->next = NULL;
    if(q1->count==0){ 
		q1->head = newPtr;
    }else{
		q1->tail->next = newPtr;
    }
	q1->tail = newPtr;
	q1->tail->next = NULL;
    q1->count++;
	//print("Name: ");
	//print(pcb->processName);
	//print(" added.\n");
  }else{
	print( "Error: Insufficient Memory\n");
  }
}

//Removes Head
Node *dequeue(Queue *q1){
  Node *current;
  if(q1->count==0){
	return NULL;
  }
  current = q1->head;
  q1->head = q1->head->next;
  q1->count--;
  if(q1->count==0){
	q1->tail = NULL;
  }
  return current;
}
PCB *dequeueFromNode(Queue *q1){
	 Node *current = dequeue(q1);
	 PCB *tempPCB;
	 if(current == NULL){
		return NULL;
	 }else{
		tempPCB = current->pcb;
		//return (current == NULL) ? NULL : current->pcb;
		freeNode(current);
		return tempPCB;
	}
}

void freeNode(Node *node){
	sys_free_mem(node);
}

PCB *dequeueNameFromNode(Queue *q1,char PCBName[20]){
	 Node *current = DequeueName(q1,PCBName);
	 return current->pcb;
}

//Allocates memory for all of the Queues in the MPX system
void CreateQueue(){
	readyQueue=sys_alloc_mem(sizeof(Queue));
	readyQueue->head = readyQueue->tail = NULL;
	readyQueue->count = 0;
	blockedQueue=sys_alloc_mem(sizeof(Queue));
	blockedQueue->head = blockedQueue->tail = NULL;
	blockedQueue->count = 0;
	suspendedReadyQueue=sys_alloc_mem(sizeof(Queue));
	suspendedReadyQueue->head = suspendedReadyQueue->tail = NULL;
	suspendedReadyQueue->count = 0;
	suspendedBlockedQueue=sys_alloc_mem(sizeof(Queue));
	suspendedBlockedQueue->head = suspendedBlockedQueue->tail = NULL;
	suspendedBlockedQueue->count = 0;
	return;
}

//Finds the PCB and returns the number of the queue that it is in
int PCBLocation(char PCBName[20]){
	Node *current;
	int PCBloc = 0;
	current = readyQueue->head;
	PCBloc = QueueLoop(PCBName,current,1);
	if(PCBloc!=0){
		return PCBloc;
	}
	current = blockedQueue->head;
	PCBloc = QueueLoop(PCBName,current,2);
	if(PCBloc!=0){
		return PCBloc;
	}
	current = suspendedReadyQueue->head;
	PCBloc = QueueLoop(PCBName,current,3);
	if(PCBloc!=0){
		return PCBloc;
	}
	current = suspendedBlockedQueue->head;
	PCBloc = QueueLoop(PCBName,current,4);
	return PCBloc;
}

//Removes the PCB from a queue (anywhere inside the queue)
Node *DequeueName(Queue *q1,char PCBName[20]){
	Node *current = q1->head;
	Node *prev = NULL;
	while(current!=NULL){
		if(strcmp(current->pcb->processName,PCBName)==0){
			if(prev==NULL){
				current = dequeue(q1);
				return current;
			}else{
				prev->next = current->next;
				current = current->next;
				current->prev = prev;
				q1->count--;
				return current;
			}
		}
		prev = current;
		current = current->next;
	}
	return NULL;
}

//Loops through the entire queue 
int QueueLoop(char PCBName[20],Node *current,int queue){
	while(current!=NULL){
		if(strcmp(current->pcb->processName,PCBName)==0){
			return queue;
		}
		current = current->next;
	}
	return 0;
}
void printQueue(Queue *q1){
  Node *current;
  int count = 0;
  current = q1->head;
  
	while(current!=NULL){
		current = current->next;
		count++;
	}
	print("\n");
}

void DeleteAllQueues(){
	DeleteAllQueuesLoop(readyQueue);
	DeleteAllQueuesLoop(blockedQueue);
	DeleteAllQueuesLoop(suspendedReadyQueue);
	DeleteAllQueuesLoop(suspendedBlockedQueue);
}

void DeleteAllQueuesLoop(Queue *q1){
	Node *current = q1->head;
	while(current!=NULL){
		freePCB(current->pcb);
		sys_free_mem(current);
		current = current->next;
	}
}

void PriorityEnqueue(Queue *q1, PCB *pcb){ 
  Node *newPtr;
  Node *current;
  Node *prev=NULL;
  int added = 0;
  newPtr = sys_alloc_mem(sizeof(Node)); 
  if(newPtr != NULL){	
    newPtr->pcb= pcb;
    newPtr->next = NULL;
    if(q1->count==0){ 
		q1->head = newPtr;
		q1->tail = newPtr;
		q1->tail->next = NULL;
    }else{
		current = q1->head;
		while(current!=NULL){
			if(current->pcb->priority <= pcb->priority){
				if(prev!=NULL){
					prev->next = newPtr;
				} else {
					q1->head = newPtr;
					newPtr->next=current;
				}
				newPtr->next = current;
				added = 1;
				break;
			}else{
				prev = current;
				current = current->next;
			}
		}
		if(current==NULL && added ==0){
			q1->tail->next = newPtr;
			q1->tail = newPtr;
			q1->tail->next = NULL;
		}
    }
    q1->count++;
  }else{
	print("Error: Insufficient Memory\n");
  }
}