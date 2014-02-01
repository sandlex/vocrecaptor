package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.IntegerUpdateType;

/**
 * A notification Model sends when translation displaying time for recaping is being restored from 
 * configuration file on application initialization.
 * 
 * @author Alexey Peskov
 */
public class RestoreTranslationTimeType extends IntegerUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public RestoreTranslationTimeType(Integer value) {
		super(value);
	}

}
