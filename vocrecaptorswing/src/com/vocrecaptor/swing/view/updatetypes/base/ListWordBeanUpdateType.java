package com.vocrecaptor.swing.view.updatetypes.base;

import java.util.List;

import com.vocrecaptor.swing.model.bean.WordBean;

/**
 * A type of notification where the new changed value in model 
 * represents a list of WordBean objects and the old value is not specified. 
 *  
 * @author Alexey Peskov
 */
public class ListWordBeanUpdateType implements UpdateType {

	private List<WordBean> value;
	
	/**
	 * Constructs an event with new changed value. 
	 * @param value new changed value as a list of WordBean objects
	 */	
	public ListWordBeanUpdateType(List<WordBean> value) {
		this.value = value;
	}
	
	@Override
	public List<WordBean> getValue() {
		return value;
	}

	@Override
	public Object getOldValue() {
		return null;
	}

}
