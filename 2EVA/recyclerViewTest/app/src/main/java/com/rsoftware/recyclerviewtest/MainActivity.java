package com.rsoftware.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recycler;
    List<PilotoKart> pilotos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.recycler1);


        PilotoKart pilotoKart1 = new PilotoKart("Roberto1",1);
        PilotoKart pilotoKart2 = new PilotoKart("Roberto2",2);
        PilotoKart pilotoKart3 = new PilotoKart("Roberto3",3);
        PilotoKart pilotoKart4 = new PilotoKart("Roberto4",4);
        PilotoKart pilotoKart5 = new PilotoKart("Roberto5",5);
        PilotoKart pilotoKart6 = new PilotoKart("Roberto6",6);

        pilotos = new ArrayList<>(Arrays.asList(pilotoKart1,pilotoKart2,pilotoKart3,pilotoKart4,pilotoKart5,pilotoKart6));

        recycler.setAdapter(new PilotoAdapter(pilotos));
        recycler.setLayoutManager(new LinearLayoutManager(this));

        recycler.setOnClickListener(v -> {

            Toast.makeText(this, "Se ha añadido un piloto", Toast.LENGTH_SHORT).show();

        });



    }
}