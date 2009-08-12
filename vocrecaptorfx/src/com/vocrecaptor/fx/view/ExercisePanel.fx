package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.view.exercise.ExerciseAbstractPanel;
import com.vocrecaptor.fx.view.exercise.ChoicePanel;
import com.vocrecaptor.fx.view.exercise.MosaicPanel;
import com.vocrecaptor.fx.view.exercise.GuessPanel;
import com.vocrecaptor.fx.view.exercise.WritePanel;
import com.vocrecaptor.fx.view.exercise.CardPanel;
import com.vocrecaptor.fx.view.components.SimpleFxButton;
import com.vocrecaptor.fx.model.async.Saver;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.ext.swing.SwingLabel;

/**
 * Class represents an exercise panel which is a parent panel for
 * all exercise sub-panels. User do exercises for all words from
 * chosen list where all words are groupped by ten meaning he should
 * do all exercises for first tenner then for second and so forth.
 * When he finishes information about learned words is loading to
 * server (it's not implemented now).
 *
 * @author Alexey Peskov
 */
public class ExercisePanel extends AbstractPanel {

    override var width = 600;
    override var height = 700;

    def EXERCISE_PANEL_HOLDER_X: Number = 50;
    def EXERCISE_PANEL_HOLDER_Y: Number = 110;
    def EXERCISE_PANEL_HOLDER_WIDTH: Integer = 485;
    def EXERCISE_PANEL_HOLDER_HEIGHT: Integer = 465;

    var saver: Saver;
    var panel: ExerciseAbstractPanel;

    var exerciseNumber: Integer;
    var wordNumber: Integer;

    var instance: Group = Group {
        content: [
            Rectangle {
                x: EXERCISE_PANEL_HOLDER_X
                y: EXERCISE_PANEL_HOLDER_Y
                width: EXERCISE_PANEL_HOLDER_WIDTH
                height: EXERCISE_PANEL_HOLDER_HEIGHT
                stroke: Color.web("#0094FF")
                fill: Color.web("#E8EEF7")
                arcHeight: 20
                arcWidth: 20
            }
            holder = Group {}
            SwingLabel {
                text: bind "Exercise: {exerciseNumber + 1} out of {sizeof model.exercises}"
                impl_layoutX: EXERCISE_PANEL_HOLDER_X + EXERCISE_PANEL_HOLDER_WIDTH - 150
                impl_layoutY: 87
            }
            HBox {
                impl_layoutX: EXERCISE_PANEL_HOLDER_X + EXERCISE_PANEL_HOLDER_WIDTH - 20
                impl_layoutY: 85
                content: [
                    SimpleFxButton {
                        caption: "<<"
                        width: 20
                        height: 20
                        textX: 4
                        textY: 15
                        onClick: function() : Void {
                            saver.cancel(); //If press goHome wile the final message is displayed
                            panel.stopProcess();
                            controller.goHome();
                        }
                    }.createButton()
                ]
            }
        ]
    }

    /**
     * Returns an instance of panel to be inserted into parent panel.
     */
    override public function getInstance(): Group {
        return instance;
    }

    /**
     * Does nothing.
     */
    override public function initialize() : Void {
    }


    /**
     * Starts exercising.
     */
    override public function startProcess() : Void {
            exerciseNumber =-1;
            //wordNumber = 10;
            nextExercise();
    }
    
    /**
     * Does nothing.
     */
    override public function stopProcess() : Void {
    }

    /**
     * Goes to next exercise. When exercises are completed emulates
     * data saving process.
     */
    public function nextExercise() : Void {

        if (exerciseNumber == sizeof model.exercises - 1) {
            saver = Saver {
                parent: instance
                parentPanel: this
                onComplete: function(value: String) : Void {
                    if (value != "false") {
                        controller.goHome();
                    } else { // If exception during connecting
                        controller.displayMessageAndGo("Connection error", controller.ACCOUNT_PANEL, 5);
                    }
                }
             }
        } else {
            exerciseNumber++;
            wordNumber = -1;

            if (sizeof holder.content > 0) delete holder.content[0];

            if (exerciseNumber == 0) {
                panel = CardPanel {
                    model: model;
                    controller: controller;
                    parent: this;
                }
            } else if (exerciseNumber == 1) {
                panel = GuessPanel {
                    model: model;
                    controller: controller;
                    parent: this;
                    isDefinitionGuessing: true;
                }
            } else if (exerciseNumber == 2) {
                panel = WritePanel{
                    model: model;
                    controller: controller;
                    parent: this;
                }
            } else if (exerciseNumber == 3) {
                panel = ChoicePanel {
                    model: model;
                    controller: controller;
                    parent: this;
                    isDefinitionChoosing: true;
                }
            } else if (exerciseNumber == 4) {
                panel = GuessPanel {
                    model: model;
                    controller: controller;
                    parent: this;
                    isDefinitionGuessing: false;
                }
            } else if (exerciseNumber == 5) {
                panel = ChoicePanel {
                    model: model;
                    controller: controller;
                    parent: this;
                    isDefinitionChoosing: false;
                }
            } else if (exerciseNumber == 6) {
                panel = MosaicPanel {
                    model: model;
                    controller: controller;
                    parent: this;
                }
            }

            nextWord();
            insert panel.getInstance() into holder.content;
        }
    }

    /**
     * Goes to next word from list. Words are learned by tens.
     */
    public function nextWord() : Void {

        if ((wordNumber == 10) or (wordNumber == sizeof model.currentWordsList - 1)) {
            nextExercise();
        } else {
            wordNumber++;
            model.currentWord = model.currentWordsList[wordNumber];
            panel.prepare();
        }
    }

}
