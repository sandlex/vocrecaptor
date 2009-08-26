package com.vocrecaptor.web.client.utils;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class ErrorNotifier {
	
	public static final String SOMETHING_WENT_WRONG = "Error. Something went wrong";
	public static final String WRONG_FILE_FORMAT = "Wrong file format cannot be uploaded";
	
	//TODO Make notification div overlapping the menu (via z-index whatsoever)
	
	static public void showError(String message) {
		RootPanel.get("error").add(new HTML("<font style=\"background: #FFF1A8; padding-left: 5px; padding-right: 5px;\">" + message + "</font>"));
		
		//FIXME Refactor
		class CountdownTimer extends Timer {

			public CountdownTimer() {
				schedule(3000);
			}
			
			@Override
			public void run() {
				RootPanel.get("error").clear();
			}
			
		}

		CountdownTimer timer = new CountdownTimer();
	}

}
