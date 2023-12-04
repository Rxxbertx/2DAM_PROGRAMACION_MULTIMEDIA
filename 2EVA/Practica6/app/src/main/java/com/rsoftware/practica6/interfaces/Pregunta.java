package com.rsoftware.practica6.interfaces;

import android.os.Bundle;
import android.view.View;

import com.rsoftware.practica6.R;

public interface Pregunta {

    void iniciarFragment();
    void btnAtras(View view);
    void btnNext(View view);
     String respuestaCorrecta();
    String respuestaUsuario();
 Bundle actualizarBundle();




}
