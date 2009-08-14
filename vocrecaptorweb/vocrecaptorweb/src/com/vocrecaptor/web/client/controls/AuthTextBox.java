package com.vocrecaptor.web.client.controls;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.TextBox;

public class AuthTextBox extends TextBox {

	public AuthTextBox(final String emptyValue) {
		
		setStylePrimaryName("large_input");
		
		setValue(emptyValue);
		
		addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent arg0) {
				if (emptyValue.equals(getValue())) {
					setValue("");
				}
			}
		});
		
		addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				if ("".equals(getValue())) {
					setValue(emptyValue);
				}
			}
		});
		
	}
}
