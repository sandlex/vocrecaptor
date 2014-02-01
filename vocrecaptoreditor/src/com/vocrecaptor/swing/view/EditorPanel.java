package com.vocrecaptor.swing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Observable;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.vocrecaptor.swing.model.Model;
import com.vocrecaptor.swing.model.bean.ImportBean;
import com.vocrecaptor.swing.model.bean.WordBean;
import com.vocrecaptor.swing.resource.PropertyGetter;
import com.vocrecaptor.swing.view.list.WordListFrame;
import com.vocrecaptor.swing.view.updatetypes.CheckDefinitionsType;
import com.vocrecaptor.swing.view.updatetypes.CheckStorageType;
import com.vocrecaptor.swing.view.updatetypes.GetCategoriesType;
import com.vocrecaptor.swing.view.updatetypes.SearchDefinitionsType;
import com.vocrecaptor.swing.view.updatetypes.SetLookAndFeelType;
import com.vocrecaptor.swing.view.updatetypes.StartSessionType;
import com.vocrecaptor.swing.view.updatetypes.base.UpdateType;

/**
 * Class represents a panel for editing storage file.
 * 
 * @author Alexey Peskov
 */
public class EditorPanel extends AbstractPanel {

	private static final long serialVersionUID = -8386378515771419656L;
	
	private JComboBox categoryComboBox;
	private JLabel categoryLabel;
	private JButton checkDefinitionButton;
	private JLabel definitionLabel;
	private JTextField definitionTextField;
	private JLabel exampleLabel;
	private JTextField exampleTextField;
	private JButton importButton;
	private JButton newCategoryButton;
	private JLabel sessionLabel;
	private JButton submitButton;
	private JLabel translationLabel;
	private JTextField translationTextField;

	private String session;

