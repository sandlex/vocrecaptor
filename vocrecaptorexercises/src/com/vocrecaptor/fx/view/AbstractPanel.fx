package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.controller.Controller;
import com.vocrecaptor.fx.model.Model;
import javafx.scene.Group;

/**
 * Class represents an abstract panel which is extended by
 * main application panels.
 *
 * @author Alexey Peskov
 */
abstract public class AbstractPanel {

    /**
     * Default width.
     */
    public var width: Number = 235;

    /**
     * Default height.
     */
    public var height: Number = 265;

    /**
     * Controller to handle user actions.
     */
    public var controller: Controller;

    /**
     * Model to bind ui components.
     */
    public var model: Model;

    /**
     * A place holder to insert additional visual panels
     * like notification messages, popup windows etc.
     */
    public var holder : Group;

    /**
     * Returns panel width.
     */
    public function getWidth() : Number {
        return width;
    }

    /**
     * Returns panel height.
     */
    public function getHeight() : Number {
        return height;
    }

    /**
     * Returns an instance of panel to be inserted into parent panel.
     */
    abstract public function getInstance() : Group;

    /**
     * Performs initialization of visual components.
     */
    abstract public function initialize() : Void;

    /**
     * Starts background process which user is going to use this
     * particular panel for.
     */
    public function startProcess() : Void {
    }

    /**
     * Stops background process when panel is about to be closed.
     */
    public function stopProcess() : Void {
    }

}
