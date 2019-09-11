package com.thecodinginterface.triviafx.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.thecodinginterface.triviafx.models.Question;
import com.thecodinginterface.triviafx.shared.SelectableChoice;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameController extends BaseViewController implements Initializable {
    
    @FXML
    private BorderPane gamePane;
    
    @FXML
    private Label questionLabel;
    
    @FXML
    private VBox choicesVBox;
    
    @FXML
    private Button backBtn;
    
    @FXML
    private Button nextBtn;
    
    private LinkedList<Question> questions;
    
    private ObjectProperty<Question> currentQuestion = new SimpleObjectProperty<>();
    private StringProperty selectedAnswer = new SimpleStringProperty();
    
    private ToggleGroup toggleGroup;
    
    @FXML
    void handleNext(ActionEvent evt) {
        currentQuestion.get().setSelectedAnswer(selectedAnswer.get());
        var idx = questions.indexOf(currentQuestion.get()) + 1;
        
        if (idx < questions.size()) {
            var question = questions.get(idx);
            updateContent(question);
        } else {
            // showResults();
        }
    }
    
    @FXML
    void handleBack(ActionEvent evt) {
        var idx = questions.indexOf(currentQuestion.get()) - 1;
        if (idx > -1) {
            var question = questions.get(idx);
            updateContent(question);
        }
    }
    
    @Override
    void initData() {
        questions = new LinkedList<Question>(frontCtrl.getQuestionsDao().getCurrentGameQuestions());
        currentQuestion.set(questions.peekFirst());

        backBtn.disableProperty().bind(currentQuestion.isEqualTo(questions.peekFirst()));
        nextBtn.disableProperty().bind(selectedAnswer.isEmpty());
        nextBtn.textProperty().bind(
                Bindings.when(currentQuestion.isEqualTo(questions.peekLast()))
                    .then("Finished")
                    .otherwise("Next >")
        );
        
        updateContent(currentQuestion.get());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    private void updateContent(Question question) {
        
        currentQuestion.set(question);
        selectedAnswer.set(question.getSelectedAnswer());
        choicesVBox.getChildren().clear();
        toggleGroup = new ToggleGroup();
        questionLabel.setText(question.getQuestion());
        var choices = new ArrayList<String>(question.getIncorrectAnswers());
        choices.add(question.getQuestion());
        choices.sort((a, b) -> a.compareTo(b));
        choices.forEach(c -> {
            var choice = new SelectableChoice(c, toggleGroup, c, gamePane);
            choicesVBox.getChildren().add(choice);
            choice.setSelected(c.equals(selectedAnswer.get()));
        });
        toggleGroup.selectedToggleProperty().addListener((obs, ov, nv) -> {
            System.out.println("Selected " + nv);
            selectedAnswer.set((String) nv.getUserData());
        });
    }
}