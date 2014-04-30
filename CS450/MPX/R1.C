#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "mpx_supt.h"
#include "R1.h"
#include "R2.h"

extern int terminateint;

void DisplayDate(){
	date_rec today;
	char test[128], confirm[128], term[128];
	int len;
	int termlen=129;
	
	strcpy(confirm, "Would you like the date to be displayed in: \nA) Digit format (1/22/2011) \nB) A word for the month(January 22,2011)\n");
	len = strlen(confirm);
	sys_req(WRITE,TERMINAL,confirm, &len);
	sys_req(READ,TERMINAL,term, &termlen);
	
	// pulls the date from the system
	sys_get_date(&today);
	
	if(strcmp(term,"A\n")==0||strcmp(term,"a\n")==0){
		// line will show the date in [month number], [day number], [year number] format
		sprintf(test, "%d / %d / %d\n", today.month, today.day, today.year);
	}

	else {	
		// lines will show the date in [month name], [day number], [year number] format
		if(today.month == 1){
			sprintf(test, "January %d, %d \n", today.day, today.year);
		}else if(today.month == 2){
			sprintf(test, "February %d, %d \n", today.day, today.year);
		}else if(today.month == 3){
			sprintf(test, "March %d, %d \n", today.day, today.year);
		}else if(today.month == 4){
			sprintf(test, "April %d, %d \n", today.day, today.year);
		}else if(today.month == 5){
			sprintf(test, "May %d, %d \n", today.day, today.year);
		}else if(today.month == 6){
			sprintf(test, "June  %d, %d \n", today.day, today.year);
		}else if(today.month == 7){
			sprintf(test, "July %d, %d \n", today.day, today.year);
		}else if(today.month == 8){
			sprintf(test, "August %d, %d \n", today.day, today.year);
		}else if(today.month == 9){
			sprintf(test, "September %d, %d \n", today.day, today.year);
		}else if(today.month == 10){
			sprintf(test, "October %d, %d \n", today.day, today.year);
		}else if(today.month == 11){
			sprintf(test, "November %d, %d \n", today.day, today.year);
		}else if(today.month == 12){
			sprintf(test, "December %d, %d \n", today.day, today.year);
		}
	}
	len = strlen(test);
	sys_req(WRITE,TERMINAL,test, &len);
}
	
void ChangeDate(int month, int day, int year){
	
	char test[128];
	int len;
	int checkLeapYear;
	int modLeapYear ;
	int validDate;

	date_rec new_date;
	new_date.month = month;
	new_date.day= day;
	new_date.year = year;
	//check for valid a valid year
	if(new_date.year >= 1000 && new_date.year <= 10000){
		
		//checking for a valid month
		if(new_date.month >= 1 && new_date.month <=12 ){
		
			//Checks for months that have 31 days in the month
			if(new_date.month == 1 || new_date.month == 3 || new_date.month == 5 || new_date.month == 7 || new_date.month == 8 || new_date.month == 10 || new_date.month == 12){  
				if(new_date.day >= 1 && new_date.day <= 31){
					validDate=1;
				}
				else{
					sprintf(test,"Error: This is an invalid day.\n");
				}
			}
			
			//Checks for months that have 30 days in the month
			else if(new_date.month == 4 || new_date.month == 6 || new_date.month == 9 || new_date.month == 11){
				if(new_date.day >= 1 && new_date.day <= 30){
					validDate=1;
					}
				else{
					sprintf(test,"Error: This is an invalid day.\n");
				}
			}
			
			//Checks for months that have 28 days in the month (February)
			else if(new_date.month == 2){
				if(new_date.day >= 1 && new_date.day <= 28){
					validDate=1;
				}
				
				//leap year
				else if(new_date.day == 29){
					checkLeapYear=new_date.year-916;
					printf("checkLeapYear: %d\n",checkLeapYear);
					modLeapYear = checkLeapYear % 4;
					if(modLeapYear==0){
						validDate=1;
					}
					//invalid leap year
					else{
						sprintf(test,"Error: This is an invalid year due to the fact it is not a leap year\nPlease try a different date\n");
					}
				} 
				else{
					sprintf(test,"Error: This is an invalid day.\n");
				}
			}
		}
		//invalid month...
		else{
			validDate=0;
			sprintf(test, "Error: This is an invalid month.\n");
		}
	}
	//invalid year
	else if (new_date.year <= 1000 || new_date.year > 10000){
		validDate=0;
		sprintf(test,"Error: This is an invalid year.\n");
	}
	
	//Sets the date
	if(validDate==1){
		sys_set_date(&new_date);
		sprintf(test,"This is the new date: %d %d,%d\n", month, day, year);
	}
	
	len = strlen(test);
	sys_req(WRITE,TERMINAL,test, &len);
}

void DisplayDir(){
	char printBuffer[128];
	int len; 
	long *file_size_p, *init_file_size_p;
	int entryInt = 0;
	
	sys_open_dir("");//opens current Directory By Default
	while(entryInt!=-113){		//Will run until there is no more .mpx files in the current directory           						
		char temp_name_buf[128]; 
		int temp_buf_size = 128;
		long temp_file_size_p;		
		entryInt = sys_get_entry(temp_name_buf, temp_buf_size,&temp_file_size_p); //looks for another .mpx entry
		//stores in temp_name, temp_buf_size, and temp_file_size
		if(entryInt!=-113){ //as long the last file has not been found
			sprintf(printBuffer,"\nName: %s\nBufSize: %d\nFileSize: %d\n",temp_name_buf,temp_buf_size, temp_file_size_p);//writes the name of temp, bufsize, and filesize intoa  string
			len = strlen(printBuffer);
			sys_req(WRITE,TERMINAL,printBuffer, &len);	//writes all details to the terminal
		}
	}	
	sys_close_dir(); //closes currently opened directory
}

