#include <stdio.h>
#include "teststaticlib.c"

void main(){
  printf("add(1,2) = %d\n", (1,2));
  printf("sub(1,2) = %d\n", sub(1,2));
  printf("mult(1,2) = %d\n", mult(1,2));
  printf("divide(1,2) = %d\n", divide(1,2));

}
