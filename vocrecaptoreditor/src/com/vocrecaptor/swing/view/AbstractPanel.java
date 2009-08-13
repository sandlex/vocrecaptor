package com.vocrecaptor.swing.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.vocrecaptor.swing.model.Model;
import com.vocrecaptor.swing.view.updatetypes.SetLookAndFeelType;
import com.vocrecaptor.swing.view.updatetypes.base.UpdateType;

/**
 * Abstract panel class generalizing some features and behavior all
 * views should have, implement and extend.
 * 
 * @author Alexey Peskov
 */
public abstract class AbstractPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 2048183432163029430L;
	
	protected Model model;
	protected JFrame parentFrame;

	/**
	 * Constructor. Create and initializes a panel.
	 * 
	 * @param model a reference to Model
	 */
    public AbstractPanel(Model model) {
		super();
		this.model = model;
		initComponents();
	}

    @Override
	public abstract void update(Observable o, Object arg);
    
    /**
     * Performs initialization and layouting of panel's child components. 
     */
    protected abstract void initComponents();
    
    /**
     * Sets panel frame it belongs to.
     * @param frame parent frame
     */
	public void setParentFrame(JFrame frame) {
        parentFrame = frame;
    }
    
    /**
     * Applies a look and feel chosen by user or restored to panel frame
     * and all child components.
     * @param lookAndFeelClassName look and feel java class name
     */
    protected void setLookAndFeel(String lookAndFeelClassName) {
        try {
            UIManager.setLookAndFeel(lookAndFeelClassName);
            SwingUtilities.updateComponentTreeUI(parentFrame);
            parentFrame.pack();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Reacts on changes of observable Model when new look and feel style is being
     * applied.
     * @param type SetLookAndFeelType event
     */
	protected void setLookAndFeel(UpdateType type) {
		String value = ((SetLookAndFeelType) type).getValue();
		setLookAndFeel(value);
	}
	
}
