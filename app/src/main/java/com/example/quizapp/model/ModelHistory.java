package com.example.quizapp.model;

import java.io.Serializable;

public class ModelHistory implements Serializable {
    private String category;
    private String correctAnswers;
    private String difficulty;
    private String date;

    public ModelHistory() {

    }

    public ModelHistory(String category, String correctAnswers, String difficulty, String date) {
        this.category = category;
        this.correctAnswers = correctAnswers;
        this.difficulty = difficulty;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
