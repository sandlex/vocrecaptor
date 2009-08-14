package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class HomePanel extends Composite {

	private FlexTable table;
	
	public HomePanel() {
		
		table = new FlexTable();
		initWidget(table);
	}

}
