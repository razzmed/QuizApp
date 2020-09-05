package com.example.quizapp.ui.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizapp.QuizApp;
import com.example.quizapp.R;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.model.Question;
import com.example.quizapp.ui.quiz.recycler.QuestionAdapter;
import com.example.quizapp.ui.quiz.recycler.QuestionViewHolder;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionViewHolder.Listener {

    public static final String EXTRA_AMOUNT = "amount";
    public static final String EXTRA_CATEGORY = "categoryIndex";
    public static final String EXTRA_DIFFICULTY = "difficultyIndex";

    private int amountIndex;
    String categoryIndex, difficultyIndex;
    int category;

    private QuizViewModel viewModel;

    RecyclerView recyclerView;
    QuestionAdapter adapter;
    ProgressBar progressBar;
    TextView quizCount, categoryQuiz;
    private List<Question> list = new ArrayList<>();
    Button btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        recyclerView = findViewById(R.id.questions_rw);
        progressBar = findViewById(R.id.count_progress);
        quizCount = findViewById(R.id.tv_count);
        btnSkip = findViewById(R.id.btn_skip);
        categoryQuiz = findViewById(R.id.tv_category_quiz);

        getData();
        setRecyclerView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapter = new QuestionAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @SuppressLint("SetTextI18n")
    private void getPosition() {
        viewModel.currentQuestionPosition.observe(this, integer -> {
            if (integer != null) {
                    recyclerView.smoothScrollToPosition(integer);
                    progressBar.setProgress(integer + 1);
                    progressBar.setMax(amountIndex);
                    quizCount.setText((integer + 1) + "/" + amountIndex);
                    categoryQuiz.setText(categoryIndex);
                    if (integer + 1 == list.size()) {
                        btnSkip.setText("Finish");
                    } else {
                        btnSkip.setText("Skip");
                }
            }
        });
    }

    public static void start(Context context, int amountIndex, int categoryIndex, String difficultyIndex) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amountIndex);
        intent.putExtra(EXTRA_CATEGORY, categoryIndex);
        intent.putExtra(EXTRA_DIFFICULTY, difficultyIndex);
        context.startActivity(intent);
    }

    private void getData() {
        if (getIntent() != null) {
            amountIndex = getIntent().getIntExtra(EXTRA_AMOUNT, 10);
            category = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
            difficultyIndex = getIntent().getStringExtra(EXTRA_DIFFICULTY);
            Log.d("ololo", "amount" + amountIndex + "categoryIndex" + categoryIndex + "difficulty" + difficultyIndex + "");

            if (category == 8) {
                category = 0;
            }
            if (difficultyIndex.equals("Any Difficulty")) {
                difficultyIndex = null;
            } else {
                difficultyIndex = difficultyIndex.toLowerCase();
            }
            getQuestions();
        }
    }

    private void getQuestions() {
        viewModel.init(amountIndex, category, difficultyIndex);

        viewModel.question.observe(this, questions -> {
            list = questions;
            adapter.setQuestions(questions);
            getPosition();
        });
    }

    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        viewModel.onAnswerClick(position, selectAnswerPosition);
    }

    public void onSkip(View view) {
        viewModel.onSkipClick();
    }

    public void onBack(View view) {
        viewModel.onBackPressed();
    }

//    @Override
//    public void onBackPressed() {
//        viewModel.onBackPressed();
//    }
}