package com.example.quizapp.ui.quiz.recycler;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.Question;

public class QuestionViewHolder extends RecyclerView.ViewHolder {

    private Listener listener;
    private TextView questionName;
    private Button btn1, btn2, btn3, btn4, btnTrue, btnFalse;
    private LinearLayout multiple, booleans;

    public QuestionViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        initViews(itemView);
        this.listener = listener;
        clickListener();
    }

    private void clickListener() {
        btn1.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        btn2.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
        btn3.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 2));
        btn4.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 3));
        btnTrue.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        btnFalse.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
    }

    private void initViews(View itemView) {
        btn1 = itemView.findViewById(R.id.multiply_answer_1);
        btn2 = itemView.findViewById(R.id.multiply_answer_2);
        btn3 = itemView.findViewById(R.id.multiply_answer_3);
        btn4 = itemView.findViewById(R.id.multiply_answer_4);
        questionName = itemView.findViewById(R.id.questions);
        btnFalse = itemView.findViewById(R.id.boolean_answer_1);
        btnTrue = itemView.findViewById(R.id.boolean_answer_2);
        multiple = itemView.findViewById(R.id.multiply_container);
        booleans = itemView.findViewById(R.id.boolean_container);
    }

    void onBind(Question question) {
        questionName.setText(Html.fromHtml(question.getQuestion()));
        if (question.getType().equals("multiple")) {
            multiple.setVisibility(View.VISIBLE);
            booleans.setVisibility(View.INVISIBLE);
            setTextButton(question, btn1, btn2, btn3, btn4);
        } else {
            multiple.setVisibility(View.INVISIBLE);
            booleans.setVisibility(View.VISIBLE);
            setTextButton(question, btnFalse, btnTrue);
        }

    }

    private void setTextButton(Question question, Button... buttons) {
        int i = 0;
        for (Button button : buttons) {
            button.setText(Html.fromHtml(question.getAnswers().get(i)));
            i++;
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}
