package com.vocrecaptor.web.client.panels;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ExercisesPanel extends Composite {

	final private String SCRIPT = "<script src=\"http://dl.javafx.com/1.1/dtfx.js\"></script>" +
	"<script>" +
	    "javafx(" +
	        "{" +
	        "      archive: \"Vocrecaptorexercises.jar\"," +
	        "      draggable: true," +
	        "      width: 600," +
	        "      height: 600," +
	        "      code: \"com.vocrecaptor.fx.Main\"," +
	        "      name: \"Vocrecaptorexercises\"" +
	        "}" +
	    ");" +
	"</script>";
	
	public ExercisesPanel() {

		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		
		panel.add(new HTML(SCRIPT));
		
		initWidget(panel);
	}

}
