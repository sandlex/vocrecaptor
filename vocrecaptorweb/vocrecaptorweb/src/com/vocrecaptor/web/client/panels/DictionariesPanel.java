package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class DictionariesPanel extends Composite {

	private FlexTable table;
	
	public DictionariesPanel() {
		
		table = new FlexTable();
		initWidget(table);
	}
	
}
