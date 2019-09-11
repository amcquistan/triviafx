package com.thecodinginterface.triviafx.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Question {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    
    @SerializedName("correct_anwser")
    private String correctAnswer;
    
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;
    private String selectedAnswer;

    public Question() {
        incorrectAnswers = new ArrayList<>();
    }

    public Question(String category, String type, String difficulty, String question, String correctAnswer, List<String> incorrectAnswers, String selectedAnswer) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.selectedAnswer = selectedAnswer;
    }

    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    @Override
    public String toString() {
        return String.format(
            "{%s: category=%s, type=%s, difficulty=%s, correctAnswer=%s, incorrectAnswers=%s, selectedAnswer=%s}",
            getClass().getSimpleName(),
            category,
            type,
            difficulty,
            correctAnswer,
            incorrectAnswers,
            selectedAnswer
        );
    }
}
