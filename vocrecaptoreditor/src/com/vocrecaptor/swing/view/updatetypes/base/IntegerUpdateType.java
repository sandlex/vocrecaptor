package com.vocrecaptor.swing.view.updatetypes.base;

/**
 * A type of notification where the new changed value in model 
 * represents an Integer and the old value is not specified. 
 *  
 * @author Alexey Peskov
 */
public class IntegerUpdateType implements UpdateType {

	private Integer value;
	
	/**
	 * Constructs an event with new changed value. 
	 * @param value new changed value as an Integer object
	 */
	public IntegerUpdateType(Integer value) {
		this.value = value; 
	}
	
	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Object getOldValue() {
		return null;
	}

}
