package com.example.quizapp.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizapp.QuizApp;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> question = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;
    private List<Question> mQuestions;

    void init(int amount, int categoryIndex, String difficultyIndex) {
        quizApiClient.getQuestions(new IQuizApiClient.QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                question.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    void finishQuiz() {

    }

    void onSkipClick() {

    }

    void onBackPressed() {
        
    }

    public void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestions.size() > position) {
            currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
            mQuestions.get(position).setSelectAnswerPosition(selectedAnswerPosition);
            question.setValue(mQuestions);

            if (position == mQuestions.size() - 1) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
            }
        }
    }
}
