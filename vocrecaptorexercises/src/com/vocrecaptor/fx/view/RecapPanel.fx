package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.view.components.SimpleFxButton;
import com.vocrecaptor.fx.view.RecapSettingsPanel;
import com.vocrecaptor.fx.controller.Controller;
import com.vocrecaptor.fx.model.async.Delayer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.ext.swing.SwingHorizontalAlignment;
import javafx.ext.swing.SwingTextField;

/**
 * Class represents a recap panel which displays words from list user
 * chosen on the previous panel. Words are displayed word-by-word and
 * for each word application displays definition followed by translation
 * followed by example of usage. User can choose a reverse order which
 * means the translation is shown firts followed by definition. Also
 * user can choose a period of each part of word will be displayed.
 * In further development user will be allow check words by clicking
 * on he panel area and words will be excluded from recap list and
 * information about that will be send to the server.
 * Word parts are displayed inside text field components because in
 * further development these parts should be able to be changed "on
 * the fly" if these parts contains mistakes.
 * @author Alexey Peskov
 */
public class RecapPanel extends AbstractPanel {

    override var width = bind controller.stage.width;
    override var height = 200;

    var settingsPanel: RecapSettingsPanel;
    
    var delayer: Delayer;
    var wordCounter: Integer = -1;
    var partCounter: Integer = 3;
    var delay: Integer = 2;
    var isReversed: Boolean = false;

    var instance: Group = Group {
        content: [
            VBox {
                impl_layoutY: 30
                content: [
                    SwingTextField { // To edit on the fly later
                        text: bind model.definition
                        editable: false
                        horizontalAlignment: SwingHorizontalAlignment.CENTER
                        width: bind width - 8
                        borderless: true
                        font: Font {
                            size: 17
                        }
                    }
                    SwingTextField {
                        text: bind model.translation
                        editable: false
                        horizontalAlignment: SwingHorizontalAlignment.CENTER
                        width: bind width - 8
                        borderless: true
                        font: Font {
                            size: 17
                        }
                    }
                    SwingTextField {
                        text: bind model.example
                        editable: false
                        horizontalAlignment: SwingHorizontalAlignment.CENTER
                        width: bind width - 8
                        borderless: true
                        font: Font {
                            size: 17
                        }
                    }
                ]
            }
            HBox {
                content: [
                    SimpleFxButton {
                        caption: "<<"
                        width: 20
                        height: 20
                        textX: 4
                        textY: 15
                        onClick: function() : Void {
                            stopProcess();
                            controller.goHome();
                        }
                    }.createButton()
                    SimpleFxButton {
                        caption: "S"
                        width: 20
                        height: 20
                        textX: 6
                        textY: 15
                        onClick: function() : Void {
                            if (sizeof holder.content > 0) delete holder.content[0];
                            settingsPanel = RecapSettingsPanel {
                                parent: this
                                del: delay
                                isr: isReversed
                            }
                            insert settingsPanel.getInstance() into holder.content;
                            settingsPanel.openPanel();
                        }
                    }.createButton()
                ]
            }
            holder = Group {}
        ]
    }

    /**
     * Returns an instance of panel to be inserted into parent panel.
     */
     override public function getInstance() : Group {
        return instance;
    }

    /**
     * Does nothing.
     */
    override public function initialize() : Void {
    }

    /**
     * Starts displaying words repeatedly.
     */
    override public function startProcess() : Void {
        delayer = Delayer {
            onComplete: function() : Void {
                if (partCounter == 3) {
                    if (wordCounter == sizeof model.currentWordsList - 1) {
                        wordCounter = 0;
                    } else {
                        wordCounter++;
                    }
                    partCounter = 1;
                    model.definition = "";
                    model.translation = "";
                    model.example = "";
                } else {
                    partCounter++;
                }

                if (partCounter == 1) {
                    model.definition =
                        if (isReversed) model.currentWordsList[wordCounter].translation
                        else model.currentWordsList[wordCounter].definition;
                } else if (partCounter == 2) {
                    model.translation =
                        if (isReversed) model.currentWordsList[wordCounter].definition
                        else model.currentWordsList[wordCounter].translation;
                } else if (partCounter == 3) {
                    model.example = model.currentWordsList[wordCounter].example;
                }

                delayer.start();
            }
        }
    }

    /**
     * Stops displaying process.
     */
    override public function stopProcess() : Void {
        delayer.cancel();
        wordCounter = -1;
        partCounter = 3;
        model.definition = "";
        model.translation = "";
        model.example = "";
    }

    /**
     * Closes and removes settings panel.
     */
    public function closeSettingsPanel() : Void {
        if (sizeof holder.content > 0) delete holder.content[0];
    }

    /**
     * Sets time for word displaying.
     */
    public function setDelay(delay: Integer) : Void {
        this.delay = delay;
        delayer.setDelay(delay);
    }

    /**
     * Sets reversed order when definition is displayed after translation.
     */
    public function setReversed(isReversed: Boolean) : Void {
        this.isReversed = isReversed;
    }

}
