#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define XOR(a,b) (a==b?0:1)

int* lshift (int *ary, int length);
void make_key (int *tkey);
int* getkey (void);
int* get_plaintext (void);
int* encrypt (int plain[64], int round, int mode);
int* f (int r[32], int key[48], int num);
int* make_asc (int *bim);
void print(int *data, char name[32], int extent , int enter, int num);

 


int pc1[56]={ 58, 50, 42, 34, 26, 18, 10,
               2, 59, 51, 43, 35, 27, 19,
              11,  3, 60, 52, 44, 36, 28,
              20, 12,  4, 61, 53, 45, 37,
              64, 56, 48, 40, 32, 24, 16,
               8, 63, 55, 47, 39, 31, 23,
              15,  7, 62, 54, 46, 38, 30,
              22, 14,  6, 29, 21, 13,  5 };

int pc2[48]={ 14, 17, 11, 24,  1,  5,
               3, 28, 15,  6, 21, 10,
              23, 19, 12,  4, 26,  8,
              16,  7, 27, 20, 13,  2,
              41, 52, 31, 37, 47, 55,
              30, 40, 51, 45, 33, 48,
              44, 49, 39, 56, 34, 53,
              46, 42, 50, 36, 29, 32 };

int ip[64] = { 58, 50, 42, 34, 26, 18, 10, 2,
               60, 52, 44, 36, 28, 20, 12, 4,
      62, 54, 46, 38, 30, 22, 14, 6,
      64, 56, 48, 40, 32, 24, 16, 8,
      57, 49, 41, 33, 25, 17,  9, 1,
      59, 51, 43, 35, 27, 19, 11, 3,
      61, 53, 45, 37, 29, 21, 13, 5,
      63, 55, 47, 39, 31, 23, 15, 7};

int ip_[64] = { 40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
    38, 6, 46, 14, 54, 22, 62, 30,
    37, 5, 45, 13, 53, 21, 61, 29,
    36, 4, 44, 12, 52, 20, 60, 28,
    35, 3, 43, 11, 51, 19, 59, 27,
    34, 2, 42, 10, 50, 18, 58, 26,
    33, 1, 41, 9, 49, 17, 57, 25 };

int expand[48]={ 32,  1,  2,  3,  4,  5,
                  4,  5,  6,  7,  8,  9,
                  8,  9, 10, 11, 12, 13,
     12, 13, 14, 15, 16, 17,
     16, 17, 18, 19, 20, 21,
     20, 21, 22, 23, 24, 25,
     24, 25, 26, 27, 28, 29,
     28, 29, 30, 31, 32,  1 };

int permutate[32]={ 16,  7, 20, 21,
                    29, 12, 28, 17,
      1, 15, 23, 26,
      5, 18, 31, 10,
      2,  8, 24, 14,
     32, 27,  3,  9,
     19, 13, 30,  6,
     22, 11,  4, 25  };

