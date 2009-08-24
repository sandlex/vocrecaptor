package com.vocrecaptor.fx.view.components;

/**
 * Class represents a simple button with default behaviour -
 * user function is invoked without any conditions when mouse
 * clicked.
 *
 * @author Alexey Peskov
 */
public class SimpleFxButton extends FxButton {

    /**
     * Invokes user defined function when user clicks button.
     */
    override function onMouseClicked() : Void {
        onClick();
    }

}
