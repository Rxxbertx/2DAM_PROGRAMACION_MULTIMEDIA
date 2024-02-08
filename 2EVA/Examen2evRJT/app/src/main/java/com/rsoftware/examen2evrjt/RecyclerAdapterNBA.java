package com.rsoftware.examen2evrjt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterNBA extends RecyclerView.Adapter<RecyclerAdapterNBA.Holder> {


    private List<Jugador> jugadorList;
    private Context parent;

    private RecyclerListener listener;

    public RecyclerAdapterNBA(List<Jugador> jugadorList, Context parent) {
        this.jugadorList = jugadorList;
        this.parent = parent;

        listener = (RecyclerListener) parent;


    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Jugador jugador = jugadorList.get(position);

        if (position%2==0){

            holder.layout.setBackgroundColor(Color.parseColor("#00B8D4"));

        }

        holder.imageView.setImageDrawable(parent.getDrawable(parent.getResources().getIdentifier(jugador.getFoto(),"drawable",parent.getPackageName())));

        holder.nombre.setText(jugador.getNombre());
        holder.equipo.setText(jugador.getEquipo());

        holder.layout.setOnClickListener(v->{

            listener.CargarJugadorListener(jugador);

        });



    }

    @Override
    public int getItemCount() {
        return jugadorList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {


        private LinearLayout layout;

        private ImageView imageView;

        private TextView nombre;

        private TextView equipo;

        public Holder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            imageView = itemView.findViewById(R.id.imagenPlayer);
            nombre = itemView.findViewById(R.id.textViewPlayerName);
            equipo = itemView.findViewById(R.id.textViewPlayerTeam);


        }
    }



    public interface RecyclerListener{

        void CargarJugadorListener(Jugador jugador);

    }


}
