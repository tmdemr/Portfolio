#include <stdio.h>

struct Record{
	int no;
	int ammount;
};

struct Record mas;
struct Record tra;

int main() {
	FILE *old_master, *transaction, *new_master, *error;
	if ((old_master = fopen("master.txt", "r")) == NULL){
		printf("file open error\n");
	}
	if ((transaction = fopen("transaction.txt", "r")) == NULL){
		printf("file open error\n");
	}
	new_master = fopen("new_master.txt", "w");
	error = fopen("error.txt", "w");

	int m_file_pointer;
	int t_file_pointer;

	char error_type;

	m_file_pointer = fscanf(old_master,"%d %d", &mas.no, &mas.ammount);
	t_file_pointer = fscanf(transaction,"%d %d", &tra.no, &tra.ammount);



  void isMasEOF(){
    if (m_file_pointer == EOF){
        mas.no = EOF;
      }else {
        printf("%d %d\n",mas.no,mas.ammount);
        m_file_pointer = fscanf(old_master,"%d %d", &mas.no, &mas.ammount);
      }
    }

  void isTraEOF(){
    if (t_file_pointer == EOF){
        tra.no = EOF;
      }else
        t_file_pointer = fscanf(transaction,"%d %d", &tra.no, &tra.ammount);

    }

	while(mas.no != EOF && tra.no != EOF){
    if(mas.no <  tra.no){
			fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
      isMasEOF();
    }
		if(mas.no <= tra.no){
			if(mas.no == tra.no){
	      if ((mas.ammount + tra.ammount) < 0){
	        error_type = 'L';
	        fprintf(error,"%c %d %d\n",error_type, tra.no, tra.ammount);
					fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
			    isMasEOF();
			    isTraEOF();
	      }else{
	        mas.ammount = mas.ammount + tra.ammount;
					fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
	        isMasEOF();
	        isTraEOF();
	      }
			}
		}else{
			error_type = 'U';
			fprintf(error, "%c %d %d\n",error_type, tra.no, tra.ammount );
			isTraEOF();
			fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
			isMasEOF();
		}
/*
void except(){
	switch (error_type) {
		case 'L':
			fprintf(error,"%c %d %d\n",error_type, tra.no, tra.ammount);
			fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
	    isMasEOF();
	    isTraEOF();
			break;
		case 'U':
			fprintf(error, "%c %d %d\n",error_type, tra.no, tra.ammount );
			isTraEOF();
			fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
			isMasEOF();
			break;
	}
}
*/
  }


	fclose(old_master);
	fclose(transaction);
	fclose(error);
	fclose(new_master);

	return 0;
}
