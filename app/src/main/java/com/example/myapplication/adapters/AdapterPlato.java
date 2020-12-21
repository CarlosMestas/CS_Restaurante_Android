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
import com.example.myapplication.clases.Comida;
import com.example.myapplication.clases.PedidoDetalle;

import java.util.List;

public class AdapterPlato extends RecyclerView.Adapter<AdapterPlato.MyViewHolder> implements View.OnClickListener{
    public int prueba=0;
    private List<Comida> mData;
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

    public AdapterPlato(List<Comida> mData, Context context ) {
        this.mData = mData;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    // Proporcione una referencia a las vistas para cada elemento de datos
    // Los elementos de datos complejos pueden necesitar más de una vista por elemento, y
    // proporciona acceso a todas las vistas para un elemento de datos en un titular de vista
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private String codigo;
        private double subtotal = 0;
        private static int cantidadTotal = 0;

        //Variables ubicadas dentro del card
        int tmp=0;
        TextView name, descripcion, precio, cantidad;
        ImageView add,remove;

        // each data item is just a string in this cas
        public MyViewHolder(View v) {
            super(v);
            //inicializacion de dichas variebles
            name = itemView.findViewById(R.id.platoTituloPD);
            descripcion = itemView.findViewById(R.id.platoDescripcion);
            precio = itemView.findViewById(R.id.platoPrecioPD);
            cantidad = itemView.findViewById(R.id.cantidadPD);
            add = itemView.findViewById(R.id.addPD);
            remove = itemView.findViewById(R.id.removePD);

            PedidoDetalle[] pedidoDetalle = new PedidoDetalle[1];

            /*
            if(cantidadTotal != 0 && ActivityCategorias.pedido.searchPedidoDetalle(Integer.valueOf(codigo)) != -1)
                Toast.makeText(v.getContext(), "Se encontro :v", Toast.LENGTH_SHORT).show();
*/
/*
            if(ActivityCategorias.pedido.searchPedidoDetalle(Integer.valueOf(codigo)) == -1){
                //
                //               Toast.makeText(v.getContext(), "NO se encontro", Toast.LENGTH_SHORT).show();
                //    pedidoDetalle = new PedidoDetalle[1];
            }
            else{
                Toast.makeText(v.getContext(), "Se encontro :v", Toast.LENGTH_SHORT).show();

            }
*/
            /*
            if( ActivityCategorias.pedido.getPedidos().size() == 0)
                pedidoDetalle = new PedidoDetalle[1];

            else {
                if(ActivityCategorias.pedido.searchPedidoDetalle(Integer.valueOf(codigo)) == -1){
     //               Toast.makeText(v.getContext(), "NO se encontro", Toast.LENGTH_SHORT).show();
                //    pedidoDetalle = new PedidoDetalle[1];
                }
                else{
                    Toast.makeText(v.getContext(), "Se encontro :v", Toast.LENGTH_SHORT).show();
                    /*
                    PedidoDetalle [] pedidoTmp = new PedidoDetalle[1];
                    pedidoTmp[0] = ActivityCategorias.pedido.getPedidos().get(Integer.valueOf(codigo));
                    pedidoDetalle = pedidoTmp;
                    tmp = Integer.valueOf(pedidoDetalle[0].getCantidad());
                    cantidad.setText("cantidad: "+ tmp);
                }

            }
            */

            PedidoDetalle[] finalPedidoDetalle = pedidoDetalle;
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tmp++;
                    cantidadTotal++;
                    cantidad.setText("cantidad: "+ tmp);
                    if(tmp == 1){
//                        Comida aux = new Comida();
     //                   Toast.makeText(v.getContext(),codigo + " +1" + cantidadTotal, Toast.LENGTH_SHORT).show();

                        subtotal = tmp * Double.parseDouble(precio.getText().toString());
                        finalPedidoDetalle[0] = new PedidoDetalle(
                                ActivityCategorias.pedido.getPedidos().size() + 1,
                                codigo,
                                String.valueOf(tmp),
                                String.valueOf(subtotal),
                                "",
                                MainActivity.tableNumber,
                                "1");
                        ActivityCategorias.pedido.getPedidos().add(finalPedidoDetalle[0]);

                    }
                    else{
                        int cantidad = tmp;
                        double tmpSubTotal = tmp * Double.parseDouble(precio.getText().toString());
                        finalPedidoDetalle[0].setCantidad(String.valueOf(tmp));
                        finalPedidoDetalle[0].setSubtotal(String.valueOf(tmpSubTotal));
                    }

       //             Toast.makeText(v.getContext(), finalPedidoDetalle[0].toString(), Toast.LENGTH_SHORT).show();


         //           Toast.makeText(v.getContext(),ActivityCategorias.pedido.toString(), Toast.LENGTH_LONG).show();

                }
            });
            PedidoDetalle[] finalPedidoDetalle1 = pedidoDetalle;
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tmp > 1 ){
                        cantidadTotal--;
                        cantidad.setText("cantidad: "+(--tmp));
                        double tmpSubTotal = tmp * Double.parseDouble(precio.getText().toString());
                        finalPedidoDetalle1[0].setCantidad(String.valueOf(tmp));
                        finalPedidoDetalle1[0].setSubtotal(String.valueOf(tmpSubTotal));

         //               Toast.makeText(v.getContext(), finalPedidoDetalle1[0].toString(), Toast.LENGTH_SHORT).show();

                    }
                    else if (tmp == 1){
                        cantidad.setText("cantidad: "+(--tmp));
                        ActivityCategorias.pedido.getPedidos().remove(finalPedidoDetalle1[0]);
                    }
                    else{

                    }

           //         Toast.makeText(v.getContext(),ActivityCategorias.pedido.toString(), Toast.LENGTH_LONG).show();

                }
            });

        }
        void bindData(final Comida item){//
            //seteo de valores de las variables
            //iconImage.setColorFilter(Color.parseColor(item.getArtist()));
            codigo = item.getCodigo();
            name.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());
            precio.setText(Double.toString(item.getPrecio()));

        }


    }

    @NonNull
    @Override
    public AdapterPlato.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.elemento_plato, null);//seleccionar el molde del card
        view.setOnClickListener(this);
        return new AdapterPlato.MyViewHolder(view);
    }

    /*
        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter2(List<Plato>myDataset, Context context, List<Plato>pedidos ) {
            this.mData = myDataset;
            this.context = context;
            this.pedidos = pedidos;
            mInflater = LayoutInflater.from(context);


        }
        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
            View view = mInflater.inflate(R.layout.elemento_platos, null);//seleccionar el molde del card

            view.setOnClickListener(this);
            return new MyAdapter2.MyViewHolder(view, pedidos);


        }
    */
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        holder.bindData(mData.get(position));
    }

    public void setitem(List<Comida> items){
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



}