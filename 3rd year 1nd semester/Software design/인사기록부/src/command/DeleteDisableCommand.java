package command;

public class DeleteDisableCommand implements Commander{

	Delete delete;
	
	public DeleteDisableCommand(Delete delete) {
		this.delete = delete;
	}
	
	@Override
	public void excute() {
		delete.DeleteDisable();
		
	}

}
