
package com.thecodinginterface.triviafx.repositories;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.thecodinginterface.triviafx.models.Category;
import com.thecodinginterface.triviafx.models.Difficulty;
import com.thecodinginterface.triviafx.models.Question;
import com.thecodinginterface.triviafx.models.QuestionType;

class QuestionsAPIDao implements QuestionsDao {
    private List<Category> categories = new ArrayList<>();
    private List<Question> currentQuestions = new ArrayList<>();
    private HttpClient client;
    private Gson gson;

    QuestionsAPIDao(HttpClient client) {
        this.client = client;
        gson = new Gson();
        loadCategories();
    }

    @Override
    public List<Category> getCategories() {
      return categories;
    }

    @Override
    public List<Difficulty> getDifficulties() {
      return List.of(Difficulty.Any, Difficulty.Easy, Difficulty.Medium, Difficulty.Hard);
    }

    @Override
    public List<QuestionType> getQuestionTypes() {
      return List.of(QuestionType.Any, QuestionType.Boolean, QuestionType.Multiple);
    }

    @Override
    public boolean loadQuestions(Category category, Difficulty difficulty, QuestionType questionType) {
        var url = "https://opentdb.com/api.php?amount=10&category=" + category.getId();
        if (difficulty != Difficulty.Any) {
            url += "&difficulty=" + difficulty.getApiName();
        }
        if (questionType != QuestionType.Any) {
            url += "&type=" + questionType.getApiName();
        }
        System.out.println(url);
        var request = HttpRequest.newBuilder(URI.create(url)).build();
        try {
            var response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                var questionsResponse = gson.fromJson(response.body(), QuestionResponse.class);
                if (questionsResponse.getQuestions() != null) {
                    currentQuestions = questionsResponse.getQuestions();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean loadCategories() {
        var request = HttpRequest.newBuilder(URI.create("https://opentdb.com/api_category.php")).build();
        client.sendAsync(request, BodyHandlers.ofString()).thenAccept(response -> {
          if (response.statusCode() == 200) {
            var categoriesResponse = gson.fromJson(response.body(), CategoriesResponse.class);
            categories = Collections.unmodifiableList(categoriesResponse.getTriviaCategories());
          }
        });
        return false;
    }

    @Override
    public List<Question> getCurrentGameQuestions() {
        return currentQuestions;
    }

    static class CategoriesResponse {
        @SerializedName("trivia_categories")
        private List<Category> triviaCategories;

        List<Category> getTriviaCategories() {
            return triviaCategories;
        }
    }

    static class QuestionResponse {
        @SerializedName("response_code")
        private int responseCode;

        @SerializedName("results")
        List<Question> questions;

        List<Question> getQuestions() {
            return questions;
        }
    }
}
