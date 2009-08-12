package com.vocrecaptor.swing.view.updatetypes.base;

/**
 * A type of notification where the new changed value in model 
 * represents a String and the old value is not specified. 
 *  
 * @author Alexey Peskov
 */
public class StringUpdateType implements UpdateType {

	private String value;
	
	/**
	 * Constructs an event with new changed value. 
	 * @param value new changed value as a String object
	 */
	public StringUpdateType(String value) {
		this.value = value; 
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public Object getOldValue() {
		return null;
	}

}
