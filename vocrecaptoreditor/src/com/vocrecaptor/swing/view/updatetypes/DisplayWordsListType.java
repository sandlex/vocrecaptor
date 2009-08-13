package com.vocrecaptor.swing.view.updatetypes;

import java.util.List;

import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.view.updatetypes.base.ListWordBeanUpdateType;

/**
 * A notification Model sends when user is changing session or category on ProgramManager 
 * panel to view a list of matching words.
 * 
 * @author Alexey Peskov
 */
public class DisplayWordsListType extends ListWordBeanUpdateType {

	/**
	 * Constructs a notification type object.
	 * @param value new value
	 */
	public DisplayWordsListType(List<WordBean> value) {
		super(value);
	}

}
