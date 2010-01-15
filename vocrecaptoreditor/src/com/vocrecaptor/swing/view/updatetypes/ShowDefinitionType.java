package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.StringUpdateType;

/**
 * A notification Model sends when definition is going to be displayed in RecapPanel
 * during recaping.
 * 
 * @author Alexey Peskov
 */
public class ShowDefinitionType extends StringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public ShowDefinitionType(String value) {
		super(value);
	}

}
