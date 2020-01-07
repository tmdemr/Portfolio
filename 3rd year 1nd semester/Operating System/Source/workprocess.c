#include <stdio.h>
#include <stdlib.h>

#define LOG printf


int main(int argc, char const *argv[])
{
    LOG("\nChild Process Started...\n");

    int fibo_range = 0;
    printf("피보나치 수열의 범위(숫자) : ");
    scanf("%d", &fibo_range);

    long k = 0, j = 1;
    long long fibo = 0;
    if(fibo_range < 0){
        LOG("\n명령어 입력 값이 음수이므로 프로세서를 종료합니다...\n");

        exit(0);

    }
    for(int i = 0; i < fibo_range; i++){
        printf("%d ",j);
        fibo = k + j;
        k = j;
        j = fibo;
        }
  
    

    LOG("\nChild Process Finished...\n");
    return 0;
}

       