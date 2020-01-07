/**
 * Information의 특정 시점에 대한 내부상태 정보
 * final modify : 2019-05-26
 * @author : Kim Kwang Ho
 */

package memento;

public class Memento {	//Information의 상태정보를 가지고 있음
	private int EID;	//Information이 가지고 있는 EID
	private String Name;	//Information이 가지고 있는 Name
	private String Surname;	//Information이 가지고 있는 Surname
	private int Age;		//Information이 가지고 있는 Age
	
	public Memento(int EID, String Name, String Surname, int Age) {
		this.EID = EID;
		this.Name = Name;
		this.Surname = Surname;
		this.Age = Age;
	}
	public int getEID() {
		return this.EID;
	}
	public String getName() {
		return this.Name;
	}
	public String getSurname() {
		return this.Surname;
	}
	public int getAge() {
		return this.Age;
	}
}
