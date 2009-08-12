package com.vocrecaptor.swing.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.vocrecaptor.swing.model.Model;
import com.vocrecaptor.swing.model.bean.CategorySessionBean;
import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.resource.PropertyGetter;
import com.vocrecaptor.swing.view.list.WordListTable;
import com.vocrecaptor.swing.view.updatetypes.DisplayWordsListType;
import com.vocrecaptor.swing.view.updatetypes.GetCategoriesType;
import com.vocrecaptor.swing.view.updatetypes.GetSessionsType;
import com.vocrecaptor.swing.view.updatetypes.RecapWordListType;
import com.vocrecaptor.swing.view.updatetypes.RestoreDefinitionTimeType;
import com.vocrecaptor.swing.view.updatetypes.RestoreExampleTimeType;
import com.vocrecaptor.swing.view.updatetypes.RestoreLookAndFeelType;
import com.vocrecaptor.swing.view.updatetypes.RestoreRandomOrderType;
import com.vocrecaptor.swing.view.updatetypes.RestoreRecapWordListType;
import com.vocrecaptor.swing.view.updatetypes.RestoreReverseOrderType;
import com.vocrecaptor.swing.view.updatetypes.RestoreTranslationTimeType;
import com.vocrecaptor.swing.view.updatetypes.SetLookAndFeelListType;
import com.vocrecaptor.swing.view.updatetypes.SetLookAndFeelType;
import com.vocrecaptor.swing.view.updatetypes.SetStoragePathType;
import com.vocrecaptor.swing.view.updatetypes.base.UpdateType;

/**
 * Class represents a program manager panel where user can specify program settings.
 * 
 * @author Alexey Peskov
 */
public class ProgramManagerPanel extends AbstractPanel {

	private static final long serialVersionUID = 2966833655167952732L;

	private JComboBox categoryComboBox;
	private JLabel categoryLabel;
	private JLabel definitionTimeLabel;
	private JSpinner definitionTimeSpinner;
	private JTabbedPane dictManagerTabbedPane;
	private JLabel exampleTimeLabel;
	private JSpinner exampleTimeSpinner;
	private JButton exportButton;
	private JPanel jPanel1;
	private JScrollPane jScrollPane2;
	private JComboBox lookAndFeelComboBox;
	private JLabel lookAndFeelLabel;
	private JButton newStorageButton;
	private JPanel programManagerPanel;
	private JCheckBox randomOrderCheckBox;
	private JPanel recapManagerPanel;
	private JCheckBox reverseOrderCheckBox;
	private JLabel sessionLabel;
	private JComboBox sessionsComboBox;
	private JButton setForRecapButton;
	private JButton storageFileButton;
	private JLabel storageFileLabel;
	private JLabel translationTimeLabel;
	private JSpinner translationTimeSpinner;
	private JLabel wordsShownLabel;
	private JTable wordsTable;