	/** 
	 * Creates new form EditorPanel 
	 */
	public EditorPanel(Model model) {
		super(model);
		submitButton.setEnabled(false);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	protected void initComponents() {
		categoryComboBox = new JComboBox();
		newCategoryButton = new JButton();
		importButton = new JButton();
		submitButton = new JButton();
		definitionTextField = new JTextField();
		definitionLabel = new JLabel();
		translationLabel = new JLabel();
		translationTextField = new JTextField();
		exampleLabel = new JLabel();
		exampleTextField = new JTextField();
		categoryLabel = new JLabel();
		sessionLabel = new JLabel();
		checkDefinitionButton = new JButton();

		newCategoryButton
				.setText(PropertyGetter.getNamedProperty("New_button"));
		newCategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newCategoryButtonActionPerformed(evt);
			}
		});

		importButton.setText(PropertyGetter
				.getNamedProperty("Import_list_button"));
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				importButtonActionPerformed(evt);
			}
		});

		submitButton.setText(PropertyGetter.getNamedProperty("Submit_button"));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});

		definitionTextField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent evt) {
				definitionTextFieldCaretUpdate(evt);
			}
		});

		definitionLabel.setText(PropertyGetter
				.getNamedProperty("Definition_label"));

		translationLabel.setText(PropertyGetter
				.getNamedProperty("Translation_label"));

		exampleLabel.setText(PropertyGetter
				.getNamedProperty("Example_of_usage_label"));

		categoryLabel
				.setText(PropertyGetter.getNamedProperty("Category_label"));

		sessionLabel.setText(PropertyGetter
				.getNamedProperty("Session_info_label"));

		checkDefinitionButton.setEnabled(false);
		checkDefinitionButton.setText(PropertyGetter
				.getNamedProperty("Check_definition_button"));
		checkDefinitionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				checkDefinitionButtonActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				definitionTextField,
																				GroupLayout.DEFAULT_SIZE,
																				337,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				checkDefinitionButton))
														.addComponent(
																translationTextField,
																GroupLayout.DEFAULT_SIZE,
																394,
																Short.MAX_VALUE)
														.addComponent(
																exampleTextField,
																GroupLayout.DEFAULT_SIZE,
																394,
																Short.MAX_VALUE)
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				submitButton)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				importButton))
														.addComponent(
																translationLabel)
														.addComponent(
																exampleLabel)
														.addComponent(
																definitionLabel)
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				categoryComboBox,
																				0,
																				337,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				newCategoryButton))
														.addComponent(
																sessionLabel)
														.addComponent(
																categoryLabel))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(sessionLabel)
										.addGap(27, 27, 27)
										.addComponent(categoryLabel)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																newCategoryButton)
														.addComponent(
																categoryComboBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(definitionLabel)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																checkDefinitionButton)
														.addComponent(
																definitionTextField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(translationLabel)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(translationTextField,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(exampleLabel)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(exampleTextField,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																submitButton)
														.addComponent(
																importButton))
										.addContainerGap()));
	}

	/**
	 * Enables/disable Submit and Check buttons on definition field content change. 
	 * @param evt caret event
	 */
	private void definitionTextFieldCaretUpdate(CaretEvent evt) {
		submitButton.setEnabled(!definitionTextField.getText().isEmpty());
		checkDefinitionButton.setEnabled(!definitionTextField.getText()
				.isEmpty());
	}

	/**
	 * Performs an import from file that user chooses in file dialog.
	 * @param evt action event
	 */
	private void importButtonActionPerformed(ActionEvent evt) {

		if (categoryComboBox.getModel().getSelectedItem() != null) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				model.importWords(new ImportBean(file.getAbsolutePath(),
						categoryComboBox.getModel().getSelectedItem().toString(),
						session));
			}
		} else {
			JOptionPane
			.showMessageDialog(
					this,
					PropertyGetter
							.getNamedProperty("Category_is_not_specified"),
					PropertyGetter
							.getNamedProperty("Category_is_not_specified_header"),
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Submits a new word into storage. Doing that checks whether such word is exists or
	 * not in storage file already.
	 * @param evt action event
	 */
	private void submitButtonActionPerformed(ActionEvent evt) {
		model.searchDefinitions(definitionTextField.getText());
	}

	/**
	 * Adds a new category.
	 * @param evt action event
	 */
	private void newCategoryButtonActionPerformed(ActionEvent evt) {
		String value = JOptionPane.showInputDialog(PropertyGetter
				.getNamedProperty("New_category_message"));
		DefaultComboBoxModel model = (DefaultComboBoxModel) categoryComboBox
				.getModel();
		if (model.getIndexOf((String) value) == -1) {
			model.addElement(value);
		}
		model.setSelectedItem(value);
		categoryComboBox.setModel(model);
	}

	/**
	 * Checks if storage already contains words with similar definition user entered. 
	 * @param evt action event
	 */
	private void checkDefinitionButtonActionPerformed(ActionEvent evt) {
		if (!"".equals(definitionTextField.getText().trim()))
			model.checkDefinitions(definitionTextField.getText());
	}

	/**
	 * Adds a new word after check was done and clears fields.
	 */
	private void submit() {
		model.addNewWord(new WordBean(categoryComboBox.getModel()
				.getSelectedItem().toString(), definitionTextField.getText(),
				translationTextField.getText(), exampleTextField.getText(),
				session));
		clearFields();
		submitButton.setEnabled(false);
	}
	
	/**
	 * Clears field content.
	 */
	private void clearFields() {
		definitionTextField.setText("");
		translationTextField.setText("");
		exampleTextField.setText("");
	}

	@Override
	public void update(Observable o, Object arg) {

		UpdateType type = (UpdateType) arg;
		if (type instanceof GetCategoriesType) {
			getCategories(type);
		} else if (type instanceof StartSessionType) {
			startSession(type);
		} else if (type instanceof SetLookAndFeelType) {
			setLookAndFeel(type);
		} else if (type instanceof SearchDefinitionsType) {
			searchDefinitions(type);
		} else if (type instanceof CheckDefinitionsType) {
			checkDefinitions(type);
		} else if (type instanceof CheckStorageType) {
			checkStorage(type);
		}
	}

	/**
	 * Fills a list of categories with values.
	 * @param type type of notification event
	 */
	private void getCategories(UpdateType type) {
		List<String> categories = ((GetCategoriesType) type).getValue();
		ComboBoxModel model = new DefaultComboBoxModel(categories.toArray());
		categoryComboBox.setModel(model);
	}

	/**
	 * Starts a new session or continues a previous one depending on user's choice.
	 * @param type type of notification event
	 */
	private void startSession(UpdateType type) {
		String prevSession = (((StartSessionType) type).getOldValue());
		String newSession = ((StartSessionType) type).getValue();

		if ("".equals(prevSession)) {
			session = newSession;
		} else {
			Object[] options = {
					PropertyGetter.getNamedProperty("Start_new_session_button"),
					PropertyGetter.getNamedProperty("Continue_last_session_button") };
			int res = JOptionPane
					.showOptionDialog(
							this,
							PropertyGetter.getNamedProperty("Start_new_session_message"),
							PropertyGetter.getNamedProperty("Start_new_session_header"),
							JOptionPane.YES_NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options,
							options[1]);

			session = ((res == 0) ? newSession : prevSession);
		}
		model.startInputSession(session);
		sessionLabel.setText(PropertyGetter
				.getNamedProperty("Session_info_started_at_label")
				+ session);
	}

	/**
	 * Fires after user presses Submit button. Displays a confirmation dialog
	 * about possible duplicate values and acts in accordance with further user choice - adds
	 * word or doesn't add.
	 * @param type type of notification event
	 */
	private void searchDefinitions(UpdateType type) {
		List<WordBean> foundedDefinitions = ((SearchDefinitionsType) type)
				.getValue();
		if (!foundedDefinitions.isEmpty()) {
			WordListFrame wlf = new WordListFrame(foundedDefinitions);
			wlf.pack();
			wlf.setVisible(true);

			
			Object[] options = {
					PropertyGetter.getNamedProperty("Submit_button"),
					PropertyGetter.getNamedProperty("Dont_submit_button") };
			int res = JOptionPane.showInternalOptionDialog(this.getParent(), PropertyGetter
					.getNamedProperty("The_word_is_found_message"), PropertyGetter
					.getNamedProperty("Duplicate_warning_message"),
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[1]);

			if (res == 0) {
				submit();
			} else {
				clearFields();
			}
			
			wlf.setVisible(false);
		} else {
			submit();
		}
	}

	/**
	 * Fires when user hits Check button. Displays a panel with found similar definitions.
	 * @param type type of notification event
	 */
	private void checkDefinitions(UpdateType type) {
		List<WordBean> foundedDefinitions = ((CheckDefinitionsType) type)
				.getValue();
		if (foundedDefinitions.isEmpty()) {
			checkDefinitionButton.setEnabled(false);
		} else {
			WordListFrame wlf = new WordListFrame(foundedDefinitions);
			wlf.pack();
			wlf.setVisible(true);
		}
	}

	/**
	 * Displays a notification dialog informing user about to specified storage file.
	 * @param type type of notification event
	 */
	private void checkStorage(UpdateType type) {
		JOptionPane
				.showMessageDialog(
						this,
						PropertyGetter
								.getNamedProperty("Dictionary_is_not_set_start_message")
								+ "\n"
								+ PropertyGetter
										.getNamedProperty("Dictionary_is_not_set_end_message"),
						PropertyGetter
								.getNamedProperty("Dictionary_is_not_set_header"),
						JOptionPane.WARNING_MESSAGE);

		parentFrame.setVisible(false);
	}
}
