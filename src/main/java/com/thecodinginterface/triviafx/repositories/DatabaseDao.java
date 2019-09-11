package com.thecodinginterface.triviafx.repositories;

import com.thecodinginterface.triviafx.models.Question;

public interface DatabaseDao {
    boolean saveQuestionResponse(Question question);
}
