package com.example.quizapp.model;

import java.io.Serializable;
import java.util.Date;

public class ModelHistory implements Serializable {

    private int id;
    private String category;
    private String difficulty;
    private int correctAnswers;
    private int amount;
    private Date createdAt;

    public ModelHistory(int id, String category, int correctAnswerResult, String difficulty, int size, Date createdAt) {
        this.id = id;
        this.category = category;
        this.correctAnswers = correctAnswerResult;
        this.difficulty = difficulty;
        this.amount = size;
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

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}