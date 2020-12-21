package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.activities.ActivityCategorias;
import com.example.myapplication.activities.ActivityChef;
import com.example.myapplication.configuration.LectorQR;
import com.example.myapplication.database.DataBase;
import com.example.myapplication.dialogs.SignInChef;
import com.example.myapplication.dialogs.SignInKey;

public class MainActivity extends AppCompatActivity {

    public Button buttonConfiguration;
    public Button buttonChef;
    public Button buttonIn;
    public TextView textNewUser;

    public static int tableNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConfiguration = (Button) findViewById(R.id.buttonConfiguration);
        buttonChef = (Button) findViewById(R.id.buttonChef);
        buttonIn = (Button) findViewById(R.id.buttonIngresar);
        textNewUser = (TextView) findViewById(R.id.textViewEresNuevo);


        buttonConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        buttonChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogChef();
            }
        });

        buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataBase.url.equals("*")){
                    Log.d("errorEnCambio","Error");
                    Toast.makeText(getApplicationContext(),"Se necesita realizar una configuración", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), ActivityCategorias.class);
                    startActivity(intent);
                }
            }
        });


        textNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Youtube.class);
                startActivity(intent);
            }
        });

    }

    public void openDialog() {
        SignInKey signInAdministrator = new SignInKey();
        signInAdministrator.show(getSupportFragmentManager(), "example dialog");
    }

    public void openDialogChef() {
        SignInChef signInAdministratorChef = new SignInChef();
        signInAdministratorChef.show(getSupportFragmentManager(), "example dialog");
    }


}