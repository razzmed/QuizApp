package com.example.quizapp.ui.settings;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quizapp.QuizApp;
import com.example.quizapp.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    TextView historyClear;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyClear = view.findViewById(R.id.tv_clear_history);
        historyClear.setOnClickListener(view1 -> {
            AlertDialog.Builder alertBilder = new AlertDialog.Builder(getActivity());
            alertBilder.setTitle("Attention!!!");
            alertBilder.setMessage("Clear history?");
            alertBilder.setPositiveButton("Yes", ((dialog, which) -> QuizApp.quizDataBase.quizDao().deleteAll()));
            alertBilder.setNegativeButton("No", ((dialog, which) -> dialog.cancel()));
            AlertDialog alertDialog = alertBilder.create();
            alertDialog.show();
        });
    }
}
