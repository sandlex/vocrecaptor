package com.vocrecaptor.swing.view.updatetypes;

import java.util.List;

import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.view.updatetypes.base.ListWordBeanUpdateType;

/**
 * A notification Model sends when user sets a certain category of words for
 * recaping on ProgramManager panel.
 * 
 * @author Alexey Peskov
 */
public class RecapWordListType extends ListWordBeanUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */	
	public RecapWordListType(List<WordBean> value) {
		super(value);
	}

}
