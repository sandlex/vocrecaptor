package com.vocrecaptor.web.client.panels;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.vocrecaptor.web.client.controls.AuthTextBox;
import com.vocrecaptor.web.client.menus.UserMenu;

public class LoginPanel extends Composite {

	private FlexTable table;
	
	public LoginPanel() {
		
		table = new FlexTable();

		final TextBox loginBox = new AuthTextBox("Login...");
		table.setWidget(0, 0, loginBox);
		
		final TextBox passwordBox = new AuthTextBox("Password...");
		table.setWidget(1, 0, passwordBox);
		
		Panel panel = new HorizontalPanel();
		//panel.setHorizontalAlignment(HorizontalAlignmentConstant.ALIGN_CENTER);
		Button signInButton = new Button("Login");
		signInButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("menu").clear();
				RootPanel.get("menu").add(new UserMenu());
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new HomePanel());
			}
		});
		panel.add(signInButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new StartPanel());
			}
		});
		panel.add(cancelButton);
		table.setWidget(2, 0, panel);

		initWidget(table);
	}
	
}
