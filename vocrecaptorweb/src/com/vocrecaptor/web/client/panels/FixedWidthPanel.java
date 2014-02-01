package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.vocrecaptor.web.client.model.ApplicationModel;

public abstract class FixedWidthPanel extends Composite {
	
	protected ApplicationModel model;

	public FixedWidthPanel() {
		//FIXME Use this initialization
//		setWidth("650px");
//		setHeight("300px");
	}
	
	public FixedWidthPanel(ApplicationModel model) {
		this();
		this.model = model;
	}

}
