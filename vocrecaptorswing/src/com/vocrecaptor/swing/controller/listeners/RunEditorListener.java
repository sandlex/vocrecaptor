package com.vocrecaptor.swing.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.vocrecaptor.swing.model.Model;

/**
 * Listener of menu item "Words editor". Opens an editor window.
 * 
 * @author Alexey Peskov
 */
public class RunEditorListener implements ActionListener {
    
    private JFrame frame;
    private Model model;
    
    /**
     *  Creates a new instance of RunEditorListener 
     */
    public RunEditorListener(JFrame frame, Model model) {
        this.frame = frame;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        model.loadCategories();
        model.setSession();
        
        frame.setVisible(true);
        model.checkStorage();
    }
}
