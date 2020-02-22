#include <stdio.h>
#include <stdlib.h>
#include <time.h>
 
void swap(int* a, int* b){
    int tmp=*a;
    *a=*b;
    *b=tmp;
}
 
void quick_sort(int* A, int left, int right){
 
    if(left>=right) return;
 
    int mid=(left+right)/2;
    int pivot=A[mid];
 
    swap(&A[left],&A[mid]);
 
    int p=left+1,q=right;
 
    while(1){
        while(A[p]<=pivot){ p++; }
        while(A[q]>pivot){ q--; }
 
        if(p>q) break;
 
        swap(&A[p],&A[q]);
    }
 
    swap(&A[left],&A[q]);
 
    quick_sort(A,left,q-1);
    quick_sort(A,q+1,right);
 
}
 
int main(void){
 
    int A[100];
    int i;
 
    srand(time(NULL));
    for(i=0; i<100; i++){
        A[i]=rand()%1000;
    }
    printf("\n 정렬 전 : \n\n");
 	for(i=0; i<100; i++){
        printf("%d ", A[i] );
    }
    printf("\n\n 정렬 후 : \n\n");
    quick_sort(A, 0, sizeof(A)/sizeof(int)-1);
 
    for(i=0; i<100; i++){
        printf("%d ",A[i]);
    }
 
    return 0;
}

