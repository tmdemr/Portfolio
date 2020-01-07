package observer;

import java.util.LinkedList;

/**
 *
 * @author 이준혁
 */
public class PRD extends Subject {

	private int EID;
	private String Name;
	private String Surname;
	private int Age;

	public PRD() {
		observers = new LinkedList<>();
	}

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	public void notifyObserver() {
		observers.forEach((observer) -> {
			observer.update(EID, Name, Surname, Age);
		});
	}

	public void measurementChanged() {
		notifyObserver();
	}

	public void setMeasurements(int EID, String Name, String Surname, int Age) {
		this.EID = EID;
		this.Name = Name;
		this.Surname = Surname;
		this.Age = Age;

		measurementChanged();
	}
}
