package command;

import strategy.Client;

public class SearchRestrictDataCommand2 implements Commander{

	Search search;
	
	public SearchRestrictDataCommand2(Search search) {
		this.search = search;
	}
	
	@Override
	public void excute() {
		search.SearchRestrictData2(Client.getTable());
		
	}
	
}
