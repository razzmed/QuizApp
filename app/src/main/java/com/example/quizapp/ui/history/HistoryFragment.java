package com.example.quizapp.ui.history;

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
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.quizapp.QuizApp;
import com.example.quizapp.R;
import com.example.quizapp.model.ModelHistory;
import com.example.quizapp.ui.fragments.Adapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements Adapter.Listener {

    private HistoryViewModel mViewModel;

    Adapter adapter;
    private List<ModelHistory> list = new ArrayList<>();
    private int position;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
        adapter.UpdateAdapter(new ArrayList<>());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        mViewModel.history.observe(getActivity(), histories -> {
            list = histories;
            if (histories != null) {
                adapter.UpdateAdapter(list);
            }
        });
        mViewModel.deleteById.observe(getActivity(), aVoid ->
                QuizApp.historyStorage.deleteById(list.get(position).getId()));
    }

    private void showPopupMenu(View view, int position) {
        this.position = position;
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.menu_dot);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.popup_delete:
                    mViewModel.deleteById.call();
                    return true;
            }
            return false;
        });
        popupMenu.setOnDismissListener(menu -> Toast.makeText(getContext(), "Deleted",
                Toast.LENGTH_SHORT).show());
        popupMenu.show();
    }

    @Override
    public void onClick(View view, int id) {
        showPopupMenu(view, position);
    }
}
