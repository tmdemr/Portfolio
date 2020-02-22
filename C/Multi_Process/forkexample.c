#include<unistd.h>
#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>

//#define DEBUG printf;
#define LOG printf


int main(int argc, char const *argv[])
{
    int fibo_range = 0;
    printf("피보나치 수열의 범위(숫자) : ");
    scanf("%d", &fibo_range);

    pid_t pid;
    pid = fork();
    
      if (pid == -1){
        printf("Error!!");
        exit(0);
    }
    if (pid == 0){
        int k = 0, j = 1, fibo = 0;
        LOG("%d Line. Child LOG..\n",__LINE__);

        if(fibo_range < 0){
            printf("\n입력된 범위가 음수입니다. 프로세스를 종료합니다.\n");
            exit(0);
        }

        for(int i = 0; i < fibo_range; i++){
            printf("%d ",j);
            fibo = k + j;
            k = j;
            j = fibo;
            }
            printf("\n");
            exit(0);    
        
    }
    else{
        printf("-------------------------\n");
        printf("-----I'm Your Father-----\n");
        printf("-------------------------\n");

        
        LOG("%d Line. Parent Log..\n",__LINE__);
        exit(0);
    }
    
    
    return 0;
}