int S[8][4][16]={
                {{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
                {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
                {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
                {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}},

                {{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
                {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
                {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
                {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}},

                {{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
                {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
                {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
                {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}},

                {{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
                {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
                {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
                {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}},

                {{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
                {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
                {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
                {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}},

                {{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
                {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
                {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
                {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}},

                {{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
                {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
                {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
                {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}},

                {{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
                {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
                {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
                {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}} };
int key[16][48];


void main ()
{
 int *plaintext, *ciphertext, *return_plain;
 int ciphert[64] , cipert[64];
 int i;

 

 make_key(getkey());

 plaintext = get_plaintext();    // plaintext 크기는 함수에서 정해진다.
 ciphertext = encrypt ( plaintext, 16, 0);

 

 for ( i = 0; i < 64; i++)
 {
  ciphert[i] = ciphertext[i];
  cipert[i] = ciphertext[i];
 }
 
 print(ciphert, "암호문", 64, 8, 0);


  
 return_plain = encrypt ( ciphert, 16, 1);
 
 print(return_plain, "평문", 64, 8, 0);

}

int* getkey (void)
{
 char ck[8];
 int i, k, temp;
 int *tkey;

 tkey = (int*) malloc ( sizeof (int) * 64);  // the key 크기 초기화.

 printf("키값에 사용할 8개 문자를 입력하세요.\n>>");
 gets(ck);

 for ( i = 0; i < 8; i++)
 {
  temp = (int) ck[i];
  
  for ( k = 7; k >= 0; k--)
  {
   tkey[(i*8)+k] = temp % 2;
   temp /= 2;
  }
 }
 print( tkey, "키 아스키값", 64, 8, 0);       //
         //키값 출력 할 부분.
 return tkey;
}

void make_key (int *tkey)
{
 int i, rn, pc1_temp[56], pc2_temp[48];
 int *c, *d, *c_temp, *d_temp, *ls;
 int lstable[16] = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2 ,2, 2, 1};
 
 c = (int*) malloc (sizeof(int)*28);
 d = (int*) malloc (sizeof(int)*28);
 c_temp = (int*) malloc (sizeof(int)*28);
 d_temp = (int*) malloc (sizeof(int)*28);
 ls = (int*) malloc (sizeof(int)*56);


 for ( i = 0; i < 56; i++ )
 {
  pc1_temp[i] = tkey[pc1[i]-1];   // pc1 전치 
 }

  print( pc1_temp, "키 전치", 56, 7, 0);       //
            // pc1 출력 부분.

 for ( i = 0; i < 28; i++)     // C,D 분리
 {
  c[i] = pc1_temp[i];
  d[i] = pc1_temp[i+28];
 }

 for ( rn = 0; rn < 16; rn++){
  for ( i = 0; i < lstable[rn]; i++)
  {
   c_temp = lshift ( c, 28 );
   d_temp = lshift ( d, 28 );
   c = c_temp;
   d = d_temp;
  }

  for ( i = 0; i < 28; i++)    // C, D 합체
  {
   ls[i] = c_temp[i];
   ls[i+28] = d_temp[i];
  }
            
  for ( i = 0; i < 48; i++)
  {
   if ( (pc2[i]-1) < 28 )
    pc2_temp[i] = c_temp[pc2[i]-1];
   else
    pc2_temp[i] = d_temp[pc2[i]-29];
   
   key[rn][i] = pc2_temp[i];
  }

           // LS, Key(pc2) 출력.

   print(ls, "LS", 56, 7, rn+1);        //
   print(key[rn], "Key", 48, 6, rn+1);       //
 } 
  
}

int* lshift (int *ary, int length)
{
 int *out, i, temp;

 out = (int*)malloc(sizeof(int)*(length+1));

  temp = ary[0];
 for( i = 0; i < length; i++ )
  out[i] = ary[i+1];
  out[length-1] = temp;

 return out;
}

int* get_plaintext (void)
{
 int i, cr;
 char cp[8];
 int *the_plain, temp, prty;

 the_plain = (int*) malloc(sizeof(int)*64);

 printf("평문으로 사용할 8개 문자를 입력하세요.\n>>");
 gets(cp);

 for ( cr = 0 ; cr < 8; cr++) // cr == character round
 { 
  prty = 0;
  temp = (int)cp[cr];
  for ( i = 6; i >= 0; i--)
  {
   if ( temp % 2 == 0 ) prty ++;
   the_plain[(cr*8)+i] = temp % 2;
   temp /= 2;   
  }

  if ( prty % 2 == 0 )
   the_plain[(cr*8)+7] = 1;
  else
   the_plain[(cr*8)+7] = 0;
 }

 print(the_plain, "아스키 평문", 64, 8, 0);
              // 평문 출력 할 부분.
 return the_plain;
}

int* encrypt (int plain[64], int round, int mode)
{
 int i, rnd, *ip_temp, *l_temp, l[32], r[32];
 int *f_out, *c, *ip__temp;

 ip_temp = (int*) malloc (sizeof(int)*64);
 l_temp = (int*) malloc (sizeof(int)*64);
 c = (int*) malloc (sizeof(int)*64);
 ip__temp = (int*) malloc (sizeof(int)*64);

 for ( i = 0; i < 64; i++)       // 초기전치
 {
  ip_temp[i] = plain[ip[i]-1]; 
 }

 print( ip_temp, "초기 전치", 64, 8, 0);

 for ( i = 0; i < 32; i++)       // R,L 분리
 {
  l[i] = ip_temp[i];
  r[i] = ip_temp[i+32];
 }

 for ( rnd = 0; rnd < round; rnd++)     // DES 반복 지역
 {
  for ( i = 0; i < 32; i++)      // Ln+1 = R, Ln = temp
  {
   l_temp[i] = l[i];
   l[i] = r[i];
  }

  if ( mode == 0 )
   f_out = f ( r, key[rnd], rnd );       // F함수 입력 
  else
   f_out = f ( r, key[round-rnd-1] , rnd );

  for ( i = 0; i < 32; i++ )      // F out 값 l 값과 XOR
  {
   r[i] = XOR ( l_temp[i], f_out[i] );
  }

  print ( l_temp, "L", 32, 4, rnd );
  print ( r, "L XOR F", 32, 4, rnd+1 );
  printf("------------------------------\n");

 }             // DES 반복 끝 
 
 for ( i = 0; i < 32; i++)       // Ln = Ln+1, Rn = Rn+1
 {
  c[i] = r[i];
  c[i+32] = l[i];
 }

 print ( c, "역전치 전", 64, 8, 0 );


 for ( i = 0; i < 64; i++)       // 역전치
 {
  ip__temp[i] = c[ip_[i]-1];
 }

 return ip__temp;
}

int* f (int r[32], int rkey[48], int num)
{
 int i, bin, r_temp[48], s_temp[8], f_temp[32], *f_out, *k ;

 f_out = (int*) malloc (sizeof(int)*32);
 k = (int*) malloc (sizeof(int)*48);

 for ( i = 0; i < 48; i++)
 {
  r_temp[i] = r[expand[i]-1];     // 확대 전치.
 }
 
 print ( r, "R", 32, 4, num);
 print ( r_temp, "확대전치", 48, 6, num+1);

 for ( i = 0; i < 48; i++)
 {
  r_temp[i] = XOR(r_temp[i], rkey[i]);   // Rn XOR Key
 }

 k = rkey;
 
 print ( k, "Key", 48, 6, num+1 );
 print ( r_temp, "R XOR Key", 48, 6, num+1 );

 for ( i = 0; i < 8 ; i++)
 {
  s_temp[i] = S[i][( 2 * r_temp[6*i] )+( r_temp[(6*i)+5] )][( 8 * r_temp[(6*i)+1] ) + ( 4 * r_temp[(6*i)+2] ) + ( 2 * r_temp[(6*i)+3] ) + ( r_temp[(6*i)+4] )];
             // S-box 8개 출력
 }

 print( s_temp, "S-box 출력", 8, 1, num+1 );

 for ( i = 0; i < 8; i++){      // S-box 2진코드 4*8로 변환 
  for( bin = 3; bin >= 0; bin--)
  {
   f_temp [(i*4)+bin] = s_temp[i] % 2;
   s_temp[i] /= 2;
  }
 }

 print ( f_temp, "S-box bit 출력", 32, 4, num+1 );

 for ( i = 0; i < 32; i++)      // 평형 전치
 {
  f_out[i] = f_temp[permutate[i] - 1];
 }

 print ( f_out, "평형전치", 32, 4, num+1 );

 return f_out;
}

void print(int *data, char name[32], int extent , int enter, int num)
{
 int i;

 
 
 if ( num != 0  )
  printf(" *** %s %d *** ", name, num);
 else
  printf(" *** %s *** ", name);
 
 for ( i = 0; i < extent; i++)
 {
  if ( !( i % enter ) ) printf("\n");
  printf("%d", data[i]);

 }
 getc(stdin);
 
}