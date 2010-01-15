package com.vocrecaptor.swing.util;

import java.awt.TrayIcon;

import com.vocrecaptor.swing.resource.PropertyGetter;

/**
 * Class implements several methods used within application to notify user about
 * his actions (e.g. import/export or learning completion).
 * 
 * @author Alexey Peskov
 *
 */
public class UserNotifier extends TrayNotifier {

	private final String EXPORT_TITLE = PropertyGetter.getNamedProperty("Recap_list_export_title");
	private final String EXPORT_ERROR = PropertyGetter.getNamedProperty("Recap_list_export_operation_failed");
	private final String EXPORT_INFORMATION_MANY = PropertyGetter.getNamedProperty("_words_were_successfully_exported");
	private final String EXPORT_INFORMATION_ONE = PropertyGetter.getNamedProperty("_word_was_successfully_exported");
	private final String IMPORT_TITLE = PropertyGetter.getNamedProperty("Word_list_import_title");
	private final String IMPORT_ERROR = PropertyGetter.getNamedProperty("Word_list_import_operation_failed");
	private final String IMPORT_INFORMATION_MANY = PropertyGetter.getNamedProperty("_words_were_successfully_imported");
	private final String IMPORT_INFORMATION_ONE = PropertyGetter.getNamedProperty("_word_was_successfully_imported");
	private final String YOU_LEARNED_LIST_TITLE = PropertyGetter.getNamedProperty("You_learned_list_title");
	private final String YOU_LEARNED_LIST = PropertyGetter.getNamedProperty("You_learned_list");

	private static UserNotifier instance = null;
	
	/**
	 * Default constructor.
	 * 
	 * @param trayIcon system tray icon 
	 */
    private UserNotifier(TrayIcon trayIcon) {
		super(trayIcon);
	}

    /**
     * Creates an instance of UserNotifier or just returns it if it is created already.
     * 
     * @param trayIcon system tray icon
     * @return an instance of UserNotifier
     */
	public static UserNotifier getInstance(TrayIcon trayIcon) {
        if (instance == null) {
            instance = new UserNotifier(trayIcon);
        }
        
        return instance;
    }
	
	/**
	 * Displays an error message if import operation went wrong.
	 */
	public void displayImportErrorMessage() {
		showErrorMessage(IMPORT_TITLE, IMPORT_ERROR);
	}
    
	/**
	 * Displays an information message if import operation was successful.
	 * 
	 * @param imported the number of imported words
	 */
	public void displayImportSuccessfulMessage(int imported) {
		if (imported == 1) {
        	showInformationMessage(IMPORT_TITLE, imported + " " + IMPORT_INFORMATION_ONE);
        } else if (imported > 1) {
            showInformationMessage(IMPORT_TITLE, imported + " " + IMPORT_INFORMATION_MANY);
        }		
	}

	/**
	 * Displays an error message if export operation went wrong.
	 */
	public void displayExportErrorMessage() {
		showErrorMessage(EXPORT_TITLE, EXPORT_ERROR);
	}
    
	/**
	 * Displays an information message if export operation was successful.
	 * 
	 * @param exported imported the number of exported words
	 */
	public void displayExportSuccessfulMessage(int exported) {
		if (exported == 1) {
        	showInformationMessage(EXPORT_TITLE, exported + " " + EXPORT_INFORMATION_ONE);
        } else if (exported > 1) {
            showInformationMessage(EXPORT_TITLE, exported + " " + EXPORT_INFORMATION_MANY);
        }		
	}

	/**
	 * Displays an information message when user has learned all words from the list.
	 */
	public void displayYouLearnedListMessage() {
		showInformationMessage(YOU_LEARNED_LIST_TITLE, YOU_LEARNED_LIST);
	}
	
}
