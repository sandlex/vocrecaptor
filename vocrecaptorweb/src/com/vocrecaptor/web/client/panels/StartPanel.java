package com.vocrecaptor.web.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.vocrecaptor.web.client.controls.Validatable;
import com.vocrecaptor.web.client.menus.UserMenu;
import com.vocrecaptor.web.client.model.User;
import com.vocrecaptor.web.client.remote.UserService;
import com.vocrecaptor.web.client.remote.UserServiceAsync;

public class StartPanel extends Composite {

	private final UserServiceAsync userService = GWT.create(UserService.class);
	
	public StartPanel() {
		
		final VerticalPanel panel = new VerticalPanel();
		
		userService.getUserCount(new AsyncCallback<Integer>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Integer result) {
						
						panel.add(new HTML("Registered users: " + result));
					}
				});
		
		initWidget(panel);
	}

}
