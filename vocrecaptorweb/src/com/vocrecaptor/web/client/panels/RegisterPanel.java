package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vocrecaptor.web.client.controls.AuthTextBox;
import com.vocrecaptor.web.client.controls.Validatable;
import com.vocrecaptor.web.client.menus.UserMenu;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.transferobjects.UserTransferObject;

public class RegisterPanel extends AbstractAuthPanel {

	private AuthTextBox passwordRetypeBox;

	public RegisterPanel(ApplicationModel model) {
		super(model);
	}

	private boolean checkPasswordsMatch() {
		
		return passwordBox.getValue().equals(passwordRetypeBox.getValue());
	}

	@Override
	protected Widget getAdditionalContorls() {
		passwordRetypeBox = new AuthTextBox("Retype password...");
		
		return passwordRetypeBox;
	}

	@Override
	protected void setCancelAction() {
		RootPanel.get("centralPart").clear();
		RootPanel.get("centralPart").add(new StartPanel());
	}

	@Override
	protected void setOkAction() {
		
		
		if (!checkPasswordsMatch()) {
			
			passwordRetypeBox.setValue(Validatable.PASSWORDS_ARE_NOT_MATCHING);
			passwordRetypeBox.setInvalid();
			
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
							loginBox.setValue(Validatable.LOGIN_IS_NOT_AVAILABLE);
							loginBox.setInvalid();
						}
					}
				});				
	}

}
