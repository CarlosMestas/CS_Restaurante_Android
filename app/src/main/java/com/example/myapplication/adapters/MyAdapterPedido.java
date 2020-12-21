package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.clases.PedidoDetalle;
import com.example.myapplication.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyAdapterPedido extends RecyclerView.Adapter<MyAdapterPedido.MyViewHolder> implements View.OnClickListener{
    private List<PedidoDetalle> mData;
    private LayoutInflater mInflater;
    private Context context;
    private OnItemClickListener listener;


    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    @Override
    public void onClick(View view) {
        /*if(listener!=null){
            listener.onClick(view);
        }*/
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapterPedido(List<PedidoDetalle>myDataset, Context context ) {
        this.mData = myDataset;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    // Proporcione una referencia a las vistas para cada elemento de datos
    // Los elementos de datos complejos pueden necesitar más de una vista por elemento, y
    // proporciona acceso a todas las vistas para un elemento de datos en un titular de vista
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Variables ubicadas dentro del card

        TextView name, cantidad, mesa;
        // each data item is just a string in this case
        public TextView textView;
        ImageView image, delete ;
        LinearLayout card;
        List<PedidoDetalle> mData;
        MyAdapterPedido a;
        OnItemClickListener listener;

        PedidoDetalle pedidoDetalle;

        public MyViewHolder(View v, OnItemClickListener listener) {
            super(v);

            card = itemView.findViewById(R.id.cardContend);
            image = itemView.findViewById(R.id.image);
            delete = itemView.findViewById(R.id.delete);
            name = itemView.findViewById(R.id.labelNombrePlato);
            cantidad = itemView.findViewById(R.id.labelCantidad);
            mesa = itemView.findViewById(R.id.labelMesa);
            this.listener = listener;


        }
        void bindData(final PedidoDetalle item){//
            pedidoDetalle = item;

            String nombre;
            String _query = "SELECT `ComCod`, `ComNom` FROM `comida` WHERE `ComCod` = ";
            _query += pedidoDetalle.getCodigoComida();

            ResultSet rs = DataBase.resultSelectFrom(_query);

            String nametmp = "";
            while(true){
                try {
                    if (!rs.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    nametmp = rs.getString(2);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            nombre = nametmp;

            name.setText(nametmp);
            cantidad.setText("Cantidad: "+pedidoDetalle.getCantidad());
            mesa.setText("Mesa: "+pedidoDetalle.getNumeroMesa());
            if (Integer.parseInt(pedidoDetalle.getEstado()) == 1){
                card.setBackgroundColor(Color.argb(100, 197, 197, 197));
            }else if (Integer.parseInt(pedidoDetalle.getEstado()) == 2){
                card.setBackgroundColor(Color.YELLOW);

            }else {
                card.setBackgroundColor(Color.GREEN);
            }
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Integer.parseInt(pedidoDetalle.getEstado()) == 1){
                        card.setBackgroundColor(Color.YELLOW);
                        pedidoDetalle.setEstado(String.valueOf(2));
                        DataBase.executeQueryChangeStatusPedidoDetalle(pedidoDetalle.getCodigo(),2);
                    }else if (Integer.parseInt(pedidoDetalle.getEstado()) == 2){
                        card.setBackgroundColor(Color.GREEN);
                        pedidoDetalle.setEstado(String.valueOf(3));
                        DataBase.executeQueryChangeStatusPedidoDetalle(pedidoDetalle.getCodigo(),3);

                    }else {
                        card.setBackgroundColor(Color.argb(100, 197, 197, 197));
                        pedidoDetalle.setEstado(String.valueOf(1));
                        DataBase.executeQueryChangeStatusPedidoDetalle(pedidoDetalle.getCodigo(),1);
                    }

                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        pedidoDetalle.setEstado(String.valueOf(4));
                        DataBase.executeQueryChangeStatusPedidoDetalle(pedidoDetalle.getCodigo(),4);
                        int position = getAdapterPosition();
                        listener.onDeleteClick(position);
                    }
                }
            });
        }
    }

    private void notifyItemMoved(int position) {
    }



    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterPedido.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = mInflater.inflate(R.layout.elemento_pedidochef, null);//seleccionar el molde del card
        view.setOnClickListener(this);
        return new MyAdapterPedido.MyViewHolder(view, listener);

        // create a new view
        /*TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_music, parent, false);
            //demás
        MyViewHolder vh = new MyViewHolder(v);
        return vh;*/
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        holder.bindData(mData.get(position));
    }

    public void setitem(List<PedidoDetalle>items){
        mData = items;
    }

    /*public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }*/

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }


}