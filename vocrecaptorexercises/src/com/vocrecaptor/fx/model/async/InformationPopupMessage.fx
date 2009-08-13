package com.vocrecaptor.fx.model.async;

import com.vocrecaptor.fx.view.AbstractPanel;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.Group;
import javafx.ext.swing.SwingLabel;

/**
 * Class represents an information message popping up under
 * panel and displaying some short text message.
 *
 * @author Alexey Peskov
 */
public class InformationPopupMessage {

    /**
     * Message to display.
     */
    public var message: String;

    /**
     * Panel to be centered in relation to.
     */
    public var parent: AbstractPanel;

    var rect: Rectangle;

    var instance: Group = Group {
        content: [
            rect = Rectangle {
                x: parent.width / 2 - 70
                y: parent.height / 2 - 50
                width: 130
                height: 20
                stroke: Color.RED
                fill: LinearGradient {
                    startX: 0.0,
                    startY: 0.0,
                    endX: 0.0,
                    endY: 1.0,
                    proportional: true
                    stops: [
                        Stop {
                            offset: 0.0
                            color: Color.web("#E8EEF7")
                        },
                        Stop {
                            offset: 1.0
                            color: Color.web("#9FC3F0")
                        }
                    ]
                }
            }
            SwingLabel {
                impl_layoutX: rect.x + 19
                impl_layoutY: rect.y + 2
                text: message
            }
        ]
    }

    /**
     * Returns information message component.
     */
    public function getInstance() : Group {
        return instance;
    }

}
