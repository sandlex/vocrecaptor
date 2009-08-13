package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.StringUpdateType;

/**
 * A notification Model sends when example is going to be displayed in RecapPanel
 * during recaping.
 * 
 * @author Alexey Peskov
 */
public class ShowExampleType extends StringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public ShowExampleType(String value) {
		super(value);
	}

}
