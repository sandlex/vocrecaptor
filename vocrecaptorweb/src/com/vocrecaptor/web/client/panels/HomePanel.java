package com.vocrecaptor.web.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.LanguageService;
import com.vocrecaptor.web.client.remote.LanguageServiceAsync;

public class HomePanel extends Composite {

//	private ApplicationModel model;

	public HomePanel(ApplicationModel model) {
//		this.model = model;
		
		/*String script =
		"<script src=\"http://dl.javafx.com/1.1/dtfx.js\"></script>" +
		"<script>" +
		    "javafx(" +
		        "{" +
		             " archive: \"/Vocrecaptorexercises.jar\"," +
		             " draggable: true," +
		              "width: 600," +
		              "height: 600," +
		              "code: \"com.vocrecaptor.fx.Main\","+
		              "name: \"Vocrecaptorexercises\""+
		        "}"+
		    ");"+
		"</script>";*/
		
		if ("Vocrecaptor".equalsIgnoreCase(model.getUser().getLogin())) {

			Button createLanguages = new Button("Create languages");
			createLanguages.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					initLanguages();
				}
			});
			
			initWidget(createLanguages);
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
	
}
