package com.example.quizapp.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.QuizApp;
import com.example.quizapp.model.QuizResult;

public class ResultViewModel extends ViewModel {
    MutableLiveData<QuizResult> quizResultMutableLiveData = new MutableLiveData<>();

    public void getResult(Integer id) {
        quizResultMutableLiveData.setValue(QuizApp.quizDataBase.quizDao().getById(id));
    }

}
