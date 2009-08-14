package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class StartPanel extends Composite {

	private FlexTable table;
	
	public StartPanel() {
		
		table = new FlexTable();
		initWidget(table);
	}

}
