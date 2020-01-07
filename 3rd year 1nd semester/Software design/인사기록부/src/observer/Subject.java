package observer;

import java.util.List;

public abstract class Subject {
	
	protected List<Observer> observers;
	
	public abstract void registerObserver(Observer o);

    public abstract void removeObserver(Observer o);

    public abstract void notifyObserver();
}
