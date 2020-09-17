package com.example.quizapp.data;

import androidx.lifecycle.LiveData;

import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.data.remote.QuizApiClient;
import com.example.quizapp.db.QuizDao;
import com.example.quizapp.model.ModelHistory;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizResult;
import com.example.quizapp.ui.history.HistoryStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;
    private QuizDao quizDao;

    public QuizRepository(QuizApiClient quizApiClient, HistoryStorage historyStorage) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
    }

    private Question shuffleAnswer(Question question) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);
        question.setAnswers(answers);

        return question;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return historyStorage.getQuizResult(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return historyStorage.saveQuizResult(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public LiveData<List<ModelHistory>> getAllHistory() {
        return historyStorage.getAllHistory();
    }

    @Override
    public void delete(QuizResult quizResult) {
        historyStorage.delete(quizResult);
    }

    @Override
    public void deleteById(int id) {
        historyStorage.deleteById(id);
    }

    @Override
    public void deleteAll() {
        historyStorage.deleteAll();
    }

    @Override
    public void getQuestions(int amountIndex, int categoryIndex, String difficultyIndex, QuestionCallBack callBack) {
        quizApiClient.getQuestions(amountIndex, categoryIndex, difficultyIndex, new QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i, shuffleAnswer(result.get(i)));
                }
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(e);
            }
        });
    }
}