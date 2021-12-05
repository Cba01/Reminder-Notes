package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Recordatorio r = (Recordatorio) getIntent().getSerializableExtra("recordatorio");

        TextView txtTitulo = findViewById(R.id.txtInfoTitulo);
        TextView txtDescripcion = findViewById(R.id.txtInfoDescricpion);
        TextView txtFechaInicio = findViewById(R.id.txtInfoFechaInicio);
        TextView txtFechaTermino = findViewById(R.id.txtInfoFechaTermino);
        TextView txtHora = findViewById(R.id.txtInfoHora);

        txtTitulo.setText("Título: " + r.getTitulo());
        txtDescripcion.setText("Descripción: " + r.getDescripcion());
        txtFechaInicio.setText("Fecha de inicio: " + r.getFechaInicio());
        txtFechaTermino.setText("Fecha de termino: " + r.getFechaTermino());
        txtHora.setText("Hora de inicio: " + r.getHora());

        Button btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("recordatorio", r);
                intent.putExtra("accion", "ELIMINAR");
                setResult(DashboardActivity.RESULT_OK, intent);
                finish();
            }
        });



    }
}