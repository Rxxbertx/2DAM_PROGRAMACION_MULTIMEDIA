package com.roberto.minigame;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.roberto.minigame.appdata.GameManager;
import com.roberto.minigame.appdata.ModeloUsuario;
import com.roberto.minigame.appdata.Usuario;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher camerALauncher;
    private ImageView imGCamara;

    private String regex = "^[a-zA-Z]{3,}$";
    Pattern pattern = Pattern.compile(regex);


    private ModeloUsuario modeloUser;

    private Usuario usuario;

    private TextView useRName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarContenido();

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("usuario", useRName.getText());
        outState.putByteArray("imagen",obtenerImagenEnBytes());

    }

    private byte[] obtenerImagenEnBytes() {

        Drawable temp = imGCamara.getDrawable();
        Bitmap img = ((BitmapDrawable) temp).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();

    }
    private Bitmap obtenerImagenEnBitmap(byte[] imagen) {


        if (imagen != null) {
            Bitmap imagenRecuperada = BitmapFactory.decodeByteArray(imagen, 0, imagen.length);

            if (imagenRecuperada != null) {
                // Carga el Bitmap en un ImageView
                return imagenRecuperada;
            }

        }

        return ((BitmapDrawable) getDrawable(R.drawable.avatar)).getBitmap();


    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        useRName.setText(savedInstanceState.getCharSequence("usuario"));
        imGCamara.setImageBitmap(obtenerImagenEnBitmap(savedInstanceState.getByteArray("imagen")));

    }



    private void cargarContenido() {

        // Compila la expresión regular en un objeto Pattern.


        modeloUser=new ModeloUsuario();
        imGCamara = findViewById(R.id.avatar);

        useRName= findViewById(R.id.editTextNombre);
        camerALauncher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap result) {
                if (result != null) {
                    imGCamara.setImageBitmap(result);
                }
            }
        });
    }

    public void openCamera(View view) {

        camerALauncher.launch(null);

    };

    public void comprobarDatos(View view) {

        // Crea un objeto Matcher para buscar coincidencias en el texto de entrada.
        Matcher matcher = pattern.matcher(String.valueOf(useRName.getText()));

        if (matcher.find()){
            usuario = new Usuario(imGCamara, String.valueOf(useRName.getText()),null);
            GameManager.usuarioActual = usuario;
            modeloUser.addUsuario(usuario);
        }else{
            Toast.makeText(this,"INTRODUCE UN USUARIO VALIDO (sin numeros y mas de 3 letras)",Toast.LENGTH_LONG).show();
            return;
        }

        modeloUser.addUsuario(new Usuario(imGCamara,"pepe", 10));

        Intent intent = new Intent(this, NivelUnoActivity.class);
        startActivity(intent);
        this.finish();

    }

    public void puntuacionClick(View view){


        Intent intent = new Intent(this, PuntuacionActivity.class);


        startActivity(intent);
        this.finish();

    }


    public void salir(View view) {

        finish();
    }
}