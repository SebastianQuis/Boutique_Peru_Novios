package com.example.dalisaapp.venta.dashclient.categoria.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dalisaapp.R;
import com.example.dalisaapp.venta.dashclient.Tienda.model.Prenda;
import com.example.dalisaapp.venta.dashclient.categoria.model.Categoria;
import com.example.dalisaapp.venta.dashclient.categoria.model.filtrar;
import com.example.dalisaapp.zUtilidad.model.LeyListenerItemCustom;
import com.example.dalisaapp.zUtilidad.model.OnItemObjectListenerLey;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {


    private List<Categoria> listCategory;
    private OnItemObjectListenerLey<Categoria> onItemObjectListener;

    public CategoriaAdapter(List<Categoria> listaCagoria) {
        this.listCategory = listaCagoria;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_categoria, parent, false);
        return new CategoriaAdapter.CategoriaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameCateogoria.setText(listCategory.get(position).getNameCategoria());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemObjectListener.onClickListenerLeyCustom(listCategory.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategory.size();
    }


    public void setOnItemListener(OnItemObjectListenerLey<Categoria> onItemObjectListener) {
        this.onItemObjectListener = onItemObjectListener;
    }

    public static class CategoriaViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private TextView nameCateogoria;

        public CategoriaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameCateogoria = itemView.findViewById(R.id.idNameCategory);
        }

        @Override
        public void onClick(View view) {
            System.out.println("click categoria");
        }
    }



}