package com.example.quizapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.quizapp.db.converter.DateConverter;
import com.example.quizapp.db.converter.QuestionConverter;

import java.util.Date;
import java.util.List;

@Entity
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String category;
    @ColumnInfo
    private String difficulty;
    @ColumnInfo(name = "correctAnswerResult")
    int correctAnswerResult;
    @TypeConverters({QuestionConverter.class})
    private List<Question> questions;
    @TypeConverters({DateConverter.class})
    private Date createdAt;

    public QuizResult(int id, String category, String difficulty, int correctAnswerResult, List<Question> questions, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswerResult = correctAnswerResult;
        this.questions = questions;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswerResult() {
        return correctAnswerResult;
    }

    public void setCorrectAnswerResult(int correctAnswerResult) {
        this.correctAnswerResult = correctAnswerResult;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
