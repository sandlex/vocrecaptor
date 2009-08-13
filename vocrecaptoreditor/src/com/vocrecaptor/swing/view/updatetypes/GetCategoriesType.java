package com.vocrecaptor.swing.view.updatetypes;

import java.util.List;

import com.vocrecaptor.swing.view.updatetypes.base.ListStringUpdateType;

/**
 * A notification Model sends when all categories are being read from storage file.
 * 
 * @author Alexey Peskov
 */
public class GetCategoriesType extends ListStringUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 * @param oldValue old value
	 */
	public GetCategoriesType(List<String> value, String oldValue) {
		super(value, oldValue);
	}

}
