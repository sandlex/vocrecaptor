package com.vocrecaptor.fx.view;

import com.vocrecaptor.fx.model.async.Connector;
import com.vocrecaptor.fx.model.bean.UserBean;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.ext.swing.SwingTextField;
import javafx.ext.swing.SwingButton;
import javafx.ext.swing.SwingLabel;
import java.lang.System;

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
public class LoginPanel extends AbstractPanel {

    override var width = 238;
    override var height = 265;

    var str: String;
    var login: SwingTextField;
    var password: SwingTextField;

    var instance: Group = Group {
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
                                width: 190
                            }
                        ]
                    }
                    VBox {
                        content: [
                            SwingLabel {
                                text: "Password"
                            }
                            password = SwingTextField {
                                width: 190
                            }
                        ]
                    }
                    HBox {
                        translateX: 24
                        translateY: 15
                        spacing: 9
                        content: [
                            SwingButton {
                                text: "Sign in"
                                action: function() {
                                    Connector {
                                        login: login.text
                                        password: password.text
                                        parent: instance
                                        parentPanel: this
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
                            SwingButton {
                                text: "Cancel"
                                action: function() {
                                    System.exit(0)
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
     override public function getInstance(): Group {
        return instance;
    }

    /**
     * Doesn nothing.
     */
     override public function initialize() : Void {
    }

}