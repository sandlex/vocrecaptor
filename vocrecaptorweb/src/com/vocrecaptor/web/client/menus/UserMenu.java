package com.vocrecaptor.web.client.menus;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.controls.HyperlinkMenuItem;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.DevicesPanel;
import com.vocrecaptor.web.client.panels.DictionariesPanel;
import com.vocrecaptor.web.client.panels.ExercisesPanel;
import com.vocrecaptor.web.client.panels.StartPanel;
import com.vocrecaptor.web.client.panels.StatisticsPanel;

public class UserMenu extends Composite {

	public UserMenu(ApplicationModel model_) {
		final ApplicationModel model = model_;
		
		HorizontalPanel panel = new HorizontalPanel();
		
		//FIXME Refactor this: create abstract menu with common item
		HyperlinkMenuItem exercises = new HyperlinkMenuItem("Exercises", new Command() {

			@Override
			public void execute() {
//				loadStaticHtmlContent("vocrecaptorexercises.html");
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new ExercisesPanel());
			}
			
		});
		
		HyperlinkMenuItem dictionaries = new HyperlinkMenuItem("Dictionaries", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new DictionariesPanel(model));
			}
			
		});
		
		HyperlinkMenuItem statistics = new HyperlinkMenuItem("Statistics", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new StatisticsPanel());
			}
			
		});
		
		HyperlinkMenuItem devices = new HyperlinkMenuItem("Devices", new Command() {

			@Override
			public void execute() {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new DevicesPanel());
			}
			
		});
		
		HyperlinkMenuItem logout = new HyperlinkMenuItem("Logout", new Command() {

			@Override
			public void execute() {
				model.setUser(null);
				RootPanel.get("menu").clear();
				RootPanel.get("menu").add(new MainMenu(model));
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new StartPanel());
			}
			
		});
		
		panel.add(exercises);
		panel.add(dictionaries);
		panel.add(statistics);
		panel.add(devices);
		panel.add(logout);

		initWidget(panel);
	}

}
