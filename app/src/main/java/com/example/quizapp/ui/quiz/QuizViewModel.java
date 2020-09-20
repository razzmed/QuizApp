package com.example.quizapp.ui.quiz;

import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.QuizApp;
import com.example.quizapp.core.SingleLiveEvent;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizResult;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private List<Question> mQuestion;
    private String resultCategory, resultDifficulty;
    private int id;
    private Integer count;
    private Boolean isClicked = true;

    MutableLiveData<List<Question>> question = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Boolean> loading = new SingleLiveEvent<>();

    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    public void init(int amount, Integer categoryIndex, String difficultyIndex) {
        loading.setValue(true);
        quizApiClient.getQuestions(amount, categoryIndex, difficultyIndex, new IQuizApiClient.QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestion = result;
                question.setValue(mQuestion);
                loading.setValue(false);
                try {
                    if (mQuestion.get(1).getCategory().equals(mQuestion.get(2).getCategory())) {
                        resultCategory = mQuestion.get(1).getCategory();
                    } else {
                        resultCategory = "Mixed";
                    }
                    if (difficultyIndex != null) {
                        resultDifficulty = mQuestion.get(0).getDifficulty();
                    } else {
                        resultDifficulty = "All";
                    }
                } catch (IndexOutOfBoundsException e) {
                    loading.setValue(false);
                    finishEvent.call();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", "onFailure: " + e.getLocalizedMessage());
            }
        });
    }

    public int getCorrectAnswersAmount() {
        int correctAnswersAmount = 0;
        for (int j = 0; j < mQuestion.size() - 1; j++) {
            if (mQuestion.get(j).getSelectAnswerPosition() != null) {
                String correctAnswer = mQuestion.get(j).getCorrectAnswer();
                String selectedAnswer = mQuestion.get(j).getAnswers().get(mQuestion.get(j).getSelectAnswerPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswersAmount++;
                }
            }
        }
        return correctAnswersAmount;
    }

    void finishQuiz() {
        Date date = new Date();
        DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(date);
        QuizResult quizResult = new QuizResult(id, resultCategory, resultDifficulty, getCorrectAnswersAmount(), mQuestion, new Date());
        int resultId = QuizApp.historyStorage.saveQuizResult(quizResult);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    void onSkipClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            onAnswerClick(currentQuestionPosition.getValue(), -1);
        } else {
            finishQuiz();
        }

    }

    void onBackPressed() {
        Integer pos = currentQuestionPosition.getValue();
        if (pos != null) {
            if (pos != 0) {
                currentQuestionPosition.setValue(--count);
            } else {
                finishEvent.call();
            }
        }
    }

    public void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestion.size() > position && position >= 0) {
            if (mQuestion.get(position).getSelectAnswerPosition() == null) {
                mQuestion.get(position).setSelectAnswerPosition(selectedAnswerPosition);
                question.setValue(mQuestion);
            }
            if (position + 1 == mQuestion.size()) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(++count);
            }
        }
    }
}
