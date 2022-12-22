package com.example.real_food.Entidades;

import java.util.UUID;

public class Asesor
{
    private String Id;
    private String Nombre;
    private String Calificacion;
    private String Area;
    private String Imagen;

    // Constructor para la base de datos local.
    public Asesor(String id, String nombre, String calificacion, String area, String imagen)
    {
        this.Id = id;
        this.Nombre = nombre;
        this.Calificacion = calificacion;
        this.Area = area;
        this.Imagen = imagen;
    }

    //Constructor temporal para Firebase.
    public Asesor(String nombre, String calificacion, String area, String imagen)
    {
        this.Id = UUID.randomUUID().toString();
        this.Nombre = nombre;
        this.Calificacion = calificacion;
        this.Area = area;
        this.Imagen = imagen;
    }

    public String getId()
    {
        return Id;
    }

    public void setId(String id)
    {
        Id = id;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCalificacion() {return Calificacion;}

    public void setCalificacion(String calificacion) {Calificacion = calificacion;}

    public String getArea() {return Area;}

    public void setArea(String area) {Area = area;}
}
