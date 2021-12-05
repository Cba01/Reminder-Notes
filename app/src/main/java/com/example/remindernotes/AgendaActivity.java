package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.remindernotes.databinding.ActivityAgendaBinding;
import com.example.remindernotes.databinding.ActivityDashboardBinding;

import java.util.ArrayList;

public class AgendaActivity extends DrawerBaseActivity {

    ActivityAgendaBinding activityAgendaBinding;
    ListView listAgenda;
    ArrayAdapter<Recordatorio> adapter;
    ArrayList<Recordatorio> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAgendaBinding = ActivityAgendaBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Agenda");
        setContentView(activityAgendaBinding.getRoot());

        this.listado = new ArrayList<Recordatorio>();
        listAgenda = findViewById(R.id.listAgenda);
        this.adapter = new ArrayAdapter<Recordatorio>(this, android.R.layout.simple_list_item_1);
        listAgenda.setAdapter(adapter);

        this.cargarDatosInicialesAgenda();


    }

    public void cargarDatosInicialesAgenda() {
        RecordatorioDB db = new RecordatorioDB(this);
        db.open();
        listado = db.getRecordatorio();
        db.close();
        for (Recordatorio r : listado) {
            if (r.getTipo() == 3) {
                adapter.add(r);
            }
        }
        adapter.notifyDataSetChanged();
    }
}