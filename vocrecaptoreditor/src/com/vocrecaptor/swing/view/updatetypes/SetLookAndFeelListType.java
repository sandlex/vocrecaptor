package com.vocrecaptor.swing.view.updatetypes;

import java.util.Map;

import com.vocrecaptor.swing.view.updatetypes.base.UpdateType;

/**
 * A notification Model sends when a list of available look and feel types
 * are loaded on program initialization.
 * 
 * @author Alexey Peskov
 */
public class SetLookAndFeelListType implements UpdateType {

	private Map<String, String> value;
	
	/**
	 * Constructs an event with new value. 
	 * @param value new changed value as a Map<String, String> object
	 */
	public SetLookAndFeelListType(Map<String, String> value) {
		this.value = value; 
	}
	
	@Override
	public Map<String, String> getValue() {
		return value;
	}
	
	@Override
	public Object getOldValue() {
		return null;
	}

}
