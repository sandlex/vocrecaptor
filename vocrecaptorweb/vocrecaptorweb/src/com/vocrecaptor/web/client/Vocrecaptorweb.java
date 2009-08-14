package com.vocrecaptor.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.menus.MainMenu;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Vocrecaptorweb implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		RootPanel.get("menu").add(new MainMenu());
		RootPanel.get("version").add(new Label("v1.2.3"));
	}
}
