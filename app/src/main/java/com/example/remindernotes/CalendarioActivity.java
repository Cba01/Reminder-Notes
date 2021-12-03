package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.remindernotes.databinding.ActivityAgendaBinding;
import com.example.remindernotes.databinding.ActivityCalendarioBinding;

public class CalendarioActivity extends DrawerBaseActivity {

    ActivityCalendarioBinding activityCalendarioBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityCalendarioBinding = ActivityCalendarioBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        allocateActivityTitle("Calendario");
        setContentView(activityCalendarioBinding.getRoot());
    }
}