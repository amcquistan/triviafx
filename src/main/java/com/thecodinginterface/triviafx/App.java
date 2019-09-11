
package com.thecodinginterface.triviafx;

import com.thecodinginterface.triviafx.controllers.FrontController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            var loader = new FXMLLoader(getClass().getResource("Base.fxml"));
            var anchorPane = (AnchorPane) loader.load();
    
            var frontController = (FrontController) loader.getController();
    
            var scene = new Scene(anchorPane);
    
            primaryStage.setScene(scene);
    
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
