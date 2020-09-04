package com.example.quizapp.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quizapp.MainViewModel;
import com.example.quizapp.R;
import com.example.quizapp.ui.quiz.QuizActivity;
import com.example.quizapp.utils.SimpleSeekBarChangeListener;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView mAmount;
    private Button mStart;
    private SeekBar mSeekBar;
    private Spinner categorySpinner, difficultySpinner;
    private String category, difficulty;

    private int amountIndex;
    private int categoryIndex = 9;
    private String difficultyIndex = "easy";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAmount = view.findViewById(R.id.amount);
        mStart = view.findViewById(R.id.start_button);
        mSeekBar = view.findViewById(R.id.amount_seek_bar);
        categorySpinner = view.findViewById(R.id.category_spinner);
        difficultySpinner = view.findViewById(R.id.difficulty_spinner);

        mSeekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAmount.setText(String.valueOf(progress));
                amountIndex = progress;
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.start(getContext(), amountIndex, categoryIndex, difficultyIndex);
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                difficulty = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
