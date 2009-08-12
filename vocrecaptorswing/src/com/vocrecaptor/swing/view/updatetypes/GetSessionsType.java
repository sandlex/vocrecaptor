package com.vocrecaptor.swing.view.updatetypes;

import java.util.List;

import com.vocrecaptor.swing.view.updatetypes.base.ListStringUpdateType;

/**
 * A notification Model sends when all sessions are being read from storage file.
 * 
 * @author Alexey Peskov
 */
public class GetSessionsType extends ListStringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 * @param oldValue old value
	 */	
	public GetSessionsType(List<String> value, String oldValue) {
		super(value, oldValue);
	}

}
