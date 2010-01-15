package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.IntegerUpdateType;

/**
 * A notification Model sends when example displaying time for recaping is being restored from 
 * configuration file on application initialization.
 * 
 * @author Alexey Peskov
 */
public class RestoreExampleTimeType extends IntegerUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public RestoreExampleTimeType(Integer value) {
		super(value);
	}

}
