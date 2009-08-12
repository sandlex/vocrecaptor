package com.vocrecaptor.swing.view.updatetypes;

import com.vocrecaptor.swing.model.bean.CategorySessionBean;
import com.vocrecaptor.swing.view.updatetypes.base.UpdateType;

/**
 * A notification Model sends when recap word list for recaping is being restored from 
 * configuration file on application initialization.
 * 
 * @author Alexey Peskov
 */
public class RestoreRecapWordListType implements UpdateType {

	private CategorySessionBean value;

	/**
	 * Constructs an event with new changed value. 
	 * @param value new changed value as an CategorySessionBean object
	 */
	public RestoreRecapWordListType(CategorySessionBean value) {
		this.value = value; 
	}
	
	@Override
	public CategorySessionBean getValue() {
		return value;
	}

	@Override
	public Object getOldValue() {
		return null;
	}

}
