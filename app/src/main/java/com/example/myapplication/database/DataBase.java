package com.example.myapplication.database;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DataBase {
    public static String url = "*";
    private static String urlP1 = "jdbc:mysql://";
    private static String urlP2 = "/cs_restaurant";
    private static String user = "cs";
    private static String password = "123456";

    public static void setIP(String _ip){
        url = urlP1 + _ip + urlP2;
    }

    public static boolean checkAdministrator (String _dni, String _password){
        boolean search = false;
        try{
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT `UsuDni`, `UsuTipUsuCod`, `UsuCon` FROM `usuario` WHERE UsuTipUsuCod = 1");

            while(rs.next()){

                if(rs.getString(1).equals(_dni) && rs.getString(2).equals(_password)){
                    search = true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("databaseError", e.toString());
        }
        return search;
    }

    public static ResultSet resultSelectFrom(String _query){
        ResultSet rs = null;
        try{
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement st = conn.createStatement();
            rs = st.executeQuery(_query);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("databaseError", e.toString());
        }
        return rs;
    }

    public static void executeQuery(String _query){
        try{
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = conn.prepareStatement(_query);
            ps.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("databaseError", e.toString());
        }
    }

    public static void executeQueryChangeStatusPedidoDetalle(int _CodPedDet, int _newStatus){
        try{
            String query = "UPDATE `pedidodetalle` SET `PedDetEst`= " + _newStatus + " WHERE `PedDetCod` = " + _CodPedDet;
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("databaseError", e.toString());
        }

    }
}
