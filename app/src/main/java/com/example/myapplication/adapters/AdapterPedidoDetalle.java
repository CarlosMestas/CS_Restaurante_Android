package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.activities.ActivityCategorias;
import com.example.myapplication.clases.Categoria;
import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.PedidoDetalle;
import com.example.myapplication.database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdapterPedidoDetalle extends RecyclerView.Adapter<AdapterPedidoDetalle.MyViewHolder> implements View.OnClickListener{

    private List<PedidoDetalle> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;



    CardView card;
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public AdapterPedidoDetalle(List<PedidoDetalle> mData, Context context ) {
        this.mData = mData;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    // Proporcione una referencia a las vistas para cada elemento de datos
    // Los elementos de datos complejos pueden necesitar más de una vista por elemento, y
    // proporciona acceso a todas las vistas para un elemento de datos en un titular de vista
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private int codigo;
        private double subtotal = 0;
        private static int cantidadTotal = 0;

        //Variables ubicadas dentro del card
        int tmp=0;
        TextView name, descripcion, precio, cantidad;
        ImageView add,remove;
        PedidoDetalle pedidoDetalle;


        // each data item is just a string in this cas
        public MyViewHolder(View v) {
            super(v);
            //inicializacion de dichas variebles

            name = itemView.findViewById(R.id.platoTituloPD);
            precio = itemView.findViewById(R.id.platoPrecioPD);
            cantidad = itemView.findViewById(R.id.cantidadPD);
            add = itemView.findViewById(R.id.addPD);
            remove = itemView.findViewById(R.id.removePD);



    //        tmp = Integer.parseInt(pedidoDetalle.getCantidad());

            add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    double pu = Double.parseDouble(pedidoDetalle.getSubtotal()) / Integer.parseInt(pedidoDetalle.getCantidad());
                    tmp = Integer.parseInt(pedidoDetalle.getCantidad());
                    tmp++;
          //          ActivityCategorias.pedido.getPedidos().get(pedidoDetalle.getCodigo() + 1).setCantidad(String.valueOf(tmp));
                    cantidad.setText("" + tmp);
                    precio.setText(String.valueOf(tmp * pu));
                    pedidoDetalle.setCantidad(String.valueOf(tmp));
                    pedidoDetalle.setSubtotal(String.valueOf(tmp * pu));
   //                 Toast.makeText(v.getContext(),pedidoDetalle.toString(), Toast.LENGTH_LONG).show();
   //                 Toast.makeText(v.getContext(),ActivityCategorias.pedido.toString(), Toast.LENGTH_LONG).show();
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tmp = Integer.parseInt(pedidoDetalle.getCantidad());
                    if(tmp > 1){
                        double pu = Double.parseDouble(pedidoDetalle.getSubtotal()) / Integer.parseInt(pedidoDetalle.getCantidad());
                        tmp--;
                        cantidad.setText("" + tmp);
                        precio.setText(String.valueOf(tmp * pu));
                        pedidoDetalle.setCantidad(String.valueOf(tmp));
                        pedidoDetalle.setSubtotal(String.valueOf(tmp * pu));
             //         Toast.makeText(v.getContext(),pedidoDetalle.toString(), Toast.LENGTH_LONG).show();
              //        Toast.makeText(v.getContext(),ActivityCategorias.pedido.toString(), Toast.LENGTH_LONG).show();
                    }
                    else{
                  //      Toast.makeText(v.getContext(),"Por eliminar", Toast.LENGTH_LONG).show();
                        tmp--;
                        cantidad.setText("" + tmp);
                        precio.setText(String.valueOf(0.0));
                        ActivityCategorias.pedido.getPedidos().remove(pedidoDetalle);
               //       Toast.makeText(v.getContext(),ActivityCategorias.pedido.toString(), Toast.LENGTH_LONG).show();
                        remove.setEnabled(false);
                        add.setEnabled(false);
                    }
                }


            });

        }
        void bindData(final PedidoDetalle item) throws SQLException {//
            pedidoDetalle = item;
            //seteo de valores de las variables
            //iconImage.setColorFilter(Color.parseColor(item.getArtist()));
            codigo = item.getCodigo();
            String _query = "SELECT `ComCod`, `ComNom` FROM `comida` WHERE `ComCod` = ";
            _query += item.getCodigoComida();


            ResultSet rs = DataBase.resultSelectFrom(_query);

            while(rs.next()){
                name.setText(rs.getString(2));
            }


            cantidad.setText(item.getCantidad());
            precio.setText(item.getSubtotal());

        }


    }

    @NonNull
    @Override
    public AdapterPedidoDetalle.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.elemento_pedido, null);//seleccionar el molde del card
        view.setOnClickListener(this);
        return new AdapterPedidoDetalle.MyViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        try {
            holder.bindData(mData.get(position));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setitem(List<PedidoDetalle> items){
        mData = items;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface dialogReloadDataTrabajadoresListener{
        void applyReloadAddTrabajadores();
    }


}