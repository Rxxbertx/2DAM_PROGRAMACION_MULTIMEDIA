package com.rsoftware.recyclerviewtest;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PilotoAdapter extends RecyclerView.Adapter<PilotoAdapter.VIEW_HOLDER> {


    private List<PilotoKart> pilotoKarts;

    PilotoAdapter(List<PilotoKart> pilotoKarts) {
        this.pilotoKarts = pilotoKarts;
    }


    @NonNull
    @Override
    public PilotoAdapter.VIEW_HOLDER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VIEW_HOLDER(LayoutInflater.from(parent.getContext()).inflate(R.layout.piloto_lista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PilotoAdapter.VIEW_HOLDER holder, int position) {


        holder.nombre.setText(pilotoKarts.get(position).getNombre());
        holder.posicion.setText(String.valueOf(pilotoKarts.get(position).getPosicion()));





        if (position % 2 == 0)
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFF00"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#FF0000"));



    }

    @Override
    public int getItemCount() {
        return pilotoKarts.size();
    }


    protected class VIEW_HOLDER extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView posicion;

        public VIEW_HOLDER(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textViewNombre);
            posicion = itemView.findViewById(R.id.textViewPosicion);


        }
    }


}
