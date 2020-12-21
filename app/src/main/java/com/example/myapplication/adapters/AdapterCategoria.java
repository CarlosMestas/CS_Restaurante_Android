package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.clases.Categoria;

import java.util.List;

public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.MyViewHolder> implements View.OnClickListener{
    private List<Categoria> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    // Proporcione una referencia a las vistas para cada elemento de datos
    // Los elementos de datos complejos pueden necesitar más de una vista por elemento, y
    // proporciona acceso a todas las vistas para un elemento de datos en un titular de vista
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Variables ubicadas dentro del card
        LinearLayout iconImage;
        TextView name;
        // each data item is just a string in this case

        public MyViewHolder(View v) {
            super(v);
            //inicializacion de dichas variebles
            name = itemView.findViewById(R.id.nameCard);


        }
        void bindData(final Categoria item){//
            //seteo de valores de las variables
            //iconImage.setColorFilter(Color.parseColor(item.getArtist()));
            name.setText(item.getNombre().toUpperCase());
            /*
            if(item.getNombre().equalsIgnoreCase("Segundos"))
                iconImage.setBackgroundResource(R.drawable.segundos);
            else if(item.getNombre().equalsIgnoreCase("Entradas"))
                iconImage.setBackgroundResource(R.drawable.entradas);
            else if(item.getNombre().equalsIgnoreCase("Bebidas"))
                iconImage.setBackgroundResource(R.drawable.bebidas);
            else if(item.getNombre().equalsIgnoreCase("postres"))
                iconImage.setBackgroundResource(R.drawable.postres);
*/
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterCategoria(List<Categoria> myDataset, Context context ) {
        this.mData = myDataset;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterCategoria.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View view = mInflater.inflate(R.layout.elemento_categoria, null);//seleccionar el molde del card
        view.setOnClickListener(this);
        return new AdapterCategoria.MyViewHolder(view);

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

    public void setitem(List<Categoria>items){
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