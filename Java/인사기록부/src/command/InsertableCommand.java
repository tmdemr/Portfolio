package command;

import strategy.Client;

public class InsertableCommand implements Commander {
	
	Insert insert;
	
	public InsertableCommand(Insert insert) {
		this.insert = insert;
	}

	@Override
	public void excute() {
		insert.Insertable(Client.getTextField(), Client.getTextField2(), Client.getTextField3(), Client.getTextField4(), Client.getTable());
		
	}

}
