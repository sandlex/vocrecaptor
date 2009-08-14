package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

public class ExercisesPanel extends Composite {

	private FlexTable table;
	
	public ExercisesPanel() {
		
		table = new FlexTable();
		initWidget(table);
	}

}
