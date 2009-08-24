package com.vocrecaptor.web.client.controls;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Hyperlink;

public class HyperlinkMenuItem extends Hyperlink {
	
	public HyperlinkMenuItem(String text, final Command command) {
		
		super(text, null);
		
		setStylePrimaryName("spacered");
		
		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				command.execute();
			}
		});
	}

}
