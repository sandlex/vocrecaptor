package com.vocrecaptor.swing.controller.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.vocrecaptor.swing.model.Model;

/**
 * Listener of recap window. Terminates recap process when user closes window.
 * 
 * @author Alexey Peskov
 */
public class RecapWindowListener implements WindowListener {
    
    private Model model;
    
    /**
     * Creates a new instance of RecapWindowListener
     */
    public RecapWindowListener(Model model) {
        this.model = model;
    }
    
    public void windowOpened(WindowEvent e) {
    }
    
    public void windowClosing(WindowEvent e) {
    }
    
    public void windowClosed(WindowEvent e) {
        model.stopRecapThread();        
    }
    
    public void windowIconified(WindowEvent e) {
    }
    
    public void windowDeiconified(WindowEvent e) {
    }
    
    public void windowActivated(WindowEvent e) {
    }
    
    public void windowDeactivated(WindowEvent e) {
    }
}
