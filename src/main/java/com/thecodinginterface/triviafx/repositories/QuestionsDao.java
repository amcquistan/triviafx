
package com.thecodinginterface.triviafx.repositories;

import java.net.http.HttpClient;
import java.util.List;

import com.thecodinginterface.triviafx.models.Category;
import com.thecodinginterface.triviafx.models.Difficulty;
import com.thecodinginterface.triviafx.models.Question;
import com.thecodinginterface.triviafx.models.QuestionType;

public interface QuestionsDao {
    boolean loadCategories();

    List<Category> getCategories();

    List<Difficulty> getDifficulties();

    List<QuestionType> getQuestionTypes();

    boolean loadQuestions(Category category, Difficulty difficulty, QuestionType questionType);

    List<Question> getCurrentGameQuestions();

    static QuestionsDao from(HttpClient client) {
        return new QuestionsAPIDao(client);
    }
}

