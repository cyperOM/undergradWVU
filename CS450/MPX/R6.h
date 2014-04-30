

typedef struct{
	char PCBName[20];
	//PCB *ppt;
	int requestType;
	char* buffer;
	int* count;
	//IOD* next;
}IOD;

typedef struct{
	int event_flag;
	int count;
	IOD* head;
	IOD* tail;
}IOCB;