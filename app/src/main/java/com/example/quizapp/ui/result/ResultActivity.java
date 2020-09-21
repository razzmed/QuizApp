package com.example.quizapp.ui.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView imageView;

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
        imageView = findViewById(R.id.result_image);

        id = getIntent().getIntExtra(EXTRA_QUIZ_ID, 0);
        resultViewModel.getResult(id);
        setResult();
        btnFinish.setOnClickListener(v -> finish());
    }

    public void setResult() {
        resultViewModel.quizResultMutableLiveData.observe(this, quizResult -> {

                categoryResult.setText(quizResult.getCategory());
                difficultyResult.setText(quizResult.getDifficulty());
                correctAnswerResult.setText(quizResult.getCorrectAnswerResult() + "/" + quizResult.getQuestions().size());
                int correctAnswersPercent = (int) ((double) quizResult.getCorrectAnswerResult() / quizResult.getQuestions().size() * 100);
                if (correctAnswersPercent == 0) {
                    imageView.setImageResource(R.drawable.ic_looser);
                }
                if (correctAnswersPercent > 0 && correctAnswersPercent <=30) {
                    imageView.setImageResource(R.drawable.ic_notbad);
                }
                if (correctAnswersPercent > 30 && correctAnswersPercent <=50) {
                    imageView.setImageResource(R.drawable.ic_cool);
                }
                if (correctAnswersPercent > 50 && correctAnswersPercent <=80) {
                    imageView.setImageResource(R.drawable.ic_exellent);
                }
                if (correctAnswersPercent > 80 && correctAnswersPercent <=100) {
                    imageView.setImageResource(R.drawable.ic_master);
                }
        });
    }

    public static void start(Context context, Integer id) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID, id));
    }
}
