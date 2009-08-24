package com.vocrecaptor.fx.view.components;

import javafx.scene.paint.LinearGradient;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.scene.Cursor;
import javafx.scene.Group;

/**
 * Abstract button. Button represents a rectangle with colored border,
 * gradient fill and text inside the rectangle. Button is mouse-movement
 * sensitive and have a user-specified action invoked on click.
 *
 * @author Alexey Peskov
 */
abstract public class FxButton extends Group {

    public var STANDARD_HEIGHT = 21;

    public var caption: String;
    public var width: Number;
    public var height: Number;
    public var onClick: function(): Void;
    public var textX: Integer = 25;
    public var textY: Integer = 16;

    var isMouseOver = false;
    var isMousePressed = false;

    /**
     * Creates a button.
     */
    public function createButton() : Group {
        return Group {
            content: [
                Rectangle {
                    cursor: Cursor.HAND
                    stroke: bind if (isMouseOver) Color.web("#0094FF") else Color.web("#9FC3F0")
                    width: width
                    height: height
                    arcHeight: 8
                    arcWidth: 8
                    focusable: true
                    fill: bind LinearGradient {
                        startX: 0.0,
                        startY: 0.0,
                        endX: 0.0,
                        endY: 1.0,
                        proportional: true
                        stops: [
                            Stop {
                                offset: 0.0
                                color: if (isMousePressed) Color.web("#9FC3F0") else Color.web("#E8EEF7")
                            },
                            Stop {
                                offset: 1.0
                                color: if (isMousePressed) Color.web("#E8EEF7") else Color.web("#9FC3F0")

                            }
                        ]
                    }

                    onMousePressed: function(evt: MouseEvent) : Void {
                        isMousePressed = true;
                    }

                    onMouseReleased: function(evt: MouseEvent) : Void {
                        isMousePressed = false;
                    }

                    onMouseEntered: function(evt: MouseEvent) : Void {
                        isMouseOver = true;
                    }

                    onMouseExited: function(evt: MouseEvent) : Void {
                        isMouseOver = false;
                    }
                }
                Text {
                    x: textX
                    y: textY
                    content: caption
                }
            ]
            onMouseClicked: function(e: MouseEvent) : Void {
                onMouseClicked();
            }
        }
    }

    /**
     * Function to be called on mouse click.
     */
    abstract function onMouseClicked() : Void;
    
}