void Version(){
	//Prints out the version of the MPX system and the Due date of the current Module
	print("Version: 1.0!\nCompletion Date: March 21,2014\n");
}

void Help(char filename[128]){
	char helpText[10000]={'\0'}, line[10000]={'\0'}, fn[128]={'\0'};
	int len;
	FILE *f1;
	if(strcmp(filename,"none")==0){
		//Opens the General Help for the MPX system
		f1 = fopen("C:\\MPX\\Help\\help.txt", "r");
	}else{
		//Gets the filename for the command then opens the necessary file
		getFileName(filename,fn);
		f1 = fopen(fn, "r");
	}
	strcpy(helpText," ");
	if (f1 != NULL) {
		//Prints the file the to terminal
		while(fgets(line, sizeof(line),f1)!=NULL){
			strcat(helpText,line);
		}
		pageinate(helpText);
	}else{
		//If the command was not found
		print("Error: File Not Found\n Make sure the name of the function is correct.\n");
	}
	//Closes the file to avoid memory leaks
	fclose(f1);
}

int Term(){
	//Asks the user if they wish to terminate the program
	//If true the MPX system will wait for 3 seconds then terminate
	unsigned int core;
	char confirm[128], term[128],goodbye[128];
	int len;
	int termlen=129;
	print("Are you sure you wish to terminate the program? Yes or No\n");
	sys_req(READ,TERMINAL,term, &termlen);
	if(strcmp(term,"yes\n")==0||strcmp(term,"Yes\n")==0||strcmp(term,"y\n")==0||strcmp(term,"Y\n")==0){
		DeleteAllQueues();
		print("Terminating...\n");
		return 1;
	}
}
void getFileName(char filename[128], char fn[128]){
//Grabs the filename /location for the help function
	if(strcmp(filename,"terminate")==0){
		strcpy(fn,"C:\\MPX\\Help\\termhelp.txt");
	}else if(strcmp(filename,"displayDate")==0){
		strcpy(fn,"C:\\MPX\\Help\\ddhelp.txt");
	}else if(strcmp(filename,"help")==0){
		strcpy(fn,"C:\\MPX\\Help\\hhelp.txt");
	}else if(strcmp(filename,"changeDate")==0){
		strcpy(fn,"C:\\MPX\\Help\\cdhelp.txt");
	}else if(strcmp(filename,"displayDir")==0){
		strcpy(fn,"C:\\MPX\\Help\\ddirhelp.txt");
	}else if(strcmp(filename,"version")==0){
		strcpy(fn,"C:\\MPX\\Help\\vhelp.txt");
	}else if(strcmp(filename,"block")==0){
		strcpy(fn,"C:\\MPX\\Help\\blochelp.txt");
	}else if(strcmp(filename,"createPCB")==0){
		strcpy(fn,"C:\\MPX\\Help\\cPCBhelp.txt");
	}else if(strcmp(filename,"resume")==0){
		strcpy(fn,"C:\\MPX\\Help\\rhelp.txt");
	}else if(strcmp(filename,"showAll")==0){
		strcpy(fn,"C:\\MPX\\Help\\shoahelp.txt");
	}else if(strcmp(filename,"showPCB")==0){
		strcpy(fn,"C:\\MPX\\Help\\shophelp.txt");
	}else if(strcmp(filename,"suspend")==0){
		strcpy(fn,"C:\\MPX\\Help\\sushelp.txt");
	}else if(strcmp(filename,"unblock")==0){
		strcpy(fn,"C:\\MPX\\Help\\unbhelp.txt");
	}else if(strcmp(filename,"deletePCB")==0){
		strcpy(fn,"C:\\MPX\\Help\\dPCBhelp.txt");
	}else if(strcmp(filename,"setPriority")==0){
		strcpy(fn,"C:\\MPX\\Help\\setphelp.txt");
	}else if(strcmp(filename,"showBlocked")==0){
		strcpy(fn,"C:\\MPX\\Help\\shoBhelp.txt");
	}else if(strcmp(filename,"showReady")==0){
		strcpy(fn,"C:\\MPX\\Help\\shorhelp.txt");
	}else if(strcmp(filename,"dispatch")==0){
		strcpy(fn,"C:\\MPX\\Help\\dispatch.txt");
	}else if(strcmp(filename,"loadProc")==0){
		strcpy(fn,"C:\\MPX\\Help\\loadproc.txt");
	}else if(strcmp(filename,"loadProgram")==0){
		strcpy(fn,"C:\\MPX\\Help\\loadProg.txt");
	}else if(strcmp(filename,"terminateProc")==0){
		strcpy(fn,"C:\\MPX\\Help\\termProc.txt");
	}
}

void readFile(char prompt[128]){
	char line[128];
	int len;
	FILE *f1;
	f1 = fopen("C:\\MPX\\Help\\Prompt.txt", "r");
	prompt[0] = '\0';
	if (f1 != NULL) {
	//Prints the file the to terminal
		while(fgets(line, sizeof(line),f1)!=NULL){
			strcat(prompt,line);
		}
	}
	fclose(f1);
}

void ChangePrompt(char prompt[128]){
	FILE *f1;
	f1 = fopen("C:\\MPX\\Help\\Prompt.txt", "w");
	fprintf(f1,prompt);
	fclose(f1);
}
