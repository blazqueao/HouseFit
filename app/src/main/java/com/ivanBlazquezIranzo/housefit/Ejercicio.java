package com.ivanBlazquezIranzo.housefit;

import java.io.Serializable;

@SuppressWarnings("unused")
public class Ejercicio implements Serializable {

    private static final long serialVersionUID = 460902978983692932L;
    private String nombre;
    private String musculo;
    private int dificultad;
    private String nombreVideo;

    public Ejercicio(String nombre, String musculo, int dificultad) {
        this.nombre = nombre;
        this.musculo = musculo;
        this.dificultad = dificultad;
    }

    public Ejercicio(String nombre, String musculo, int dificultad, String nombreVideo) {
        this.nombre = nombre;
        this.musculo = musculo;
        this.dificultad = dificultad;
        this.nombreVideo = nombreVideo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getnombreVideo() {
        return nombreVideo;
    }

    public void setnombreVideo(String nombreVideo) {
        this.nombreVideo = nombreVideo;
    }
}
