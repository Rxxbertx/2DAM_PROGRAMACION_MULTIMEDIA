package com.rsoftware.practica7;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rsoftware.practica7.model.Pelicula;

public class PeliculaFragment extends Fragment implements View.OnClickListener {

    private Pelicula pelicula;
    private ImageView img;
    private TextView nombre;
    private TextView anio;
    private TextView actor;
    private TextView director;
    private TextView sinopsis;
    private RatingBar puntuacion;

    private Button boton;

    public PeliculaFragment() {
        // Constructor vacío no es necesario
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pelicula = (Pelicula) getArguments().getSerializable("pelicula");
        }



    }

    private void cargarDatos() {
        if (pelicula != null) {
            actor.setText(pelicula.getActor());
            nombre.setText(pelicula.getTitulo());
            anio.setText(pelicula.getAnio());
            puntuacion.setRating(pelicula.getValoracion());
            director.setText(pelicula.getDirector());
            sinopsis.setText(pelicula.getSinopsis());


            View otroLayout = getLayoutInflater().inflate(R.layout.activity_main, null);

// Obtener la referencia al ImageView en el otro layout
            //ImageView imageViewEnOtroLayout = otroLayout.findViewById(pelicula.getFoto());


            //img.setImageDrawable(imageViewEnOtroLayout.getDrawable());


            boton.setOnClickListener(this);

        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


        // Asocia la actividad con el listener si implementa la interfaz
        if (context instanceof OnDatosEnviadosListener) {

            listener = (OnDatosEnviadosListener) context;
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pelicula, container, false);
        img = view.findViewById(R.id.imageView);
        nombre = view.findViewById(R.id.textViewNombre);
        anio = view.findViewById(R.id.textViewAnio);
        puntuacion = view.findViewById(R.id.ratingBar);
        actor = view.findViewById(R.id.textViewActores);
        director = view.findViewById(R.id.textViewDirectores);
        sinopsis = view.findViewById(R.id.textViewSinopsis);
        boton=view.findViewById(R.id.elevatedButton);
        cargarDatos(); // Llama a cargarDatos() aquí si deseas que los datos se carguen automáticamente al crear el fragmento.
        return view;
    }

    @Override
    public void onClick(View view) {

        listener.onPuntuacionEnviada(puntuacion.getRating());


    }


    // Interfaz para comunicarse con la actividad
    public interface OnDatosEnviadosListener {
        void onPuntuacionEnviada(float puntuacion);
    }

    private OnDatosEnviadosListener listener;

}
