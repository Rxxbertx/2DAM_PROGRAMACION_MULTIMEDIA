package com.rsoftware.repasoexamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentTest.ListenerActivity {

    private View view;
    private boolean isButtonBlue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarFragment();
        setSupportActionBar(findViewById(R.id.toolbar));
        registerForContextMenu(findViewById(R.id.buttonAct));
    }

    private void cargarFragment() {

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragment,FragmentTest.class,null).commit();

    }

    @Override
    public void enviarDatosListener() {

        Toast.makeText(this, "DATOS RECIBIDOS DEL FRAGMENT", Toast.LENGTH_SHORT).show();
    }


    public void clicAct(View view) {


        FragmentTest fragment = (FragmentTest) getSupportFragmentManager().findFragmentById(R.id.fragment);

        if (fragment != null) {
            fragment.mandarDatos();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        if (item.getItemId()==R.id.btnmenu)
            Toast.makeText(this, "CLIC DEL MENU", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        view = v;
        getMenuInflater().inflate(R.menu.context,menu);


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.btncontext) {

            if (view instanceof Button) {
                Button button = (Button) view;
                if (isButtonBlue) {
                    button.setBackgroundColor(Color.RED); // Cambia el color a rojo si el botón es azul
                    Toast.makeText(this, "color cambiado a rojo", Toast.LENGTH_SHORT).show();
                } else {
                    button.setBackgroundColor(Color.BLUE); // Cambia el color a azul si el botón no es azul
                    Toast.makeText(this, "color cambiado a azul", Toast.LENGTH_SHORT).show();
                }
                isButtonBlue = !isButtonBlue; // Actualiza el estado del botón
            }

        }

        return super.onContextItemSelected(item);
    }
}