package com.rsoftware.practica7.recycler;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsoftware.practica7.R;
import com.rsoftware.practica7.model.PeliculaCollection;



public class RecyclerViewPeliculas extends RecyclerView.Adapter<RecyclerViewPeliculas.PeliculaViewHolder>{


    private PeliculaCollection peliculaCollection;

    public RecyclerViewPeliculas(PeliculaCollection peliculaCollection) {


        this.peliculaCollection = peliculaCollection;


    }


    @NonNull
    @Override
    public RecyclerViewPeliculas.PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PeliculaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPeliculas.PeliculaViewHolder holder, int position) {


        // Obtener el ID del recurso drawable
        int resourceId = holder.context.getResources().getIdentifier("cars","drawable", holder.context.getPackageName());


        holder.titulo.setText(peliculaCollection.getPelicula(position).getTitulo());
        holder.foto.setImageResource(resourceId);
        holder.ratingBar.setRating(peliculaCollection.getPelicula(position).getValoracion());



    }

    @Override
    public int getItemCount() {
        return peliculaCollection.size();
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {


        private TextView titulo;
        private ImageView foto;

        private RatingBar ratingBar;

        private Context context;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.txt);
            foto = itemView.findViewById(R.id.img);
            ratingBar = itemView.findViewById(R.id.rb);

             context = itemView.getContext();




        }
    }
}
