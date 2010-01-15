package com.vocrecaptor.fx;

import com.vocrecaptor.fx.controller.Controller;
import com.vocrecaptor.fx.model.Model;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.vocrecaptor.fx.view.TemplatePanel;

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
    width: 600;
    height: 700;
    title: "Vocrecaptor Exercises";
    scene: Scene {
    }
}

var panel: TemplatePanel = TemplatePanel {
    controller: controller;
    model: model;
};

insert panel.getInstance() into vocrecaptorStage.scene.content;
controller.setTemplatePanel(panel);
controller.switchPanelTo(controller.LOGIN_PANEL);
