package com.example.quizapp.ui.quiz.recycler;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

        btn1 = itemView.findViewById(R.id.multiply_answer_1);
        btn2 = itemView.findViewById(R.id.multiply_answer_2);
        btn3 = itemView.findViewById(R.id.multiply_answer_3);
        btn4 = itemView.findViewById(R.id.multiply_answer_4);
        questionName = itemView.findViewById(R.id.questions);
        btnFalse = itemView.findViewById(R.id.boolean_answer_1);
        btnTrue = itemView.findViewById(R.id.boolean_answer_2);
        multiple = itemView.findViewById(R.id.multiply_container);
        booleans = itemView.findViewById(R.id.boolean_container);
        this.listener = listener;
        clickListener();

    }

    private void setButton(Boolean enabled) {
        btn1.setEnabled(enabled);
        btn2.setEnabled(enabled);
        btn3.setEnabled(enabled);
        btn4.setEnabled(enabled);
        btnTrue.setEnabled(enabled);
        btnFalse.setEnabled(enabled);
    }

    private void clickListener() {
        btn1.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        btn2.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
        btn3.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 2));
        btn4.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 3));
        btnTrue.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        btnFalse.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
    }

    void onBind(Question question) {
        reset();
        if (question.getSelectAnswerPosition() == null) {
            setButton(true);
        } else {
            setButton(false);
        }
        questionName.setText(Html.fromHtml(question.getQuestion()));
        if (question.getType().equals("multiple")) {
            multiple.setVisibility(View.VISIBLE);
            booleans.setVisibility(View.INVISIBLE);
            btn1.setText(Html.fromHtml(question.getAnswers().get(0)));
            btn2.setText(Html.fromHtml(question.getAnswers().get(1)));
            btn3.setText(Html.fromHtml(question.getAnswers().get(2)));
            btn4.setText(Html.fromHtml(question.getAnswers().get(3)));
        } else {
            multiple.setVisibility(View.INVISIBLE);
            booleans.setVisibility(View.VISIBLE);
            btnTrue.setText(Html.fromHtml(question.getAnswers().get(0)));
            btnFalse.setText(Html.fromHtml(question.getAnswers().get(1)));
        }
        if (question.getSelectAnswerPosition() != null) {
            btnState(question);
        }

    }

    private void reset() {
        resetAnswerButtons(btn1, btn2, btn3, btn4, btnTrue, btnFalse);
    }

    private void resetAnswerButtons(Button... buttons) {
        for (Button button: buttons)
        {
            button.setBackgroundResource(R.drawable.btn_rouded_quiz);
             button.setTextColor(itemView.getResources().getColor(R.color.Black));

        }
    }

//    private void setTextButton(Question question, Button... buttons) {
//        int i = 0;
//        for (Button button : buttons) {
//            button.setText(Html.fromHtml(question.getAnswers().get(i)));
//            i++;
//        }
//    }

    @SuppressLint("ResourceAsColor")
    public void btnState(Question question) {
        if (question.getSelectAnswerPosition() != null) {
            switch (question.getSelectAnswerPosition()) {
                case 0:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(0))) {
                        btn1.setBackgroundResource(R.drawable.green);
                        btnTrue.setBackgroundResource(R.drawable.green);
                        btn1.setTextColor(Color.WHITE);
                        btnTrue.setTextColor(Color.WHITE);
                    } else {
                        btn1.setBackgroundResource(R.drawable.red);
                        btnTrue.setBackgroundResource(R.drawable.red);
                        btn1.setTextColor(Color.WHITE);
                        btnTrue.setTextColor(Color.WHITE);
                    }
                    break;
                case 1:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(1))) {
                        btn2.setBackgroundResource(R.drawable.green);
                        btnFalse.setBackgroundResource(R.drawable.green);
                        btn2.setTextColor(Color.WHITE);
                        btnFalse.setTextColor(Color.WHITE);
                    } else {
                        btn2.setBackgroundResource(R.drawable.red);
                        btnFalse.setBackgroundResource(R.drawable.red);
                        btn2.setTextColor(Color.WHITE);
                        btnFalse.setTextColor(Color.WHITE);
                    }
                    break;
                case 2:
                    if (question.getAnswers().get(2).equals(question.getCorrectAnswer())) {
                        btn3.setBackgroundResource(R.drawable.green);
                        btn3.setTextColor(Color.WHITE);
                    } else {
                        btn3.setBackgroundResource(R.drawable.red);
                        btn3.setTextColor(Color.WHITE);
                    }
                    break;
                case 3:
                    if (question.getAnswers().get(3).equals(question.getCorrectAnswer())) {
                        btn4.setBackgroundResource(R.drawable.green);
                        btn4.setTextColor(Color.WHITE);
                    } else {
                        btn4.setBackgroundResource(R.drawable.red);
                        btn4.setTextColor(Color.WHITE);
                    }
                    break;
            }
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}
