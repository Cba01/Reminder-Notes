package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.remindernotes.databinding.ActivityCuentaBinding;

public class CuentaActivity extends DrawerBaseActivity {

    ActivityCuentaBinding activityCuentaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityCuentaBinding = ActivityCuentaBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Cuenta");
        setContentView(activityCuentaBinding.getRoot());
    }
}