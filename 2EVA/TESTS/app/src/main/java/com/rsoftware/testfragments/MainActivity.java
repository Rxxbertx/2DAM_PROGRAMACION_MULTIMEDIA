package com.rsoftware.testfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements rsFragment.OnFragmentInteractListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView,new rsFragment())
                .commit();
    }

    @Override
    public void pasarDatos(int dato) {




    }
}