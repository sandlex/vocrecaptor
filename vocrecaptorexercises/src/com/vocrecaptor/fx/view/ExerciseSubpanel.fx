package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.model.async.Saver;
import com.vocrecaptor.fx.view.exercise.CardSubpanel;
import com.vocrecaptor.fx.view.exercise.ChoiceSubpanel;
import com.vocrecaptor.fx.view.exercise.ExerciseAbstractSubpanel;
import com.vocrecaptor.fx.view.exercise.GuessSubpanel;
import com.vocrecaptor.fx.view.exercise.MosaicSubpanel;
import com.vocrecaptor.fx.view.exercise.WriteSubpanel;
import javafx.ext.swing.SwingLabel;
import javafx.scene.Group;


/**
 * @author Alexey_Peskov
 */

public class ExerciseSubpanel extends AbstractSubpanel {

    var saver: Saver;
    var panel: ExerciseAbstractSubpanel;

    var exerciseNumber: Integer;

    /**
     * Stops threads if such exists in exercise.
     */
    override public function stopProcess() : Void {
            saver.cancel();
            panel.stopProcess();
            if (sizeof parent.exerciseInfo.content > 0) delete parent.exerciseInfo.content[0];
    }

    /**
     * Goes to next exercise. When exercises are completed emulates
     * data saving process.
     */
    public function nextExercise() : Void {

        if (exerciseNumber == sizeof model.exercises - 1) {
            if (sizeof model.currentWordsList - 10 * model.tenner > 10) {
                model.tenner++;
                exerciseNumber = -1;
                nextExercise();
            } else if (model.user.id != -1 and model.user.login != "anonymous") {
                saver = Saver {
                    onComplete: function(value: String) : Void {
                        if (value != "false") {
                            controller.goHome();
                        } else { // If exception during connecting
                            controller.displayMessageAndGo("Connection error", controller.ACCOUNT_PANEL, 5);
                        }
                    }
                }

            } else {
                controller.goHome();
            }
        } else {
            exerciseNumber++;
            model.wordNumber = -1;

            if (sizeof parent.holder.content > 0) delete parent.holder.content[0];

            if (exerciseNumber == 0) {
                panel = CardSubpanel {
                    model: model;
                    controller: controller;
                    exercisePanel: this;
                }
            } else if (exerciseNumber == 1) {
                panel = GuessSubpanel {
                    model: model;
                    controller: controller;
                    exercisePanel: this;
                    isDefinitionGuessing: true;
                }
            } else if (exerciseNumber == 2) {
                panel = WriteSubpanel{
                    model: model;
                    controller: controller;
                    exercisePanel: this;
                }
            } else if (exerciseNumber == 3) {
                panel = ChoiceSubpanel {
                    model: model;
                    controller: controller;
                    exercisePanel: this;
                    isDefinitionChoosing: true;
                }
            } else if (exerciseNumber == 4) {
                panel = GuessSubpanel {
                    model: model;
                    controller: controller;
                    exercisePanel: this;
                    isDefinitionGuessing: false;
                }
            } else if (exerciseNumber == 5) {
                panel = ChoiceSubpanel {
                    model: model;
                    controller: controller;
                    exercisePanel: this;
                    isDefinitionChoosing: false;
                }
            } else if (exerciseNumber == 6) {
                panel = MosaicSubpanel {
                    model: model;
                    controller: controller;
                    exercisePanel: this;
                }
            }

            insert panel.getInstance() into parent.holder.content;
            nextWord();
        }
    }

    /**
     * Goes to next word from list. Words are learned by tens.
     */
    public function nextWord() : Void {

        if ((model.wordNumber == 9) or (model.wordNumber == sizeof model.currentWordsList - model.tenner * 10 - 1)) {
            nextExercise();
        } else {
            model.wordNumber++;
            model.currentWord = model.currentWordsList[model.tenner * 10 + model.wordNumber];
            panel.initialize();
        }
    }

    var exerciseInfo : Group = Group {
        content: [
            SwingLabel {
                text: bind "Words {model.tenner * 10 + 1} - {model.tenner * 10 + (if (sizeof model.currentWordsList - 10 * model.tenner > 10) 10 else sizeof model.currentWordsList - 10 * model.tenner)} out of {sizeof model.currentWordsList}"
                impl_layoutX: parent.EXERCISE_PANEL_HOLDER_X + parent.EXERCISE_PANEL_HOLDER_WIDTH - 200
                impl_layoutY: 87
                width: parent.EXERCISE_PANEL_HOLDER_WIDTH
            }
            SwingLabel {
                text: bind "Exercise {exerciseNumber + 1} out of {sizeof model.exercises}"
                impl_layoutX: parent.EXERCISE_PANEL_HOLDER_X + parent.EXERCISE_PANEL_HOLDER_WIDTH - 200
                impl_layoutY: 67
            }
        ]
    }

    override public function initialize() : Void {
        insert exerciseInfo into parent.exerciseInfo.content;
        exerciseNumber = -1;
        model.tenner = 0;
        nextExercise();
    }

}
