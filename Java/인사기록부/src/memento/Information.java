/**
 * ���¸� �����ϰ� �����ϴ� �����͸� ������ Ŭ����
 * final modify : 2019-05-26
 * @author : Kim Kwang Ho
 */

package memento;

public class Information {
	private int EID;	//Information이 가지고 있는 EID
	private String Name;	//Information이 가지고 있는 Name
	private String Surname;	//Information이 가지고 있는 Surname
	private int Age;		//Information이 가지고 있는  Age
	
	public void RestorMemento(Memento memento) {	//Memento복원(상태 복구)
		EID = memento.getEID();
		Name = memento.getName();
		Surname = memento.getSurname();
		Age = memento.getAge();
	}
	
	public Information set_EID(int EID) {	//EID값 지정
		this.EID = EID;
		return this;
	}
	public Information set_Name(String Name) {	//Name값 지정
		this.Name = Name;
		return this;
	}
	public Information set_Surname(String Surname) {	//Surname값 지정
		this.Surname = Surname;
		return this;
	}
	public Information set_Age(int Age) {	//Age값 지정
		this.Age = Age;
		return this;
	}
	public Memento build() {	//Memento생성(상태 저장)
		return new Memento(EID,Name,Surname,Age);
	}

	public int getEID() {
		return EID;
	}
	public String getName() {
		return Name;
	}
	public String getSurname() {
		return Surname;
	}
	public int getAge() {
		return Age;
	}
}
