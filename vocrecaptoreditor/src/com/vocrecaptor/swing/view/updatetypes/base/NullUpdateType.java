package com.vocrecaptor.swing.view.updatetypes.base;

/**
 * A type of notification where both the new changed value in model 
 * and an old value are not required. 
 *  
 * @author Alexey Peskov
 */
public class NullUpdateType implements UpdateType {

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public Object getOldValue() {
		return null;
	}

}
