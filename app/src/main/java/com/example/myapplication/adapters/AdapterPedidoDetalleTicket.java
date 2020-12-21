package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.clases.PedidoDetalle;
import com.example.myapplication.database.DataBase;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdapterPedidoDetalleTicket extends BaseAdapter {
    private Context context;
    private int layout;
    private List<PedidoDetalle> pedidos;

    public AdapterPedidoDetalleTicket(Context context, int layout, List<PedidoDetalle> pedidos) {
        this.context = context;
        this.layout = layout;
        this.pedidos = pedidos;
    }

    @Override
    public int getCount() {
        return this.pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_pedidodetalle, null);
        String nombre = pedidos.get(position).getCodigoComida();
        String cantidad = pedidos.get(position).getCantidad();
        String pu = String.valueOf(Double.parseDouble(pedidos.get(position).getSubtotal()) / Double.parseDouble(pedidos.get(position).getCantidad()));
        String subtotal = pedidos.get(position).getSubtotal();


        String _query = "SELECT `ComCod`, `ComNom` FROM `comida` WHERE `ComCod` = ";
        _query += pedidos.get(position).getCodigoComida();

        ResultSet rs = DataBase.resultSelectFrom(_query);

        String name = "";
        while(true){
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                name = rs.getString(2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        nombre = name;

        TextView tvNombre = v.findViewById(R.id.textView1pd1);
        TextView tvCantidad = v.findViewById(R.id.textView2pd2);
        TextView tvPu = v.findViewById(R.id.textView3pd3);
        TextView tvSubtotal = v.findViewById(R.id.textView4pd4);

        tvNombre.setText(nombre);
        tvCantidad.setText(cantidad);
        tvPu.setText(pu);
        tvSubtotal.setText(subtotal);
        return v;
    }
}
