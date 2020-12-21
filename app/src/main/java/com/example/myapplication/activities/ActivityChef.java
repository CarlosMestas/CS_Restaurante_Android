package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.MyAdapterPedido;
import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.PedidoDetalle;
import com.example.myapplication.database.DataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityChef extends AppCompatActivity {
    List<PedidoDetalle> pedidosChef;
    private RecyclerView reciclerPedidos;
    private MyAdapterPedido mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        reciclerPedidos = findViewById(R.id.reciclerPedidosChef);

        pedidosChef = new ArrayList<PedidoDetalle>();

        try {
            chargeData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        reciclerPedidos.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        reciclerPedidos.setLayoutManager(layoutManager);
        mAdapter = new MyAdapterPedido (pedidosChef, this.getApplicationContext());

        mAdapter.setOnItemClickListener(new MyAdapterPedido.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

        reciclerPedidos.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fabReload);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    pedidosChef.clear();
                    chargeData();
                    mAdapter.notifyDataSetChanged();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });


        /*
        for(;;){
            try {
                chargeData();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                Thread.sleep(5000);
                Toast.makeText(getApplicationContext(), "Durmiendo" + 5000,Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */
    }

    public void chargeData() throws SQLException {
        String query = "SELECT * FROM `pedidodetalle` WHERE `PedDetEst` = 1 OR `PedDetEst` = 2 OR `PedDetEst` = 3";
        ResultSet rs = DataBase.resultSelectFrom(query);

        while(rs.next()){
            pedidosChef.add(new PedidoDetalle(
                            rs.getInt(1),
                            String.valueOf(rs.getInt(2)),
                            String.valueOf(rs.getInt(3)),
                            String.valueOf(rs.getDouble(4)),
                            String.valueOf(rs.getInt(5)),
                            rs.getInt(6),
                            String.valueOf(rs.getInt(7))
                    )
            );

        }
    }


    public void removeItem(int position){
        pedidosChef.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

}