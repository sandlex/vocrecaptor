package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.controller.Controller;
import com.vocrecaptor.fx.view.TemplatePanel;
import com.vocrecaptor.fx.model.Model;
import javafx.scene.Group;

/**
 * Class represents an abstract subpanel which is extended by
 * all subpanels.
 *
 * @author Alexey Peskov
 */
abstract public class AbstractSubpanel {

    /**
     * Exercise subpanel width.
     */
    public def WIDTH: Number = 385;

    /**
     * Exercise subpanel height.
     */
    public def HEIGHT: Number = 300;

    /**
     * Exercise subpanel horizontal position in relation to parent panel.
     */
    public def X_POSITION: Number = 100;

    /**
     * Exercise subpanel vertical position in relation to parent panel.
     */
    public def Y_POSITION: Number = 160;

    /**
     * Model.
     */
    public var model: Model;
    /**
     * Controller.
     */
    public var controller: Controller;

    /**
     * Parent panel where all subpanels are build in.
     */
    public var parent: TemplatePanel;

    public function getParent() : TemplatePanel {
        return parent;
    }

    public var instance: Group;

    /**
     * Returns an instance of panel to be inserted into parent panel.
     */
     public function getInstance() : Group {
         return instance;
     }

    /**
     * Performs some initial initialization of UI components.
     */
    abstract public function initialize() : Void;

    /**
     * Terminate all processes when user presses back button.
     */
    abstract public function stopProcess() : Void;

}
