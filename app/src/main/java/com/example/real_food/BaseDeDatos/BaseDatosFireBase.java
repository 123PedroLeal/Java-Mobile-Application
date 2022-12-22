package com.example.real_food.BaseDeDatos;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.real_food.Adaptadores.ProductoAdaptador;
import com.example.real_food.Adaptadores.SucursalAdaptador;
import com.example.real_food.Entidades.Producto;
import com.example.real_food.Entidades.Sucursal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseDatosFireBase
{
    private FirebaseFirestore db;

    public BaseDatosFireBase()
    {
        this.db = FirebaseFirestore.getInstance();
    }

    public void InsertarProducto(Producto producto)
    {
        // Crea un producto con su nombre descripcion y precio.
        Map<String, Object> products = new HashMap<>();
        products.put("id", producto.getId());
        products.put("nombre", producto.getNombre());
        products.put("description", producto.getDescription());
        products.put("precio", producto.getPrecio());
        products.put("image", producto.getImagen());

        // Agrega el nuevo "product" a la base de datos.
        db.collection("Productos").add(producto);
    }

    public void getProductos(ProductoAdaptador productoAdaptador, ArrayList<Producto> ListaProductos)
    {
        db.collection("Productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Producto producto = new Producto
                                (
                                document.getData().get("id").toString(),
                                document.getData().get("nombre").toString(),
                                document.getData().get("description").toString(),
                                Integer.parseInt(document.getData().get("precio").toString()),
                                document.getData().get("imagen").toString()
                                );
                        ListaProductos.add(producto);
                    }
                    productoAdaptador.notifyDataSetChanged();
                }
                else
                {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }

    public void BorrarporId(String id)
    {
        db.collection("Productos").whereEqualTo("id",id)
        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                            {
                                documentSnapshot.getReference().delete();
                            }
                        }
                    }
                });
    }

    public void ActualizarProducto(Producto producto)
    {
        db.collection("Productos").whereEqualTo("id",producto.getId())
        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                            {
                                documentSnapshot.getReference().update
                                        (
                                                "nombre",producto.getNombre(),
                                                "description", producto.getDescription(),
                                                "precio",producto.getPrecio(),
                                                "imagen",producto.getImagen()
                                        );
                            }
                        }
                    }
                });

    }


    public void InsertarSucursal(Sucursal sucursal)
    {
        // Crea un producto con su nombre descripcion y precio.
        Map<String, Object> markets = new HashMap<>();
        markets.put("id", sucursal.getId());
        markets.put("nombre", sucursal.getNombre());
        markets.put("latitud",sucursal.getLatitud());
        markets.put("longitud",sucursal.getLongitud());
        markets.put("imagen", sucursal.getImagen());


        // Agrega el nuevo "markets" a la base de datos.
        db.collection("Sucursales").add(sucursal);
    }

    public void getSucursales(SucursalAdaptador sucursalAdaptador, ArrayList<Sucursal> ListaSucursales)
    {
        db.collection("Sucursales").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Sucursal sucursal = new Sucursal
                                (
                                        document.getData().get("id").toString(),
                                        document.getData().get("nombre").toString(),
                                        Double.parseDouble(document.getData().get("latitud").toString()),
                                        Double.parseDouble(document.getData().get("longitud").toString()),
                                        document.getData().get("imagen").toString()
                                );
                        ListaSucursales.add(sucursal);
                    }
                    sucursalAdaptador.notifyDataSetChanged();
                }
                else
                {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
}
