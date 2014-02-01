package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.view.components.SimpleFxButton;
import com.vocrecaptor.fx.view.LoginSubpanel;
import com.vocrecaptor.fx.view.AbstractSubpanel;
import com.vocrecaptor.fx.view.AccountSubpanel;
import javafx.ext.swing.SwingLabel;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.ext.swing.SwingHorizontalAlignment;
import javafx.ext.swing.SwingTextField;
import com.vocrecaptor.fx.controller.Controller;
import com.vocrecaptor.fx.model.Model;


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
public class TemplatePanel {

    /**
    * Controller to handle user actions.
     */
    public var controller: Controller;

    /**
    * Model to bind ui components.
     */
    public var model: Model;

    /**
     * A place holder to insert subpanels.
     */
    public var holder: Group;

    /**
     * A place holder to insert information concerning exercises.
     */
    public var exerciseInfo: Group;

    public var width = 600;
    public var height = 700;

    public def EXERCISE_PANEL_HOLDER_X: Number = 50;
    public def EXERCISE_PANEL_HOLDER_Y: Number = 110;
    public def EXERCISE_PANEL_HOLDER_WIDTH: Integer = 485;
    public def EXERCISE_PANEL_HOLDER_HEIGHT: Integer = 465;

    var panel : AbstractSubpanel;
    var closeButton : SimpleFxButton = SimpleFxButton {
                        width: 20
                        height: 20
                        textX: 4
                        textY: 15
                    }
                    
    var instance: Group = Group {
        content: [
            SwingTextField {
                text: bind model.notification
                editable: false
                horizontalAlignment: SwingHorizontalAlignment.CENTER
                borderless: true
                //background: Color.YELLOW
                width: width
            }
            SwingLabel {
                impl_layoutX: 50
                impl_layoutY: 80
                text: bind model.activity
                width: 400
                font: Font {
                    size: 20
                }
            }
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
            holder = Group {
            }
            exerciseInfo = Group {
            }
            HBox {
                impl_layoutX: EXERCISE_PANEL_HOLDER_X + EXERCISE_PANEL_HOLDER_WIDTH - 20
                impl_layoutY: 85
                content: [
                    closeButton.createButton()
                ]
            }
        ]
    }

    /**
    * Returns an instance of panel to be inserted into parent panel.
     */
    public function getInstance(): Group {
        return instance;
    }

    public function setSubpanel(panel : AbstractSubpanel) : Void {
            this.panel = panel;
            if (sizeof holder.content > 0) delete holder.content[0];
        insert panel.getInstance() into holder.content;
    }

    public function initCloseButton(caption: String, action: function(): Void) : Void {
        closeButton.caption = caption;
        closeButton.onClick = action;
    }

}
