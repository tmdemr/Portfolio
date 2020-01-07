package command;

import strategy.Client;

public class UpdatableCommand implements Commander{

	Update update;
	
	public UpdatableCommand(Update update) {
		this.update = update;
	}
	
	@Override
	public void excute() {
		update.Updatable(Client.getTextField(), Client.getTextField2(), Client.getTextField3(), Client.getTextField4(), Client.getTable());
		
	}

}
