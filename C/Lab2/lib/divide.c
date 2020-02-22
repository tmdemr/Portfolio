#include <stdio.h>


int divide(int a, int b){
  if (b != 0)  {
    int div = a/b;
    return div;
  }
  else{
    printf("You must input Not 0\n");
    printf("Please Try again");
  }
  

}
