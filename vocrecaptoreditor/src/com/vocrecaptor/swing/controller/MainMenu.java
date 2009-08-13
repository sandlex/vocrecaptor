package com.vocrecaptor.swing.controller;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

import com.vocrecaptor.swing.resource.PropertyGetter;

/**
 * Application main menu class. Main menu belongs to system tray area and
 * allows the user the only way to run tasks and activities. Main menu
 * is used as a notification area for displaying notification messages.
 *
 * @author Alexey Peskov
 */
public class MainMenu extends PopupMenu {
    
	private static final long serialVersionUID = -7331054216876226026L;
	
	MenuItem aboutItem;
    MenuItem exitItem;
    MenuItem recapItem;
    MenuItem editorItem;
    MenuItem dictManagerItem;

    /**
     * Creates a new instance of MainMenu.
     */
    public MainMenu() {
        
        exitItem = new MenuItem(PropertyGetter.getNamedProperty("Exit_menu_item"));
        recapItem = new MenuItem(PropertyGetter.getNamedProperty("Words_recap_menu_item"));
        editorItem = new MenuItem(PropertyGetter.getNamedProperty("Words_editor_menu_item"));
        dictManagerItem = new MenuItem(PropertyGetter.getNamedProperty("Program_manager_menu_item"));
        
        this.add(recapItem);
        this.add(editorItem);
        this.addSeparator();
        this.add(dictManagerItem);
        this.addSeparator();
        this.add(exitItem);
    }
    
    /**
     * Sets a listener of event when user closes application.
     * @param listener action to be executed
     */
    public void setExitListener(ActionListener listener) {
        exitItem.addActionListener(listener);
    }
    
    /**
     * Sets a listener of event when user starts recaping.
     * @param listener action to be executed
     */
    public void setRecapListener(ActionListener listener) {
        recapItem.addActionListener(listener);
    }
    
    /**
     * Sets a listener of event when user starts editor.
     * @param listener action to be executed
     */
    public void setEditorListener(ActionListener listener) {
        editorItem.addActionListener(listener);
    }
    
    /**
     * Sets a listener of event when user starts application manager.
     * @param listener action to be executed
     */
    public void setDictManagerListener(ActionListener listener) {
        dictManagerItem.addActionListener(listener);
    }

}
