package com.vocrecaptor.web.client.menus;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.controls.HyperlinkMenuItem;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.LoginPanel;
import com.vocrecaptor.web.client.panels.RegisterPanel;

public class MainMenu extends Composite {

	public MainMenu(ApplicationModel model_) {
		final ApplicationModel model = model_;
		
		HorizontalPanel panel = new HorizontalPanel();
		HyperlinkMenuItem login = new HyperlinkMenuItem("Login", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new LoginPanel(model));
			}
			
		});
		
		HyperlinkMenuItem register = new HyperlinkMenuItem("Register", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new RegisterPanel(model));
			}
			
		});

		panel.add(login);
		panel.add(register);
		
		initWidget(panel);
	}

}
