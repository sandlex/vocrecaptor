package com.vocrecaptor.web.client.utils;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class ProgressNotifier {

	public static final String LOADING = "Loading...";
	
	static public void show(String message) {
		RootPanel.get("error").add(new HTML("<font style=\"background: #FFF1A8; padding-left: 5px; padding-right: 5px;\">" + message + "</font>"));
	}
	
	static public void hide() {
		RootPanel.get("error").clear();
	}

}
