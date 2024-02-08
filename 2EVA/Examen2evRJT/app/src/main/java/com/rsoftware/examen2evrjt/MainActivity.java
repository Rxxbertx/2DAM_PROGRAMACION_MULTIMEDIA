package com.rsoftware.examen2evrjt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecyclerAdapterNBA.RecyclerListener, InfoPlayerFragment.FragmentListener {

    private CheckBox este;
    private CheckBox oeste;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        cargarDB();
        cargarRecycler(findViewById(R.id.recycler));
        crearListeners();
        cargarPreferencias();

    }

    private void cargarPreferencias() {

        SharedPreferences sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE);


        oeste.setChecked(sharedPreferences.getBoolean("oeste", false));

        este.setChecked(sharedPreferences.getBoolean("este", false));

        spinner.getItemAtPosition(sharedPreferences.getInt("seleccion", 0));


    }

    private void crearListeners() {

        este = findViewById(R.id.checkBoxEste);
        oeste = findViewById(R.id.checkBoxOeste);

        spinner = findViewById(R.id.spinner);




        este.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                getSharedPreferences("preferencias", MODE_PRIVATE).edit().putBoolean("este", isChecked);


            }
        });

        oeste.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                getSharedPreferences("preferencias", MODE_PRIVATE).edit().putBoolean("oeste", isChecked);


            }
        });


    }


    private void cargarRecycler(RecyclerView recyclerView) {

        JugadorCollection jugadorCollection = new JugadorCollection();

        recyclerView.setAdapter(new RecyclerAdapterNBA(jugadorCollection.getJugadorList(), this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void cargarDB() {

        NBADao db = new NBADao(this);
        db.ReadAll();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.Salir)
            finish();

        if (item.getItemId() == R.id.mostrarEquipos)
            Toast.makeText(this, "equipos", Toast.LENGTH_SHORT).show();


        return super.onOptionsItemSelected(item);

    }

    @Override
    public void CargarJugadorListener(Jugador jugador) {


        Bundle bundle = new Bundle();
        bundle.putSerializable("jugador", jugador);

        findViewById(R.id.fragmentContainerView).setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, InfoPlayerFragment.class, bundle).commit();


    }

    @Override
    public void CerrarFragment() {

        findViewById(R.id.fragmentContainerView).setVisibility(View.INVISIBLE);

    }

    public void filtrar(View view) {

        findViewById(R.id.fragmentContainerView).setVisibility(View.INVISIBLE);

        JugadorCollection jugadorCollection = new JugadorCollection();


        List<Jugador> jugadorList = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.recycler);

        recyclerView.setAdapter(new RecyclerAdapterNBA(jugadorCollection.getJugadorList(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        if (oeste.isChecked()) {

            for (Jugador jugador : jugadorCollection.getJugadorList()
            ) {

                if (jugador.getConferencia().equals("oeste"))
                jugadorList.add(jugador);


            }

            recyclerView.setAdapter(new RecyclerAdapterNBA(jugadorList, this));

        }



        if (este.isChecked()) {

            for (Jugador jugador : jugadorCollection.getJugadorList()
            ) {

                if (jugador.getConferencia().equals("este"))
                 jugadorList.add(jugador);

            }

            recyclerView.setAdapter(new RecyclerAdapterNBA(jugadorList, this));
        }





    }
}