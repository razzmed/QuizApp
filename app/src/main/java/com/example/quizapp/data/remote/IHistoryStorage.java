package com.example.quizapp.data.remote;

import com.example.quizapp.model.QuizResult;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    void delete(int id);

    void deleteAll();
}
