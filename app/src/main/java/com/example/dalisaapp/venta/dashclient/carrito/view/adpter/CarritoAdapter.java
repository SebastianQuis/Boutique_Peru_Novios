package com.example.dalisaapp.venta.dashclient.carrito.view.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.carrito.model.Carrito;
import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;
import com.example.dalisaapp.venta.dashclient.categoria.view.adapter.CategoriaAdapter;
import com.example.dalisaapp.zUtilidad.Util;

import java.util.List;

public class CarritoAdapter  extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {


    private List<Carrito> listaCarrito;


    public CarritoAdapter(List<Carrito> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    @NonNull
    @Override
    public CarritoAdapter.CarritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrito, parent, false);
        return new CarritoAdapter.CarritoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull CarritoViewHolder holder, int i) {
        holder.colorPrenda.setBackgroundColor(listaCarrito.get(i).getPrenda().getColorProd());
        holder.nombrePrenda.setText(listaCarrito.get(i).getPrenda().getNombre());
        holder.cant.setText(String.valueOf(listaCarrito.get(i).getCantidad()));
        holder.precioPrenda.setText(String.valueOf(listaCarrito.get(i).getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listaCarrito.size();
    }

    public void removeItem(int position) {
        System.out.println("SSSSSSSSSSS: "+listaCarrito.size()+" "+position);
        listaCarrito.remove(position);
        Util.listaCarritoProducto=listaCarrito;
        notifyItemRemoved(position);
    }


    public static class CarritoViewHolder extends RecyclerView.ViewHolder {
        private  TextView colorPrenda;
        private  TextView cateProducto;
        private  TextView nombrePrenda;
        private  TextView cant;
        private  TextView precioPrenda;


        public CarritoViewHolder(View itemView) {
            super(itemView);
            colorPrenda = itemView.findViewById(R.id.colorPrenda);
            cateProducto = itemView.findViewById(R.id.cateProducto);
            nombrePrenda = itemView.findViewById(R.id.nombrePrenda);
            cant = itemView.findViewById(R.id.cant);
            precioPrenda = itemView.findViewById(R.id.precioPrenda);
        }
    }
}
