package command;

import strategy.Client;

public class SearchRestrictDataCommand implements Commander{

	Search search;
	
	public SearchRestrictDataCommand(Search search) {
		this.search = search;
	}
	
	@Override
	public void excute() {
		search.SearchRestrictData(Client.getTable());
		
	}

}
