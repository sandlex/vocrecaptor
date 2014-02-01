package com.vocrecaptor.fx.view.components;

import com.vocrecaptor.fx.view.exercise.MosaicSubpanel;
import com.vocrecaptor.fx.model.Model;

/**
 * Class represents a specifiec button for mosaic exercise only.
 * Button contains information in which column it's placed.
 * Before firing user defined event button checks if it's matching
 * the button from the opposite row and if they are pair - user
 * action invoked.
 *
 * @author Alexey Peskov
 */
public class MosaicFxButton extends FxButton {

    public var panel: MosaicSubpanel;
    public var isInLeftColumn: Boolean;
    public var model: Model;

    /**
     * Invokes user defined function when user clicks button and a
     * condition is met.
     */
     override function onMouseClicked() : Void {
        if (isInLeftColumn) panel.selLeft = this else panel.selRight = this;
        
        for (word in model.currentWordsList) {
            if (word.definition == panel.selLeft.caption and
                word.translation == panel.selRight.caption) {
                    onClick();
            }
        }
    }

}
