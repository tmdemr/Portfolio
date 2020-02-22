#include <stdio.h>
#include <stdlib.h>

#define NUM_RANGE 30

struct Node {
  struct node *l;
  int key;
  struct node *r;
};

void TreeInit(){
    struct node *t, *head;
    head = (struct node *) malloc(sizeof *head);
    head->l = NULL;
    head->r = NULL;
    head->key = 0;
  }

struct Node *TreeSearch(struct Node *head, int xkey){
  struct node *t;
  t = head->r;
  while(t!=NULL){
    if (xkey == t->key) return(t);
    if (xkey < t->key) t=t->l;
    if (xkey > t->key) t=t->r;
  }
  return(NULL);
}

void TreeInsert(struct Node *head, int xkey){
  struct node *p, *t;
  p = head; t = p->r;
  while(t != NULL){
    p = t;
    if (xkey == t->key) return;
    else if (xkey < t->key) t=t->l;
    else t=t->r;
  }
  t = (struct Node *) malloc(sizeof *t);
  t->key = xkey; t->l = NULL; t->r = NULL;
  if(xkey < p->key) p->l = t;
  else p->r = t;
}

void TreeDel(struct Node *head, int xkey){
  struct node *p, *c, *t, *x;
  p = head; x = head->r;
  while(x != NULL && x->key != xkey){
    p=x;
    if (xkey < x->key) x=x->l;
    else x=x->r;
  }
  if (x == NULL) return;
  else t=x;
  if (t->r == NULL) x=t->l;
  else if (t->r->l == NULL){
    x = t->r;
    x->l = t->l;
  }else{
    c = x->r;
    while(c->l->l != NULL) c=c->l;
    x = c-> l;
    c->l = x->r;
    x->l = t->l;
    x->r = t->r;
  }
  free(t);
  if (xkey < p->key) p->l = x;
  else p->r = x;
}

void main(){
  struct Node node;
  int arr[NUM_RANGE];
  TreeInit();
  
  srand(time(NULL));

  printf("---- Init Random Array ----\n[");
  for (i = 0; i < NUM_RANGE; i++){
    arr[i] = rand() % 100;
    printf("%d ", arr[i])
  }
  printf("]\n---- Init Random Array ----\n");
    
  
  
  
  


}
