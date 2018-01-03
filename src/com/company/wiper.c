#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <memory.h>
#include <math.h>
#include <assert.h>


long computeFileSize(FILE * file){
    fseek(file, 0, SEEK_END);
    long fsize = ftell(file); // size of the file
    fseek(file, 0, SEEK_SET);
    return fsize;
}


void zeroBuffer(char * buffer, long fileSize){
    memset(buffer,'0',fileSize);
}

void oneBuffer(char * buffer, long fileSize){
    memset(buffer,'1',fileSize);
}

int fillBuffer(char * buffer, long fileSize, char data){
    int filled = 0;
    if(fileSize < strlen(buffer)){
        filled = 1;
    }
    else{
        strcat(buffer,&data);
    }
    return filled;
}

int main(int argc, char * argv[]) {

    static int numPasses = 512;
    static long fsize;
    static int filled;
    if(argc == 1){//requires a drive name
        fprintf(stderr,"An argument is required wiper [drive path]\n");
        return 1;//returns error
    }
    else if(argc == 2){
        FILE * f = fopen(argv[argc-1],"r+"); //open path to drive
        fsize = computeFileSize(f);
        char * buffer = calloc(sizeof(char), fsize + 1);
        srand(time(NULL));   // should only be called once
        for(int i = 0; i<numPasses; i++){//36 passes
            //printPass(argv[argc-1], i);
            printf("Starting Pass %d\n", i);

            switch(i % 4) { // will alternate between the different wipes each pass
                case 0://writes all 0's
                    zeroBuffer(buffer,fsize);
                    fwrite(buffer,fsize,1,f);
                    fseek(f, 0, SEEK_SET);
                    break;

                case 1://writes all 1's
                    oneBuffer(buffer,fsize);
                    fwrite(buffer,fsize,1,f);
                    fseek(f, 0, SEEK_SET);
                    break;
                case 2://writes all random integers's, buffer must be zero'd completely prior
                    free(buffer);
                    buffer = calloc(sizeof(char), fsize + 1);
                    do{
                        char num = rand() % 10;
                        filled = fillBuffer(buffer,fsize,num);
                    }while(filled != 1);
                    fwrite(buffer,fsize,1,f);
                    fseek(f, 0, SEEK_SET);
                    break;
                case 3://writes the string "NTID[random integer]Wipe"'s

                    free(buffer);
                    buffer = calloc(sizeof(char), fsize + 1);
                    do{
                        filled = fillBuffer(buffer,fsize,'N');if(filled){break;}
                        filled = fillBuffer(buffer,fsize,'T');if(filled){break;}
                        filled = fillBuffer(buffer,fsize,'I');if(filled){break;}
                        filled = fillBuffer(buffer,fsize,'D');if(filled){break;}
                        char num = rand() % 10;
                        filled = fillBuffer(buffer,fsize,num);
                    }while(filled != 1);
                    fwrite(buffer,fsize,1,f);
                    fseek(f, 0, SEEK_SET);
                    break;
            }
        }
        fclose(f);
    }

    return 0;
}