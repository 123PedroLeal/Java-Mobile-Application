package com.example.real_food.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.real_food.Entidades.Producto;
import com.example.real_food.R;
import com.example.real_food.Vistas.Productos.Detalle_Productos;

import java.util.ArrayList;

public class ProductoAdaptador extends BaseAdapter
{
    // Declaracion del contexto y lista de productos.
    private Context context;
    private ArrayList<Producto> arrayProductos;

    // Constructor del adaptador
    public ProductoAdaptador(Context context, ArrayList<Producto> arrayProductos)
    {
        this.context = context;
        this.arrayProductos = arrayProductos;
    }

    // Instruccion para sacar la cantidad de productos que hay en la lista.
    @Override
    public int getCount()
    {
        return arrayProductos.size();
    }

    // Instruccion para obtener el producto en la posicion "position".
    @Override
    public Object getItem(int position)
    {
        return arrayProductos.get(position);
    }

    // Instruccion para obtener la posicion que ocupa el producto en dicho arreglo de productos.
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    // Instruccion para obtener informacion de la vista llamada "modelo_producto" y con ello conectar con la
    // vista3
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(R.layout.prod_model,null);

        Producto producto = arrayProductos.get(position);

        ImageView imagenproducto = (ImageView) convertView.findViewById(R.id.ImagenProducto);
        TextView nombreproducto = (TextView)convertView.findViewById(R.id.NombreProducto);
        TextView descripcionproducto = (TextView)convertView.findViewById(R.id.DescripcionProducto);
        TextView precioproducto = (TextView)convertView.findViewById(R.id.PrecioProducto);
        LinearLayout tarjetaproducto = (LinearLayout) convertView.findViewById(R.id.TarjetaProducto);

        //Bitmap bitmap = BitmapFactory.decodeByteArray(producto.getImagen(),0,producto.getImagen().length);
        //imagenproducto.setImageBitmap(bitmap);
        nombreproducto.setText(producto.getNombre());
        descripcionproducto.setText(producto.getDescription());
        precioproducto.setText(String.valueOf(producto.getPrecio()));

        //Instruccion para que al cliquear la tarjeta completa pase a la vista 3.
        tarjetaproducto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context.getApplicationContext(), Detalle_Productos.class);
                intent.putExtra("nombre",producto.getNombre());
                intent.putExtra("descripcion",producto.getDescription());
                intent.putExtra("precio",producto.getPrecio());
                intent.putExtra("id",producto.getId());

                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
