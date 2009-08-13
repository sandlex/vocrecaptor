package com.vocrecaptor.swing.view.updatetypes.base;

import java.util.List;

/**
 * A type of notification where the new changed value in model 
 * represents a list of String objects and the old value is a String. 
 *  
 * @author Alexey Peskov
 */
public class ListStringUpdateType implements UpdateType {

	private List<String> value;
	private String oldValue;
	
	/**
	 * Constructs an event with new and old changed values. 
	 * @param value new changed value as a list of String objects
	 * @param oldValue old changed value as a String object
	 */
	public ListStringUpdateType(List<String> value, String oldValue) {
		this.value = value;
		this.oldValue = oldValue;
	}
	
	@Override
	public List<String> getValue() {
		return value;
	}

	@Override
	public String getOldValue() {
		return oldValue;
	}

}
