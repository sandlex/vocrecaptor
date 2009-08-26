package com.vocrecaptor.web.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.remote.DictionaryService;
import com.vocrecaptor.web.client.remote.DictionaryServiceAsync;
import com.vocrecaptor.web.client.remote.LanguageService;
import com.vocrecaptor.web.client.remote.LanguageServiceAsync;
import com.vocrecaptor.web.client.remote.UserService;
import com.vocrecaptor.web.client.remote.UserServiceAsync;

public class StartPanel extends Composite {

	private final UserServiceAsync userService = GWT.create(UserService.class);
	private final LanguageServiceAsync languageService = GWT.create(LanguageService.class);
	private final DictionaryServiceAsync dictionaryService = GWT.create(DictionaryService.class);
	
	
	public StartPanel() {
		
		final VerticalPanel panel = new VerticalPanel();
		
		userService.count(new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(Integer result) {
						
						panel.add(new HTML("Registered users: " + result));
						
						languageService.count(new AsyncCallback<Integer>() {

							@Override
							public void onFailure(Throwable caught) {
								caught.printStackTrace();
							}

							@Override
							public void onSuccess(Integer result) {
								
								panel.add(new HTML("Languages: " + result));
								
								dictionaryService.count(new AsyncCallback<Integer>() {

									@Override
									public void onFailure(Throwable caught) {
										caught.printStackTrace();
									}

									@Override
									public void onSuccess(Integer result) {
										
										panel.add(new HTML("Dictionaries: " + result));
									}
								});

							}
						});

					}
				});
		
		initWidget(panel);
	}

}
