package com.example.remindernotes.ui.calendario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.remindernotes.databinding.FragmentCalendarioBinding;

public class CalendarioFragment extends Fragment {

    private CalendarioViewModel calendarioViewModel;
    private FragmentCalendarioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarioViewModel =
                new ViewModelProvider(this).get(CalendarioViewModel.class);

        binding = FragmentCalendarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}