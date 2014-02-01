package com.vocrecaptor.swing.view.updatetypes;

import java.util.List;

import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.view.updatetypes.base.ListWordBeanUpdateType;

/**
 * A notification Model sends when user entered a new word in Editor and pressed
 * Submit button and program performed a search for duplicating values in storage.
 * 
 * @author Alexey Peskov
 */
public class SearchDefinitionsType extends ListWordBeanUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public SearchDefinitionsType(List<WordBean> value) {
		super(value);
	}

}
