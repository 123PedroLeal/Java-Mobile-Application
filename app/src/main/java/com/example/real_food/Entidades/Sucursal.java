package com.example.real_food.Entidades;

import java.util.UUID;

public class Sucursal
{
    private String Id;
    private String Imagen;
    private String Nombre;
    private String Ubicacion;

    // Constructor para la base de datos local.
    public Sucursal(String id, String nombre,String ubicacion,String imagen)
    {
        this.Id = id;
        this.Nombre = nombre;
        this.Ubicacion = ubicacion;
        this.Imagen = imagen;
    }

    //Constructor temporal para Firebase.
    public Sucursal(String nombre, String ubicacion, String imagen)
    {
        this.Id = UUID.randomUUID().toString();
        this.Nombre = nombre;
        this.Ubicacion = ubicacion;
        this.Imagen = imagen;
        ;
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

    public String getUbicacion()
    {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion)
    {
        Ubicacion = ubicacion;
    }
}
