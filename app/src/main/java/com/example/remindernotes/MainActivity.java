package com.example.remindernotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText login_usuario = findViewById(R.id.login_usuario);
        EditText login_contraseña = findViewById(R.id.login_contraseña);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (login_usuario.length() == 0 || login_contraseña.length() == 0){
                    Toast.makeText(getApplicationContext(), "Rellene los campos.",Toast.LENGTH_SHORT).show();

                }
                else{ Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                    finish(); }


            }
        });


    }



}
