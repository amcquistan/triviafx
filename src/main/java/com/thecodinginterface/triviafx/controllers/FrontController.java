package com.thecodinginterface.triviafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.List;
import java.util.ResourceBundle;

import com.thecodinginterface.triviafx.App;
import com.thecodinginterface.triviafx.models.Question;
import com.thecodinginterface.triviafx.repositories.DatabaseDao;
import com.thecodinginterface.triviafx.repositories.QuestionsDao;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class FrontController implements Initializable {

    @FXML
    private StackPane rootStackPane;

    private HttpClient httpClient;
    private QuestionsDao questionsDao;
    private DatabaseDao databaseDao;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        showHomeView();
        httpClient = HttpClient.newHttpClient();
        questionsDao = QuestionsDao.from(httpClient);
        rootStackPane.setStyle("-fx-background-color: yellow");
    }

    QuestionsDao getQuestionsDao() {
        return questionsDao;
    }

    StackPane getRootStackPane() {
        return rootStackPane;
    }

    void showHomeView() {
        try {
            rootStackPane.getChildren().clear();
            var loader = new FXMLLoader(App.class.getResource("/Home.fxml"));
            var pane = (BorderPane) loader.load();
            HomeController homeCtrl = loader.getController();
            homeCtrl.setFrontController(this);
            addBorderPane(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showSetupGameView() {
        try {
            rootStackPane.getChildren().clear();
            var loader = new FXMLLoader(App.class.getResource("/Setup.fxml"));
            var pane = (BorderPane) loader.load();
            SetupController setupCtrl = loader.getController();
            setupCtrl.setFrontController(this);
            addBorderPane(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addBorderPane(BorderPane pane) {
        pane.setPrefWidth(Double.MAX_VALUE);
        pane.setPrefHeight(Double.MAX_VALUE);
        rootStackPane.getChildren().add(pane);
    }

    void removeBorderPane(BorderPane pane) {
        if (rootStackPane.getChildren().contains(pane)) {
            rootStackPane.getChildren().remove(pane);
        }
    }

    void showGameView() {
        try {
            rootStackPane.getChildren().clear();
            var loader = new FXMLLoader(App.class.getResource("/Game.fxml"));
            var pane = (BorderPane) loader.load();
            GameController ctrl = loader.getController();
            ctrl.setFrontController(this);
            rootStackPane.getChildren().clear();
            addBorderPane(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}