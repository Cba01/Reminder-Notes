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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.remindernotes.databinding.ActivityDashboardBinding;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class DashboardActivity extends DrawerBaseActivity {

    ActivityDashboardBinding activityDashboardBinding;

    ArrayAdapter<Recordatorio> adapter;
    ArrayList<Recordatorio> listado;
    ListView lstRecordatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Dashboard");
        setContentView(activityDashboardBinding.getRoot());

        this.listado = new ArrayList<Recordatorio>();
        lstRecordatorio = findViewById(R.id.lstRecordatorio);
        this.adapter = new ArrayAdapter<Recordatorio>(this, android.R.layout.simple_list_item_1);
        lstRecordatorio.setAdapter(adapter);

        this.cargarDatosInciales();

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
                            Recordatorio r = (Recordatorio) data.getSerializableExtra("recordatorio");

                            RecordatorioDB db = new RecordatorioDB(DashboardActivity.this);
                            db.open();
                            db.guardarRecordatorio(r.getTitulo(),r.getDescripcion());
                            db.close();

                            listado.add(r);
                            adapter.add(r);
                            adapter.notifyDataSetChanged();

                        }

                        if (data.getIntExtra("update", 0) == 1){
                            adapter.remove(adapter.getItem(data.getIntExtra("position", 0)));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                });

        ImageButton btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, AddRecord.class);
                launchActivity.launch(intent);
                startActivityForResult(intent,Activity.RESULT_OK);
            }
        });



    }

    private void cargarDatosInciales(){

     RecordatorioDB db = new RecordatorioDB(this);
     db.open();
     listado = db.getRecordatorio();
     db.close();
     for (Recordatorio r : listado){

            Log.i("CON:", r.getTitulo());

     }
    }
}