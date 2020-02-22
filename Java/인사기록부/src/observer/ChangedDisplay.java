package observer;

public class ChangedDisplay implements Observer, DisplayElement {
	
	private int EID;
	private String Name;
	private String Surname;
	private int Age;
	private Subject PRD;
	
	public ChangedDisplay(Subject PRD) {
		this.PRD = PRD;
		this.PRD.registerObserver(this);
	}
	
	@Override
	public void update(int EID, String Name, String Surname, int Age) {
		this.EID = EID;
		this.Name = Name;
		this.Surname = Surname;
		this.Age = Age;
		display();
		
	}
	
	@Override
	public void display() {
		System.out.println("아래와 같이 바뀜");
		System.out.println("EID : " + EID);
		System.out.println("Name : " + Name);
		System.out.println("Surname : " + Surname);
		System.out.println("Age : " + Age);
	}
}
