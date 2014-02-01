package com.vocrecaptor.fx.view.exercise;

import com.vocrecaptor.fx.view.exercise.ExerciseAbstractSubpanel;
import com.vocrecaptor.fx.view.components.MosaicFxButton;
import com.vocrecaptor.fx.model.bean.WordBean;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.ext.swing.SwingLabel;
import java.util.Random;

/**
 * Class represents a panel of "Mosaic" exercise.
 * In this exercise user sees two columns - in right-hand column
 * buttons with translations, in left-hand column - with definitions.
 * Buttons are arranged in random order. User should find pair and remove
 * words by clicking on these mathcing buttons. Exercise ends when user
 * deletes all buttons.
 *
 * @author Alexey Peskov
 */
public class MosaicSubpanel extends ExerciseAbstractSubpanel {

    public var selLeft: MosaicFxButton;
    public var selRight: MosaicFxButton;
    public var leftButtons: MosaicFxButton[];
    public var rightButtons: MosaicFxButton[];

    def ACTION_BUTTON_WIDTH: Integer = 190;
    def ACTION_BUTTON_HEIGHT: Integer = 30;

    var lInd: Integer = 0;
    var rInd: Integer = 0;
    var leftRow: VBox;
    var rightRow: VBox;
    var generator: Random = new Random();

    override var instance = Group {
        content: [
            VBox {
                impl_layoutX: X_POSITION
                impl_layoutY: Y_POSITION
                spacing: 20
                content: [
                    HBox {
                        spacing: 5
                        content: [
                            leftRow = VBox {
                                spacing: 5
                                content: [
                                ]
                            }
                            rightRow = VBox {
                                spacing: 5
                                content: [
                                ]
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
    /*override public function getInstance(): Group {
        return instance;
    }*/

    /**
     * Performs initialization of buttons.
     */
    override public function initialize() : Void {
            model.activity = "Mosaic";

        var randomIndex : Integer;

        var limit: Integer;
        if (sizeof model.currentWordsList - 10 * model.tenner > 10) {
            limit = 10;
        } else {
            limit = sizeof model.currentWordsList - 10 * model.tenner;
        }

        var words: WordBean[] = model.currentWordsList[model.tenner * 10..model.tenner * 10 + limit - 1];

        for (word in words) {
            var leftButton = MosaicFxButton {
                model: model
                caption: word.definition
                width: ACTION_BUTTON_WIDTH
                height: ACTION_BUTTON_HEIGHT
                panel: this
                isInLeftColumn: true
                onClick: function() : Void {
                    deleteIndexedButton();
                }
            }
            randomIndex = generator.nextInt(sizeof words);
            insert leftButton before leftButtons[randomIndex];
            insert leftButton.createButton() before leftRow.content[randomIndex];
        }

        for (word in words) {
            var rightButton = MosaicFxButton {
                model: model
                caption: word.translation
                width: ACTION_BUTTON_WIDTH
                height: ACTION_BUTTON_HEIGHT
                panel: this
                isInLeftColumn: false
                onClick: function() : Void {
                    deleteIndexedButton();
                }
            }
            randomIndex = generator.nextInt(sizeof words);
            insert rightButton before rightButtons[randomIndex];
            insert rightButton.createButton() before rightRow.content[randomIndex];
        }
    }


    /**
     * Does nothing.
     */
    override public function stopProcess() : Void {
    }

    /**
     * Deletes a pair of buttons user have chosen if these buttons
     * are matching.
     */
    function deleteIndexedButton() : Void {
        var i: Integer = 0;
        for (btn in rightButtons) {
            if (btn.caption == selRight.caption) {
                delete btn from rightButtons;
                delete rightRow.content[i];
            }
            i++;
        }

        i = 0;
        for (btn in leftButtons) {
            if (btn.caption == selLeft.caption) {
                delete btn from leftButtons;
                delete leftRow.content[i];
            }
            i++;
        }

        if (sizeof leftRow.content == 0 and sizeof rightRow.content == 0) {
            exercisePanel.nextExercise();
        }
    }

}
