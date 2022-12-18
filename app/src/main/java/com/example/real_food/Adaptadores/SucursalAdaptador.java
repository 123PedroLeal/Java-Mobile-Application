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
import com.example.real_food.Entidades.Sucursal;
import com.example.real_food.R;
import com.example.real_food.Vistas.Productos.Detalle_Productos;

import java.util.ArrayList;

public class SucursalAdaptador extends BaseAdapter
{
    // Declaracion del contexto y lista de productos.
    private Context context;
    private ArrayList<Sucursal> arraySucursales;

    // Constructor del adaptador
    public SucursalAdaptador(Context context, ArrayList<Sucursal> arraySucursales)
    {
        this.context = context;
        this.arraySucursales = arraySucursales;
    }

    // Instruccion para sacar la cantidad de productos que hay en la lista.
    @Override
    public int getCount()
    {
        return arraySucursales.size();
    }

    // Instruccion para obtener el producto en la posicion "position".
    @Override
    public Object getItem(int position)
    {
        return arraySucursales.get(position);
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

        Sucursal sucursal = arraySucursales.get(position);

        ImageView imagensucursal = (ImageView) convertView.findViewById(R.id.ImagenProducto);
        TextView nombresucursal = (TextView)convertView.findViewById(R.id.NombreProducto);
        TextView ubicacionsucursal = (TextView)convertView.findViewById(R.id.DescripcionProducto);
        TextView preciosucursal = (TextView)convertView.findViewById(R.id.PrecioProducto);
        LinearLayout tarjetasucursal = (LinearLayout) convertView.findViewById(R.id.TarjetaProducto);

        //Bitmap bitmap = BitmapFactory.decodeByteArray(producto.getImagen(),0,producto.getImagen().length);
        //imagenproducto.setImageBitmap(bitmap);
        nombresucursal.setText(sucursal.getNombre());
        ubicacionsucursal.setText(sucursal.getUbicacion());

        //Instruccion para que al cliquear la tarjeta completa pase a la vista 3.
        tarjetasucursal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context.getApplicationContext(), Detalle_Productos.class);
                intent.putExtra("id",sucursal.getId());
                intent.putExtra("nombre",sucursal.getNombre());
                intent.putExtra("ubicacion",sucursal.getUbicacion());

                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
