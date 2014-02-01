package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.view.components.SimpleFxButton;
import com.vocrecaptor.fx.http.HttpManager;
import com.vocrecaptor.fx.xml.XmlUtil;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.ext.swing.SwingComboBoxItem;
import javafx.ext.swing.SwingComboBox;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;
import javafx.scene.text.Font;

/**
 * Class represents an account panel where user choosing dictionaries,
 * category and session of words to learn and choosing further action -
 * recaping and exercising.
 *
 * @author Alexey Peskov
 */
public class AccountSubpanel extends AbstractSubpanel {

    //public var dicts: DictionaryBean[];

    var dicBox: SwingComboBox;
    var catBox: SwingComboBox;
    var sesBox: SwingComboBox;

    var categs: String[] on replace {
        catBox.items = for (category in categs)
            SwingComboBoxItem {
                text: category
        }
    }

    var sessns: String[] on replace {
        sesBox.items = for (session in sessns)
            SwingComboBoxItem {
                text: session
        }
    }

    override var instance = Group {
        content: [
            VBox {
                impl_layoutX: X_POSITION
                impl_layoutY: Y_POSITION
                spacing: 30
                content: [
                    HBox {
                        spacing: 10
                        content: [
                            SwingLabel {
                                text: "Logged as:"
                            }
                            SwingLabel {
                                text: bind model.user.login
                                width: 210
                            }
                        ]
                    }
                    VBox {
                        var curDict: Integer = bind dicBox.selectedIndex on replace {
                            if (sizeof dicBox.items > 0 and dicBox.selectedIndex != - 1) {
                                model.dict = model.dicts[dicBox.selectedIndex];
                                categs =
                                    XmlUtil{
                                    }.parseListXmlString(HttpManager.getCategoryList(model.dicts[dicBox.selectedIndex].id));
                                println("categs {sizeof categs}");
                                sessns =
                                    XmlUtil{
                                    }.parseListXmlString(HttpManager.getSessionList(model.dicts[dicBox.selectedIndex].id));
                                println("sessns {sizeof sessns}");
                            }
                        }
                        content: [
                            SwingLabel {
                                width: 210
                                text: "Dictionary"
                            }
                            dicBox = SwingComboBox {
                                width: WIDTH - 20
                                items: for (dictionary in model.dicts)
                                    SwingComboBoxItem {
                                        text: dictionary.description
                                        //selected: dictionary.description.equals(model.dict.description)
                                    }
                            }
                            SwingLabel {
                                width: 210
                                text: "Category"
                            }
                            catBox = SwingComboBox {
                                width: WIDTH - 20
                            }
                            SwingLabel {
                                width: 210
                                text: "Session"
                            }
                            sesBox = SwingComboBox {
                                width: WIDTH - 20
                            }
                                    SwingButton {
                                        translateX: WIDTH / 2 - 50
                                        translateY: 100
                                        width: 100
                                        height: 50
                                        font: Font {
                                            size: 20
                                        }
                                        text: "Start"
                                        enabled: bind model.dict != null
                                        action: function() {
                                            startActivity(controller.EXERCISE_PANEL);
                                        }
                                    }
]
                            }
                ]
            }
        ]
    }

    /**
     * Performs comboboxes initialization.
     */
    override public function initialize() : Void {
        model.activity = "Account";
        dicBox.getJComboBox().setMaximumRowCount(5);
        catBox.getJComboBox().setMaximumRowCount(5);
        sesBox.getJComboBox().setMaximumRowCount(5);
    }

    /**
     * Prepares and opens activity chosen by user - recaping
     * or exercising.
     */
    function startActivity(nextPanel: String) : Void {
        model.category = catBox.items[catBox.selectedIndex].text;
        model.session = sesBox.items[sesBox.selectedIndex].text;
        controller.pickDictionary();

        if (sizeof model.currentWordsList > 0 and nextPanel.equals(controller.EXERCISE_PANEL)) {
            model.notification = "";
            controller.switchPanelTo(nextPanel);
        } else {
            model.notification = "No words for these criterias";
        }

    }

    override public function stopProcess() : Void {
    }

}
