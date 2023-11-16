package com.roberto.minigame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.roberto.minigame.appdata.ModeloUsuario;
import com.roberto.minigame.appdata.Usuario;

import java.util.ArrayList;

public class PuntuacionActivity extends AppCompatActivity {

    private final ModeloUsuario modeloUsuario = new ModeloUsuario();
    private final ArrayList<Usuario> highScores=  modeloUsuario.getHighScores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);

        cargarContenido();
        controlBtnSalir();

    }


    private void controlBtnSalir() {
        // Agrega aquí la lógica para redirigir al usuario a la pantalla deseada
        // Opcional, para cerrar la actividad actual
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Agrega aquí la lógica para redirigir al usuario a la pantalla deseada
                Intent intent = new Intent(PuntuacionActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar la actividad actual
            }
        };

        getOnBackPressedDispatcher().addCallback(callback);
    }

    private void cargarContenido() {

        TableLayout miTabla = findViewById(R.id.tableLayout);

        modeloUsuario.getHighScores();

        for (Usuario user: highScores) {

            if (user.getPuntuacion()==null)continue;

            TableRow tbr = new TableRow(this);


            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 10, 0, 10);
            tbr.setGravity(Gravity.CENTER);
            tbr.setLayoutParams(layoutParams);



            // Crea un CardView redondeado.
            CardView cardView = new CardView(this);
            TableRow.LayoutParams cardLayoutParams = new TableRow.LayoutParams((int) (50* getResources().getDisplayMetrics().density), (int) (80* getResources().getDisplayMetrics().density));
            cardLayoutParams.setMargins(10, 0, 10, 0);
            cardView.setLayoutParams(cardLayoutParams);
            cardView.setCardElevation(20);
            cardView.setCardBackgroundColor(0x00FFFFFF);  // Color transparente
            cardView.setRadius(100);  // Radio para hacerlo redondo


            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(user.getAvatar().getDrawable());  // Copia la imagen desde el usuario

            imageView.setLayoutParams(new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            cardView.addView(imageView);



            TextView nombreTextView = new TextView(this);
            nombreTextView.setText(user.getNombre());

            TableRow.LayoutParams nombreLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            nombreLayoutParams.setMargins(10, 0, 10, 0);
            nombreTextView.setLayoutParams(nombreLayoutParams);

            nombreTextView.setGravity(Gravity.CENTER);


            TextView puntuacionTextView = new TextView(this);
            puntuacionTextView.setText(String.valueOf(user.getPuntuacion()));

            TableRow.LayoutParams puntuacionLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            puntuacionLayoutParams.setMargins(10, 0, 10, 0);
            puntuacionTextView.setLayoutParams(puntuacionLayoutParams);

            puntuacionTextView.setGravity(Gravity.CENTER);

            // Agrega las vistas a la fila.
            tbr.addView(cardView);
            tbr.addView(nombreTextView);
            tbr.addView(puntuacionTextView);



            miTabla.addView(tbr);

        }

    }

    public void ventanaPrincipal(View view) {

        Intent princi = new Intent(this, MainActivity.class);
        startActivity(princi);
        this.finish();


    }
}