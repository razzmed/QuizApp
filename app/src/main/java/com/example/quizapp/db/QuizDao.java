package com.example.quizapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.model.QuizResult;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    long insert(QuizResult quizResult);

    @Query("SELECT * FROM QuizResult WHERE id=:id")
    QuizResult getById(int id);

    @Delete
    void delete(QuizResult quizResult);

    @Query("DELETE FROM quizresult WHERE id=:id")
    void deleteById(int id);

    @Query("SELECT * FROM quizResult")
    LiveData<List<QuizResult>> getAll();

    @Query("DELETE FROM quizResult")
    void deleteAll();
}
