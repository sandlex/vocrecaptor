package com.vocrecaptor.fx.view.exercise;

import com.vocrecaptor.fx.view.exercise.ExerciseAbstractSubpanel;
import com.vocrecaptor.fx.model.async.Delayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.ext.swing.SwingHorizontalAlignment;
import javafx.ext.swing.SwingTextField;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;

/**
 * Class represents a panel of two exercises "Definition guessing" and
 * "Translation guessing".
 * In this exercise user sees definition or translation
 * and have 5 seconds to remember translation or definition of this word.
 * In he can he presses the button and word will be displayed immediately
 * otherwise the answer will be shown after countdown timer stops. If you
 * haven't pressed remember button you can remember the word and press
 * "Next >>" button to display next word.
 *
 * @author Alexey Peskov
 */
public class GuessSubpanel extends ExerciseAbstractSubpanel {

    /**
    * Defines a type of exercise.
     */
    public var isDefinitionGuessing: Boolean;
    var delayer: Delayer;
    var waiter: Delayer;
    var timeleft: Integer;
    var answer: String = "5";
    var rememberButton: SwingButton;
    
    override var instance = Group {
        content: [
            VBox {
                impl_layoutX: X_POSITION
                impl_layoutY: Y_POSITION
                spacing: 20
                content: [
                    SwingTextField {
                        text: bind if (isDefinitionGuessing) model.currentWord.translation else model.currentWord.definition
                        columns: 24
                        editable: false
                        horizontalAlignment: SwingHorizontalAlignment.CENTER
                        borderless: true
                        focusable: false
                        font: Font {
                            size: 20
                        }
                        background: Color.web("#E8EEF7")
                    }
                    SwingTextField {
                        text: bind answer
                        columns: 24
                        editable: false
                        horizontalAlignment: SwingHorizontalAlignment.CENTER
                        borderless: true
                        focusable: false
                        font: Font {
                            size: 20
                        }
                        background: Color.web("#E8EEF7")
                    }
                    rememberButton = SwingButton {
                        translateX: WIDTH / 2 - 65
                        translateY: 100
                        width: 130
                        height: 50
                        //text: "Remember"
                        font: Font {
                            size: 20
                        }
                        //action: function() {
                        //    delayer.cancel();
                        //    showAnswerWaitAndGo();
                        //}
                    }
                ]
            }
        ]
    }

    /**
    * Performs initialization of countdown timer and focus the only button.
     */
    override public function initialize() : Void {
        model.activity = if (isDefinitionGuessing) "Definition guessing" else "Translation guessing";

        setButtonDefaultBehavior();

        timeleft = 5;
        answer = "5";
        delayer = Delayer {
            delay: 1
            onComplete: function() : Void {
                if (timeleft > 1) {
                    timeleft--;
                    answer = String.valueOf(timeleft);
                    delayer.start();
                } else {
                    setButtonSpecialBehavior();
                    showNotAnsweredAnswer();
                }
            }
        };
        rememberButton.requestFocus();
    }

    /**
    * Stops countdown timer and timer of correct answer display.
     */
    override public function stopProcess() : Void {
        delayer.cancel();
        waiter.cancel();
    }

    /**
     * Displays a correct answer during one second and moves to the next word.
     */
    function showAnswerWaitAndGo() : Void {
        answer = if (isDefinitionGuessing) model.currentWord.definition else model.currentWord.translation;
        waiter.cancel();
        waiter = Delayer {
            delay: 1
            onComplete: function() : Void {
                exercisePanel.nextWord();
            }
        }
    }

    /**
     * If user didn't remember a word he can wait until remember it and press button to get next word.
     */
    function showNotAnsweredAnswer() : Void {
        answer = if (isDefinitionGuessing) model.currentWord.definition else model.currentWord.translation;
        setButtonSpecialBehavior();
    }

    /**
     * Default behavoir: stop timer, display word for a second and get next.
     */
    function setButtonDefaultBehavior() : Void {
        rememberButton.text = "Remember";
        rememberButton.action = function() {
            delayer.cancel();
            showAnswerWaitAndGo();
        }
    }

    /**
     * Special behavoir: stop timer, display word until Next >> button is pressed.
     */
    function setButtonSpecialBehavior() : Void {
        rememberButton.text = "Next >>";
        rememberButton.action = function() {
            exercisePanel.nextWord();
        }
    }

}
