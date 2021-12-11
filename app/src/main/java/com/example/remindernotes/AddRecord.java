package com.example.remindernotes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class AddRecord extends AppCompatActivity {

    Calendar c;
    DatePickerDialog dpd;
    int hora, minuto;

    TextView txtFecha_inicio, txtHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        txtFecha_inicio = findViewById(R.id.txtFecha_inicio);
        txtHora = findViewById(R.id.txtHora);

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
        TextView txtTipo = findViewById(R.id.txtTipo);
        Button btnFechaInicio = findViewById(R.id.btnFechaInicio);
        Button btnFechaTermino = findViewById(R.id.btnFechaFinal);
        Button btnHora = findViewById(R.id.btnHora);


        RadioGroup grupoRadio = findViewById(R.id.grupoRadio);
        RadioButton btnRecordatorio = findViewById(R.id.btnRecordatorio);
        RadioButton btnProgramado = findViewById(R.id.btnProgramado);

        grupoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btnRecordatorio:
                        System.out.println("---------------------RECORDATORIO--------------------------");
                        btnFechaInicio.setText("");
                        btnFechaTermino.setText("");
                        btnHora.setText("");
                        txtTipo.setText("1");

                        txtFechaInicio.setVisibility(View.VISIBLE);
                        txtHora.setVisibility(View.VISIBLE);
                        btnFechaInicio.setVisibility(View.VISIBLE);
                        btnHora.setVisibility(View.VISIBLE);

                        btnFechaTermino.setVisibility(View.GONE);
                        txtFechaTermino.setVisibility(View.GONE);
                        break;
                    case R.id.btnProgramado:
                        System.out.println("---------------------PROGRAMADO--------------------------");
                        btnFechaInicio.setText("");
                        btnFechaTermino.setText("");
                        btnHora.setText("");
                        txtTipo.setText("2");

                        txtFechaInicio.setVisibility(View.VISIBLE);
                        txtHora.setVisibility(View.VISIBLE);
                        btnFechaInicio.setVisibility(View.VISIBLE);
                        btnHora.setVisibility(View.VISIBLE);
                        btnFechaTermino.setVisibility(View.VISIBLE);
                        txtFechaTermino.setVisibility(View.VISIBLE);

                        break;

                }

            }
        });

        //AGREGAR LA FECHA
        btnFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int año = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(AddRecord.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        btnFechaInicio.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        System.out.println(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, 2021, mes, dia);
                dpd.show();
            }
        });


        //AGREGAR HORA
        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        hora = hourOfDay;
                        minuto = minute;
                        btnHora.setText(hora + ":" + minuto);
                        System.out.println(hora + ":" + minuto);

                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddRecord.this, onTimeSetListener, hora, minuto, true);
                timePickerDialog.setTitle("Selecciona una hora");
                timePickerDialog.show();
            }
        });

        btnFechaTermino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int año = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(AddRecord.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        btnFechaTermino.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        System.out.println(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, 2021, mes, dia);
                dpd.show();
            }
        });

        //GUARDAR RECORDATORIO
        Button btnListo = findViewById(R.id.btnListo);
        btnListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitulo = findViewById(R.id.txtTituloRecordatorio);
                EditText txtDescripcion = findViewById(R.id.txtDescripcionRecordatorio);

                String titulo = txtTitulo.getText().toString().trim();
                String descripcion = txtDescripcion.getText().toString().trim();
                String fechaInicio = String.valueOf(btnFechaInicio.getText());
                String fechaTermino = String.valueOf(btnFechaTermino.getText());
                String hora = String.valueOf(btnHora.getText());
                String txtTipos = String.valueOf(txtTipo.getText());
                int tipo = Integer.parseInt(txtTipos);

                if (!btnRecordatorio.isChecked() && !btnProgramado.isChecked()) {

                    Toast.makeText(getApplicationContext(), "Seleccione un tipo de Recordatorio", Toast.LENGTH_SHORT).show();

                } else {
                    if (btnProgramado.isChecked()) {
                        if (titulo.length() == 0 || descripcion.length() == 0 || fechaInicio.length() == 0 || fechaTermino.length() == 0 || hora.length() == 0) {
                            Toast.makeText(getApplicationContext(), "Complete los campos", Toast.LENGTH_SHORT).show();
                        } else {
                            Recordatorio r = new Recordatorio(titulo, descripcion, fechaInicio, fechaTermino, hora, tipo);
                            Intent intent = new Intent();
                            intent.putExtra("recordatorio", r);
                            intent.putExtra("accion", "AGREGAR");
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }

                    } else if (btnRecordatorio.isChecked()) {
                        if (titulo.length() == 0 || descripcion.length() == 0 || fechaInicio.length() == 0 || hora.length() == 0) {
                            Toast.makeText(getApplicationContext(), "Complete los campos", Toast.LENGTH_SHORT).show();
                        } else {
                            Recordatorio r = new Recordatorio(titulo, descripcion, fechaInicio, fechaTermino, hora, tipo);
                            Intent intent = new Intent();
                            intent.putExtra("recordatorio", r);
                            intent.putExtra("accion", "AGREGAR");
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        }
                    }

                }


                }
            });


        }


    }