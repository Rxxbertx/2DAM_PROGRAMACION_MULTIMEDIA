package com.rsoftware.practica8.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsoftware.practica8.R;
import com.rsoftware.practica8.MainActivity;
import com.rsoftware.practica8.model.Pelicula;
import com.rsoftware.practica8.model.PeliculaCollection;

import java.util.List;


public class RecyclerViewPeliculas extends RecyclerView.Adapter<RecyclerViewPeliculas.PeliculaViewHolder>{


    private final List<Pelicula> peliculaCollection;
    private OnClickListenerRecyclerView onClickListenerRecyclerView;

    public RecyclerViewPeliculas(List<Pelicula> pelis, Context parent) {


        this.peliculaCollection = pelis;

        if (parent instanceof OnClickListenerRecyclerView)
            onClickListenerRecyclerView = (MainActivity)parent;


    }


    @NonNull
    @Override
    public RecyclerViewPeliculas.PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PeliculaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPeliculas.PeliculaViewHolder holder, int position) {


        // Obtener el ID del recurso drawable
        int resourceId = holder.context.getResources().getIdentifier(peliculaCollection.get(position).getFoto(),"drawable", holder.context.getPackageName());


        holder.titulo.setText(peliculaCollection.get(position).getTitulo());
        holder.foto.setImageResource(resourceId);
        holder.ratingBar.setRating(peliculaCollection.get(position).getValoracion());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    onClickListenerRecyclerView.verDetallesPelicula(peliculaCollection.get(holder.getAdapterPosition()));

            }
        });


    }

    @Override
    public int getItemCount() {
        return peliculaCollection.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {


        private final TextView titulo;
        private final ImageView foto;

        private final RatingBar ratingBar;

        private final LinearLayout layout;
        private final Context context;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.txt);
            foto = itemView.findViewById(R.id.img);
            ratingBar = itemView.findViewById(R.id.rb);

            layout=itemView.findViewById(R.id.layout);
            context = itemView.getContext();




        }
    }


    public  interface  OnClickListenerRecyclerView{


        void verDetallesPelicula(Pelicula positionAdapter);



    }
}