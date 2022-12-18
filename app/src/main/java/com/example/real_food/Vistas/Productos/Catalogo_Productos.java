package com.example.real_food.Vistas.Productos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.real_food.Adaptadores.ProductoAdaptador;
import com.example.real_food.BaseDeDatos.BaseDatos;
import com.example.real_food.BaseDeDatos.BaseDatosFireBase;
import com.example.real_food.Entidades.Producto;
import com.example.real_food.R;
import com.example.real_food.Servicios.ServicioProductos;

import java.util.ArrayList;

public class Catalogo_Productos extends AppCompatActivity
{
    private BaseDatos baseDatos;
    private BaseDatosFireBase baseDatosFireBase;
    private ServicioProductos servicioProductos;
    private ListView ListaProductos;
    private ProductoAdaptador productoAdaptador;
    private ArrayList<Producto> arrayProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prod_catalogo);

        arrayProductos = new ArrayList<>();
        ListaProductos = (ListView) findViewById(R.id.ListaProductos);
        try
        {
            baseDatos = new BaseDatos(this);
            baseDatosFireBase = new BaseDatosFireBase();
            // Instruccion para leer los datos.
            servicioProductos = new ServicioProductos();

            //Lista de Productos en Base datos local.
            //arrayProductos = servicioProductos.cursorToArray(baseDatos.getProductos());
        }
        catch (Exception e)
        {
            Log.e("BaseDeDatos",e.toString());
        }

        //Lista de productos en local.
        //ListaProductos = (ListView) findViewById(R.id.ListaProductos);

        productoAdaptador = new ProductoAdaptador(this,arrayProductos);
        ListaProductos.setAdapter(productoAdaptador);

        //Lista de productos en FireBase.
        baseDatosFireBase.getProductos(productoAdaptador, arrayProductos);
    }

    //Instruccion para que muestre el menu arriba.
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menuproductos, menu);
        return true;
    }

    // Intruccion para cuando haga click en la opcion "Agregar"
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.accionagregar:
                Intent intent = new Intent(getApplicationContext(), FormularioProductos.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}