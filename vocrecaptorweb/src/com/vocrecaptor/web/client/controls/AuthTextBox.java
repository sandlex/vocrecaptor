package com.vocrecaptor.web.client.controls;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.TextBox;

public class AuthTextBox extends TextBox implements Validatable {

	public AuthTextBox(final String emptyValue) {
		
		setStylePrimaryName("large_input_valid");
		
		setValue(emptyValue);
		
		addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent arg0) {
				setValid();
				if (emptyValue.equals(getValue())
						|| PASSWORDS_ARE_NOT_MATCHING.equals(getValue())
						|| LOGIN_IS_NOT_AVAILABLE.equals(getValue())
						|| NO_USER_WITH_SUCH_LOGIN.equals(getValue())
						|| WRONG_PASSWORD.equals(getValue())) {
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

	@Override
	public void setInvalid() {
		setStylePrimaryName("large_input_invalid");
	}

	@Override
	public void setValid() {
		setStylePrimaryName("large_input_valid");
		
	}
}
