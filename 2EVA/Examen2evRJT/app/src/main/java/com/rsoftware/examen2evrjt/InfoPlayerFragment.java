package com.rsoftware.examen2evrjt;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class InfoPlayerFragment extends Fragment {


    private Jugador jugador;
private JugadorCollection jugadorCollection = new JugadorCollection();

private TextView nombre;
private TextView equipo;
private TextView conferencia;
private TextView posicion;
private TextView dorsal;

private ImageView foto;

private FragmentListener listener;

private Button button ;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listener = (FragmentListener)context;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            jugador = (Jugador) getArguments().getSerializable("jugador");

            jugador = jugadorCollection.getJugadorList().get(jugadorCollection.getJugadorList().indexOf(jugador));





        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info_player, container, false);


         equipo = view.findViewById(R.id.textViewPlayerTeam);
        nombre = view.findViewById(R.id.textViewPlayerName);
        conferencia =  view.findViewById(R.id.textViewPlayerConferencia);
        posicion =  view.findViewById(R.id.textViewPlayerPosition);
        dorsal =  view.findViewById(R.id.textViewPlayerNumber);
        foto = view.findViewById(R.id.imageViewPlayer);

        button = view.findViewById(R.id.buttonCerrar);
        equipo.setText(jugador.getEquipo());
        nombre.setText(jugador.getNombre());
        conferencia.setText("(Conferencia "+jugador.getConferencia()+")");



        posicion.setText(jugador.getPosicion_1());
        if (jugador.getPosicion_2()!=null)
            posicion.setText(posicion.getText()+" "+jugador.getPosicion_2());

        if (jugador.getPosicion_3()!=null)
            posicion.setText(posicion.getText()+" "+jugador.getPosicion_3());


        dorsal.setText(String.valueOf(jugador.getDorsal()));

        foto.setImageDrawable(getContext().getDrawable(getContext().getResources().getIdentifier(jugador.getFoto(),"drawable", getContext().getPackageName())));


        button.setOnClickListener(v->{


            listener.CerrarFragment();
            try {
                finalize();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }


        });




        return view;

    }



    public interface FragmentListener{

        void CerrarFragment();

    }

}