package com.vocrecaptor.fx.view.exercise;

import com.vocrecaptor.fx.view.exercise.ExerciseAbstractPanel;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.ext.swing.SwingHorizontalAlignment;
import javafx.ext.swing.SwingTextField;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;


/**
 * Class represents a panel of exercise "Cards".
 * In this exercise user sees definition and translation
 * and clicks to go to the next words when remember current word.
 *
 * @author Alexey Peskov
 */
public class CardPanel extends ExerciseAbstractPanel {

    var defin: String = "definition";
    var trans: String = "translation";
    var nextButton: SwingButton;
    
    var instance: Group = Group {
        content: [
            SwingLabel {
                impl_layoutX: 50
                impl_layoutY: 80
                text: "Cards"
                font: Font {
                    size: 20
                }
            }
            VBox {
                impl_layoutX: X_POSITION
                impl_layoutY: Y_POSITION
                spacing: 20
                content:[
                    SwingTextField {
                        text: bind defin
                        width: WIDTH
                        editable: false
                        horizontalAlignment: SwingHorizontalAlignment.CENTER
                        borderless: true
                        font: Font {
                            size: 20
                        }
                        background: Color.web("#E8EEF7")
                    }
                    SwingTextField {
                        text: bind trans
                        width: WIDTH
                        editable: false
                        horizontalAlignment: SwingHorizontalAlignment.CENTER
                        borderless: true
                        font: Font {
                            size: 20
                        }
                        background: Color.web("#E8EEF7")
                    }
                    nextButton = SwingButton {
                        translateX: WIDTH / 2 - 50
                        translateY: 100
                        width: 100
                        height: 50
                        text: "Next >>"
                        font: Font {
                            size: 20
                        }
                        action: function() {
                            parent.nextWord();
                        }
                    }
                ]
            }
        ]
    }

    /**
     * Returns an instance of panel to be inserted into parent panel.
     */
    override public function getInstance() : Group {
        return instance;
    }

    /**
     * Performs initialization of textfields for displaying and
     * sets focus to the only button.
     */
     override public function prepare() : Void {
        defin = model.currentWord.definition;
        trans = model.currentWord.translation;
        nextButton.requestFocus();
    }

    /**
     * Does nothing.
     */
    override public function stopProcess() : Void {
    }

}
