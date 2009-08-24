package com.vocrecaptor.fx.view.exercise;

import com.vocrecaptor.fx.view.exercise.ExerciseAbstractPanel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.ext.swing.SwingTextField;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;
import javafx.scene.paint.Color;

/**
 * Class represents panel of "Writing" exercise.
 * In this exercise user sees translation of the word and its masked definition.
 * In masked definition all characters except whitespaces and dashes
 * are replaced with asterisks. User has to guess and write definition.
 * When he ready to check answer he presses Answer button and if he is right
 * he goes to the next word otherwise he has to try again. He can get a hint
 * by pressing Check button. By clicking this button th correct answer is
 * displayed and stays visible until user starts to write again.
 *
 * @author Alexey Peskov
 */
public class WritePanel extends ExerciseAbstractPanel {

    var field: SwingTextField;
    var maskLabel: SwingLabel;

    //TODO Use event handling onKeyTyped. It's not working now
    var value: String = bind field.text on replace {
        maskLabel.text = getMaskedValue(model.currentWord.definition);
    }
    
    var instance: Group = Group {
        content: [
            SwingLabel {
                impl_layoutX: 50
                impl_layoutY: 80
                text: "Writing"
                font: Font {
                    size: 20
                }
            }
            VBox {
                impl_layoutX: X_POSITION
                impl_layoutY: Y_POSITION
                spacing: 20
                content: [
                    SwingTextField {
                        text: bind model.currentWord.translation
                        width: WIDTH
                        editable: false
                        borderless: true
                        font: Font {
                            size: 20
                        }
                        background: Color.web("#E8EEF7")
                    }
                    field = SwingTextField {
                        columns: 10
                        width: WIDTH
                        font: Font {
                            size: 20
                        }
                        text: ""
                        editable: true
                    }
                    maskLabel = SwingLabel {
                        text: getMaskedValue(model.currentWord.definition)
                        width: WIDTH
                        font: Font {
                            size: 20
                        }
                    }
                    HBox {
                        translateX: WIDTH / 2 - 102
                        spacing: 5
                        content: [
                            SwingButton {
                                text: "Check"
                                width: 100
                                height: 50
                                font: Font {
                                    size: 20
                                }
                                action: function() {
                                    maskLabel.text = model.currentWord.definition;
                                }
                            }
                            SwingButton {
                                text: "Answer"
                                width: 100
                                height: 50
                                font: Font {
                                    size: 20
                                }
                                action: function() {
                                    if (field.text.equalsIgnoreCase(model.currentWord.definition)) {
                                        parent.nextWord();
                                        field.requestFocus();
                                    } else {
                                        maskLabel.text = "Incorrect answer"
                                    }
                                }
                            }
                        ]
                    }
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
     * Performs initialization of text field where user writes and
     * make it focused.
     */
    override public function prepare() : Void {
        maskLabel.text = getMaskedValue(model.currentWord.definition);
        field.text = "";
        field.requestFocus();
    }

    /**
     * Does nothing.
     */
    override public function stopProcess() : Void {
    }

    /**
     * Builds masked value for definition of the word. All characters
     * except whitespaces and dashes will be replaced with asterisks.
     */
    function getMaskedValue(originalValue: String) : String {
        var result: String;
        var i = 0;
        while (i < originalValue.length()) {
            if ((originalValue.charAt(i).toString() == " ") or (originalValue.charAt(i).toString() == "-")) {
                result += originalValue.charAt(i).toString();
            } else {
                result += "*";
            }
            i++;
        }

        return result;
    }

}
