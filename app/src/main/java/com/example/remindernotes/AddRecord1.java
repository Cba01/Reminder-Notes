package com.example.remindernotes;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddRecord1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record1);

        ImageButton btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRecord1.this, AddRecord.class);
                startActivity(intent);

            }
        });

        Button btnListo = findViewById(R.id.btnListo);
        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtTitulo1 = findViewById(R.id.txtTituloRecordatorio);
                EditText txtDescripcion1 = findViewById(R.id.txtDescripcionRecordatorio);

                String tituloRecordatorio = txtTitulo1.getText().toString().trim();
                String descripcion1 = txtDescripcion1.getText().toString().trim();

                Recordatorio r = new Recordatorio(tituloRecordatorio, descripcion1);

                RecordatorioDB db = new RecordatorioDB(AddRecord1.this);
                db.open();
                db.guardarRecordatorio(r.getTitulo(),r.getDescripcion());
                db.close();

                Intent intent = new Intent();
                intent.putExtra("recordatorio", r);
                intent.putExtra("add", 1);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });


    }
}