package com.example.quizapp.ui.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.ModelHistory;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ModelHistory> list_history;
    private Listener listener;

    public void UpdateAdapter(List<ModelHistory> list_history) {
        this.list_history = list_history;
        notifyDataSetChanged();
    }

    public Adapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list_history.get(position));

    }

    @Override
    public int getItemCount() {
        return list_history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        TextView correctAnswers;
        TextView difficulty;
        TextView date;

        public ViewHolder(@NonNull View itemView, Listener listener) {
            super(itemView);
            category = itemView.findViewById(R.id.tv_category);
            category.setOnClickListener(v -> listener.onClick(v, getAdapterPosition()));
            correctAnswers = itemView.findViewById(R.id.tv_correct_answers);
            difficulty = itemView.findViewById(R.id.tv_difficulty);
            date = itemView.findViewById(R.id.tv_date);
        }

        public void bind(ModelHistory history) {
            category.setText(history.getCategory());
            correctAnswers.setText(history.getCorrectAnswers() + "/" + history.getAmount());
            difficulty.setText(history.getDifficulty());
            date.setText(String.valueOf(history.getCreatedAt()));
        }
    }

    public interface Listener {
        void onClick(View view, int id);
    }

}
