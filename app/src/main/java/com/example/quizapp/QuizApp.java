package com.example.quizapp;

import android.app.Application;

import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.ui.history.HistoryStorage;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;

    private static IHistoryStorage historyStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        quizApiClient = new QuizApiClient();
        historyStorage = new HistoryStorage();
    }

    QuizRepository repository = new QuizRepository(quizApiClient, historyStorage);
}
