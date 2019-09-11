package com.thecodinginterface.triviafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SetupCharacteristicController {

    @FXML
    private Button backBtn;

    @FXML
    private Label characteristicLabel;

    @FXML
    private VBox contentVBox;

    VBox getContentVBox() {
        return contentVBox;
    }

    Button getBackBtn() {
        return backBtn;
    }

    Label getCharacteristicLabel() {
        return characteristicLabel;
    }
}
