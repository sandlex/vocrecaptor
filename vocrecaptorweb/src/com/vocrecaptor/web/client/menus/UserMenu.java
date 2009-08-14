package com.vocrecaptor.web.client.menus;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.panels.DevicesPanel;
import com.vocrecaptor.web.client.panels.DictionariesPanel;
import com.vocrecaptor.web.client.panels.ExercisesPanel;
import com.vocrecaptor.web.client.panels.StartPanel;
import com.vocrecaptor.web.client.panels.StatisticsPanel;

public class UserMenu extends Composite {

	public UserMenu() {
		MenuBar menu = new MenuBar();
		menu.setStylePrimaryName("voc-gwt-MenuBar");
		
		menu.addItem("Exercises", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new ExercisesPanel());
			}
		});

		menu.addSeparator();
		
		menu.addItem("Dictionaries", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new DictionariesPanel());
			}
		});

		menu.addItem("Statistics", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new StatisticsPanel());
			}
		});

		menu.addItem("Devices", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new DevicesPanel());
			}
		});

		menu.addItem("Logout", new Command() {

			@Override
			public void execute() {
				RootPanel.get("menu").clear();
				RootPanel.get("menu").add(new MainMenu());
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new StartPanel());
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
