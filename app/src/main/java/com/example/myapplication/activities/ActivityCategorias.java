package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterCategoria;
import com.example.myapplication.clases.Categoria;
import com.example.myapplication.clases.Pedido;
import com.example.myapplication.database.DataBase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityCategorias extends AppCompatActivity {

    List<Categoria> categoriaList;

    public static Pedido pedido;
    private RecyclerView recyclerCategorias;
    private AdapterCategoria adapterCategoria;
    private RecyclerView.LayoutManager layoutManager;


    public FloatingActionButton fabPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        Calendar calendar = Calendar.getInstance();

        pedido = new Pedido(
                MainActivity.tableNumber,
                String.valueOf(1),
                calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE),
                new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date()));

//      Toast.makeText(getApplicationContext(),pedido.toString(), Toast.LENGTH_LONG).show();

        categoriaList = new ArrayList<>();

        adapterCategoria = new AdapterCategoria (categoriaList, this.getApplicationContext());
        recyclerCategorias = findViewById(R.id.reciclerCategorias);
        recyclerCategorias.setHasFixedSize(true);//el tamaño es dinamico
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerCategorias.setLayoutManager(layoutManager);

        adapterCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     //           Toast.makeText(getApplicationContext(),"Nombre: " + categoriaList.get(recyclerCategorias.getChildAdapterPosition(v)).getCodigo(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityCategorias.this,ActivityPlatos.class);
                intent.putExtra("idCat", String.valueOf(categoriaList.get(recyclerCategorias.getChildAdapterPosition(v)).getCodigo()));
                startActivity(intent);


            }
        });

        recyclerCategorias.setAdapter(adapterCategoria);

        try {
            chargeData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        fabPedidos = (FloatingActionButton) findViewById(R.id.fabPedido);
        fabPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityPedidos.class);
                startActivity(intent);
            }
        });


    }

    public void chargeData() throws SQLException {
        String query = "SELECT * FROM `categoria` WHERE `CatEst` = 1";
        ResultSet rs = DataBase.resultSelectFrom(query);

        while(rs.next()){
            categoriaList.add(new Categoria(
                    String.valueOf(rs.getInt(1)),
                    rs.getString(2),
                    String.valueOf(rs.getInt(3))
                    )
            );
        }
    }


}