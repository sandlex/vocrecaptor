package com.vocrecaptor.web.client.menus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.controls.HyperlinkMenuItem;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.DevicesPanel;
import com.vocrecaptor.web.client.panels.DictionariesPanel;
import com.vocrecaptor.web.client.panels.StartPanel;
import com.vocrecaptor.web.client.panels.StatisticsPanel;
import com.vocrecaptor.web.client.remote.StaticContentService;
import com.vocrecaptor.web.client.remote.StaticContentServiceAsync;

public class UserMenu extends Composite {

	private final StaticContentServiceAsync staticContentService = GWT.create(StaticContentService.class);
	
	public UserMenu(ApplicationModel model_) {
		final ApplicationModel model = model_;
		
		HorizontalPanel panel = new HorizontalPanel();
		
		HyperlinkMenuItem exercises = new HyperlinkMenuItem("Exercises", new Command() {

			@Override
			public void execute() {
				loadStaticHtmlContent("vocrecaptorexercises.html");
				//RootPanel.get("centralPart").clear();
				//RootPanel.get("centralPart").add(new ExercisesPanel());
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

	private void loadStaticHtmlContent(String fileName) {
		staticContentService.getHtmlContent(fileName, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(String result) {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new HTMLPanel(result));
			}
		});		
	}
	
}
