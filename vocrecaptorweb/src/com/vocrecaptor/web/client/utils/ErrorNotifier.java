package com.vocrecaptor.web.client.utils;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class ErrorNotifier {
	
	//TODO Make notification div overlapping the menu (via z-index whatsoever)
	
	static public void showError(String message) {
		
		RootPanel.get("error").add(new HTML(message));
		
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
