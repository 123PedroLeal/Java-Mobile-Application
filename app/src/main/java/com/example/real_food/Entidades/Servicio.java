package com.example.real_food.Entidades;

import java.util.UUID;

public class Servicio
{
    private String Id;
    private String Imagen;
    private String Nombre;
    private String Description;
    private int Precio;

    // Constructor para la base de datos local.
    public Servicio(String id, String nombre, String description, int precio, String imagen)
    {
        this.Id = id;
        this.Nombre = nombre;
        this.Description = description;
        this.Precio = precio;
        this.Imagen = imagen;
    }

    //Constructor temporal para Firebase.
    public Servicio(String nombre, String description, int precio, String imagen)
    {
        this.Id = UUID.randomUUID().toString();
        this.Nombre = nombre;
        this.Description = description;
        this.Precio = precio;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }
}
