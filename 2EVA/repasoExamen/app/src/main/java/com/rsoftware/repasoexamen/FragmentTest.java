package com.rsoftware.repasoexamen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTest extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListenerActivity listenerActivity;


    private  Button button;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        listenerActivity = (MainActivity)context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        button =  view.findViewById(R.id.buttonFrag);

        button.setOnClickListener((v)->{

            listenerActivity.enviarDatosListener();

        });

        return view;


    }

    public void mandarDatos() {

        Toast.makeText(getContext(), "DATOS RECIBIDOS DEL ACTIVITY", Toast.LENGTH_SHORT).show();

    }


    public  interface  ListenerActivity {



        void enviarDatosListener();




    }

}