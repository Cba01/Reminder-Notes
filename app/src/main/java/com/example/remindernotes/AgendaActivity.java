package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.remindernotes.databinding.ActivityAgendaBinding;
import com.example.remindernotes.databinding.ActivityDashboardBinding;

public class AgendaActivity extends DrawerBaseActivity {

    ActivityAgendaBinding activityAgendaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAgendaBinding = ActivityAgendaBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Agenda");
        setContentView(activityAgendaBinding.getRoot());
    }
}