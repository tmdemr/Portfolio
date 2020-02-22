package command;

public class InsertDisableCommand implements Commander{
	
	Insert insert;
	
	public InsertDisableCommand(Insert insert) {
		this.insert = insert;
	}

	@Override
	public void excute() {
		insert.InsertDisable();
		
	}

}
