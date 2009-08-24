package com.vocrecaptor.web.client.menus;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.vocrecaptor.web.client.panels.StartPanel;

public class LogoImage extends Image {

	public LogoImage(String image) {
		super(image);
		setStylePrimaryName("handy");
		
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("centralPart").clear();
				RootPanel.get("centralPart").add(new StartPanel());
			}
		});
	}
}
