package com.vocrecaptor.web.client.panels;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ExercisesPanel extends Composite {

	private class LegendedImage extends VerticalPanel{
		
		public LegendedImage(final String link, String description) {
			setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
			setWidth("295px");
			Image image = new Image("/images/webstart.gif");
			image.setStylePrimaryName("handy");
			image.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Window.open(link, "_blank", "");
				}
			});
			add(image);
			add(new HTML(description));
		}
		
	}
	
	public ExercisesPanel() {

		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		panel.add(new LegendedImage("/exercises/Vocrecaptorexercises.html", 
				"Click to start exercises in a new window inside you web-browser"));
		panel.add(new LegendedImage("/exercises/Vocrecaptorexercises.jnlp", 
				"Click to download application and start exercises from you desktop"));
		
		initWidget(panel);
		setWidth("590px");
		setHeight("300px");
	}

}
