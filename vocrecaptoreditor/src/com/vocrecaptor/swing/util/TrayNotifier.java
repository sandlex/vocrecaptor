package com.vocrecaptor.swing.util;

import java.awt.TrayIcon;

/**
 * Class is responsible for displaying notification messages in system
 * tray area.
 *
 * @author Alexey Peskov
 */
public abstract class TrayNotifier {
    
    private TrayIcon trayIcon = null;

    /**
     * Creates a new instance of TrayNotificator.
     * 
     * @param trayIcon
     */
    protected TrayNotifier(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }
    
    /**
     * Displays a notification message of the INFO type with specified
     * title and message.
     * 
     * @param messageHeader message title
     * @param message message body
     */
    protected void showInformationMessage(String messageHeader, String message) {
        trayIcon.displayMessage(messageHeader, 
                        message,
                        TrayIcon.MessageType.INFO);
    }
    
    /**
     * Displays a notification message of the ERROR type with specified
     * title and message.
     * 
     * @param messageHeader message title
     * @param message message body
     */
    protected void showErrorMessage(String messageHeader, String message) {
        trayIcon.displayMessage(messageHeader, 
                        message,
                        TrayIcon.MessageType.ERROR);
    }
    
    /**
     * Displays a notification message of the WARNING type with specified
     * title and message.
     * 
     * @param messageHeader message title
     * @param message message body
     */
    protected void showWarningMessage(String messageHeader, String message) {
        trayIcon.displayMessage(messageHeader, 
                        message,
                        TrayIcon.MessageType.WARNING);
    }
    
}
