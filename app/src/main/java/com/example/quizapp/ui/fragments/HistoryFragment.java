package com.example.quizapp.ui.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.model.ModelHistory;
import com.example.quizapp.ui.HistoryViewModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;

    Adapter adapter;
    ArrayList<ModelHistory> list = new ArrayList<>();

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.history_fragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list.add(new ModelHistory("Mixed", "8/10", "difficult", "12 July 2020"));
        list.add(new ModelHistory("Mixed", "2/10", "difficult", "12 July 2020"));
        list.add(new ModelHistory("Mixed", "3/10", "difficult", "12 July 2020"));
        list.add(new ModelHistory("Mixed", "4/10", "difficult", "12 July 2020"));
        list.add(new ModelHistory("Mixed", "10/10", "difficult", "12 July 2020"));
        adapter = new Adapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

    }

}
