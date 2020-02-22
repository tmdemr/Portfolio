 #include <stdlib.h>

 #define MAX 20

void sub(void){
  int i;
  int *ptr;
  i = *ptr; 
  ptr= malloc(MAX * sizeof(int));
  ptr[MAX] = 0;
  ptr = 0;
}

int main(){
  sub();
}
