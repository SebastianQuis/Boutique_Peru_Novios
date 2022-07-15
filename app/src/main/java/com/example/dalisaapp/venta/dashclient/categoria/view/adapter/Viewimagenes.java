package com.example.dalisaapp.venta.dashclient.categoria.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.zUtilidad.model.OnItemObjectListenerLey;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class Viewimagenes extends RecyclerView.Adapter<Viewimagenes.viewimagenesHolder> {

    private List<Prenda> list;
    private OnItemObjectListenerLey<Prenda> onItemObjectListenerLey;
    public Viewimagenes(List<Prenda> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Viewimagenes.viewimagenesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_prendar, parent, false);
        return new Viewimagenes.viewimagenesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewimagenes.viewimagenesHolder holder, int position) {
        holder.nombrePrenda.setText(list.get(position).getNombre());
        System.out.println(list.get(position).getColorNumber());
        holder.colorPrenda.setBackgroundColor(Integer.parseInt(list.get(position).getColorNumber()));
        holder.cateProducto.setText(list.get(position).getCategoria().getNameCategoria());
        holder.precioPrenda.setText("S/."+list.get(position).getPrecio());
        try {
            Picasso.get().load(list.get(position).getFoto()).into( holder.imgPrendra);
        } catch (Exception e) {

            e.printStackTrace();
        }
        holder.anadirCesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemObjectListenerLey.onClickListenerLeyCustom(list.get(position));
            }
        });

    }

    public void setOnItemObjectListenerLey(OnItemObjectListenerLey<Prenda> onItemObjectListenerLey) {
        this.onItemObjectListenerLey = onItemObjectListenerLey;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewimagenesHolder extends RecyclerView.ViewHolder {
        private ImageView imgPrendra;
        private TextView colorPrenda;
        private TextView cateProducto;
        private TextView nombrePrenda;
        private TextView precioPrenda;
        private ImageView anadirCesta;
        public viewimagenesHolder(View itemView) {
            super(itemView);
            imgPrendra = itemView.findViewById(R.id.imgPrendra);
            colorPrenda = itemView.findViewById(R.id.colorPrenda);
            cateProducto = itemView.findViewById(R.id.cateProducto);
            nombrePrenda = itemView.findViewById(R.id.nombrePrenda);
            precioPrenda = itemView.findViewById(R.id.precioPrenda);
            anadirCesta = itemView.findViewById(R.id.anadirCesta);
        }
    }

}