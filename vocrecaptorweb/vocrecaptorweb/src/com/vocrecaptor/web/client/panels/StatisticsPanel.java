package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class StatisticsPanel extends Composite {

	private FlexTable table;
	
	public StatisticsPanel() {
		
		table = new FlexTable();
		initWidget(table);
	}

}
