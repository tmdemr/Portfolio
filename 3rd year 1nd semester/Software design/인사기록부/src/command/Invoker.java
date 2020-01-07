package command;

public class Invoker {
	
	Commander[] commander;
	
	public Invoker() {
		commander = new Commander[4];
	}

	public void SetCommander(int index,Commander commander) {
		this.commander[index] = commander;
	}
	
	public void ButtonWasPressed(int index) {
		commander[index].excute();
	}
	
}
