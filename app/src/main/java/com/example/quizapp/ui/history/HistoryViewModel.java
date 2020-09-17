package com.example.quizapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.QuizApp;
import com.example.quizapp.core.SingleLiveEvent;
import com.example.quizapp.model.ModelHistory;
import com.example.quizapp.model.QuizResult;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    public LiveData<List<ModelHistory>> history = QuizApp.historyStorage.getAllHistory();
    public SingleLiveEvent<Void> deleteById = new SingleLiveEvent<>();

    public void delete() {
        deleteById.call();
    }
}
