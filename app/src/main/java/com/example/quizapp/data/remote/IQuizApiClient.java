package com.example.quizapp.data.remote;

import com.example.quizapp.core.IBaseCallback;
import com.example.quizapp.model.Question;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionCallBack callBack);

    interface QuestionCallBack extends IBaseCallback<List<Question>> {
        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception e);
    }
}
