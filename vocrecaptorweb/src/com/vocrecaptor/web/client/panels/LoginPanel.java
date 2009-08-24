package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vocrecaptor.web.client.controls.Validatable;
import com.vocrecaptor.web.client.menus.UserMenu;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.transferobjects.UserTransferObject;

public class LoginPanel extends AbstractAuthPanel {

	public LoginPanel(ApplicationModel model) {
		super(model);
	}

	@Override
	protected Widget getAdditionalContorls() {
		return null;
	}

	@Override
	protected void setCancelAction() {
		RootPanel.get("centralPart").clear();
		RootPanel.get("centralPart").add(new StartPanel());
	}

	@Override
	protected void setOkAction() {
		userService.find(loginBox.getText(), passwordBox.getText(),
				new AsyncCallback<Long>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(Long result) {
						
						if (result == -1L) {
							loginBox.setValue(Validatable.NO_USER_WITH_SUCH_LOGIN);
							loginBox.setInvalid();
							return;
						}
						
						if (result == -2L) {
							passwordBox.setValue(Validatable.WRONG_PASSWORD);
							passwordBox.setInvalid();
							return;
						}

						model.setUser(new UserTransferObject(result, loginBox.getText(), passwordBox.getText()));
						RootPanel.get("menu").clear();
						RootPanel.get("menu").add(new UserMenu(model));
						RootPanel.get("centralPart").clear();
						RootPanel.get("centralPart").add(new HomePanel(model));
					}
				});
	}

}
