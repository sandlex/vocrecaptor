package com.vocrecaptor.fx;

import com.vocrecaptor.fx.controller.Controller;
import com.vocrecaptor.fx.model.Model;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application starting point.
 * Initialization of model, controller and views.
 *
 * @author Alexey Peskov
 */
var model: Model = Model {
}

var controller: Controller = Controller {
    model: model;
}

var vocrecaptorStage: Stage = Stage {
    title: "Vocrecaptor";
    scene: Scene {
    }
}
controller.setMainStage(vocrecaptorStage);
controller.switchPanelTo(controller.LOGIN_PANEL);
