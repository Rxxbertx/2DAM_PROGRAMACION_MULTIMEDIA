package com.rsoftware.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PilotoAdapter extends RecyclerView.Adapter<PilotoAdapter.Viewholder> {


    List<PilotoKart> pilotoKarts;


    public PilotoAdapter(List<PilotoKart> pilotoKarts) {
        this.pilotoKarts = pilotoKarts;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//aqui se infla la vista pero sin datos
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.piloto_lista,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        PilotoKart pilotoKart = pilotoKarts.get(position);
        holder.nombreTxt.setText(pilotoKart.getNombre());
        holder.posicionTxt.setText(String.valueOf(pilotoKart.getPosicion()));

    }


    @Override
    public int getItemCount() {
        return pilotoKarts.size();
    }





    //clase donde coges los elementos creado en xml y le decimos que el elemento a del xml es el elemento b del objeto
    public  class Viewholder extends RecyclerView.ViewHolder {

        public TextView nombreTxt;
        public TextView posicionTxt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            nombreTxt = itemView.findViewById(R.id.textViewNombre);
            posicionTxt = itemView.findViewById(R.id.textViewPosicion);

        }




    }


}
