package com.vocrecaptor.web.client.utils;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class ErrorNotifier {
	
	public static final String SOMETHING_WENT_WRONG = "Error. Something went wrong";
	public static final String WRONG_FILE_FORMAT = "Wrong file format cannot be uploaded";
	public static final String DICTIONARY_FILE_IS_TOO_LARGE = "Dictionary file is too large (about 900Kb) for this GAE quota rules";
	public static final String FILE_IS_NOT_SPECIFIED = "Dictionary file is not specified";
	public static final String DICTIONARY_DESCRIPTION_IS_NOT_SPECIFIED = "Please, add some description";
	
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

	static public void showMessage(String message) {
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
