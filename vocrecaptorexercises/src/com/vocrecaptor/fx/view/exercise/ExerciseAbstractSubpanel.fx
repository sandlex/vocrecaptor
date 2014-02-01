package com.vocrecaptor.fx.view.exercise;

import com.vocrecaptor.fx.view.AbstractSubpanel;
import com.vocrecaptor.fx.view.ExerciseSubpanel;

/**
 * Class represents an abstract panel which is extended by
 * panels of all exercises. These panels are built in parent
 * exercise panel.
 *
 * @author Alexey Peskov
 */
abstract public class ExerciseAbstractSubpanel extends AbstractSubpanel {

    /**
     * Parent panel where all subpanels are build in.
     */
    public var exercisePanel: ExerciseSubpanel;

    public def EXERCISE_PANEL_HOLDER_X: Number = 50;
    public def EXERCISE_PANEL_HOLDER_WIDTH: Integer = 485;
}
