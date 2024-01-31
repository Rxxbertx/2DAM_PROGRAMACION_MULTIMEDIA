package com.rsoftware.practica9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsoftware.practica9.db.PeliculasDAO;
import com.rsoftware.practica9.dialog.CrearPeliculaDialog;
import com.rsoftware.practica9.model.Pelicula;
import com.rsoftware.practica9.model.PeliculaCollection;
import com.rsoftware.practica9.recycler.RecyclerViewPeliculas;

public class MainActivity extends AppCompatActivity implements PeliculaFragment.OnDatosEnviadosListener, RecyclerViewPeliculas.OnClickListenerRecyclerView, CrearPeliculaDialog.CrearPeliculaDialogListener {


    public static Context context;
    private PeliculaCollection peliculaCollection;
    View fragment;
    private boolean salidaFragment = true;
    RecyclerView recycler;

    private static boolean appStarted = false;
    private static boolean recyclerFilmsCondition = false;

    private SharedPreferences sharedPrefs;
    private MenuItem switchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.materialToolbar));
        sharedPrefs = getPreferences(MODE_PRIVATE);
        context = this;
        peliculaCollection = new PeliculaCollection();
        recycler =  findViewById(R.id.recycler);


        if (!appStarted)
        {
        cargarPeliculas();
        appStarted=true;
        }

        if (recyclerFilmsCondition) {
            mostrarPeliculasValoradas();
        } else {
            mostrarPeliculas();
        }

    }
    private void mostrarPeliculas() {


        recyclerFilmsCondition = false;
        if (comprobarFragment())fragment.setVisibility(View.GONE);

        RecyclerViewPeliculas adapter = new RecyclerViewPeliculas(peliculaCollection.getPeliculas(),this);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));



    }
    public void mostrarPeliculasValoradas() {


        recyclerFilmsCondition = true;
        if (comprobarFragment())fragment.setVisibility(View.GONE);
        recycler.setAdapter(new RecyclerViewPeliculas(peliculaCollection.getPeliculasVistas(),this));
        recycler.setLayoutManager(new LinearLayoutManager(this));


    }
    private void cargarPeliculas() {

        PeliculasDAO.ReadPeliculas();

    }
    private boolean comprobarFragment() {

        return (fragment = findViewById(R.id.fragmentContainerView)) != null;
    }
    private void crearActividad() {


        Intent intent = new Intent(this, DatosPeliculaActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);


    }
    @Override
    public void verDetallesPelicula(Pelicula pelicula,int positionAdapter) {


        getIntent().putExtra("positionAdapter",positionAdapter);
        getIntent().putExtra("pelicula",pelicula);


        salidaFragment = false;

        if (comprobarFragment()) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, PeliculaFragment.class, getIntent().getExtras()).commit();
            fragment.setVisibility(View.VISIBLE);



        } else {
            crearActividad();
        }

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        salidaFragment = savedInstanceState.getBoolean("salidaFragment");

        if (!salidaFragment) {

            verDetallesPelicula((Pelicula) getIntent().getSerializableExtra("pelicula"),getIntent().getIntExtra("positionAdapter",0));
        }

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putBoolean("salidaFragment", salidaFragment);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuapp,menu);

        switchView = menu.findItem(R.id.app_bar_switch);

        switchView.setChecked(sharedPrefs.getBoolean("nightMode",false));
        switchView.setIcon(switchView.isChecked()?R.drawable.nightmode:R.drawable.lightmode);
        AppCompatDelegate.setDefaultNightMode(switchView.isChecked()?AppCompatDelegate.MODE_NIGHT_YES:AppCompatDelegate.MODE_NIGHT_NO);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.ratedFilms)
            mostrarPeliculasValoradas();
        if (item.getItemId()==R.id.allFilms)
            mostrarPeliculas();
        if (item.getItemId()== R.id.app_bar_switch){
            item.setChecked(!item.isChecked());
            sharedPrefs.edit().putBoolean("nightMode",item.isChecked()).apply();
            item.setIcon(item.isChecked()?R.drawable.nightmode:R.drawable.lightmode);
            AppCompatDelegate.setDefaultNightMode(switchView.isChecked()?AppCompatDelegate.MODE_NIGHT_YES:AppCompatDelegate.MODE_NIGHT_NO);
            return true;
        }

        Toast.makeText(this, getString(R.string.peliculas_actualizadas_correctamente),Toast.LENGTH_LONG).show();

        return true;
    }
    @Override
    public void onPuntuacionEnviada(int positionAdapter) {

         recycler.getAdapter().notifyItemChanged(positionAdapter);

    }
    @Override
    public void onFinishCommunication() {

        if (fragment != null) findViewById(R.id.fragmentContainerView).setVisibility(View.GONE);
        salidaFragment = true;
        recycler.getAdapter().notifyDataSetChanged();

    }
    @Override
    public void crearPeliculaListener(int position) {

            recycler.getAdapter().notifyItemInserted(recycler.getAdapter().getItemCount()-1);
            recycler.getLayoutManager().scrollToPosition(recycler.getAdapter().getItemCount()-1);

    }
    public void crearPeliculaBtn(View view) {

            new CrearPeliculaDialog().show(getSupportFragmentManager(),"crearPeliculaDialog");
    }
}