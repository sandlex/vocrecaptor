package com.vocrecaptor.swing.view.list;

import java.util.List;

import javax.swing.JTable;

import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.resource.PropertyGetter;

/**
 * Class represents a table for displaying a list of words. This table is
 * used in EditorPanel (WordListFrame in particular) to display duplicating
 * words during user input session and also this table is built in 
 * ProgramManagerPanel to display current list of words for recaping.
 * 
 * @author Alexey Peskov
 */
public class WordListTable extends JTable {

	private static final long serialVersionUID = -937104759460233182L;

	/**
	 * Initializes and returns a table with 3 columns and data taken
	 * from specified list.
	 * 
	 * @param wordsList list of WordBean to populate table
	 * @return created JTable and filled with user data 
	 */
	public static JTable createTable(List<WordBean> wordsList) {
		String[] columnNames = { PropertyGetter
				.getNamedProperty("Definition_label"), PropertyGetter
				.getNamedProperty("Translation_label"), PropertyGetter
				.getNamedProperty("Example_of_usage_label") };
		
		String[][] data = new String[wordsList.size()][3];
		int i = 0;
		for (WordBean word : wordsList) {
			data[i][0] = word.getDefinition();
			data[i][1] = word.getTranslation();
			data[i][2] = word.getExample();
			i++;
		}

		return new JTable(data, columnNames);
	}

}
