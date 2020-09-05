package com.example.quizapp.data;

import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;

    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage) {
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
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

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

            }
        });
    }
}