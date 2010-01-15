package com.vocrecaptor.fx.view.exercise;

import com.vocrecaptor.fx.view.exercise.ExerciseAbstractSubpanel;
import com.vocrecaptor.fx.view.components.ChoiceFxButton;
import com.vocrecaptor.fx.model.bean.WordBean;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.ext.swing.SwingHorizontalAlignment;
import javafx.ext.swing.SwingTextField;
import javafx.ext.swing.SwingLabel;
import java.util.Random;

/**
 * Class represents a panel of two exercises "Definition choosing" and
 * "Translation choosing".
 * In this exercise user sees definition or translation
 * and should choose translation or definition of this word by
 * clicking the proper button.
 *
 * @author Alexey Peskov
 */
public class ChoiceSubpanel extends ExerciseAbstractSubpanel {

    /**
     * Defines a type of exercise.
     */
    public var isDefinitionChoosing: Boolean;
    var holder: VBox;
    var generator: Random = new Random();
    
    //var instance: Group = Group {
    override var instance = Group {
        content: [
            VBox {
                impl_layoutX: X_POSITION
                impl_layoutY: Y_POSITION
                spacing: 20
                content: [
                    SwingTextField {
                        text: bind if (isDefinitionChoosing) model.currentWord.translation else model.currentWord.definition
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
                    holder = VBox {
                        spacing: 5
                        impl_layoutX: WIDTH / 2 - 200
                        content: [
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
            model.activity = if (isDefinitionChoosing) "Definition choosing" else "Translation choosing";

        if (sizeof holder.content > 0) delete holder.content;
        var limit: Integer;
        if (sizeof model.currentWordsList - 10 * model.tenner > 10) {
            limit = 10;
        } else {
            limit = sizeof model.currentWordsList - 10 * model.tenner;
        }

        var words: WordBean[] = model.currentWordsList[model.tenner * 10..model.tenner * 10 + limit - 1];

        for (word in words) {
            insert ChoiceFxButton {
                caption: if (isDefinitionChoosing) word.definition else word.translation
                width: 383;
                height: 25;
                isKeyButton: if (isDefinitionChoosing) (word.definition == model.currentWord.definition) else (word.translation == model.currentWord.translation)
                onClick: function() : Void {
                    exercisePanel.nextWord();
                }
            }.createButton() before holder.content[generator.nextInt(sizeof words)];
        }
    }

    /**
     * Does nothing.
     */
    override public function stopProcess() : Void {
    }

}
