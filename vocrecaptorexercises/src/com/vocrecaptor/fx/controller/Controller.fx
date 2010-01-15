package com.vocrecaptor.fx.controller;

import com.vocrecaptor.fx.model.async.Delayer;
import com.vocrecaptor.fx.model.bean.UserBean;
import com.vocrecaptor.fx.model.Model;
import com.vocrecaptor.fx.view.AccountSubpanel;
import com.vocrecaptor.fx.view.LoginSubpanel;
import com.vocrecaptor.fx.view.ExerciseSubpanel;
import com.vocrecaptor.fx.view.AbstractSubpanel;
import com.vocrecaptor.fx.http.HttpManager;
import com.vocrecaptor.fx.xml.XmlUtil;
import com.vocrecaptor.fx.view.TemplatePanel;

/**
 * Controller class. Handles switching between views.
 *
 * @author Alexey Peskov
 */
public class Controller {

    public def LOGIN_PANEL: String = "LoginPanel";
    public def ACCOUNT_PANEL: String = "AccountPanel";
    public def EXERCISE_PANEL: String = "ExercisePanel";

    public var model: Model;
    public var templatePanel: TemplatePanel;

    public function setTemplatePanel(panel: TemplatePanel) : Void {
        this.templatePanel = panel;
    }

    /**
    * Main controller method performing switching between
     * panels. Opening of a certain panel is accomplished
     * with starting of its processes likes recaping and
     * exercises.
     */
    public function switchPanelTo(panelType: String) : Void {

        var subPanel : AbstractSubpanel;
        
        if (panelType == LOGIN_PANEL) {
                model.activity = "Login";
            subPanel = LoginSubpanel {
                model: model;
                controller: this;
                parent: templatePanel;
            }
            templatePanel.initCloseButton(" X", function() : Void {
                            java.lang.System.exit(0);
                        });
        } else if (panelType == ACCOUNT_PANEL) {
                model.activity = "Account";
            subPanel = AccountSubpanel {
                model: model;
                controller: this;
                parent: templatePanel;
            };
            templatePanel.initCloseButton("<<", function() : Void {
                                    model.clearState();
                                    switchPanelTo(LOGIN_PANEL);
                        });
        } else if (panelType == EXERCISE_PANEL) {
            subPanel = ExerciseSubpanel {
                model: model;
                controller: this;
                parent: templatePanel;
            };
            templatePanel.initCloseButton("<<", function() : Void {
                            subPanel.stopProcess();
                            signIn(model.user);
                            //switchPanelTo(ACCOUNT_PANEL);
                        });
        }

        templatePanel.setSubpanel(subPanel);
        subPanel.initialize();
    }

    /**
    * Performs user login and swithes to starting panel
     * which is an account panel.
     */
    public function signIn(user: UserBean) : Void {
        model.user = user;
        goHome();
        model.notification = "";
    }

    /**
    * Performs loading of words list via http. All further
     * recaping and exercises will use this list of words.
     */
    public function pickDictionary() : Void {
        //TODO Add async call.
        model.currentWordsList =
        XmlUtil{
        }.parseWordsXmlString(
            HttpManager.getWordList(model.dict.id, model.category, model.session));
    }

    /**
     * Refreshes dictionary list from server via http and switches
     * application to user starting point which is an account panel.
     * If anonymous session is started fetches languages from server
     * to provide user with a choice of public dictionaries for
     * desired languages.
     */
    public function goHome() : Void {
        println("loading dicts...");
        if (model.user.id == -1 and model.user.login == "anonymous") {
            model.dicts =
            XmlUtil {
            }.parseDictsXmlString(
            HttpManager.getDictionaryList(model.user.id, "public", 
            model.languages[model.learningLanguage].shortName,
            model.languages[model.throughLanguage].shortName));
        } else {
            model.dicts =
            XmlUtil {
            }.parseDictsXmlString(
            HttpManager.getDictionaryList(model.user.id, "private", "", ""));
        }
        println("loaded {sizeof model.dicts} dicts");
        switchPanelTo(ACCOUNT_PANEL);
    }

    /**
    * Occurs when fatal error happens, for example connection with
     * server cannot be established (login, loading data).
     * Displays information message and goes to login screen.
     * 
     * TODO: If problem occures during exercising or recaping process
     * we store result locally and send them to server when connection
     * is restored.
     */
    public function displayMessageAndGo(message: String, panelToGo: String, delay: Integer) : Delayer {
        return Delayer {
            delay: delay;
            onComplete: function() : Void {
                switchPanelTo(panelToGo);
            }
        }
    }

}
