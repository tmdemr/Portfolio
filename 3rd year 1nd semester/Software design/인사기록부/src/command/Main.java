package command;

import GUI.LoginGUI;

public class Main {

	public static void main(String[] args) {
	
		Login.DBConnection();
		LoginGUI a = new LoginGUI();
		a.setVisible(true);
		
	}

}
