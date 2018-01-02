#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <memory.h>
#include <math.h>

int main(int argc, char * argv[]) {
    static int numPasses = 36;
    if(argc == 1){//requires a drive name
        fprintf(stderr,"An argument is required ex: wiper [drive path]");
        return 1;//returns error
    }
    else if(argc == 2){
        srand(time(NULL));   // should only be called once
        for(int i = 0; i<numPasses; i++){//36 passes
            FILE * f = fopen(argv[argc-1],"r+"); //open path to drive
            switch(i % 4){
                case 0://writes all 0's
                    while(1){//while not at the end of the file
                        break;
                    }
                    break;
                case 1://writes all 1's
                    while(1){//while not at the end of the file
                        break;
                    }
                    break;
                case 2://writes all random integers's
                    while(1){//while not at the end of the file
                        int num = rand();
                        int size = (int)((ceil(log10(num))+1)*sizeof(char));
                        char * numtoStr = (char *) malloc(sizeof(char) * size);
                        sprintf(numtoStr, "%d", num);
                        printf("For case 2, the random number is %s\n", numtoStr);
                        free(numtoStr);
                        break;
                    }
                    break;
                case 3://writes the string "NTID[random integer]Wipe"'s
                    while(1){
                        int num = rand();
                        int size = (int)((ceil(log10(num))+1)*sizeof(char));
                        char * numtoStr = (char *) malloc(sizeof(char) * size);
                        sprintf(numtoStr, "%d", num);
                        char * b = "NTID";
                        char * e = "Wiper";
                        int maxLen = strlen(b) + strlen(numtoStr) + strlen(e);
                        //printf("The length is %i\n", maxLen);
                        char * wipeString = (char *)malloc(sizeof(char) * maxLen + 1);
                        strcat(wipeString,b);
                        strcat(wipeString,numtoStr);
                        strcat(wipeString,e);
                        printf("For case 3, the random string is %s\n", wipeString);
                        free(numtoStr);
                        free(wipeString);
                        break;
                    }
                    break;
            }
            fclose(f);
        }
    }
    return 0;
}