package com.vocrecaptor.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.menus.MainMenu;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.StartPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Vocrecaptorweb implements EntryPoint {

	// Contains application data.
	private ApplicationModel model;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		model = new ApplicationModel();
		//RootPanel.get("logo").add(new LogoImage("images/logo153.png"));
		RootPanel.get("menu").add(new MainMenu(model));
		RootPanel.get("centralPart").add(new StartPanel(model));
		//RootPanel.get("statistics").add(new StatisticsPanel());
	}
	
}
