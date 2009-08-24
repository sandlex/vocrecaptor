package com.vocrecaptor.fx.view;

import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.Group;
import javafx.ext.swing.SwingCheckBox;
import javafx.ext.swing.SwingSlider;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;

/**
 * Class represents a panel where user can set up parameters for
 * recaping. User can choose a display time length and reverse order
 * of displaying when definition is displayed after translation.
 * In order to decrease a memory footprint this panel is created each
 * time when it's needed and will be deleted after user closes it.
 *
 * @author Alexey Peskov
 */
public class RecapSettingsPanel {

    public var parent: RecapPanel;
    public var del: Integer;
    public var isr: Boolean;

    def barWidth: Number = 310;
    def barHeight: Number = 132;

    var ww: Number = 0;
    var slider: SwingSlider;
    var check: SwingCheckBox;
    var box: VBox;
    
    var delay: Number = bind slider.value on replace {
        parent.setDelay(delay);
    }

    var isReversed: Boolean = bind check.selected on replace {
        parent.setReversed(isReversed);
    }

    var instance: Group = Group {
        content: [
            Group {
                impl_layoutX: parent.width / 2 - 155
                impl_layoutY: 20

                content: [
                    Rectangle {
                        x: 0
                        y: 0
                        width: bind ww
                        height: barHeight
                        fill: LinearGradient {
                            startX: 0.0
                            startY: 0.0
                            endX: 0.0
                            endY: 1.0
                            stops: [
                                Stop {
                                    color: Color.web("#636363")
                                    offset: 0.0
                                },
                                Stop {
                                    color: Color.web("#4C4C4C")
                                    offset: 1.0
                                },
                            ]
                        }
                    }
                    box = VBox {
                        impl_layoutX: 10
                        impl_layoutY: 10
                        spacing: 10
                        visible: false
                        content: [
                            SwingLabel {
                                text: bind "Delay: {slider.value} sec."
                                width: barWidth - 20
                            }
                            slider = SwingSlider {
                                minimum: 0
                                maximum: 10
                                value: del
                                vertical: false
                            }
                            check = SwingCheckBox {
                                text: "Display definition and translation in reverse order"
                                selected: isr
                            }
                            SwingButton {
                                text: "Close"
                                action: function() {
                                    parent.closeSettingsPanel();
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
     public function getInstance() : Group {
        return instance;
    }

    /**
     * Opens settings panel.
     */
    public function openPanel() : Void {
        Timeline {
            repeatCount: 1
            autoReverse: true
            keyFrames: [
                at (0s) {ww => 0 tween Interpolator.LINEAR},
                at (0.2s) {ww => barWidth tween Interpolator.LINEAR; box.visible => true},
            ]
        }.play();
    }

}