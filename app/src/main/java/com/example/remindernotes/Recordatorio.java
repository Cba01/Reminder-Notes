package com.example.remindernotes;

import java.io.Serializable;
import java.text.DateFormat;

public class Recordatorio implements Serializable {

    String id;
    String titulo;
    String descripcion;
    String fechaInicio;
    String fechaTermino;
    String hora;
    int tipo;


    public Recordatorio(String id, String titulo, String descripcion, String fechaInicio, String fechaTermino, String hora, int tipo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.hora = hora;
        this.tipo = tipo;
    }

    public Recordatorio() {

    }

    public Recordatorio(String titulo, String descripcion, String fechaInicio, String fechaTermino, String hora, int tipo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.hora = hora;
        this.tipo = tipo;
    }

    public Recordatorio(String id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Recordatorio(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return
                 titulo + '\n'+
                 descripcion +'\n'+
                  fechaInicio  +' '+
                  fechaTermino ;
    }
}
