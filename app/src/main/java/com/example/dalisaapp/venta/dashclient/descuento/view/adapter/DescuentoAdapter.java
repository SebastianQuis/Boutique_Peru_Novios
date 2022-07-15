package com.example.dalisaapp.venta.dashclient.descuento.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.descuento.model.Descuento;

import java.util.List;

public class DescuentoAdapter extends RecyclerView.Adapter<DescuentoAdapter.DescuentoViewHolder> {

     private List<Descuento> list;

    public DescuentoAdapter(List<Descuento> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public DescuentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_descuento, parent, false);
        return new DescuentoAdapter.DescuentoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DescuentoViewHolder holder, int position) {
      holder.imgView= list.get(position).getImg();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DescuentoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgView;

        public DescuentoViewHolder(View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.idImgView);
        }
    }
}
