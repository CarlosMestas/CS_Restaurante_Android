package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterCategoria;
import com.example.myapplication.adapters.AdapterPedidoDetalle;
import com.example.myapplication.adapters.AdapterPlato;
import com.example.myapplication.clases.Categoria;
import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.Pedido;
import com.example.myapplication.clases.PedidoDetalle;
import com.example.myapplication.database.DataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityPedidos extends AppCompatActivity {

    List<PedidoDetalle> pedidoDetalleList;

    private RecyclerView recyclerPedidosDetalles;
    private AdapterPedidoDetalle adapterCategoria;
    private RecyclerView.LayoutManager layoutManager;
    TextView titulo;

    private SwipeRefreshLayout refreshLayout;

    FloatingActionButton fabUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        recyclerPedidosDetalles = findViewById(R.id.recyclerPedidos);
        titulo = findViewById(R.id.tituloMenu);
        fabUsuario = (FloatingActionButton) findViewById(R.id.fabConfirmar);

        titulo.setText("Pedidos realizados");
        pedidoDetalleList = new ArrayList<>();


        chargePedidosDetalles();

        adapterCategoria = new AdapterPedidoDetalle(pedidoDetalleList, this.getApplicationContext());
        recyclerPedidosDetalles = findViewById(R.id.recyclerPedidos);
        recyclerPedidosDetalles.setHasFixedSize(true);//el tamaño es dinamico
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerPedidosDetalles.setLayoutManager(layoutManager);




        recyclerPedidosDetalles.setAdapter(adapterCategoria);

        pedidoDetalleList = ActivityCategorias.pedido.getPedidos();

        fabUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityPedidos.this,ActivityUsuarios.class);
                startActivity(intent);
            }
        });

    }


    public void chargePedidosDetalles(){
        pedidoDetalleList = ActivityCategorias.pedido.getPedidos();
    }


}