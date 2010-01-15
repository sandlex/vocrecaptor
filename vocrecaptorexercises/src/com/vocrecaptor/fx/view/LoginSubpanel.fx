package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.model.async.LanguagesLoader;
import com.vocrecaptor.fx.model.async.Connector;
import com.vocrecaptor.fx.model.bean.UserBean;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.ext.swing.SwingTextField;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;
import javafx.ext.swing.SwingCheckBox;
import javafx.ext.swing.SwingComboBoxItem;
import javafx.ext.swing.SwingComboBox;
import javafx.scene.text.Font;
import com.vocrecaptor.fx.http.HttpManager;
import com.vocrecaptor.fx.xml.XmlUtil;

/**
 * Class represents a login panel. User enters his login and
 * password and should be authorized. Login and password are
 * the same as he used for registration on the web-site.
 * If authorization is successfully complete program loads
 * all user dictionaries and displays them on the following
 * panel (account panel).
 *
 * @author Alexey Peskov
 */
public class LoginSubpanel extends AbstractSubpanel {

    var str: String;
    var login: SwingTextField;
    var password: SwingTextField;
    var anonymous: SwingCheckBox;

    var learningBox: SwingComboBox;
    var throughBox: SwingComboBox;

    override var instance = Group {
        content: [
            VBox {
                impl_layoutX: X_POSITION
                impl_layoutY: Y_POSITION
                spacing: 20
                content: [
                    VBox {
                        impl_layoutX: 20
                        impl_layoutY: 20
                        spacing: 10
                        content: [
                            VBox {
                                content: [
                                    SwingLabel {
                                        text: "Login"
                                    }
                                    login = SwingTextField {
                                        columns: 24
                                        enabled: bind not anonymous.selected
                                        font: Font {
                                            size: 20
                                        }
                                    }
                                ]
                            }
                            VBox {
                                content: [
                                    SwingLabel {
                                        text: "Password"
                                    }
                                    password = SwingTextField {
                                        columns: 24
                                        enabled: bind not anonymous.selected
                                        font: Font {
                                            size: 20
                                        }
                                    }
                                ]
                            }
                            anonymous = SwingCheckBox {
                                text: "Anonymous"
                            }
                            VBox {
                                impl_layoutX: 10
                                impl_layoutY: 150
                                visible: bind anonymous.selected
                                content: [
                                    SwingLabel {
                                        width: 210
                                        text: "You learn"
                                    }
                                    learningBox = SwingComboBox {
                                        width: 190 - 20
                                        items: bind for (language in model.languages)
                                            SwingComboBoxItem {
                                                text: language.name
                                            }
                                    }
                                    SwingLabel {
                                        text: "through"
                                    }
                                    throughBox = SwingComboBox {
                                        width: 190 - 20
                                        items: bind for (language in model.languages)
                                            SwingComboBoxItem {
                                                text: language.name
                                            }
                                    }
                                ]
                            }
                                    SwingButton {
                                        translateX: WIDTH / 2 - 50
                                        translateY: 90
                                        width: 100
                                        height: 50
                                        font: Font {
                                            size: 20
                                        }
                                        text: "Sign in"
                                        enabled: bind (anonymous.selected and learningBox.selectedIndex != - 1 and throughBox.selectedIndex != - 1) or (not anonymous.selected and login.text != "" and password.text != "")
                                        action: function() {
                                                model.notification = "Signing in...";
                                            if (anonymous.selected) {
                                                model.learningLanguage = learningBox.selectedIndex;
                                                model.throughLanguage = throughBox.selectedIndex;
                                                controller.signIn(UserBean {
                                                    id: -1;
                                                    login: "anonymous";
                                                })
                                            } else {
                                                Connector {
                                                    login: login.text
                                                    password: password.text
                                                    onComplete: function(value: Long) : Void {
                                                        if (value >= 0) {
                                                            controller.signIn(UserBean {
                                                                id: value.intValue();
                                                                login: login.text;
                                                            })
                                                        } else { // If exception during connecting
                                                            controller.displayMessageAndGo("Connection error", controller.LOGIN_PANEL, 5);
                                                        }
                                                }
                                            }
                                            }
                                        }
                                    }
                        ]
                    }
                    SwingLabel {
                        text: "v1.2.0"
                        translateX: WIDTH - 20
                        translateY: 100
                        font: Font {
                            size: 10
                        }
                    }
                ]
            }
        ]
    }

    override public function initialize() : Void {
        model.activity = "Login";
        model.notification = "Loading languages...";

        LanguagesLoader {
            login: "anonymous"
            onComplete: function(value: String) : Void {
                if (value != "") {
                    model.languages = XmlUtil {}.parseLanguagesXmlString(value);
                    model.notification = "";
                } else {
                    controller.displayMessageAndGo("Loading language error", controller.LOGIN_PANEL, 5);
                }
            }
        }
    }

    override public function stopProcess() : Void {
    }

}