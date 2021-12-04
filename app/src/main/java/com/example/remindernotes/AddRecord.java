package com.example.remindernotes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class AddRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);


        ImageButton btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);

            }
        });

        TextView txtFechaInicio = findViewById(R.id.txtFecha_inicio);
        TextView txtFechaTermino = findViewById(R.id.txtFechaTermino);
        TextView txtHora = findViewById(R.id.txtHora);
        Button btnFechaInicio = findViewById(R.id.btnFechaInicio);
        Button btnFechaTermino = findViewById(R.id.btnFechaFinal);
        Button btnHora = findViewById(R.id.btnHora);


        RadioGroup grupoRadio = findViewById(R.id.grupoRadio);
        RadioButton btnRecordatorio = findViewById(R.id.btnRecordatorio);
        RadioButton btnProgramado = findViewById(R.id.btnProgramado);
        RadioButton btnSimple = findViewById(R.id.btnProgramado);

        grupoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){
                    case R.id.btnRecordatorio:
                        System.out.println("---------------------RECORDATORIO--------------------------");

                        txtFechaInicio.setVisibility(View.VISIBLE);
                        txtHora.setVisibility(View.VISIBLE);
                        btnFechaInicio.setVisibility(View.VISIBLE);
                        btnHora.setVisibility(View.VISIBLE);

                        btnFechaTermino.setVisibility(View.GONE);
                        txtFechaTermino.setVisibility(View.GONE);
                                break;
                    case R.id.btnProgramado:
                        System.out.println("---------------------PROGRAMADO--------------------------");
                        txtFechaInicio.setVisibility(View.VISIBLE);
                        txtHora.setVisibility(View.VISIBLE);
                        btnFechaInicio.setVisibility(View.VISIBLE);
                        btnHora.setVisibility(View.VISIBLE);
                        btnFechaTermino.setVisibility(View.VISIBLE);
                        txtFechaTermino.setVisibility(View.VISIBLE);

                        break;
                    case R.id.btnSimple:
                        System.out.println("---------------------SIMPLE--------------------------");
                        txtFechaInicio.setVisibility(View.GONE);
                        txtHora.setVisibility(View.GONE);
                        btnFechaInicio.setVisibility(View.GONE);
                        btnHora.setVisibility(View.GONE);
                        btnFechaTermino.setVisibility(View.GONE);
                        txtFechaTermino.setVisibility(View.GONE);

                        break;
                }

            }
        });

    }


}