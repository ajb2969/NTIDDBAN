#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <memory.h>
#include <fcntl.h>
#include <zconf.h>
#define BUFFSIZE 8

long computeFileSize(int fileDes){
    lseek(fileDes, 0, SEEK_END);//goes to the end of the file
    long fsize = lseek(fileDes, 0, SEEK_CUR);//gets the position
    lseek(fileDes, 0, SEEK_SET);//goes back to the beginning of the file
    return fsize;
}


void zeroBuffer(char * buffer){
    buffer = memset(buffer,'0',BUFFSIZE);
}

void oneBuffer(char * buffer){
    memset(buffer,'1',BUFFSIZE);
}

void fillBuffer(char * buffer, const char data){
    strcat(buffer,&data);
}


int openFile(char * drive){
    ///dev/sdd - example of drive
    int fd = open(drive, O_WRONLY, 0);
    if (fd < 0) {
        fprintf(stderr, "Error opening device file.\n");
        return -1;
    }
    else
        return fd;
}

int main(int argc, char * argv[]) {

    static int numPasses = 36;
    static long fsize; //file size
    if(argc == 1){//requires a drive name
        fprintf(stderr,"An argument is required wiper [drive path]\n");
        //drive path should be absolute not canonical
        return 1;//returns error
    }
    else if(argc == 2){
        int f = openFile(argv[argc-1]);
        if(f == -1){return -1;}
        fsize = computeFileSize(f);
        char * buffer = calloc(sizeof(char), BUFFSIZE);
        srand(time(NULL));   // should only be called once
        for(int i = 0; i<numPasses; i++){//36 passes
            //printPass(argv[argc-1], i);
            printf("Starting Pass %d\n", i);

            switch(i % 4) { // will alternate between the different wipes each pass
                case 0://writes all 0's
                    zeroBuffer(buffer);
                    for(int i =0; i<(fsize/BUFFSIZE); i++) {
                        write(f, buffer, strlen(buffer));//overwrite the entire fs with buffer contents
                    }
                    lseek(f, 0, SEEK_SET);//goes back to the beginning of the file
                    break;

                case 1://writes all 1's
                    oneBuffer(buffer);
                    for(int i =0; i<(fsize/BUFFSIZE); i++) {
                        write(f, buffer, strlen(buffer));//overwrite the entire fs with buffer contents
                    }
                    lseek(f, 0, SEEK_SET);//goes back to the beginning of the file
                    break;
                case 2://writes all random integers's, buffer must be zero'd completely prior
                    for(int i = 0; i<BUFFSIZE; i++){
                        char num = rand() % 10;
                        fillBuffer(buffer, num);
                    }

                    for(int i =0; i<(fsize/BUFFSIZE); i++) {
                        write(f, buffer, strlen(buffer));//overwrite the entire fs with buffer contents
                    }
                    lseek(f, 0, SEEK_SET);//goes back to the beginning of the file
                    break;
                default://writes the string "NTID[random integer]Wipe"'s
                    for(int i = 0; i<BUFFSIZE; i++){
                        uint16_t num = rand() % 65535;
                        fillBuffer(buffer, num);
                    }
                    for(int i =0; i<(fsize/BUFFSIZE); i++) {
                        write(f, buffer, strlen(buffer));//overwrite the entire fs with buffer contents
                    }
                    lseek(f, 0, SEEK_SET);//goes back to the beginning of the file
                    break;
            }
        }
        close(f);
    }
    else{
        fprintf(stderr,"Too many drives have been provided\n");
        return 1;//returns error
    }
    return 0;
}