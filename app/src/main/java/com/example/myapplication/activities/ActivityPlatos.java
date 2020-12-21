package com.example.myapplication.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.AdapterPlato;
import com.example.myapplication.clases.Comida;
import com.example.myapplication.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityPlatos extends AppCompatActivity {

    private RecyclerView recyclerPlatos;
    private AdapterPlato mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView titulo, descripcion;
    List<Comida> comidas;
    Button back;
    Button listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String numeroCategoria = "1";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            numeroCategoria = bundle.getString("idCat");
        }


   //     Toast.makeText(getApplicationContext(),":v" + numeroCategoria, Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_comidas);

        descripcion = findViewById(R.id.textViewDescripcion);

        ResultSet des = DataBase.resultSelectFrom("SELECT `CatDes` FROM `categoria` WHERE `CatCod` = " + numeroCategoria);

        String tmpDes = "";

        while(true){
            try {
                if (!des.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                tmpDes =  des.getString(1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        descripcion.setText(tmpDes);


        recyclerPlatos = findViewById(R.id.recicletPlatos);
        titulo = findViewById(R.id.tituloMenu);

        back  = findViewById(R.id.ButtonBack);

        comidas = new ArrayList<Comida>();


        mAdapter = new AdapterPlato(comidas, this.getApplicationContext());
        recyclerPlatos = findViewById(R.id.recicletPlatos);
        recyclerPlatos.setHasFixedSize(true);//el tamaño es dinamico
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerPlatos.setLayoutManager(layoutManager);




        recyclerPlatos.setAdapter(mAdapter);

        try {
            chargeData(numeroCategoria);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void chargeData(String _categoria) throws SQLException {
        String query = "SELECT * FROM `comida` WHERE `ComEst` = 1 AND `ComCatCod` = ";
        query = query + _categoria;
        ResultSet rs = DataBase.resultSelectFrom(query);

        while(rs.next()){
            comidas.add(new Comida(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        rs.getDouble(6)
                    )
            );
        }
    }


}