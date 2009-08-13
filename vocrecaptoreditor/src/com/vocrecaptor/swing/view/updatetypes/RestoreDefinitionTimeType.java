package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.IntegerUpdateType;

/**
 * A notification Model sends when definition displaying time for recaping is being restored from 
 * configuration file on application initialization.
 * 
 * @author Alexey Peskov
 */
public class RestoreDefinitionTimeType extends IntegerUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public RestoreDefinitionTimeType(Integer value) {
		super(value);
	}

}
