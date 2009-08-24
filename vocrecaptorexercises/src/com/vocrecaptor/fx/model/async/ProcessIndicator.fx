package com.vocrecaptor.fx.model.async;

import com.vocrecaptor.fx.view.AbstractPanel;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.Stop;
import javafx.scene.Group;
import javafx.ext.swing.SwingLabel;

/**
 * Class represents a progress indicator popping up under panel and 
 * displaying some short text message and displaying animated icon.
 *
 * @author Alexey Peskov
 */
public class ProcessIndicator {

    /**
     * Message to display.
     */
    public var message: String;

    /**
     * Panel to be centered in relation to.
     */
    public var parent: AbstractPanel;

    var outerRect: Rectangle;
    var innerRect: Rectangle;

    var instance: Group = Group {
        content: [
            outerRect = Rectangle {
                x: parent.width / 2 - 40
                y: parent.height / 2 - 65
                width: 68
                height: 70
                stroke: Color.web("#0094FF")
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
            innerRect = Rectangle {
                x: outerRect.x + 12
                y: outerRect.y + 5
                width: 45
                height: 45
                fill: Color.GRAY
            }
            ImageView {
                x: innerRect.x + 1
                y: innerRect.y + 1
                image: Image {
                    url: "{__DIR__}loading.gif"
                }
            }
            SwingLabel {
                impl_layoutX: outerRect.x + 3
                impl_layoutY: outerRect.y + 52
                text: message
            }
        ]
    }

    /**
     * Returns progress indicator component.
     */
    public function getInstance() : Group {
        return instance;
    }
}
