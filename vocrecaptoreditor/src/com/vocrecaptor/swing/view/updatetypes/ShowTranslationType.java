package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.StringUpdateType;

/**
 * A notification Model sends when translation is going to be displayed in RecapPanel
 * during recaping.
 * 
 * @author Alexey Peskov
 */
public class ShowTranslationType extends StringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public ShowTranslationType(String value) {
		super(value);
	}

}
