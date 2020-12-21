package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterPedidoDetalleTicket;
import com.example.myapplication.clases.PedidoDetalle;
import com.example.myapplication.dialogs.DialogEmail;

import java.util.ArrayList;

public class ActivityTicket extends AppCompatActivity {

    TextView textView01;
    TextView textView02;
    TextView textView03;
    TextView textView04, textView04b;
    TextView textView05, textView05b;
    TextView textView06, textView06b;
    TextView textView07, textView07b;
    TextView textView08a, textView08b, textView08c, textView08d;
    ListView listView;
    TextView textView09, textView10;
    TextView textView09b, textView10b;


    Button buttonEnviarCorreo;
    Button buttonFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        textView01 = findViewById(R.id.textView01);
        textView02 = findViewById(R.id.textView02);
        textView03 = findViewById(R.id.textView03);
        textView04 = findViewById(R.id.textView04);
        textView04b = findViewById(R.id.textView04b);
        textView05 = findViewById(R.id.textView05);
        textView05b = findViewById(R.id.textView05b);
        textView06 = findViewById(R.id.textView06);
        textView06b = findViewById(R.id.textView06b);
        textView07 = findViewById(R.id.textView07);
        textView07b = findViewById(R.id.textView07b);
        textView08a = findViewById(R.id.textView08a);
        textView08b = findViewById(R.id.textView08b);
        textView08c = findViewById(R.id.textView08c);
        textView08d = findViewById(R.id.textView08d);
        listView = findViewById(R.id.listView);
        textView09 = findViewById(R.id.textView09);
        textView09b = findViewById(R.id.textView09b);
        textView10 = findViewById(R.id.textView10);
        textView10b = findViewById(R.id.textView10b);
        buttonEnviarCorreo = findViewById(R.id.buttonEnviarCorreo);
        buttonFinalizar = findViewById(R.id.buttonFinalizar);

        textView04b.setText(ActivityCategorias.pedido.getCodigo());
        String completeName = ActivityCategorias.pedido.getCliente().getNombres() + " " + ActivityCategorias.pedido.getCliente().getApellidos();
        textView05b.setText(completeName);
        textView06b.setText(ActivityCategorias.pedido.getFecha());
        textView07b.setText(ActivityCategorias.pedido.getHora());

        AdapterPedidoDetalleTicket adapterPedidoDetalleTicket = new AdapterPedidoDetalleTicket(
                this,
                R.layout.list_pedidodetalle,
                ActivityCategorias.pedido.getPedidos());

        listView.setAdapter(adapterPedidoDetalleTicket);

        textView10.setText(String.valueOf(round(ActivityCategorias.pedido.getTotal()*0.18)));
        textView10b.setText(String.valueOf(round(ActivityCategorias.pedido.getTotal())));

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonEnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                DialogEmail dialogEmail = new DialogEmail();
                dialogEmail.show(getSupportFragmentManager(), "example dialog");
                 */
            }
        });
    }

    public double round(double value) {
        int places = 2;
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}