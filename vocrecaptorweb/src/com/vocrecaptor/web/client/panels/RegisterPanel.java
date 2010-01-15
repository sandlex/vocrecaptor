package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vocrecaptor.web.client.controls.AuthTextBox;
import com.vocrecaptor.web.client.controls.Validatable;
import com.vocrecaptor.web.client.menus.UserMenu;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.transferobjects.UserTransferObject;
import com.vocrecaptor.web.client.utils.ErrorNotifier;

public class RegisterPanel extends AbstractAuthPanel {

	private AuthTextBox passwordRetypeBox;
	//private AuthTextBox codeBox;

	public RegisterPanel(ApplicationModel model) {
		super(model);
	}

	private boolean checkPasswordsMatch() {
		
		return passwordBox.getValue().equals(passwordRetypeBox.getValue());
	}

	@Override
	protected Widget getAdditionalContorls() {
		VerticalPanel panel = new VerticalPanel();
		
		passwordRetypeBox = new AuthTextBox("Retype password...", true);
		panel.add(passwordRetypeBox);
		
		/*VerticalPanel code = new VerticalPanel();
		codeBox = new AuthTextBox("Enter a code...");
		code.add(codeBox);
		Hyperlink what = new Hyperlink("What is a code?", null); 
		what.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final DialogBox dialogBox = new DialogBox();
				dialogBox.setStylePrimaryName("voc-gwt-DialogBox");
				dialogBox.setModal(true);
				dialogBox.setAutoHideEnabled(true);
				dialogBox.setText("Registration code");
				dialogBox.setAnimationEnabled(true);
				dialogBox.setWidget(new HTML("<font color=\"gray\">Since Vocrecaptor Web is currently in active<br>development stage registration is temporarily<br>available only by invitation codes. <br>To get one please send a request to<br><a href=\"mailto:aspeskov@gmail.com\">aspeskov@gmail.com</a>.</font>"));
				dialogBox.center();
			}
		});
		code.add(what);
		
		panel.add(code);*/
		
		return panel;
	}

	@Override
	protected void setCancelAction() {
		RootPanel.get("centralPart").clear();
		RootPanel.get("centralPart").add(new StartPanel(model));
	}

	@Override
	protected void setOkAction() {
		
		/*if (!"ZvU4wMsTsVwRJ".equals(codeBox.getValue())) {
			ErrorNotifier.showError(Validatable.INCORRECT_CODE);
			return;
		}*/
		
		if (!checkPasswordsMatch()) {
			ErrorNotifier.showError(Validatable.PASSWORDS_ARE_NOT_MATCHING);
			return;
		}
		
		userService.find(loginBox.getText(),
				new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							userService.create(loginBox.getText(), passwordBox.getText(),
									new AsyncCallback<Long>() {

										@Override
										public void onFailure(Throwable caught) {
											caught.printStackTrace();
										}

										@Override
										public void onSuccess(Long result) {
											
											model.setUser(new UserTransferObject(result, loginBox.getText(), passwordBox.getText()));
											RootPanel.get("menu").clear();
											RootPanel.get("menu").add(new UserMenu(model));
											RootPanel.get("centralPart").clear();
											RootPanel.get("centralPart").add(new HomePanel(model));
										}
									});
						} else {
							ErrorNotifier.showError(Validatable.LOGIN_IS_NOT_AVAILABLE);
						}
					}
				});				
	}

}
