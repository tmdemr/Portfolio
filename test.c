#include <stdio.h>
#include <string.h>
#include <stdlib.h>


struct record{
	int no;
	int ammount;

} rec;


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
	int temp_no;
	int temp_ammount;
	char error_type;
	char buf_m[10];
	char buf_t[10];

	while(m_file_pointer != EOF && t_file_pointer != EOF){

		m_file_pointer = fscanf(old_master,"%d %d", &rec.no, &rec.ammount);
		t_file_pointer = fscanf(transaction,"%d %d", &temp_no, &temp_ammount);

		if(rec.no == temp_no){
			if((rec.ammount + temp_ammount) < 0){
					error_type = 'L';
					fprintf(error, "%c %d %d\n", error_type, temp_no, temp_ammount);
			}else{
				rec.ammount = rec.ammount + temp_ammount;
				fprintf(new_master, "%d %d\n", rec.no, rec.ammount);
			}
		}else{
			fprintf(new_master,"%d %d\n",rec.no, rec.ammount);
		}
		printf("%d %d\n", rec.no, rec.ammount);

		sprintf(buf_m, "%d", rec.no);
		sprintf(buf_t, "%d", temp_no);


	}

	fclose(old_master);
	fclose(transaction);
	fclose(error);
	fclose(new_master);


	return 0;

}
