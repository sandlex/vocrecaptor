package com.vocrecaptor.fx.view.components;

/**
 * Class represents a specifiec button for choice exercise only.
 * Button contains information whether this button contains a correct
 * answer. And if so - user click will invoke user action.
 *
 * @author Alexey Peskov
 */
public class ChoiceFxButton extends FxButton {

    /**
     * Indicates that button contains a correct answer.
     */
    public var isKeyButton: Boolean;

    /**
     * Invokes user defined function when user clicks button and a
     * condition is met.
     */
    override function onMouseClicked() : Void {
        if (isKeyButton) {
            onClick();
        }
    }

}
