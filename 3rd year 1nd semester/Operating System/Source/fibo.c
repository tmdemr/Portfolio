#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
typedef int buffer_item;
#define BUFFER_SIZE 5
#define TRUE 1
buffer_item START_NUMBER;
buffer_item item; 
int counter;
int insert_item(buffer_item item);
int remove_item(buffer_item *item);  
buffer_item buffer[BUFFER_SIZE];
void *producer(void *ptr);
void *consumer(void *ptr);  
pthread_cond_t condc, condp;
pthread_mutex_t mutex;
int sleepTime, producerThreads, consumerThreads, a, item;
pthread_attr_t attr;
sem_t full, empty;
int insert_item(buffer_item item)
{
   if(counter < BUFFER_SIZE) {
      buffer[counter] = item;
      counter++;
      return 0;
   }
   else { 
      return -1;
   }
}
int remove_item(buffer_item *item)
{
   if(counter > 0) {
      *item = buffer[(counter-1)];
      printf("consumer%u consumed %d\n", (unsigned int)pthread_self(),buffer[counter-1]);
      counter--;
      return 0;
   }
   else { 
      return -1;
   }
}
void* producer(void *ptr) {     
   while(TRUE) {
      sleep(sleepTime);     
      sem_wait(&empty);
      pthread_mutex_lock(&mutex);    
      if(insert_item(START_NUMBER)) {
         fprintf(stderr, "error \n");
      }
      else {
        printf("producer%u produced %d\n", (unsigned int)pthread_self(),START_NUMBER);
        START_NUMBER++;
      }     
      pthread_mutex_unlock(&mutex);
      sem_post(&full);
  }
 }
void* consumer(void *ptr) {
   while(TRUE) {
      sleep(sleepTime);
      sem_wait(&full);
      pthread_mutex_lock(&mutex);
      if(remove_item(&item)) {
         fprintf(stderr, "error \n");
      }
      else {
        // printf("consumer%u consumed %d\n", (unsigned int)pthread_self(),&START_NUMBER);
      }
      pthread_mutex_unlock(&mutex);
      sem_post(&empty);
   }
}
void initializeData() {
   pthread_mutex_init(&mutex, NULL);
   sem_init(&full, 0, 0);
    sem_init(&empty, 0, BUFFER_SIZE);
   pthread_attr_init(&attr);
   counter = 0;
}
int main(int argc, char **argv) {
    sleepTime = atoi(argv[1]);
    producerThreads = atoi(argv[2]);
    consumerThreads = atoi(argv[3]);
    START_NUMBER = atoi(argv[4]);
    item = START_NUMBER;
    initializeData();
    pthread_t pro, con;
    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&condc, NULL); 
    pthread_cond_init(&condp, NULL); 
   for(a=0; a< consumerThreads;a++)
    pthread_create(&con, NULL, consumer, NULL);
    for(a=0;a<producerThreads;a++)
    pthread_create(&pro, NULL, producer, NULL);
    pthread_join(con, NULL);
    pthread_join(pro, NULL);
    pthread_mutex_destroy(&mutex); 
    pthread_cond_destroy(&condc); 
    pthread_cond_destroy(&condp); 
    sleep(sleepTime);
}
