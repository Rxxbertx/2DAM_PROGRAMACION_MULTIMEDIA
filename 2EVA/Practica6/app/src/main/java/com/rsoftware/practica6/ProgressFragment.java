package com.rsoftware.practica6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressFragment extends Fragment {

     static final String PARAM_PROGRESO = "PROGRESO";
     static final String PARAM_PUNTUACION = "PUNTUACION";

    private int progreso;
    private int puntuacion;

    public ProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            progreso = getArguments().getInt(PARAM_PROGRESO);
            puntuacion = getArguments().getInt(PARAM_PUNTUACION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        ProgressBar pb = view.findViewById(R.id.progressBar);
        pb.setProgress(progreso);
        TextView tv = view.findViewById(R.id.textViewPuntuacion);
        tv.setText(String.valueOf(puntuacion));
        TextView tv1 = view.findViewById(R.id.textProgress);
        tv1.setText(String.valueOf(progreso));
        return view;
    }

}