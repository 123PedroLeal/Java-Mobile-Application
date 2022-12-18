package com.example.real_food.Vistas.Productos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.real_food.BaseDeDatos.BaseDatos;
import com.example.real_food.Entidades.Producto;
import com.example.real_food.R;
import com.example.real_food.Servicios.ServicioProductos;

import java.util.ArrayList;
public class Detalle_Productos extends AppCompatActivity
{
    private Button BotonRegresoProductos;
    private TextView TituloInfo , DescripcionInfo, PrecioInfo;
    private ImageView ImagenInfo;
    private BaseDatos baseDatos;
    private ServicioProductos servicioProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prod_detalle);

        TituloInfo = (TextView) findViewById(R.id.TituloInfo);
        ImagenInfo = (ImageView) findViewById(R.id.ImagenInfo);
        DescripcionInfo = (TextView) findViewById(R.id.DescripcionInfo);
        PrecioInfo = (TextView) findViewById(R.id.PrecioInfo);
        BotonRegresoProductos = (Button) findViewById(R.id.BotonRegresoProductos);

        Intent intent = getIntent();
        baseDatos = new BaseDatos(this);
        servicioProductos = new ServicioProductos();

        String id = String.valueOf(intent.getIntExtra("id",0));
        ArrayList<Producto> ProductoDb = servicioProductos.cursorToArray(baseDatos.getProductoporId(id));

        //Comandos para la imagen como Bitmap.
        //Producto producto = ProductoDb.get(0);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(producto.getImagen(),0,producto.getImagen().length);
        //ImagenInfo.setImageBitmap(bitmap);

        TituloInfo.setText(intent.getStringExtra("nombre"));
        DescripcionInfo.setText(intent.getStringExtra("descripcion"));
        PrecioInfo.setText(String.valueOf(intent.getIntExtra("precio",0)));

        BotonRegresoProductos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent (getApplicationContext(), Catalogo_Productos.class);
                startActivity(intent);
            }
        });
    }
}
