package com.vocrecaptor.web.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vocrecaptor.web.client.controls.AuthTextBox;
import com.vocrecaptor.web.client.model.ApplicationModel;
import com.vocrecaptor.web.client.remote.UserService;
import com.vocrecaptor.web.client.remote.UserServiceAsync;

public abstract class AbstractAuthPanel extends FixedWidthPanel {

	protected final UserServiceAsync userService = GWT.create(UserService.class);
	
	protected AuthTextBox loginBox;
	protected AuthTextBox passwordBox;
	
	public AbstractAuthPanel(ApplicationModel model) {
		super(model);
		
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		panel.add(getCommonControls());
		
		initWidget(panel);
		setWidth("650px");
		setHeight("300px");
	}
	
	private VerticalPanel getCommonControls() {
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		loginBox = new AuthTextBox("Login...", false);
		panel.add(loginBox);
		
		passwordBox = new AuthTextBox("Password...", true);
		panel.add(passwordBox);
		
		Widget widget = getAdditionalContorls();
		if (widget != null)
			panel.add(widget);
		
		
		HorizontalPanel buttons = new HorizontalPanel();
		Button signInButton = new Button("&nbsp;&nbsp;&nbsp;Ok&nbsp;&nbsp;&nbsp;");
		signInButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setOkAction();
			}
		});
		buttons.add(signInButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setCancelAction();
			}
		});
		buttons.add(cancelButton);
		panel.add(buttons);
		
		return panel;
	}
	
	abstract protected Widget getAdditionalContorls();
	
	abstract protected void setOkAction();
	
	abstract protected void setCancelAction();
	
	//Window.Location.replace(CONTEXT_URL + "/install.jsp");
}
