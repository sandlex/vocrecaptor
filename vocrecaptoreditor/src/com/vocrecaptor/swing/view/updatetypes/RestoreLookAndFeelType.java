package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.StringUpdateType;

/**
 * A notification Model sends when look and feel type is being restored from 
 * configuration file on application initialization.
 * 
 * @author Alexey Peskov
 */
public class RestoreLookAndFeelType extends StringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public RestoreLookAndFeelType(String value) {
		super(value);
	}

}
