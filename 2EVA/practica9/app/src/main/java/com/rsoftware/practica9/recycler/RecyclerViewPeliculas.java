package com.rsoftware.practica9.recycler;

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

import com.rsoftware.practica9.MainActivity;
import com.rsoftware.practica9.R;
import com.rsoftware.practica9.model.Pelicula;

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

        if (peliculaCollection.get(position).getVista()){
            holder.visto.setVisibility(View.VISIBLE);
        }else {
            holder.visto.setVisibility(View.INVISIBLE);
        }

        holder.titulo.setText(peliculaCollection.get(position).getTitulo());
        holder.foto.setImageResource(resourceId);
        holder.ratingBar.setRating(peliculaCollection.get(position).getValoracion());
        holder.director.setText(peliculaCollection.get(position).getDirector());
        holder.layout.setOnClickListener(v -> onClickListenerRecyclerView.verDetallesPelicula(peliculaCollection.get(holder.getAdapterPosition()),holder.getAdapterPosition()));


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

        private final ImageView visto;

        private final TextView director;
        private final Context context;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.txt);
            foto = itemView.findViewById(R.id.img);
            ratingBar = itemView.findViewById(R.id.rb);
            director = itemView.findViewById(R.id.txtDirector);
            visto = itemView.findViewById(R.id.visto);
            layout=itemView.findViewById(R.id.layout);
            context = itemView.getContext();




        }
    }


    public  interface  OnClickListenerRecyclerView{


        void verDetallesPelicula(Pelicula positionAdapter,int position);



    }
}
