package com.example.dalisaapp.venta.dashclient.Tienda.view.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Producto;
import com.example.dalisaapp.zUtilidad.model.OnItemObjectListenerLey;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> implements OnItemObjectListenerLey<Prenda> {


    private List<Producto> listaProducto;
    private ItemPrendaSelect itemPrendaSelect;


    public ProductoAdapter(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_producto, parent, false);
        return new ProductoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.txtCategoria.setText(listaProducto.get(position).getCategoria().getNameCategoria());
        holder.prendaRecly.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),1, RecyclerView.HORIZONTAL,false));
        var dapter=new PrendaAdapter(listaProducto.get(position).getLista());
        holder.prendaRecly.setAdapter(dapter);
        dapter.setOnItemObjectListenerLey(this);

    }



    @Override
    public int getItemCount() {
        return listaProducto.size();
    }

    public void setItemPrendaSelect(ItemPrendaSelect itemPrendaSelect) {
        this.itemPrendaSelect = itemPrendaSelect;
    }

    @Override
    public void onClickListenerLeyCustom(Prenda prenda) {
        itemPrendaSelect.onclickPrenda(prenda);
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCategoria;
        private RecyclerView prendaRecly;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.nomCategoria);
            prendaRecly = itemView.findViewById(R.id.prendaRecyclerView);
        }
    }

    public interface  ItemPrendaSelect{
        void onclickPrenda(Prenda prenda);
    }

}