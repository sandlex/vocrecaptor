package com.vocrecaptor.web.client.menus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.panels.DevicesPanel;
import com.vocrecaptor.web.client.panels.DictionariesPanel;
import com.vocrecaptor.web.client.panels.ExercisesPanel;
import com.vocrecaptor.web.client.panels.StartPanel;
import com.vocrecaptor.web.client.panels.StatisticsPanel;
import com.vocrecaptor.web.client.remote.StaticContentService;
import com.vocrecaptor.web.client.remote.StaticContentServiceAsync;

public class UserMenu extends Composite {

	private final StaticContentServiceAsync staticContentService = GWT.create(StaticContentService.class);
	
	public UserMenu(ApplicationModel model_) {
		final ApplicationModel model = model_;
		MenuBar menu = new MenuBar();
		menu.setStylePrimaryName("voc-gwt-MenuBar");
		
		menu.addItem("Exercises", new Command() {

			@Override
			public void execute() {
				loadStaticHtmlContent("vocrecaptorexercises.html");
				//RootPanel.get("centralPart").clear();
				//RootPanel.get("centralPart").add(new ExercisesPanel());
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
				model.setUser(null);
				RootPanel.get("menu").clear();
				RootPanel.get("menu").add(new MainMenu(model));
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

	private void loadStaticHtmlContent(String fileName) {
		staticContentService.getHtmlContent(fileName, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(String result) {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new HTMLPanel(result));
			}
		});		
	}
	
}
