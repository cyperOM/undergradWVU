typedef struct context {
	unsigned int BP, DI, SI, DS, ES;
	unsigned int DX, CX, BX, AX;
	unsigned int IP, CS, FLAGS;
} context;

typedef struct params {
	int op_code;
	int device_id;
	unsigned char *buf_addr;
	int *count_addr;
} params;

typedef struct{
	char PCBName[20];
	PCB* ppt;
	int requestType;
	char* buffer;
	int* count;
	struct IOD* next;
}IOD;

typedef struct{
	int event_flag;
	int count;
	IOD* head;
	IOD* tail;
}IOCB;

void interrupt dispatch();
void load_proc();
void interrupt sys_call();	
void InstallCommhand();
void Cleanup();
void DeleteAllTheThings();
void ProcessIORequest(int);
void CreateIOCB();
void IOScheduler();
void IODCleanup(int);