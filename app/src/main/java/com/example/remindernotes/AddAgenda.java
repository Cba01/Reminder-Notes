package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddAgenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_agenda);


        ImageButton btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgendaActivity.class);
                startActivity(intent);

            }
        });


        //GUARDAR AGENDA
        Button btnListoSimple = findViewById(R.id.btnListoSimple);
        btnListoSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTituloSimple = findViewById(R.id.txtTituloSimple);
                EditText txtDescripcionSimple = findViewById(R.id.txtDescripcionSimple);

                String titulo = txtTituloSimple.getText().toString().trim();
                String descripcion = txtDescripcionSimple.getText().toString().trim();


                Agenda a = new Agenda(titulo, descripcion);
                Intent intent = new Intent();
                intent.putExtra("agenda", a);
                intent.putExtra("accion", "AGREGAR");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}