package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.BooleanUpdateType;

/**
 * A notification Model sends when user chooses a reverse order for recaping in
 * ProgramManager panel.
 * 
 * @author Alexey Peskov
 */
public class SetReverseOrderType extends BooleanUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public SetReverseOrderType(Boolean value) {
		super(value);
	}

}
