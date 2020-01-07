#include <stdio.h>
#include <stdlib.h>

#define MAX_RANGE 100

void MakeHeap(int A[], int Root, int LastNode){
  int Parent, Leftson, Rightson, Son, RootValue;
  Parent = Root;
  RootValue = A[Root];
  Leftson = 2 * Parent + 1;
  Rightson = Leftson + 1;
  while(Leftson <= LastNode){
    if(Rightson <= LastNode && A[Leftson] < A[Rightson])
      Son = Rightson;
    else Son = Leftson;
    if(RootValue < A[Son]){
      A[Parent] = A[Son];
      Parent = Son;
      Leftson = Parent * 2 + 1;
      Rightson = Leftson +1;
    }
    else break;
  }
  A[Parent] = RootValue;
}

void Heapsort(int A[], int n){
  int i, temp;
  for(i = n/2; i>0; i—)
    MakeHeap(A, i-1, n-1);
  for(i=n-1; i>0; i—){
    temp = A[i];
    A[i] = A[0];
    A[0] = temp;
    MakeHeap(A, 0, i-1);
  }
}

int main(){
  srand(time(NULL));

  int arr[MAX_RANGE];


  printf("\n\n---- PRINT ARRAY! ----\n\n");  
  for (i = 0; i < MAX_RANGE; i++){
    arr[i] = rand() % 200;
    printf("%d ", arr[i]);
  }
  printf("\n\n---- PRINT ARRAY! ----\n\n");


  MakeHeap(arr, 0, MAX_RANGE);

  Heapsort(arr, MAX_RANGE);


  return 0;
}
