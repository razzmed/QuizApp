package com.example.quizapp.ui.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.model.QuizResult;

public class ResultActivity extends AppCompatActivity {
    ResultViewModel resultViewModel;
    private static String EXTRA_QUIZ_ID = "result_Id";
    private Integer id;
    private TextView categoryResult, difficultyResult, correctAnswerResult, resultResult;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        categoryResult = findViewById(R.id.result_text_mixed);
        difficultyResult = findViewById(R.id.result_difficulty);
        correctAnswerResult = findViewById(R.id.result_correct_answers);
        resultResult = findViewById(R.id.result_result);
        btnFinish = findViewById(R.id.btn_result_finish);

        id = getIntent().getIntExtra(EXTRA_QUIZ_ID, 0);
        resultViewModel.getResult(id);
        setResult();
        btnFinish.setOnClickListener(v -> finish());
    }

    public void setResult() {
        resultViewModel.quizResultMutableLiveData.observe(this, quizResult -> {
            if (quizResult != null) {
                categoryResult.setText("Category: " + quizResult.getCategory());
                difficultyResult.setText(quizResult.getDifficulty());
                correctAnswerResult.setText(quizResult.getCorrectAnswerResult() + "/" + quizResult.getQuestions().size());
                int correctAnswersPercent = (int) ((double) quizResult.getCorrectAnswerResult() / quizResult.getQuestions().size() * 100);
                resultResult.setText(correctAnswersPercent + " %");
            }
        });
    }

    public static void start(Context context, Integer id) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID, id));
    }
}
