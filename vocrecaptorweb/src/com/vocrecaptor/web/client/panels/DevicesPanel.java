package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class DevicesPanel extends Composite {

	private FlexTable table;
	
	public DevicesPanel() {
		
		table = new FlexTable();
		initWidget(table);
	}
	
}
