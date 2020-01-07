package command;

public class UpdateDisableCommand implements Commander{

	Update update;
	
	public UpdateDisableCommand(Update update) {
		this.update = update;
	}
	
	@Override
	public void excute() {
		update.UpdateDisable();
		
	}

}
