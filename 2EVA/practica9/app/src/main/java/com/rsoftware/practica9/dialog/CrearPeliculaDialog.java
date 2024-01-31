package com.rsoftware.practica9.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.rsoftware.practica9.R;
import com.rsoftware.practica9.db.PeliculasDAO;
import com.rsoftware.practica9.model.Pelicula;
import com.rsoftware.practica9.model.PeliculaCollection;

public class CrearPeliculaDialog extends DialogFragment {

    PeliculaCollection peliculaCollection = new PeliculaCollection();
    CrearPeliculaDialogListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CrearPeliculaDialogListener){
            listener = (CrearPeliculaDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()+" debe implementar CrearPeliculaDialogListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getActivity().getLayoutInflater();

        View view = inflater.inflate(com.rsoftware.practica9.R.layout.dialog_crear_pelicula,null);

        builder.setView(view)
                .setTitle("Crear Pelicula")
                .setNeutralButton("Crear", null)
                .setNegativeButton("Cancelar", (dialog, which) -> dismiss());


        AlertDialog dialog = builder.create();

        // Establecemos el OnClickListener personalizado después de que se haya mostrado el diálogo
        dialog.setOnShowListener(dialogInterface -> {

            Button button = ((AlertDialog) dialogInterface).getButton(AlertDialog.BUTTON_NEUTRAL);

            button.setOnClickListener(view1 -> {
                // Aquí es donde llamamos a nuestro método para crear la película
                crearPelicula(view);
            });
        });

        return dialog;


    }

    private void crearPelicula(View view) {

        if (comprobarCampos(view)){

            listener.crearPeliculaListener(peliculaCollection.size()-1);
            dismiss();
        }


    }

    private boolean comprobarCampos(View view) {

        EditText titulo = view.findViewById(com.rsoftware.practica9.R.id.titulo);
        EditText anio = view.findViewById(com.rsoftware.practica9.R.id.anio);
        EditText actor = view.findViewById(com.rsoftware.practica9.R.id.actores);
        EditText director = view.findViewById(com.rsoftware.practica9.R.id.directores);
        EditText sinopsis = view.findViewById(com.rsoftware.practica9.R.id.sinopsis);
        Spinner imagen = view.findViewById(R.id.cbxImagen);

        if (titulo.getText().toString().isEmpty() || anio.getText().toString().isEmpty() || actor.getText().toString().isEmpty() || director.getText().toString().isEmpty() || sinopsis.getText().toString().isEmpty() || imagen.getSelectedItem()==null)
        {

            Toast.makeText(getContext(), getString(R.string.rellene_todos_los_campos), Toast.LENGTH_SHORT).show();

        } else {

            Pelicula pelicula = new Pelicula(
                    false,
                    imagen.getSelectedItem().toString(),
                    titulo.getText().toString(),
                    anio.getText().toString(),
                    actor.getText().toString(),
                    director.getText().toString(),
                    sinopsis.getText().toString(),
                    0.0f);


            PeliculasDAO.CreatePelicula(pelicula);



            return true;
        }


        return false;
    }

    public interface CrearPeliculaDialogListener{
        void crearPeliculaListener(int position);
    }
}
