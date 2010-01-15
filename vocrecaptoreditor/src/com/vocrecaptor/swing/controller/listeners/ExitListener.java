package com.vocrecaptor.swing.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener of menu item "Exit". Closes application.
 * 
 * @author Alexey Peskov
 */
public class ExitListener implements ActionListener {
    
	/**
	 * Creates a new instance of ExitListener.
	 */
    public ExitListener() {
    }

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    
}
