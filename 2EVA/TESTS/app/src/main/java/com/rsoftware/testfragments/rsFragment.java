package com.rsoftware.testfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractListener listener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public rsFragment() {
        // Required empty public constructor
    }
    
    public interface OnFragmentInteractListener{

        void pasarDatos(int dato);

    }



    public static rsFragment newInstance(String param1, String param2) {
        rsFragment fragment = new rsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            try {
                listener= MainActivity.class.newInstance();
                listener.pasarDatos();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (java.lang.InstantiationException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rs, container, false);
    }
}