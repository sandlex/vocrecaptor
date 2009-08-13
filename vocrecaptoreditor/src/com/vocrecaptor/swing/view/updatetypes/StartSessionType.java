package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.StringStringUpdateType;

/**
 * A notification Model sends when new session in Editor is being started.
 * 
 * @author Alexey Peskov
 */
public class StartSessionType extends StringStringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 * @param oldValue old value
	 */
	public StartSessionType(String value, String oldValue) {
		super(value, oldValue);
	}

}
