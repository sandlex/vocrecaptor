package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.BooleanUpdateType;

/**
 * A notification Model sends when random order flag for recaping is being restored from 
 * configuration file on application initialization.
 * 
 * @author Alexey Peskov
 */
public class RestoreRandomOrderType extends BooleanUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public RestoreRandomOrderType(Boolean value) {
		super(value);
	}

}
