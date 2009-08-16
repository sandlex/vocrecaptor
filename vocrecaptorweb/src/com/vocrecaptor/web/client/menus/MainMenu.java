package com.vocrecaptor.web.client.menus;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.LoginPanel;
import com.vocrecaptor.web.client.panels.RegisterPanel;

public class MainMenu extends Composite {

	public MainMenu(ApplicationModel model_) {
		final ApplicationModel model = model_;
		MenuBar menu = new MenuBar();
		menu.setStylePrimaryName("voc-gwt-MenuBar");
		
		menu.addItem("Login", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new LoginPanel(model));
			}
		});

		menu.addSeparator();
		
		menu.addItem("Register", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new RegisterPanel(model));
			}
		});

		initWidget(menu);
//		Hyperlink link = new Hyperlink("Login", null);
//		link.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		initWidget(link);
	}
		
}
