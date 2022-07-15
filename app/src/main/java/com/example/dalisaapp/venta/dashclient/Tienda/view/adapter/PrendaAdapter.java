package com.example.dalisaapp.venta.dashclient.Tienda.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.zUtilidad.model.OnItemObjectListenerLey;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PrendaAdapter extends RecyclerView.Adapter<PrendaAdapter.PrendaViewHolder> {


    private List<Prenda> listaPrenda;
    private OnItemObjectListenerLey<Prenda> onItemObjectListenerLey;
    public PrendaAdapter(List<Prenda> listaPrenda) {
        this.listaPrenda = listaPrenda;
    }

    @NonNull
    @Override
    public PrendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_prenda_adapter, parent, false);
        return new PrendaViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull PrendaViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.cateProducto.setText(listaPrenda.get(position).getNombre());
        holder.nombrePrenda.setText(listaPrenda.get(position).getDescripcion());
        holder.precioPrenda.setText("S/."+listaPrenda.get(position).getPrecio());
        holder.colorPrenda.setBackgroundColor(listaPrenda.get(position).getColorProd());
        try {
            Picasso.get().load(listaPrenda.get(position).getFoto()).into( holder.imgPrendra);
        } catch (Exception e) {

            e.printStackTrace();
        }
        holder.anadirCesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemObjectListenerLey.onClickListenerLeyCustom(listaPrenda.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPrenda.size();
    }

    public void setOnItemObjectListenerLey(OnItemObjectListenerLey<Prenda> onItemObjectListenerLey) {
        this.onItemObjectListenerLey = onItemObjectListenerLey;
    }

    public static class PrendaViewHolder extends RecyclerView.ViewHolder {
        private TextView cateProducto;
        private TextView nombrePrenda;
        private TextView precioPrenda;
        private TextView colorPrenda;
        private ImageView imgPrendra;
        private ImageView anadirCesta;

        public PrendaViewHolder(View itemView) {
            super(itemView);
            cateProducto = itemView.findViewById(R.id.cateProducto);
            nombrePrenda = itemView.findViewById(R.id.nombrePrenda);
            precioPrenda = itemView.findViewById(R.id.precioPrenda);
            colorPrenda = itemView.findViewById(R.id.colorPrenda);
            imgPrendra = itemView.findViewById(R.id.imgPrendra);
            anadirCesta = itemView.findViewById(R.id.anadirCesta);
        }
    }

}
