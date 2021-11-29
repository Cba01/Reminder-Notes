package com.example.remindernotes.ui.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.remindernotes.databinding.FragmentAgendaBinding;

public class AgendaFragment extends Fragment {

    private AgendaViewModel agendaViewModel;
    private FragmentAgendaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        agendaViewModel =
                new ViewModelProvider(this).get(AgendaViewModel.class);

        binding = FragmentAgendaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}