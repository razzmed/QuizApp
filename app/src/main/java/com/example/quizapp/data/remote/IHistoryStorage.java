package com.example.quizapp.data.remote;

import androidx.lifecycle.LiveData;

import com.example.quizapp.model.ModelHistory;
import com.example.quizapp.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    LiveData<List<ModelHistory>> getAllHistory();

    void delete(QuizResult quizResult);

    void deleteById(int id);

    void deleteAll();
}
