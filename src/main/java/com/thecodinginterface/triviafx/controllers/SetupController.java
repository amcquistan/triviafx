package com.thecodinginterface.triviafx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thecodinginterface.triviafx.App;
import com.thecodinginterface.triviafx.models.Category;
import com.thecodinginterface.triviafx.models.Difficulty;
import com.thecodinginterface.triviafx.models.QuestionType;
import com.thecodinginterface.triviafx.shared.SelectableChoice;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

public class SetupController extends BaseViewController implements Initializable {
    @FXML
    private Label categoryLabel;

    @FXML
    private Label difficultyLabel;

    @FXML
    private Label typeLabel;

    private BorderPane characteristBP;
    private SetupCharacteristicController characteristicCtrl;
    private ToggleGroup toggleGrp;

    private ObjectProperty<Category> selectedCategory = new SimpleObjectProperty<>();
    private ObjectProperty<Difficulty> selectedDifficulty = new SimpleObjectProperty<>(Difficulty.Any);
    private ObjectProperty<QuestionType> selectedQuestionType = new SimpleObjectProperty<>(QuestionType.Any);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            var loader = new FXMLLoader(App.class.getResource("SetupCharacteristic.fxml"));
            characteristBP = (BorderPane) loader.load();
            characteristicCtrl = loader.getController();
            characteristicCtrl.getBackBtn().setOnAction(evt -> frontCtrl.removeBorderPane(characteristBP));

            categoryLabel.textProperty()
                    .bindBidirectional(selectedCategory, new StringConverter<Category>() {
                @Override
                public String toString(Category category) {
                  if (category != null) {
                      return category.getName();
                  }
                  return "";
                }

                @Override
                public Category fromString(String string) {
                    return null;
                }
            });

            difficultyLabel.textProperty()
                    .bindBidirectional(selectedDifficulty, new StringConverter<Difficulty>(){
                @Override
                public String toString(Difficulty difficulty) {
                    return difficulty != null ? difficulty.getDisplayName() : "";
                }

                @Override
                public Difficulty fromString(String string) {
                    return null;
                }
            });

            typeLabel.textProperty()
                    .bindBidirectional(selectedQuestionType,  new StringConverter<QuestionType>(){
                @Override
                public String toString(QuestionType type) {
                  return type != null ? type.getDisplayName() : "";
                }

                @Override
                public QuestionType fromString(String string) {
                    return null;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }

    @FXML
    void handleCancelSetup(MouseEvent event) {
        frontCtrl.showHomeView();
    }

    @FXML
    void handleOpenCategory() {
        toggleGrp = new ToggleGroup();
        characteristicCtrl.getContentVBox().getChildren().clear();
        frontCtrl.getQuestionsDao().getCategories().forEach(category -> {
            var tglBtn = new SelectableChoice(category.getName(), toggleGrp, category, characteristBP);
            characteristicCtrl.getContentVBox().getChildren().add(tglBtn);
            tglBtn.setSelected(category.equals(selectedCategory.getValue()));
        });

        toggleGrp.selectedToggleProperty().addListener((obs, ov, nv) -> {
            selectedCategory.setValue((Category) nv.getUserData());
            frontCtrl.removeBorderPane(characteristBP);
        });

        frontCtrl.addBorderPane(characteristBP);
    }

    @FXML
    void handleOpenDifficulty() {
        toggleGrp = new ToggleGroup();
        characteristicCtrl.getContentVBox().getChildren().clear();
        frontCtrl.getQuestionsDao().getDifficulties().forEach(difficulty -> {
            var tglBtn = new SelectableChoice(difficulty.getDisplayName(), toggleGrp, difficulty, characteristBP);
            characteristicCtrl.getContentVBox().getChildren().add(tglBtn);
            tglBtn.setSelected(difficulty ==  selectedDifficulty.getValue());
        });

        toggleGrp.selectedToggleProperty().addListener((obs, ov, nv) -> {
            selectedDifficulty.setValue((Difficulty) nv.getUserData());
            frontCtrl.removeBorderPane(characteristBP);
        });

        frontCtrl.addBorderPane(characteristBP);
    }

    @FXML
    void handleOpenQuestionType() {
        toggleGrp = new ToggleGroup();
        characteristicCtrl.getContentVBox().getChildren().clear();
        frontCtrl.getQuestionsDao().getQuestionTypes().forEach(questionType -> {
            var tglBtn = new SelectableChoice(questionType.getDisplayName(), toggleGrp, questionType, characteristBP);
            characteristicCtrl.getContentVBox().getChildren().add(tglBtn);
            tglBtn.setSelected(questionType == selectedQuestionType.getValue());
        });

        toggleGrp.selectedToggleProperty().addListener((obs, ov, nv) -> {
            selectedQuestionType.setValue((QuestionType) nv.getUserData());
            frontCtrl.removeBorderPane(characteristBP);
        });
        frontCtrl.addBorderPane(characteristBP);
    }

    @FXML
    void handleStart() {
        var loaded = frontCtrl.getQuestionsDao().loadQuestions(
            selectedCategory.get(),
            selectedDifficulty.get(),
            selectedQuestionType.get()
        );
        if (loaded) {
            frontCtrl.showGameView();
        }
    }

    @Override
    public void initData() {
        var categories = frontCtrl.getQuestionsDao().getCategories();
        if (categories != null) {
            selectedCategory.set(categories.get(0));
        }
    }
}
