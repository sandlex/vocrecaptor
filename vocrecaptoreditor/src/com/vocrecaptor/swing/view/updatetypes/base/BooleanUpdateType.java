package com.vocrecaptor.swing.view.updatetypes.base;

/**
 * A type of notification where the new changed value in model 
 * represents an Boolean and the old value is not specified. 
 *  
 * @author Alexey Peskov
 */
public class BooleanUpdateType implements UpdateType {

	private Boolean value;
	
	/**
	 * Constructs an event with new changed value. 
	 * @param value new changed value as an Boolean object
	 */
	public BooleanUpdateType(Boolean value) {
		this.value = value;
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public Object getOldValue() {
		return null;
	}
	
}
