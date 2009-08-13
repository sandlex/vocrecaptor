package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.BooleanUpdateType;

/**
 * A notification Model sends when reverse order flag for recaping is being restored from 
 * configuration file on application initialization.
 * 
 * @author Alexey Peskov
 */
public class RestoreReverseOrderType extends BooleanUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public RestoreReverseOrderType(Boolean value) {
		super(value);
	}

}
