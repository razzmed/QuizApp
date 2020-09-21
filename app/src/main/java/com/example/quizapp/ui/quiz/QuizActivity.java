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
import android.os.CountDownTimer;
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
import com.example.quizapp.ui.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionViewHolder.Listener {

    public static final String EXTRA_AMOUNT = "amount";
    public static final String EXTRA_CATEGORY = "categoryIndex";
    public static final String EXTRA_DIFFICULTY = "difficultyIndex";

    private int amountIndex;
    private Integer categoryIndex;
    private String difficultyIndex;

    QuizViewModel viewModel;

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private ProgressBar progressBar;
    private TextView quizCount, categoryQuiz, time;
    private List<Question> list = new ArrayList<>();
    private Button btnSkip;

    public static void start(Context context, int amountIndex, int categoryIndex, String difficultyIndex) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amountIndex);
        intent.putExtra(EXTRA_CATEGORY, categoryIndex);
        intent.putExtra(EXTRA_DIFFICULTY, difficultyIndex);
        context.startActivity(intent);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        getData();
        setRecyclerView();
        viewModel.finishEvent.observe(this, aVoid -> finish());
        viewModel.openResultEvent.observe(this, integer -> ResultActivity.start(QuizActivity.this, integer));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuestionAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.questions_rw);
        progressBar = findViewById(R.id.count_progress);
        quizCount = findViewById(R.id.tv_count);
        btnSkip = findViewById(R.id.btn_skip);
        categoryQuiz = findViewById(R.id.tv_category_quiz);
    }

    private void getData() {
        if (getIntent() != null) {
            amountIndex = getIntent().getIntExtra(EXTRA_AMOUNT, 10);
            categoryIndex = getIntent().getIntExtra(EXTRA_CATEGORY, 21);
            difficultyIndex = getIntent().getStringExtra(EXTRA_DIFFICULTY);
            Log.d("ololo", "amount" + amountIndex + "categoryIndex" + categoryIndex + "difficulty" + difficultyIndex + "");

            if (categoryIndex == 8) {
                categoryIndex = 0;
            }
            if (difficultyIndex.equals("Any Difficulty")) {
                difficultyIndex = null;
            }
            questionObserver();
        }
    }

    private void questionObserver() {
        viewModel.init(amountIndex, categoryIndex, difficultyIndex);
        viewModel.question.observe(this, questions -> {
            list = questions;
            adapter.updateQuestion(list);
            Log.d("my list", questions.toString());
            getPosition();
        });
    }

    @SuppressLint("SetTextI18n")
    private void getPosition() {
        viewModel.currentQuestionPosition.observe(this, integer -> {
            if (integer != null) {
                if (list != null) {
                    quizCount.setText(integer + 1 + "/" + amountIndex);
                    progressBar.setProgress(integer + 1);
                    progressBar.setMax(amountIndex);
                    new CountDownTimer(500, 500) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            recyclerView.scrollToPosition(integer);
                        }
                    }.start();
                    categoryQuiz.setText(list.get(integer).getCategory());
                    if (integer + 1 == list.size()) {
                        btnSkip.setText("Finish");
                    } else {
                        btnSkip.setText("Skip");
                    }
                }
            }
        });
    }

    public void onSkip(View view) {
        viewModel.onSkipClick();
    }

    public void onBack(View view) {
        viewModel.onBackPressed();
    }

    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        viewModel.onAnswerClick(position, selectAnswerPosition);
    }

}