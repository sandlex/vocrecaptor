package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.view.updatetypes.base.StringUpdateType;

/**
 * A notification Model sends when user chooses a look and feel type in
 * ProgramManager panel.
 * 
 * @author Alexey Peskov
 */
public class SetLookAndFeelType extends StringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public SetLookAndFeelType(String value) {
		super(value);
	}

}
