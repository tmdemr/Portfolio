#include <iostream>

void ShellSort(int A[], int n){
	
	int h,i,j, Value;
	h = 1;
	do (h=3*h+1);
	while (h<n);
	
	do {
		h = h/3;
		for(i = h; i < n; i++){
			Value = A[i];
			j = i;
			while (A[j-h] > Value){
				A[j] = A[j-h];
				j -= h;
				if (j <= (h-1)) break;
				}
				A[j] = Value;
			}
		} while ( h>1);
	}	


int main(int argc, char** argv) {
	int arr[] = {21, 25, 86, 13, 2, 7, 72, 99, 103, 132, 263, 243, 124, 19, 98, 6, 611, 81};
	
	ShellSort(arr, sizeof(arr)/4);
	
	for(int i=0; i<sizeof(arr)/4; i++){
		printf("%d\n", arr[i]);
	}
	return 0;
}
