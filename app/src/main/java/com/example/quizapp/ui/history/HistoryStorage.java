package com.example.quizapp.ui.history;

import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.model.QuizResult;

public class HistoryStorage implements IHistoryStorage {
    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
