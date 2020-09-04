package com.example.quizapp.ui.quiz.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.model.Question;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    private  Listener listener;

    public QuestionViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        this.listener = listener;
    }

    void onBind(Question question) {

    }

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}
