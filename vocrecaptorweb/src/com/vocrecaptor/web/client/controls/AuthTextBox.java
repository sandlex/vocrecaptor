package com.vocrecaptor.web.client.controls;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.TextBox;

public class AuthTextBox extends TextBox implements Validatable {

	public AuthTextBox(final String emptyValue, boolean isPasswordField) {

		setStylePrimaryName("large_input_valid");

		setValue(emptyValue);

		addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent arg0) {
				setValid();
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

//		if (isPasswordField) {
//			addKeyUpHandler(new KeyUpHandler() {
//
//				@Override
//				public void onKeyUp(KeyUpEvent event) {
//					if (!emptyValue.equals(getValue())) {
//						int pos = getCursorPos();
//						String value = "";
//						for (int i = 0; i < getValue().length(); i++) {
//							value += "*";
//						}
//						setValue(value);
//						setCursorPos(pos);
//					}
//				}
//			});
//		}

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
