package com.vocrecaptor.fx.controller;

import com.vocrecaptor.fx.model.async.InformationPopupMessage;
import com.vocrecaptor.fx.model.async.Delayer;
import com.vocrecaptor.fx.model.bean.UserBean;
import com.vocrecaptor.fx.model.Model;
import com.vocrecaptor.fx.view.ExercisePanel;
import com.vocrecaptor.fx.view.AbstractPanel;
import com.vocrecaptor.fx.view.AccountPanel;
import com.vocrecaptor.fx.view.LoginPanel;
import com.vocrecaptor.fx.view.RecapPanel;
import com.vocrecaptor.fx.http.HttpManager;
import com.vocrecaptor.fx.xml.XmlUtil;
import javafx.stage.Stage;

/**
 * Controller class. Handles switching between views.
 *
 * @author Alexey Peskov
 */
public class Controller {

    public def LOGIN_PANEL: String = "LoginPanel";
    public def ACCOUNT_PANEL: String = "AccountPanel";
    public def RECAP_PANEL: String = "RecapPanel";
    public def EXERCISE_PANEL: String = "ExercisePanel";

    public var model: Model;
    public var stage: Stage;
    var panel: AbstractPanel;

    /**
     * Main stage setter method. Main stage is a frame
     * inside which all panels are built.
     */
    public function setMainStage(stage: Stage) : Void {
        this.stage = stage;
    }

    /**
     * Main controller method performing switching between
     * panels. Opening of a certain panel is accomplished
     * with starting of its processes likes recaping and
     * exercises.
     */
    public function switchPanelTo(panelType: String) : Void {

        if (sizeof stage.scene.content > 0) delete stage.scene.content[0];

        if (panelType == LOGIN_PANEL) {
            panel = LoginPanel {
                controller: this;
                model: model;
            }
        } else if (panelType == ACCOUNT_PANEL) {
            panel = AccountPanel {
                controller: this;
                model: model;
                dicts: model.dicts;
            }
        } else if (panelType == RECAP_PANEL) {
            panel = RecapPanel {
                controller: this;
                model: model;
            }
            stage.width = 400;
            panel.startProcess();
        } else if (panelType == EXERCISE_PANEL) {
            panel = ExercisePanel {
                controller: this;
                model: model;
            }

            panel.startProcess();
        }

        insert panel.getInstance() into stage.scene.content;
        panel.initialize();
        stage.width = panel.getWidth();
        stage.height = panel.getHeight();
    }

    /**
     * Performs user login and swithes to starting panel
     * which is an account panel.
     */
    public function signIn(user: UserBean) : Void {
        model.user = user;
        goHome();
    }

    /**
     * Performs loading of words list via http. All further
     * recaping and exercises will use this list of words.
     */
    public function pickDictionary() : Void {
        //TODO Add async call.
        model.currentWordsList = XmlUtil{
            }.parseWordsXmlString(
            HttpManager.getWordList(model.user.id,
            model.dict.id, model.category, model.session));
    }

    /**
     * Refreshes dictionary list from server via http and switches
     * application to user starting point which is an account panel.
     */
    public function goHome() : Void {
        model.dicts = XmlUtil {
            }.parseDictsXmlString(
            HttpManager.getDictionaryList(model.user.id));
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
        insert InformationPopupMessage {
            parent: panel
            message: message }.getInstance() into panel.getInstance().content;
        return Delayer {
            delay: delay;
            onComplete: function() : Void {
                switchPanelTo(panelToGo);
                //TODO Find solution in future sdk releases to relayout components
                stage.width = stage.width + 1;
                stage.width = stage.width - 1;
            }
        }
    }

}
