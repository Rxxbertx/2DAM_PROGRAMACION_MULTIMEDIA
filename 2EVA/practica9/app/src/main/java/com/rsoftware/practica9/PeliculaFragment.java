package com.rsoftware.practica9;

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
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.rsoftware.practica9.db.PeliculasDAO;
import com.rsoftware.practica9.model.Pelicula;
import com.rsoftware.practica9.model.PeliculaCollection;

public class PeliculaFragment extends Fragment implements View.OnClickListener {

    private Pelicula pelicula;
    private ImageView img;
    private TextView nombre;
    private TextView anio;
    private TextView actor;
    private TextView director;
    private SwitchCompat vista;
    private TextView sinopsis;
    private RatingBar puntuacion;

    private Button backBtn;

    private Button rmvBtn;
    private int positionAdapter;

    public PeliculaFragment() {
        // Constructor vacío no es necesario
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            PeliculaCollection temp = new PeliculaCollection();


            positionAdapter = getArguments().getInt("positionAdapter");
            pelicula = (Pelicula) getArguments().getSerializable("pelicula");
            pelicula = temp.getPelicula(temp.getPeliculas().indexOf(pelicula));


        }


    }

    private void cargarDatos() {
        if (pelicula != null) {

            vista.setChecked(pelicula.getVista());
            actor.setText(pelicula.getActor());
            nombre.setText(pelicula.getTitulo());
            anio.setText(pelicula.getAnio());
            puntuacion.setRating(pelicula.getValoracion());
            director.setText(pelicula.getDirector());
            sinopsis.setText(pelicula.getSinopsis());
            img.setImageResource(getResources().getIdentifier(pelicula.getFoto(), "drawable", getContext().getPackageName()));
            backBtn.setOnClickListener(this);
            rmvBtn.setOnClickListener(this);

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
        backBtn = view.findViewById(R.id.backBtn);
        rmvBtn = view.findViewById(R.id.rmvBtn);
        vista = view.findViewById(R.id.switch1);
        cargarDatos(); // Llama a cargarDatos() aquí si deseas que los datos se carguen automáticamente al crear el fragmento.
        crearListeners(puntuacion, vista);

        return view;
    }

    private void crearListeners(RatingBar puntuacion, SwitchCompat vista) {

        puntuacion.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {

            if (fromUser) pelicula.setValoracion(rating);
            listener.onPuntuacionEnviada(positionAdapter);
            PeliculasDAO.UpdatePelicula(pelicula);


        });

        vista.setOnCheckedChangeListener((buttonView, isChecked) -> {


            pelicula.setVista(isChecked);


            PeliculasDAO.UpdatePelicula(pelicula);

        });

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.rmvBtn) {
            PeliculasDAO.DeletePelicula(pelicula);
        }
        listener.onFinishCommunication();


    }


    // Interfaz para comunicarse con la actividad
    public interface OnDatosEnviadosListener {
        void onPuntuacionEnviada(int positionAdapter);

        void onFinishCommunication();
    }

    private OnDatosEnviadosListener listener;

}
