package com.thecodinginterface.triviafx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class HomeController extends BaseViewController implements Initializable {

    @FXML
    private VBox gamesContainerVBox;

    @FXML
    void handleNewGame(ActionEvent event) {
        frontCtrl.showSetupGameView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    void initData() {

    }
}
