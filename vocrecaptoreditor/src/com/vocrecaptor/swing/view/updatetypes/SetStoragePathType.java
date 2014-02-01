package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.StringUpdateType;

/**
 * A notification Model sends when user chooses a new storage file.
 * 
 * @author Alexey Peskov
 */
public class SetStoragePathType extends StringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public SetStoragePathType(String value) {
		super(value);
	}

}
