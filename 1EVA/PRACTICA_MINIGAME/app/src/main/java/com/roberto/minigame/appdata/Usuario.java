package com.roberto.minigame.appdata;

import android.widget.ImageView;

import java.util.Objects;

public class Usuario  implements Comparable<Usuario> {

    private ImageView avatar;
    private String nombre;

    private Integer puntuacion;

    public Usuario(ImageView avatar, String nombre, Integer puntuacion) {
        this.avatar = avatar;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    public ImageView getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageView avatar) {
        this.avatar = avatar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public int compareTo(Usuario other) {

        // Primero, compara por puntuación.
        int comparacionPorPuntuacion;
        try {
            comparacionPorPuntuacion = Integer.compare(other.puntuacion,this.puntuacion);
        }catch (Exception e){
            comparacionPorPuntuacion=0;
        }
        // Si las puntuaciones son iguales, compara por nombre de usuario.
        if (comparacionPorPuntuacion == 0) {
            return this.nombre.compareTo(other.nombre);
        } else {
            return comparacionPorPuntuacion;
        }
    }
}
