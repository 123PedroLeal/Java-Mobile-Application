package com.example.real_food.Vistas.Sucursales;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.real_food.Adaptadores.SucursalAdaptador;
import com.example.real_food.BaseDeDatos.BaseDatos;
import com.example.real_food.BaseDeDatos.BaseDatosFireBase;
import com.example.real_food.Entidades.Sucursal;
import com.example.real_food.R;
import com.example.real_food.Servicios.ServicioSucursales;

import java.util.ArrayList;

public class Catalogo_Sucursales extends AppCompatActivity
{
    private BaseDatos baseDatos;
    private BaseDatosFireBase baseDatosFireBase;
    private ServicioSucursales servicioSucursales;
    private ListView ListaSucursales;
    private SucursalAdaptador sucursalAdaptador;
    private ArrayList<Sucursal> arraySucursales;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_catalogo);

        arraySucursales= new ArrayList<>();
        ListaSucursales = (ListView) findViewById(R.id.ListaSucursales);
        try
        {
            baseDatos = new BaseDatos(this);
            baseDatosFireBase = new BaseDatosFireBase();
            // Instruccion para leer los datos.
            servicioSucursales = new ServicioSucursales();

            //Lista de Productos en Base datos local.
            //arrayProductos = servicioProductos.cursorToArray(baseDatos.getProductos());
        }
        catch (Exception e)
        {
            Log.e("BaseDeDatos",e.toString());
        }

        //Lista de productos en local.
        //ListaProductos = (ListView) findViewById(R.id.ListaProductos);

        sucursalAdaptador = new SucursalAdaptador(this,arraySucursales);
        ListaSucursales.setAdapter(sucursalAdaptador);

        //Lista de productos en FireBase.
        baseDatosFireBase.getSucursales(sucursalAdaptador, arraySucursales);
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
                Intent intent = new Intent(getApplicationContext(), FormularioSucursales.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}