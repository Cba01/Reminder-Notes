package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;

import android.os.Bundle;

import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.example.remindernotes.databinding.ActivityAgendaBinding;
import com.example.remindernotes.databinding.ActivityCalendarioBinding;

public class CalendarioActivity extends DrawerBaseActivity {

    ActivityCalendarioBinding activityCalendarioBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCalendarioBinding = ActivityCalendarioBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Calendario");
        setContentView(activityCalendarioBinding.getRoot());

        CalendarView cvEvent = findViewById(R.id.cvEvent);
        cvEvent.setCalendarOrientation(OrientationHelper.HORIZONTAL);


    }
}