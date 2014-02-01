package com.vocrecaptor.swing.view.updatetypes.base;

/**
 * A type of notification where the new changed value in model 
 * represents a String and the old value also is String object. 
 *  
 * @author Alexey Peskov
 */
public class StringStringUpdateType implements UpdateType {

	private String value;
	private String oldValue;
	
	/**
	 * Constructs an event with new and old changed values. 
	 * @param value new changed value as a String object
	 * @param oldValue old changed value as a String object
	 */
	public StringStringUpdateType(String value, String oldValue) {
		this.value = value;
		this.oldValue = oldValue;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getOldValue() {
		return oldValue;
	}

}
