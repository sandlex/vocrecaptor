package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.model.bean.DictionaryBean;
import com.vocrecaptor.fx.http.HttpManager;
import com.vocrecaptor.fx.xml.XmlUtil;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.ext.swing.SwingComboBoxItem;
import javafx.ext.swing.SwingComboBox;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;

/**
 * Class represents an account panel where user choosing dictionaries,
 * category and session of words to learn and choosing further action -
 * recaping and exercising.
 *
 * @author Alexey Peskov
 */
public class AccountPanel extends AbstractPanel {

    override var width = 274;
    override var height = 320;

    public var dicts: DictionaryBean[];

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

    var instance: Group = Group {
        content: [
            VBox {
                impl_layoutX: 28
                impl_layoutY: 20
                spacing: 10
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
                                model.dict = dicts[dicBox.selectedIndex];
                                categs =
                                    XmlUtil{
                                    }.parseListXmlString(HttpManager.getCategoryList(dicts[dicBox.selectedIndex].id));

                                sessns =
                                    XmlUtil{
                                    }.parseListXmlString(HttpManager.getSessionList(dicts[dicBox.selectedIndex].id));
                            }
                        }
                        content: [
                            SwingLabel {
                                width: 210
                                text: "Dictionary"
                            }
                            dicBox = SwingComboBox {
                                width: 210
                                items: for (dictionary in dicts)
                                    SwingComboBoxItem {
                                        text: dictionary.description
                                        selected: dictionary.description.equals(model.dict.description)
                                    }
                            }
                            SwingLabel {
                                width: 210
                                text: "Category"
                            }
                            catBox = SwingComboBox {
                                width: 210
                            }
                            SwingLabel {
                                width: 210
                                text: "Session"
                            }
                            sesBox = SwingComboBox {
                                width: 210
                            }
                            HBox {
                                translateX: 31
                                translateY: 26
                                spacing: 9
                                content: [
                                    SwingButton {
                                        text: "Recap"
                                        enabled: bind model.dict != null
                                        action: function() {
                                            startActivity(controller.RECAP_PANEL);
                                        }
                                    }
                                    SwingButton {
                                        text: "Exercise"
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
        ]
    }

    /**
     * Returns an instance of panel to be inserted into parent panel.
     */
     override public function getInstance(): Group {
        return instance;
    }

    /**
     * Performs comboboxes initialization.
     */
    override public function initialize() : Void {
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
        controller.switchPanelTo(nextPanel);
    }

}
