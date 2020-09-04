package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.quizapp.adapters.MainPagerAdapter;
import com.example.quizapp.data.remote.IQuizApiClient;
import com.example.quizapp.model.Question;
import com.example.quizapp.ui.fragments.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.main_nav:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.history_nav:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.settings_nav:
                        viewPager.setCurrentItem(2, false);
                        break;

                }
                return true;
            }
        });

        QuizApp.quizApiClient.getQuestions(new IQuizApiClient.QuestionCallBack() {
            @Override
            public void onSuccess(List<Question> result) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
