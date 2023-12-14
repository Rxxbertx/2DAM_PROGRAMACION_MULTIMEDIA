package com.rsoftware.practica7;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class PeliculaFragment extends Fragment {

    Pelicula pelicula;
    ImageView img;
    TextView nombre;
    TextView anio;
    TextView actor;
    TextView director;
    TextView sinopsis;
    RatingBar puntuacion;


    public PeliculaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pelicula = (Pelicula) getArguments().getSerializable("pelicula");
        }
    }

    private void cargarDatos() {

        actor.setText(pelicula.getActor());
        nombre.setText(pelicula.getTitulo());
        anio.setText(pelicula.getAnio());
        puntuacion.setRating(pelicula.getValoracion());
        director.setText(pelicula.getDirector());
        sinopsis.setText(pelicula.getSinopsis());
        img.setImageResource(pelicula.getIdFoto());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pelicula, container, false);
        img = view.findViewById(R.id.imageView);
        nombre = view.findViewById(R.id.textViewNombre);
        anio = view.findViewById(R.id.textViewAnio);
        puntuacion = view.findViewById(R.id.ratingBar);
        actor = view.findViewById(R.id.textViewActores);
        director = view.findViewById(R.id.textViewDirectores);
        sinopsis = view.findViewById(R.id.textViewSinopsis);
        cargarDatos();
        return view;
    }


    private interface ConnectionActivityFragment{}

}
