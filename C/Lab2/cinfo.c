#include <stdio.h>


int main(){
  #ifdef DEBUG
  printf("compiled : " __DATE__"at"__TIME__"\n");
  printf("This is line %d of file %s\n",__LINE__,__FILE__);
  #endif
  printf("Hello, WOrld!\n");
  }
