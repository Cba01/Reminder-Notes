package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleAgenda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_agenda);

        Agenda a = (Agenda) getIntent().getSerializableExtra("agenda");

        TextView txtTitulo = findViewById(R.id.txtInfoTituloSimple);
        TextView txtDescripcion = findViewById(R.id.txtInfoDescricpionSimple);

        txtTitulo.setText("Título: " + a.getTitulo());
        txtDescripcion.setText("Descripción: " + a.getDescripcion());

        Button btnEliminarAgenda = findViewById(R.id.btnEliminarAgenda);
        btnEliminarAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("agenda", a);
                intent.putExtra("accion", "ELIMINAR");
                setResult(DashboardActivity.RESULT_OK, intent);
                finish();
            }
        });




    }
}