#include <stdio.h>

struct Record{
	int no;
	int ammount;
};

struct Record mas;
struct Record tra;

void get_transaction_record();
void get_master_record();

	FILE *old_master, *transaction, *new_master, *error;


	if ((old_master = fopen("master.txt", "r")) == NULL){
		printf("file open error\n");
		exit(-1);
	}

	if ((transaction = fopen("transaction.txt", "r")) == NULL){
		printf("file open error\n");
		exit(-1);
	}

	new_master = fopen("new_master.txt", "w");
	error = fopen("error.txt", "w");

	int m_file_pointer;
	int t_file_pointer;

	char error_type;

	m_file_pointer = fscanf(old_master,"%d %d", &mas.no, &mas.ammount);
	t_file_pointer = fscanf(transaction,"%d %d", &tra.no, &tra.ammount);

  void get_master_record(){
    if (m_file_pointer == EOF){
		        mas.no = EOF;}
      else {
        printf("%d %d\n",mas.no,mas.ammount);
        m_file_pointer = fscanf(old_master,"%d %d", &mas.no, &mas.ammount);
      }
    }

  void get_transaction_record(){
    if (t_file_pointer == EOF){
        tra.no = EOF;}
      else
        t_file_pointer = fscanf(transaction,"%d %d", &tra.no, &tra.ammount);
    }

int main(int argc, char const *argv[]) {

		while(mas.no != EOF && tra.no != EOF){
	    if(mas.no < tra.no){
	      fprintf(new_master, "%d %d\n", mas.no, mas.ammount);
	      get_master_record();
	    } else if (mas.no == tra.no) {
	      if ((mas.ammount + tra.ammount) < 0){
	        error_type = 'L';
	        fprintf(error,"%c %d %d\n",error_type, tra.no, tra.ammount);
	        fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
	        get_master_record();
	        get_transaction_record();
	      }else{
	        mas.ammount = mas.ammount + tra.ammount;
	        fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
	        get_master_record();
	        get_transaction_record();
	      }
	    } else {
	      error_type = 'U';
	      fprintf(error, "%c %d %d\n",error_type, tra.no, tra.ammount );
	      get_transaction_record();
	      fprintf(new_master,"%d %d\n",mas.no, mas.ammount);
	      get_master_record();

	    }
		}

	fclose(old_master);
	fclose(transaction);
	fclose(error);
	fclose(new_master);

	return 0;
}
