package com.vocrecaptor.swing.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.vocrecaptor.swing.model.Model;

/**
 * Listener of menu item "Program manager". Opens a program manager window.
 * 
 * @author Alexey Peskov
 */
public class RunManagerListener implements ActionListener {
    
    private JFrame frame;
    private Model model;
    
    /** 
     * Creates a new instance of RunDictManagerListener 
     */
    public RunManagerListener(JFrame frame, Model model) {
        this.frame = frame;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
    	model.loadCategories();
        model.loadSessions();
        frame.setVisible(true);
    }
    
}
