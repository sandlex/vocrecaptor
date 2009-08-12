package com.vocrecaptor.fx.view.exercise;

import com.vocrecaptor.fx.controller.Controller;
import com.vocrecaptor.fx.view.ExercisePanel;
import com.vocrecaptor.fx.model.Model;
import javafx.scene.Group;

/**
 * Class represents an abstract panel which is extended by
 * panels of all exercises. These panels are built in parent
 * exercise panel.
 *
 * @author Alexey Peskov
 */
abstract public class ExerciseAbstractPanel {

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
     * Parent exercise panel where all subpanels are buil in.
     */
    public var parent: ExercisePanel;

    /**
     * Returns an instance of panel to be inserted into parent panel.
     */
    abstract public function getInstance() : Group;

    /**
     * Performs some initial initialization of exercise UI components.
     */
    abstract public function prepare() : Void;

    /**
     * Stops threads if such exists in exercise.
     */
    abstract public function stopProcess() : Void;

}
