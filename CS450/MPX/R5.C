#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <dos.h>
#include "R5.h"
#include "mpx_supt.h"

DCB *COM1;

void interrupt (*oldVect)();

//Opens the Com port
int com_open(int* eflag_p,int baud_rate){
	long baud_rate_div;
	int mask;
	int low;
	int high;
	if(eflag_p==NULL){
		//no eflag 
		return 101;
	}else if(baud_rate < 0){
		//baud_rate is less than 0
		return 102;
	}else if(COM1->COM_OPEN == OPENCOM){
		//Com is already open
		return 103;
	}else{
		COM1->COM_OPEN = 1;
		COM1->event_flag = eflag_p;
		COM1->status = IDLECOM;
		COM1->ring_buffer_in = 0;
		COM1->ring_buffer_out = 0;
		COM1->ring_buffer_count = 0;
		oldVect = getvect(COM1_INT_ID);
		setvect(COM1_INT_ID,topLevel);
		baud_rate_div = 115200 / (long) baud_rate;
		outportb(COM1_LC,0x80);
		low = lowByte(baud_rate_div);
		high = highByte(baud_rate_div);
		outportb(COM1_BRD_MSB,high);
		outportb(COM1_BRD_LSB,low);
		outportb(COM1_LC,0x03);
		disable();
		mask = inportb(PIC_MASK);
		mask = mask & ~0x10;
		outportb(PIC_MASK, mask);
		enable();		
		outportb(COM1_MC,0x08);
		outportb(COM1_INT_EN,0x01);
		return 0;
	}
}
//Closes the com port
int com_close(void){
	if(COM1->COM_OPEN != OPENCOM){
		return -201;
	}else{
		COM1->COM_OPEN = CLOSEDCOM;
		outportb(PIC_MASK,EOI);
		outportb(COM1_MS,0);
		outportb(COM1_INT_EN,0);
		setvect(COM1_INT_ID,oldVect);
		return 0;
	}	
}

//Grabs the least significant bytes
int lowByte(long x){
	int lowBytez = x & 0x00FF;
	return lowBytez;
}

//Grabs the most significant bytes
int highByte(long x){
	int highBytez = x >> 8;
	return highBytez;
}

//Reads from the Com
int com_read(char *buf_p, int *count_p){
	if(COM1->COM_OPEN == CLOSEDCOM){
		// the port isn't open
		return -301;
	} else if (COM1->status != IDLECOM){
		// the device is busy
		return -304;
	} else if (buf_p == NULL){
		// the buffer address is invalid
		return -302;
	} else if (count_p == NULL){
		// the  count address or count value is invalid
		return -303;
	} else {
		// there is no error
		COM1->in_buff = buf_p;
		COM1->in_count = count_p; 
		COM1->in_done = 0;
		*COM1->event_flag = 0;
		COM1->status = READING;
		disable();
		// checks to see if characters that haven't been read
		if(COM1->ring_buffer_count != 0){
			while(COM1->ring_buffer_count != 0 && COM1->ring_buffer != "\r" &&  COM1->ring_buffer_count <= *(COM1->in_count)){
				COM1->in_buff[COM1->in_done]=COM1->ring_buffer[COM1->ring_buffer_out];
				COM1->in_done++;
				COM1->ring_buffer_count--;
				COM1->ring_buffer_out++;
			}
			// checks to see if the requestor's buffer has not been filled
			if (COM1->in_done < *(COM1->in_count)){
				return 0; 
			}
			COM1->in_buff[COM1->in_done] = '\0';
			enable();
			COM1->status = IDLECOM;
			*COM1->event_flag = COMPLETE; 
			*COM1->in_count=COM1->in_done;
			return COM1->in_done;
		}
		return 0;
	}
}

//Writes from the Com
int com_write(char *buf_p, int *count_p){
	int mask;
	if (COM1->COM_OPEN != OPENCOM){
		// the port is not open
		return -401;
	} else if (COM1->status != IDLECOM){
		// the device is busy
		return -404;
	} else if (buf_p == NULL){
		// the buffer address is invalid
		return -402;
	} else if (count_p == NULL){
		// the count address or count value is invalid
		return -403;
	} else {
		// there is no error
		COM1->out_buff = buf_p;
		COM1->out_count = count_p; 
		COM1->out_done = 0;
		COM1->status = WRITING;
		*COM1->event_flag = INPROGRESS;
		outportb(COM1_BASE ,COM1->out_buff[0]);
		COM1->out_done++;
		COM1->out_buff++;
		disable();
		mask = inportb(COM1_INT_EN);
		mask = mask | 0x02;
		outportb(COM1_INT_EN,mask);
		enable();
		return 0;
	}
}
//Top level interrupt handler
void interrupt topLevel(){
	 static int mask;
	if(COM1->COM_OPEN!=OPENCOM){
		outportb(PIC_CMD,EOI);
		return;
	}else{
		mask = inportb(COM1_INT_ID_REG);
		if(mask==2){
			writeInterrupt();
		}else if(mask==4){
			readInterrupt();
		}
	}
	outportb(PIC_CMD,EOI);
}
//Read Interrupt occurs when the top level interrupt is reading 
void readInterrupt(){
	char currentChar = inportb(COM1_BASE);
	if(COM1->status!=READING){
		if((COM1->ring_buffer_count)>=*(COM1->in_count)){ //if ring buffer is full
			return;
		}else{ //if ring buffer is not full
			COM1->ring_buffer[COM1->ring_buffer_count] = currentChar;//store character in ring buffer 
			COM1->ring_buffer_count++; 
		}
		return;
	}else{ //if reading
		COM1->in_buff[COM1->in_done]= currentChar;//store character in the in buffer 
		COM1->in_done++;
	}
	if((currentChar=='\r')||(COM1->ring_buffer_count>=*(COM1->in_count))){
		COM1->in_buff[COM1->in_done-1] = '\0';
		*COM1->in_count = COM1->in_done;
		COM1->status = IDLECOM;
		*COM1->event_flag = COMPLETE;
		return *COM1->in_count;
	}
}
//Write Interrupt occurs when the top level interrupt is Write 
void writeInterrupt(){
	static int mask;
	if(COM1->status == WRITING){
		if(COM1->out_done<*(COM1->out_count)){
			outportb(COM1_BASE,COM1->out_buff[0]); 
			COM1->out_done++; 
			COM1->out_buff++;
		}else{
			COM1->status = IDLECOM;
			*COM1->event_flag=COMPLETE;
			*(COM1->out_count) = COM1->out_done;
			disable();
			mask = inportb(COM1_INT_EN);
			mask = mask & ~0x02;
			outportb(COM1_INT_EN,mask);
			enable();
		}
	}
}
void CreateDCB(){
	COM1 = sys_alloc_mem(sizeof(DCB));
}