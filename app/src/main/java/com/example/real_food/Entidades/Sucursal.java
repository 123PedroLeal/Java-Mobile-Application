package com.example.real_food.Entidades;

import java.util.UUID;

public class Sucursal
{
    private String Id;
    private String Nombre;
    private Double Latitud;
    private Double Longitud;
    private String Imagen;

    // Constructor para la base de datos local.
    public Sucursal(String id, String nombre, Double latitud, Double longitud,String imagen)
    {
        this.Id = id;
        this.Nombre = nombre;
        this.Latitud = latitud;
        this.Longitud = longitud;
        this.Imagen = imagen;
    }

    //Constructor temporal para Firebase.
    public Sucursal(String nombre,Double latitud, Double longitud,String imagen)
    {
        this.Id = UUID.randomUUID().toString();
        this.Nombre = nombre;
        this.Latitud = latitud;
        this.Longitud = longitud;
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

    public Double getLatitud()
    {
        return Latitud;
    }

    public void setLatitud(Double latitud)
    {
        Latitud = latitud;
    }

    public Double getLongitud()
    {
        return Longitud;
    }

    public void setLongitud(Double longitud)
    {
        Longitud = longitud;
    }
}
