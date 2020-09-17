package com.example.quizapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.quizapp.data.remote.IHistoryStorage;
import com.example.quizapp.db.QuizDao;
import com.example.quizapp.model.ModelHistory;
import com.example.quizapp.model.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {
    private QuizDao dao;

    public HistoryStorage(QuizDao quizDao) {
        dao = quizDao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return dao.getById(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) dao.insert(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<ModelHistory>> getAllHistory() {
        return Transformations.map(getAll(), quizResults -> {
            ArrayList<ModelHistory> histories = new ArrayList<>();

            if (quizResults.size() > 0) {
                for (int i = 0; i < quizResults.size(); i++) {
                    histories.add(i, new ModelHistory(quizResults.get(i).getId(),
                            quizResults.get(i).getCategory(),
                            quizResults.get(i).getCorrectAnswerResult(),
                            quizResults.get(i).getDifficulty(),
                            quizResults.get(i).getQuestions().size(),
                            quizResults.get(i).getCreatedAt()));
                }
            }
            return histories;
        });
    }


    @Override
    public void delete(QuizResult quizResult) {
        dao.delete(quizResult);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}
