package com.example.quizapp.ui.quiz.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {

    private List<Question> questionList = new ArrayList<>();
    private QuestionViewHolder.Listener listener;

    public QuestionAdapter(QuestionViewHolder.Listener listener) {
        this.listener = listener;
    }

    public void updateQuestion(List<Question> questionList) {
        this.questionList = questionList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
       holder.onBind(questionList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void setQuestions(List<Question> questions) {
        this.questionList.clear();
        this.questionList.addAll(questions);
        notifyDataSetChanged();
    }
}
