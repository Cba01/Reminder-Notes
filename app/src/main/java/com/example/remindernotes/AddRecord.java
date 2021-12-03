package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);




        Button btntipoR = findViewById(R.id.btn_recordatorio);
        btntipoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRecord.this, AddRecord1.class);
               startActivity(intent);
            }
        });

        Button btntipoP = findViewById(R.id.btn_programado);
        btntipoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRecord.this, AddRecord2.class);
                startActivity(intent);
            }
        });

        Button btntipoS = findViewById(R.id.btn_simple);
        btntipoS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRecord.this, AddRecord3.class);
                startActivity(intent);
            }
        });

        ImageButton btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);

            }
        });

    }


}