	/**
	 * Creates new form ProgramManagerPanel
	 */
	public ProgramManagerPanel(Model model) {
		super(model);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	protected void initComponents() {

		dictManagerTabbedPane = new JTabbedPane();
		jPanel1 = new JPanel();
		categoryLabel = new JLabel();
		categoryComboBox = new JComboBox();
		categoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showListButtonActionPerformed(evt);
			}
		});

		sessionLabel = new JLabel();
		sessionsComboBox = new JComboBox();
		sessionsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showListButtonActionPerformed(evt);
			}
		});

		jScrollPane2 = new JScrollPane();
		wordsTable = new JTable();
		setForRecapButton = new JButton();
		wordsShownLabel = new JLabel();
		storageFileLabel = new JLabel();
		storageFileButton = new JButton();
		exportButton = new JButton();
		newStorageButton = new JButton();
		recapManagerPanel = new JPanel();
		randomOrderCheckBox = new JCheckBox();
		definitionTimeSpinner = new JSpinner();
		definitionTimeLabel = new JLabel();
		translationTimeLabel = new JLabel();
		translationTimeSpinner = new JSpinner();
		exampleTimeLabel = new JLabel();
		exampleTimeSpinner = new JSpinner();
		reverseOrderCheckBox = new JCheckBox();
		programManagerPanel = new JPanel();
		lookAndFeelLabel = new JLabel();
		lookAndFeelComboBox = new JComboBox();
		lookAndFeelComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setLookAndFeel((String) lookAndFeelComboBox.getModel()
						.getSelectedItem());
			}
		});

		dictManagerTabbedPane.setPreferredSize(new Dimension(105, 120));

		categoryLabel.setText(PropertyGetter.getNamedProperty("Category_label"));

		sessionLabel.setText(PropertyGetter.getNamedProperty("Session_label"));

		setForRecapButton.setText(PropertyGetter
				.getNamedProperty("Set_for_recap_button"));
		setForRecapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setForRecapButtonActionPerformed(evt);
			}
		});

		wordsShownLabel.setText(PropertyGetter
				.getNamedProperty("Total_words_shown_label"));

		storageFileLabel.setText(PropertyGetter.getNamedProperty("Path_label"));

		storageFileButton.setText(PropertyGetter
				.getNamedProperty("Open_button"));
		storageFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				storageFileButtonActionPerformed(evt);
			}
		});

		exportButton.setText(PropertyGetter
				.getNamedProperty("Export_list_button"));
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				exportButtonActionPerformed(evt);
			}
		});

		newStorageButton.setText(PropertyGetter.getNamedProperty("New_button"));
		newStorageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newStorageButtonActionPerformed(evt);
			}
		});

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane2,
																GroupLayout.DEFAULT_SIZE,
																588,
																Short.MAX_VALUE)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				categoryLabel)
																		.addContainerGap(
																				530,
																				Short.MAX_VALUE))
														.addComponent(
																sessionLabel)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				wordsShownLabel)
																		.addContainerGap(
																				468,
																				Short.MAX_VALUE))
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel1Layout
																														.createParallelGroup(
																																GroupLayout.Alignment.LEADING)
																														.addComponent(
																																sessionsComboBox,
																																0,
																																302,
																																Short.MAX_VALUE)
																														.addComponent(
																																categoryComboBox,
																																0,
																																302,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												LayoutStyle.ComponentPlacement.RELATED)
																										.addPreferredGap(
																												LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												setForRecapButton))
																						.addComponent(
																								storageFileLabel,
																								GroupLayout.DEFAULT_SIZE,
																								477,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								exportButton)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												storageFileButton)
																										.addPreferredGap(
																												LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												newStorageButton)))
																		.addContainerGap()))));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																newStorageButton)
														.addComponent(
																storageFileButton)
														.addComponent(
																storageFileLabel))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(categoryLabel)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(categoryComboBox,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(sessionLabel)
										.addGap(9, 9, 9)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																sessionsComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																setForRecapButton)
														.addComponent(
																exportButton))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jScrollPane2,
												GroupLayout.DEFAULT_SIZE, 183,
												Short.MAX_VALUE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(wordsShownLabel)));

		dictManagerTabbedPane.addTab(PropertyGetter
				.getNamedProperty("Words_manager_tab"), jPanel1);

		randomOrderCheckBox.setText(PropertyGetter
				.getNamedProperty("Show_words_in_random_order"));
		randomOrderCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,
				0));
		randomOrderCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				randomOrderCheckBoxActionPerformed(evt);
			}
		});

		definitionTimeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				definitionTimeSpinnerStateChanged(evt);
			}
		});

		definitionTimeLabel.setText(PropertyGetter
				.getNamedProperty("Definition_display_period_(sec.)"));

		translationTimeLabel.setText(PropertyGetter
				.getNamedProperty("Translation_display_period_(sec.)"));

		translationTimeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				translationTimeSpinnerStateChanged(evt);
			}
		});

		exampleTimeLabel.setText(PropertyGetter
				.getNamedProperty("Example_display_period_(sec.)"));

		exampleTimeSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				exampleTimeSpinnerStateChanged(evt);
			}
		});

		reverseOrderCheckBox
				.setText(PropertyGetter
						.getNamedProperty("Show_definition_and_translation_in_reverse_order"));
		reverseOrderCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,
				0));
		reverseOrderCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				reverseOrderCheckBoxActionPerformed(evt);
			}
		});

		GroupLayout recapManagerPanelLayout = new GroupLayout(recapManagerPanel);
		recapManagerPanel.setLayout(recapManagerPanelLayout);
		recapManagerPanelLayout
				.setHorizontalGroup(recapManagerPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								recapManagerPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												recapManagerPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																randomOrderCheckBox)
														.addComponent(
																reverseOrderCheckBox)
														.addGroup(
																recapManagerPanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				recapManagerPanelLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								translationTimeLabel)
																						.addComponent(
																								definitionTimeLabel)
																						.addComponent(
																								exampleTimeLabel))
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				recapManagerPanelLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								exampleTimeSpinner)
																						.addComponent(
																								translationTimeSpinner)
																						.addComponent(
																								definitionTimeSpinner,
																								GroupLayout.DEFAULT_SIZE,
																								57,
																								Short.MAX_VALUE))))
										.addContainerGap(265, Short.MAX_VALUE)));
		recapManagerPanelLayout
				.setVerticalGroup(recapManagerPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								recapManagerPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(randomOrderCheckBox)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(reverseOrderCheckBox)
										.addGap(38, 38, 38)
										.addGroup(
												recapManagerPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																definitionTimeLabel)
														.addComponent(
																definitionTimeSpinner,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												recapManagerPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																translationTimeLabel)
														.addComponent(
																translationTimeSpinner,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												recapManagerPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																exampleTimeLabel)
														.addComponent(
																exampleTimeSpinner,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addContainerGap(100, Short.MAX_VALUE)));

		dictManagerTabbedPane.addTab(PropertyGetter
				.getNamedProperty("Recap_manager_tab"), recapManagerPanel);

		lookAndFeelLabel.setText(PropertyGetter
				.getNamedProperty("Look_and_feel_mode"));

		GroupLayout programManagerPanelLayout = new GroupLayout(
				programManagerPanel);
		programManagerPanel.setLayout(programManagerPanelLayout);
		programManagerPanelLayout
				.setHorizontalGroup(programManagerPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								programManagerPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												programManagerPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																lookAndFeelLabel)
														.addComponent(
																lookAndFeelComboBox,
																0, 246,
																Short.MAX_VALUE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addContainerGap(289, Short.MAX_VALUE)));
		programManagerPanelLayout
				.setVerticalGroup(programManagerPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								programManagerPanelLayout
										.createSequentialGroup()
										.addGap(50, 50, 50)
										.addComponent(lookAndFeelLabel)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												programManagerPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																lookAndFeelComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(268, Short.MAX_VALUE)));

		dictManagerTabbedPane.addTab(PropertyGetter
				.getNamedProperty("Program_manager_tab"), programManagerPanel);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(
				dictManagerTabbedPane, GroupLayout.DEFAULT_SIZE, 608,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(
				dictManagerTabbedPane, GroupLayout.DEFAULT_SIZE, 407,
				Short.MAX_VALUE));
	}

	/**
	 * Creates a new storage in file user chooses in file dialog. 
	 * @param evt action event
	 */
	private void newStorageButtonActionPerformed(ActionEvent evt) {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			model.createStorage(file.getAbsolutePath());
		}
	}

	/**
	 * Exports a current list into text file.
	 * @param evt action event
	 */
	private void exportButtonActionPerformed(ActionEvent evt) {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			model.exportWords(file.getAbsolutePath());
		}
	}

	/**
	 * Sets a reverse order flag for recap.
	 * @param evt action event
	 */
	private void reverseOrderCheckBoxActionPerformed(ActionEvent evt) {
		model.setReverseOrder(new Boolean(reverseOrderCheckBox.getModel()
				.isSelected()));
	}

	/**
	 * Sets a random order flag for recap.
	 * @param evt action event
	 */
	private void randomOrderCheckBoxActionPerformed(ActionEvent evt) {
		model.setRandomOrder(new Boolean(randomOrderCheckBox.getModel()
				.isSelected()));
	}

	/**
	 * Sets an example display time value for recap.
	 * @param evt change event
	 */
	private void exampleTimeSpinnerStateChanged(ChangeEvent evt) {
		if ((Integer) exampleTimeSpinner.getValue() < 0) {
			exampleTimeSpinner.setValue(0);
		}
		model.setExampleTime((Integer) exampleTimeSpinner.getValue());
	}

	/**
	 * Sets a translation display time value for recap.
	 * @param evt change event
	 */
	private void translationTimeSpinnerStateChanged(ChangeEvent evt) {
		if ((Integer) translationTimeSpinner.getValue() < 0) {
			translationTimeSpinner.setValue(0);
		}
		model.setTranslationTime((Integer) translationTimeSpinner.getValue());
	}

	/**
	 * Sets a definition display time value for recap.
	 * @param evt change event
	 */
	private void definitionTimeSpinnerStateChanged(ChangeEvent evt) {
		if ((Integer) definitionTimeSpinner.getValue() < 0) {
			definitionTimeSpinner.setValue(0);
		}
		model.setDefinitionTime((Integer) definitionTimeSpinner.getValue());
	}

	/**
	 * Sets a new storage for file user choses in file dialog.
	 * @param evt action event
	 */
	private void storageFileButtonActionPerformed(ActionEvent evt) {

		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			model.setStoragePath(file.getAbsolutePath());
		}
	}

	/**
	 * Sets a selected word criterias (category and session) for recap.
	 * @param evt action event
	 */
	private void setForRecapButtonActionPerformed(ActionEvent evt) {
		String category = (String) categoryComboBox.getModel().getSelectedItem();
		String session = (String) sessionsComboBox.getModel().getSelectedItem();
		
		if (category != null && session != null) {
			model.setRecapWordsList(new CategorySessionBean(category, session));
		}
	}

	/**
	 * Displays a list of words matching the selected criterias (category and session).
	 * @param evt action event
	 */
	private void showListButtonActionPerformed(ActionEvent evt) {
		if ((String) categoryComboBox.getModel().getSelectedItem() != null
				&& (String) sessionsComboBox.getModel().getSelectedItem() != null)
			model.loadWordsList(new CategorySessionBean(
					(String) categoryComboBox.getModel().getSelectedItem(),
					(String) sessionsComboBox.getModel().getSelectedItem()));
	}

	@Override
	public void update(Observable o, Object arg) {

		UpdateType type = (UpdateType) arg;
		if (type instanceof GetCategoriesType) {
			getCategories(type);
		} else if (type instanceof GetSessionsType) {
			getSessions(type);
		} else if (type instanceof DisplayWordsListType) {
			displayWordsList(type);
		} else if (type instanceof SetStoragePathType) {
			setStoragePath(type);
		} else if (type instanceof SetLookAndFeelType) {
			setLookAndFeel(type);
		} else if (type instanceof SetLookAndFeelListType) {
			setLookAndFeelList(type);
		} else if (type instanceof RestoreDefinitionTimeType) {
			restoreDefinitionTime(type);
		} else if (type instanceof RestoreTranslationTimeType) {
			restoreTranslationTime(type);
		} else if (type instanceof RestoreExampleTimeType) {
			restoreExampleTime(type);
		} else if (type instanceof RestoreRecapWordListType) {
			restoreRecapWordList(type);
		} else if (type instanceof RestoreReverseOrderType) {
			restoreReverseOrder(type);
		} else if (type instanceof RestoreRandomOrderType) {
			restoreRandomOrder(type);
		} else if (type instanceof RecapWordListType) {
			recapWordList(type);
		} else if (type instanceof RestoreLookAndFeelType) {
			restoreLookAndFeel(type);
		}
	}

	/**
	 * Fills a list of categories with values.
	 * @param type type of notification event
	 */
	private void getCategories(UpdateType type) {
		List<String> categories = ((GetCategoriesType) type).getValue();
		if (categories.size() > 0) {
			categories.add(0, "");
			ComboBoxModel model = new DefaultComboBoxModel(categories.toArray());
			categoryComboBox.setModel(model);
			if (categories.indexOf(((GetCategoriesType) type).getOldValue()) > -1) {
				categoryComboBox.getModel().setSelectedItem(
						((GetCategoriesType) type).getOldValue());
			} 
		} else {
			ComboBoxModel model = new DefaultComboBoxModel(categories.toArray());
			categoryComboBox.setModel(model);
		}
	}

	/**
	 * Fills a list of sessions with values.
	 * @param type type of notification event
	 */
	private void getSessions(UpdateType type) {
		List<String> sessions = ((GetSessionsType) type).getValue();
		if (sessions.size() > 0) {
			sessions.add(0, "");
			ComboBoxModel model = new DefaultComboBoxModel(sessions.toArray());
			sessionsComboBox.setModel(model);
			if (sessions.indexOf(((GetSessionsType) type).getOldValue()) > -1) {
				sessionsComboBox.getModel().setSelectedItem(
						((GetSessionsType) type).getOldValue());
			} 
		} else {
			ComboBoxModel model = new DefaultComboBoxModel(sessions.toArray());
			sessionsComboBox.setModel(model);
		}
	}

	/**
	 * Displays a list of words matching the selected criterias (category and session).
	 * @param type type of notification event
	 */
	//FIXME: Refresh word list bug.
	//1. Open dictionary, set list for recap. Note a shown list of words.
	//2. Open another dictionary where there is now such category or session.
	//3. Note the list is not refreshed.
	//4. See private void getSessions(UpdateType type) && private void getCategoris(UpdateType type) to fix.
	//5. Create a new dictionary. List is not refreshed. See Model.setStoragePath to fix.
	private void displayWordsList(UpdateType type) {
		List<WordBean> wordsList = ((DisplayWordsListType) type).getValue();

		wordsTable = WordListTable.createTable(wordsList);
		jScrollPane2.setViewportView(wordsTable);
		
		wordsShownLabel.setText(PropertyGetter
				.getNamedProperty("Total_words_shown_label") + wordsList.size());
		setForRecapButton.setEnabled(wordsList.size() > 0);
	}

	/**
	 * Displays a new storage value in label.
	 * @param type type of notification event
	 */
	private void setStoragePath(UpdateType type) {
		String storagePath = ((SetStoragePathType) type).getValue();
		if (storagePath == "-1") {
			storagePath = PropertyGetter.getNamedProperty("Path_error_label");
		}
		storageFileLabel.setText(PropertyGetter.getNamedProperty("Path_label")
				+ " " + storagePath);
		storageFileLabel.setToolTipText(PropertyGetter
				.getNamedProperty("Path_label")
				+ " " + storagePath);
		
		setForRecapButton.setEnabled(false);
		exportButton.setEnabled(false);
	}

	/**
	 * Fills a list of look and feel values with values.
	 * @param type type of notification event
	 */
	private void setLookAndFeelList(UpdateType type) {
		Map<String, String> lookAndFeelMap = ((SetLookAndFeelListType) type)
				.getValue();
		ComboBoxModel model = new DefaultComboBoxModel(lookAndFeelMap.keySet()
				.toArray());
		lookAndFeelComboBox.setModel(model);
	}

	/**
	 * Applies a definition display time value for recap to control.
	 * @param type type of notification event
	 */
	private void restoreDefinitionTime(UpdateType type) {
		Integer definitionTime = ((RestoreDefinitionTimeType) type).getValue();
		definitionTimeSpinner.setValue(definitionTime);
	}

	/**
	 * Applies a translation display time value for recap to control.
	 * @param type type of notification event
	 */
	private void restoreTranslationTime(UpdateType type) {
		Integer translationTime = ((RestoreTranslationTimeType) type)
				.getValue();
		translationTimeSpinner.setValue(translationTime);
	}

	/**
	 * Applies a example display time value for recap to control.
	 * @param type type of notification event
	 */
	private void restoreExampleTime(UpdateType type) {
		Integer exampleTime = ((RestoreExampleTimeType) type).getValue();
		exampleTimeSpinner.setValue(exampleTime);
	}

	/**
	 * Applies a category and session for recap to controls.
	 * @param type type of notification event
	 */
	private void restoreRecapWordList(UpdateType type) {
		CategorySessionBean bean = ((RestoreRecapWordListType) type).getValue();
		if (categoryComboBox.getModel().getSize() > 0 && sessionsComboBox.getModel().getSize() > 0) {
			categoryComboBox.getModel().setSelectedItem(bean.getCategory());
			sessionsComboBox.getModel().setSelectedItem(bean.getSession());
		}
	}

	/**
	 * Applies a reverse order flag for recap to control.
	 * @param type type of notification event
	 */
	private void restoreReverseOrder(UpdateType type) {
		Boolean isReverseOrder = ((RestoreReverseOrderType) type).getValue();
		reverseOrderCheckBox.getModel().setSelected(
				isReverseOrder.booleanValue());
	}

	/**
	 * Applies a random order flag for recap to control.
	 * @param type type of notification event
	 */
	private void restoreRandomOrder(UpdateType type) {
		Boolean isRandomOrder = ((RestoreRandomOrderType) type).getValue();
		randomOrderCheckBox.getModel()
				.setSelected(isRandomOrder.booleanValue());
	}

	/**
	 * Makes possible to export list when user chooses a list for recap.
	 * @param type type of notification event
	 */
	private void recapWordList(UpdateType type) {
		List<WordBean> recapWordsList = ((RecapWordListType) type).getValue();
		exportButton.setEnabled(recapWordsList.size() > 0);
	}

	/**
	 * Applies a look and feel value to control.
	 * @param type type of notification event
	 */
	private void restoreLookAndFeel(UpdateType type) {
		String value = ((RestoreLookAndFeelType) type).getValue();
		lookAndFeelComboBox.getModel().setSelectedItem(value);
	}

}
