package com.vocrecaptor.swing.view.list;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import com.vocrecaptor.swing.model.bean.WordBean;

/**
 * Class represents a frame with table where a world list is displayed.
 * It frame is used within Editor working session when user checks some
 * definitions for existence or Editor automatically does it if the 
 * definition is not unique within storage.
 * 
 * @author Alexey Peskov
 */
public class WordListFrame extends JFrame {

	//FIXME The frame is doesn't react on lookandfeel change when it's open. 
	
	/**
	 * Inner class representing a panel with a word list table.
	 * 
	 * @author Alexey Peskov
	 */
	private class WordsPanel extends JPanel {

		private static final long serialVersionUID = 3808976562934775412L;
		
		/**
		 * Constructor. Initializes a panel with values from given list of word beans.
		 * 
		 * @param wordsList list of WordBean objects
		 */
		public WordsPanel(List<WordBean> wordsList) {

			JScrollPane scrollpane = new JScrollPane();
			scrollpane.setViewportView(WordListTable.createTable(wordsList));

			add(scrollpane);
		}
	}

	private static final long serialVersionUID = 7278067474594625416L;

	/**
	 * Constructor. Constructs a frame with panel containing a table.
	 * 
	 * @param wordsList list of WordBean to populate table
	 */
	public WordListFrame(List<WordBean> wordsList) {
		super("Search results: " + wordsList.size());
		getContentPane().add(new WordsPanel(wordsList), BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
	}
	
}
