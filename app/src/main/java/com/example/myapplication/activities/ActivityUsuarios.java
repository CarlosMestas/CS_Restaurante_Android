package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.clases.Cliente;
import com.example.myapplication.database.DataBase;
import com.example.myapplication.dialogs.DialogEmail;
import com.example.myapplication.dialogs.SignInKey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityUsuarios extends AppCompatActivity {

    EditText editTextDNI;
    Button buttonContinuar;
    TextView textViewRegistrarse;
    TextView textViewInvitado;

    EditText editTextInv1DNI;
    EditText editTextInv2Nombres;
    EditText editTextInv3Apellidos;
    EditText editTextInv4Telefono;
    Button buttonRegistrarse;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        linearLayout = findViewById(R.id.linearRegistro);

        editTextDNI = findViewById(R.id.editTextDNI);
        buttonContinuar = findViewById(R.id.buttonContinuar2);

        textViewRegistrarse = findViewById(R.id.textViewRegistrarse);
        textViewInvitado = findViewById(R.id.textViewInvitado);

        editTextInv1DNI = findViewById(R.id.editTextInv2);
        editTextInv2Nombres = findViewById(R.id.editTextInv3);
        editTextInv3Apellidos = findViewById(R.id.editTextInv4);
        editTextInv4Telefono = findViewById(R.id.editTextInv5);
        buttonRegistrarse = findViewById(R.id.buttonRegistrarse);

        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextDNI.getText().toString().length() == 8){
                    String query = "SELECT `UsuCod`, `UsuNom`, `UsuApe`, `UsuDni`, `UsuTel` FROM `usuario` WHERE `UsuDni` = " + editTextDNI.getText().toString();
                    try {
                        setClientePedido(query);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Bienvenido " +
                            ActivityCategorias.pedido.getCliente().getNombres(), Toast.LENGTH_SHORT).show();

                    try {
                        setLastData();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
              //      Toast.makeText(getApplicationContext(), ActivityCategorias.pedido.toString(), Toast.LENGTH_SHORT).show();

                    ArrayList<String> finalquery =  ActivityCategorias.pedido.finalExecution();

                    Log.d("databaseFinalEscution", finalquery.toString());

                    for(int i = 0 ; i < finalquery.size(); i++){
                        DataBase.executeQuery(finalquery.get(i).toString());
                    }
//                    DataBase.executeQuery(finalquery.toString());

                    Intent intent = new Intent(getApplicationContext(), ActivityTicket.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Error en la cantidad de números del DNI", Toast.LENGTH_SHORT).show();
                    editTextDNI.setText("");
                }
            }
        });

        textViewRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        textViewInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "SELECT `UsuCod`, `UsuNom`, `UsuApe`, `UsuDni`, `UsuTel` FROM `usuario` WHERE `UsuCod` = 1";
                try {
                    setClientePedido(query);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Bienvenido " +
                        ActivityCategorias.pedido.getCliente().getNombres(), Toast.LENGTH_SHORT).show();

                try {
                    setLastData();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

//                Toast.makeText(getApplicationContext(), ActivityCategorias.pedido.toString(), Toast.LENGTH_SHORT).show();

                ArrayList<String> finalquery =  ActivityCategorias.pedido.finalExecution();

                Log.d("databaseFinalEscution", finalquery.toString());

                for(int i = 0 ; i < finalquery.size(); i++){
                    DataBase.executeQuery(finalquery.get(i).toString());
                }
//                    DataBase.executeQuery(finalquery.toString());

                Intent intent = new Intent(getApplicationContext(), ActivityTicket.class);
                startActivity(intent);

            }
        });

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextInv1DNI.getText().toString().length() == 8){
                    String registroDNI = editTextInv1DNI.getText().toString();
                    String registroNombres = editTextInv2Nombres.getText().toString();
                    String registroApellidos = editTextInv3Apellidos.getText().toString();
                    String registroNumero = editTextInv4Telefono.getText().toString();
                    int lastCodeUser = 0;
                    try {
                        lastCodeUser = searchLastUserCode();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    int newCodeUser = lastCodeUser + 1;

                    String query = "INSERT INTO `usuario`(`UsuCod`, `UsuNom`, `UsuApe`, `UsuDni`, `UsuTel`, `UsuTipUsuCod`) " +
                            "VALUES (" + newCodeUser + ", '" + registroNombres+ "', '"+registroApellidos+"', "+registroDNI+", " + registroNumero + ", 2)";
                    DataBase.executeQuery(query);
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                    editTextDNI.setText(registroDNI);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Error en la cantidad de números del DNI", Toast.LENGTH_SHORT).show();
                    editTextInv1DNI.setText("");
                }
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });



    }

    public void setClientePedido (String _query) throws  SQLException{
        ResultSet rs =  DataBase.resultSelectFrom(_query);
        while(rs.next()){
            ActivityCategorias.pedido.setCliente(
                    new Cliente(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5)
                    ));
        }
    }
    public int searchLastUserCode() throws SQLException {
        ResultSet rs = DataBase.resultSelectFrom("select max(`UsuCod`) from usuario");
        int tmpCode = 0;
        while(rs.next()){
            tmpCode = rs.getInt(1);
        }
        return tmpCode;
    }

    public int searchlastPedidoCode() throws SQLException {
        ResultSet rs = DataBase.resultSelectFrom("select max(`PedCod`) from pedido");
        int tmpCode = 0;
        while(rs.next()){
            tmpCode = rs.getInt(1);
        }
        return tmpCode + 1;
    }

    public int searchLastPedidoDetalleCode() throws SQLException{
        ResultSet rs = DataBase.resultSelectFrom("select max(`PedDetCod`) from pedidodetalle");
        int tmpCode = 0;
        while(rs.next()){
            tmpCode = rs.getInt(1);
        }
        return tmpCode + 1;
    }

    public void setLastData() throws SQLException{
        int codigoPedido = 0;
        try {
            codigoPedido = searchlastPedidoCode();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ActivityCategorias.pedido.setCodigo(String.valueOf(codigoPedido));

        double total = 0;
        int lastPedidoDetalleCode = 0;
        lastPedidoDetalleCode = searchLastPedidoDetalleCode();

        for(int i = 0; i < ActivityCategorias.pedido.getPedidos().size(); i++){
            ActivityCategorias.pedido.getPedidos().get(i).setCodigoPedido(String.valueOf(codigoPedido));
            ActivityCategorias.pedido.getPedidos().get(i).setCodigo(lastPedidoDetalleCode);
            total += Double.parseDouble(ActivityCategorias.pedido.getPedidos().get(i).getSubtotal().toString());
            lastPedidoDetalleCode++;
        }
        ActivityCategorias.pedido.setTotal(total);
    }





}