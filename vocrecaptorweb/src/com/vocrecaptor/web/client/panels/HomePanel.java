package com.vocrecaptor.web.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.LanguageService;
import com.vocrecaptor.web.client.remote.LanguageServiceAsync;

public class HomePanel extends FixedWidthPanel {

	public HomePanel(ApplicationModel model) {
		super(model);
		
		if ("Vocrecaptor".equalsIgnoreCase(model.getUser().getLogin())) {

			VerticalPanel panel = new VerticalPanel();
			
			Button createLanguages = new Button("Create languages");
			createLanguages.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					initLanguages();
				}
			});
			panel.add(createLanguages);
			
			Button deleteLanguages = new Button("Delete languages");
			deleteLanguages.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					deleteLanguages();
				}
			});
			panel.add(deleteLanguages);

			initWidget(panel);
		} else {
			String script = "Start";
			
			initWidget(new HTML(script));
		}
		
	}
	
	private void initLanguages() {
		LanguageServiceAsync languageService = GWT.create(LanguageService.class);
		languageService.createTestData(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Integer result) {
			}
		});
	}
	
	private void deleteLanguages() {
		LanguageServiceAsync languageService = GWT.create(LanguageService.class);
		languageService.deleteAll(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Integer result) {
			}
		});
	}

}
