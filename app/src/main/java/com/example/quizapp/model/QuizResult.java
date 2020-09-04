package com.example.quizapp.model;

import java.util.Date;
import java.util.List;

public class QuizResult {

    private int id;
    private String category;
    private String difficulty;
    int correctAnswerResult;
    private List<Question> questions;
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
