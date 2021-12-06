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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.remindernotes.databinding.ActivityAgendaBinding;
import com.example.remindernotes.databinding.ActivityDashboardBinding;

import java.util.ArrayList;

public class AgendaActivity extends DrawerBaseActivity {

    ActivityAgendaBinding activityAgendaBinding;
    ListView listAgenda;
    ArrayAdapter<Agenda> adapter;
    ArrayList<Agenda> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAgendaBinding = ActivityAgendaBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Agenda");
        setContentView(activityAgendaBinding.getRoot());

        this.listado = new ArrayList<Agenda>();
        listAgenda = findViewById(R.id.listAgenda);
        this.adapter = new ArrayAdapter<Agenda>(this, android.R.layout.simple_list_item_1);
        listAgenda.setAdapter(adapter);

        this.cargarDatosInicialesAgenda();

        ActivityResultLauncher<Intent> launchActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            String accion = data.getStringExtra("accion");
                            Log.i("ACCION", accion);

                            if(accion.equalsIgnoreCase("AGREGAR")){
                                Agenda a = (Agenda) data.getSerializableExtra("agenda");

                                SimpleDB db = new SimpleDB(AgendaActivity.this);
                                db.open();
                                long id = db.guardarAgenda(a.getTitulo(),a.getDescripcion());
                                a.setId(String.valueOf(id));
                                db.close();

                                    listado.add(a);
                                    adapter.add(a);

                                adapter.notifyDataSetChanged();

                            }

                            if (accion.equalsIgnoreCase("ELIMINAR")){
                                Agenda a = (Agenda) data.getSerializableExtra("agenda");
                                SimpleDB db = new SimpleDB(AgendaActivity.this);
                                db.open();
                                db.borrarAgenda(a.getId());
                                db.close();
                                final int size = listado.size();
                                for (int i = 0; i < size; i++) {
                                    Agenda object = listado.get(i);
                                    Log.i("ITEM " + i, object.getId());
                                    if (object.getId().equals(a.getId())){
                                        listado.remove(object);
                                        adapter.remove(object);
                                        adapter.notifyDataSetChanged();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                });

        ImageButton btnAgregarSimple = findViewById(R.id.btnAgregarSimple);
        btnAgregarSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgendaActivity.this, AddAgenda.class);
                launchActivity.launch(intent);

            }
        });

        listAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda a = listado.get(position);
                Intent intent = new Intent(AgendaActivity.this, DetalleAgenda.class);
                intent.putExtra("agenda", a);
                launchActivity.launch(intent);
            }
        });



    }




    public void cargarDatosInicialesAgenda() {
        SimpleDB db = new SimpleDB(this);
        db.open();
        listado = db.getAgenda();
        db.close();
        for (Agenda a : listado) {
                adapter.add(a);
        }
        adapter.notifyDataSetChanged();
    }
}