#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct Record{//파일을 한 줄씩 받아줄 Record 생성
	int no; 						//순차파일의 Key값 no
	int amount; 				//은행 잔고
}Record;

Record mas; 					//old_master 레코드 생성
Record tra;

int main() {

FILE *old_master, *transaction, *new_master, *error;

if((old_master = fopen("master.txt", "r")) == NULL){ //만약 열 파일이 없을 시 발생하는 예외처리
	printf("file open error\n");
}
if ((transaction = fopen("transaction.txt", "r")) == NULL){
	printf("file open error\n");
}
new_master = fopen("new_master.txt", "w");				//프로그램으로 인해 기록될 파일을 쓰기 모드로 열거나 생성함.
error = fopen("error.txt", "w");

int m_file_pointer; //마스터 파일의 라인을 저장할 변수
int t_file_pointer;

char error_type;

m_file_pointer = fscanf(old_master,"%d %d", &mas.no, &mas.amount); //마스터 파일을 한 라인씩 읽어들임.
t_file_pointer = fscanf(transaction,"%d %d", &tra.no, &tra.amount);

void get_master_record(){ //마스터 파일의 키 값이 EOF가 아닐 시 다음 라인을 읽어들이는 함수.
  if (m_file_pointer == EOF){
      mas.no = EOF; //파일의 끝을 포인터가 가르킬 시 키 값을 EOF로 지정
    }else {
      printf("%d %d\n",mas.no,mas.amount); //프로그램이 정상적으로 돌고 있는지 로그 기록
      m_file_pointer = fscanf(old_master,"%d %d", &mas.no, &mas.amount);
    }
  }

void get_transaction_record(){
  if (t_file_pointer == EOF){
      tra.no = EOF;
    }else
      t_file_pointer = fscanf(transaction,"%d %d", &tra.no, &tra.amount);
  }

	while(1){
    if(mas.no < tra.no){ //transaction 파일의 키 값이 old_master의 키 값보다 클 시.
      fprintf(new_master, "%d %d\n", mas.no, mas.amount); // 처리할 transaction이 없으므로
      get_master_record();																//new_master에 레코드 그대로 복사.
			continue;			//키 값 가산으로 인한 오류를 막기 위해 다음 반복문으로 이동함.
    }

		if (mas.no == tra.no) {		//처리할 transaction이 있을 때.
				if ((mas.amount + tra.amount) < 0){//은행잔고가 부족하다면
	        error_type = 'L';								//L에러를 출력.
					fprintf(error,"%c %d %d\n",error_type, tra.no, tra.amount); //에러파일에 에러 기록
	        fprintf(new_master,"%d %d\n",mas.no, mas.amount);						//new_master에 old_master 레코드
	        get_master_record();																				//그대로 복사. 다음 old_master 레코드 읽어옴
	        get_transaction_record();																		//다음 transaction 레코드 읽어옴.
					continue;
				}else{					//은행잔고가 부족하지 않을 경우 실행되는 구문.
					mas.amount = mas.amount + tra.amount;							//old_master 잔고에 transaction 금액을 더하고
					fprintf(new_master,"%d %d\n",mas.no, mas.amount); //new_master에 기록
					get_master_record();
	        get_transaction_record();
					continue;
				}
			}

			//old_master의 키 값이 transaction의 키 값을 지나쳤을 때.
		if(mas.no > tra.no && tra.no != EOF){ //tra.no가 EOF일 때 마지막으로 한 번 실행되는 오류 제거
			error_type = 'U';										//old_master 파일에는 없는 transaction의 Key값이므로
			fprintf(error,"%c %d %d\n",error_type, tra.no, tra.amount);//에러메세지 출력.
      fprintf(new_master,"%d %d\n",mas.no, mas.amount);						//다음 old_master와 transaction 레코드를 불러옴.
      get_master_record();
      get_transaction_record();
			continue;
			}

		if (mas.no == EOF && tra.no == EOF) break;									//파일 포인터들이 각 파일의 끝에 도달했을 시
	}																															//반복문 탈출


	fclose(old_master);
	fclose(transaction);
	fclose(error);
	fclose(new_master);

	return 0;
}
