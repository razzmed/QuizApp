package com.example.quizapp.ui.quiz;

import android.util.Log;

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
    private Integer count;

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    void init(int amount, int categoryIndex, String difficultyIndex) {
        quizApiClient.getQuestions(amount, categoryIndex, difficultyIndex, new IQuizApiClient.QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                question.setValue(mQuestions);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", "onFailure: " + e.getLocalizedMessage());
            }
        });
    }

    void finishQuiz() {
    }

    void onSkipClick() {
        Integer pos = currentQuestionPosition.getValue();
        if (pos != null) {
            onAnswerClick(pos, -1);
        }

    }

    void onBackPressed() {
        Integer pos = currentQuestionPosition.getValue();
        if (pos != null) {
            if (pos != 0) {
                currentQuestionPosition.setValue(--count);
            } else {
                finishQuiz();
            }
        }
    }

    public void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestions.size() > position && position >= 0) {
            if (mQuestions.get(position).getSelectAnswerPosition() == null) {
                mQuestions.get(position).setSelectAnswerPosition(selectedAnswerPosition);
                question.setValue(mQuestions);
            }
            if (position + 1 == mQuestions.size()) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(++count);
            }
        }
    }
}
