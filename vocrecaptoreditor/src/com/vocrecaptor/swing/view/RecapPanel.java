package com.vocrecaptor.swing.view;

import java.util.Observable;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import com.vocrecaptor.swing.model.Model;
import com.vocrecaptor.swing.view.updatetypes.ClearRecapPanelType;
import com.vocrecaptor.swing.view.updatetypes.SetLookAndFeelType;
import com.vocrecaptor.swing.view.updatetypes.SetReverseOrderType;
import com.vocrecaptor.swing.view.updatetypes.ShowDefinitionType;
import com.vocrecaptor.swing.view.updatetypes.ShowExampleType;
import com.vocrecaptor.swing.view.updatetypes.ShowTranslationType;
import com.vocrecaptor.swing.view.updatetypes.base.UpdateType;

/**
 * Class represents a panel for recap.
 * 
 * @author  Alexey Peskov
 */
public class RecapPanel extends AbstractPanel {
    
	private static final long serialVersionUID = -7747427408055943661L;
	
    private JLabel definitionLabel;
    private JLabel exampleLabel;
    private JLabel translationLabel;
	
    private boolean isReversedOrder;
    
    /** 
     * Creates new form RecapPanel.
     */
    public RecapPanel(Model model) {
    	super(model);
    }
    
    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
    protected void initComponents() {
        definitionLabel = new JLabel();
        translationLabel = new JLabel();
        exampleLabel = new JLabel();

        definitionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        translationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        exampleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(definitionLabel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(translationLabel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(exampleLabel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(definitionLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(translationLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exampleLabel)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }
    
	@Override
	public void update(Observable o, Object arg) {
		
		UpdateType type = (UpdateType) arg;
		
		if (type instanceof ShowDefinitionType) {
			showDefinition(type);
		} else if (type instanceof ShowTranslationType) {
			showTranslation(type);
		} else if (type instanceof ShowExampleType) {
			showExample(type);
		} else if (type instanceof ClearRecapPanelType) {
			clearRecapPanel();
		} else if (type instanceof SetLookAndFeelType) {
			setLookAndFeel(type);
		} else if (type instanceof SetReverseOrderType) {
			setReverseOrder(type);
		}
	}
	
	/**
	 * Displays a word definition.
	 * @param type type of notification event
	 */
	private void showDefinition(UpdateType type) {
		String value = ((ShowDefinitionType) type).getValue();
        if (!isReversedOrder) {
            definitionLabel.setText(value);
        } else {
            translationLabel.setText(value);
        }
	}
	
	/**
	 * Displays a word translation.
	 * @param type type of notification event
	 */
	private void showTranslation(UpdateType type) {
		String value = ((ShowTranslationType) type).getValue();
        if (!isReversedOrder) {
            translationLabel.setText(value);
        } else {
            definitionLabel.setText(value);
        }
	}
	
	/**
	 * Displays a word example.
	 * @param type type of notification event
	 */
	private void showExample(UpdateType type) {
		String value = ((ShowExampleType) type).getValue();
        exampleLabel.setText(value);
	}

	/**
	 * Clears panel.
	 */
	private void clearRecapPanel() {
        definitionLabel.setText("");
        translationLabel.setText("");
        exampleLabel.setText("");
	}
	
	/**
	 * Initializes a reverse order flag.
	 * @param type type of notification event
	 */
	private void setReverseOrder(UpdateType type) {
		Boolean value = ((SetReverseOrderType) type).getValue();
        this.isReversedOrder = value.booleanValue();
	}
    
}
