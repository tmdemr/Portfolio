package command;

import strategy.Client;

public class DeletableCommand implements Commander{
	
	Delete delete;

	public DeletableCommand(Delete delete) {
		this.delete = delete;
	}
	
	@Override
	public void excute() {
		delete.Deletable(Client.getTextField(), Client.getTable());
		
	}

}
