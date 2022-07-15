package com.example.dalisaapp.venta.dashclient.pedido.view.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;
import com.example.dalisaapp.venta.dashclient.categoria.view.adapter.CategoriaAdapter;
import com.example.dalisaapp.venta.dashclient.pagar.model.CabBoleta;
import com.example.dalisaapp.zUtilidad.model.OnItemObjectListenerLey;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {


    private List<CabBoleta> lisBoleta;
    private OnItemObjectListenerLey<CabBoleta> onItemObjectListener;

    public PedidoAdapter(List<CabBoleta> lisBoleta) {
        this.lisBoleta = lisBoleta;
    }

    @NonNull
    @Override
    public PedidoAdapter.PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido, parent, false);
        return new PedidoAdapter.PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoAdapter.PedidoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.totalMonto.setText("S/."+lisBoleta.get(position).getImporteTotal());
         holder.estado.setText(lisBoleta.get(position).getEstado());
        holder.fecha.setText(lisBoleta.get(position).getFechaGenerada());
        if(lisBoleta.get(position).getEstado().equalsIgnoreCase("Facturado") ){

            Drawable img  =  holder.imageView.getContext().getResources().getDrawable(R.drawable.cuenta);
            holder.imageView.setImageDrawable(img);
            holder.estado.setTextColor(Color.GREEN);
        }else{
            Drawable img  =  holder.imageView.getContext().getResources().getDrawable(R.drawable.reloj);
            holder.imageView.setImageDrawable(img);
            holder.estado.setTextColor(Color.LTGRAY);
        }
     /*   holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemObjectListener.onClickListenerLeyCustom(lisBoleta.get(position));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return lisBoleta.size();
    }


    public void setOnItemListener(OnItemObjectListenerLey<CabBoleta> onItemObjectListener) {
        this.onItemObjectListener = onItemObjectListener;
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private TextView totalMonto;
        private ImageView imageView;
        private TextView estado;
        private TextView fecha;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            totalMonto = itemView.findViewById(R.id.totalMonto);
            estado = itemView.findViewById(R.id.estado);
            imageView = itemView.findViewById(R.id.imageView);
            fecha = itemView.findViewById(R.id.fecha);
        }

        @Override
        public void onClick(View view) {
            System.out.println("click categoria");
        }
    }



}