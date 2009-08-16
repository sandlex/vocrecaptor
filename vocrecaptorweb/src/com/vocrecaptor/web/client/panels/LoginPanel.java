package com.vocrecaptor.web.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.controls.AuthTextBox;
import com.vocrecaptor.web.client.controls.Validatable;
import com.vocrecaptor.web.client.menus.UserMenu;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.model.User;
import com.vocrecaptor.web.client.remote.UserService;
import com.vocrecaptor.web.client.remote.UserServiceAsync;

public class LoginPanel extends Composite {

	private final UserServiceAsync userService = GWT.create(UserService.class);
	
	public LoginPanel(ApplicationModel model_) {
		final ApplicationModel model = model_;
		
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		final AuthTextBox loginBox = new AuthTextBox("Login...");
		panel.add(loginBox);
		
		final AuthTextBox passwordBox = new AuthTextBox("Password...");
		panel.add(passwordBox);
		
		HorizontalPanel buttons = new HorizontalPanel();
		Button signInButton = new Button("Login");
		signInButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				userService.login(loginBox.getText(), passwordBox.getText(),
						new AsyncCallback<Integer>() {

							@Override
							public void onFailure(Throwable caught) {
							}

							@Override
							public void onSuccess(Integer result) {
								
								if (result == 1) {
									loginBox.setValue(Validatable.NO_USER_WITH_SUCH_LOGIN);
									loginBox.setInvalid();
									return;
								}
								
								if (result == 2) {
									passwordBox.setValue(Validatable.WRONG_PASSWORD);
									passwordBox.setInvalid();
									return;
								}

								model.setUser(new User(loginBox.getText(), passwordBox.getText()));
								RootPanel.get("menu").clear();
								RootPanel.get("menu").add(new UserMenu(model));
								RootPanel.get("centralPart").clear();
								RootPanel.get("centralPart").add(new HomePanel());
							}
						});
			}
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
	//Window.Location.replace(CONTEXT_URL + "/install.jsp");

}
