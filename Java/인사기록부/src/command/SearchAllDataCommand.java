package command;

import strategy.Client;

public class SearchAllDataCommand implements Commander{

	Search search;
	
	public SearchAllDataCommand(Search search) {
		this.search = search;
	}
	
	@Override
	public void excute() {
		search.SearchAllData(Client.getTable());
		
	}

}
