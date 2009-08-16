package com.vocrecaptor.web.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.controls.AuthTextBox;
import com.vocrecaptor.web.client.controls.Validatable;
import com.vocrecaptor.web.client.menus.UserMenu;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.UserService;
import com.vocrecaptor.web.client.remote.UserServiceAsync;

public class RegisterPanel extends Composite {

	private final UserServiceAsync userService = GWT.create(UserService.class);
	
	private AuthTextBox loginBox; 
	
	private AuthTextBox passwordBox;
	
	private AuthTextBox passwordRetypeBox;

	public RegisterPanel(ApplicationModel model_) {
		final ApplicationModel model = model_;
		
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		loginBox = new AuthTextBox("Login...");
		panel.add(loginBox);
		
		passwordBox = new AuthTextBox("Password...");
		panel.add(passwordBox);
		
		passwordRetypeBox = new AuthTextBox("Retype password...");
		panel.add(passwordRetypeBox);

		Panel buttons = new HorizontalPanel();
		//panel.setHorizontalAlignment(HorizontalAlignmentConstant.ALIGN_CENTER);
		Button signInButton = new Button("Register");
		signInButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				
				if (!checkPasswordsMatch()) {
					
					passwordRetypeBox.setValue(Validatable.PASSWORDS_ARE_NOT_MATCHING);
					passwordRetypeBox.setInvalid();
					
					return;
				}
				
				if (!checkLoginIsAvailable()) {
					
					loginBox.setValue(Validatable.LOGIN_IS_NOT_AVAILABLE);
					loginBox.setInvalid();

					return;
				}
				
				
				userService.register(loginBox.getText(), passwordBox.getText(),
						new AsyncCallback<Integer>() {

							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Integer result) {
								if (result == null) {
								} else {
								}
							}
						});
				
				
				RootPanel.get("menu").clear();
				RootPanel.get("menu").add(new UserMenu(model));
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new HomePanel());
			}

//			private boolean checkLoginIsAvailable() {
//				// TODO Auto-generated method stub
//				return false;
//			}
		});
		buttons.add(signInButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new StartPanel());
			}
		});
		buttons.add(cancelButton);
		panel.add(buttons);

		initWidget(panel);
		setWidth("600px");
	}

	private boolean checkPasswordsMatch() {
		
		return passwordBox.getValue().equals(passwordRetypeBox.getValue());
	}

	private Boolean statusObject;
	
	private boolean checkLoginIsAvailable() {

		statusObject = false;
		
		userService.checkLogin(loginBox.getText(),
				new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Boolean result) {
						statusObject = result;
					}
				});
		
		return statusObject;
	}
	
}
