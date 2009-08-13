package com.vocrecaptor.swing.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.vocrecaptor.swing.model.Model;

/**
 * Listener of menu item "Words recap". Opens an editor window.
 * 
 * @author Alexey Peskov
 */
public class RunRecapListener implements ActionListener {

    private JFrame frame;
    private Model model;

    /** 
     * Creates a new instance of RunRecapListener 
     */
    public RunRecapListener(JFrame frame, Model model) {
        this.frame = frame;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        if (!frame.isVisible()) {
            frame.setVisible(true);
            model.startRecapThread();
        }
    }
}
