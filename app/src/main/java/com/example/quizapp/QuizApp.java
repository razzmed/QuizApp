package com.example.quizapp;

import android.app.Application;

import androidx.room.Room;

import com.example.quizapp.data.QuizRepository;
import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.db.QuizDataBase;
import com.example.quizapp.ui.history.HistoryStorage;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizDataBase quizDataBase;
    public QuizRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        quizDataBase = Room.databaseBuilder(this, QuizDataBase.class, "quiz.db")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        quizDataBase.quizDao();
        repository = new QuizRepository(new QuizApiClient(), new HistoryStorage(quizDataBase.quizDao()));

        quizApiClient = repository;
        historyStorage = repository;


    }

}
