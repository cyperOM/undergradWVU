typedef struct PCB{
	int memory_size;
	unsigned char *load_address;
	unsigned char *exec_address;	
	int priority;
	char processName[20];
	//PCB Stack *needed
	int PCBclass;
	int suspended;//0 for not suspended, 1 for suspended
	int state; //0 for blocked, 1 for unblocked/running, 2 for ready
	char *stack; 
	char *stackBottom; 
}PCB;

typedef struct Node { 
  PCB *pcb;
  int priority;
  struct Node *next; 
  struct Node *prev; 
}Node;

typedef struct{
  Node *head; 
  Node *tail;
  int count;
}Queue;

void DeleteAllQueuesLoop(Queue *);
void enqueue(Queue *,PCB *);
void PriorityEnqueue(Queue *,PCB *);
Node *dequeue(Queue *);
Node *DequeueName(Queue *,char[20]);
void CreateQueue();
PCB *setupPCB(char*,int,int);
PCB *allocatePCB();
void createPCB(char*,int,int);
void freePCB(PCB *);
void print(char[1700]);
int PCBLocation(char[20]);
void printQueue(Queue *);
void insertPCB(PCB *);
PCB *findPCB(char [20]);
void removePCB(PCB *);
void deletePCB(char[20]);
void showAll();
void resumePCB(char[20]);
void setPCBpriority(char[20],int);
void showPCB(char[20]);
void showReady();
void showBlocked();
void suspendPCB(char[20]);
void blockPCB(char[20]);
void unblockPCB(char [20]);
void pageinate(char[10000]);
void DelateAllQueues();
void PCBToString(char [20],char [10000]);
void printNumber(int);
PCB *dequeueFromNode(Queue *);
PCB *dequeueNameFromNode(Queue *,char[20]);
void freeNode(Node *);