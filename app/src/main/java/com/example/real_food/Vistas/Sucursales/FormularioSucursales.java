package com.example.real_food.Vistas.Sucursales;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.real_food.BaseDeDatos.BaseDatos;
import com.example.real_food.BaseDeDatos.BaseDatosFireBase;
import com.example.real_food.Entidades.Producto;
import com.example.real_food.Entidades.Sucursal;
import com.example.real_food.R;
import com.example.real_food.Servicios.ServicioProductos;
import com.example.real_food.Servicios.ServicioSucursales;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class FormularioSucursales extends AppCompatActivity
{
    // Declaracion de las variables del formulario.
    private Button BotonCrearSucursalNS,BotonConsultarSucursalNS,BotonBorrarSucursalNS,BotonActualizarSucursalNS;
    private EditText EspacioNombreNS, EspacioUbicacionNS, EspacioIdSucursalNS;
    private ImageView ImagenSucursalNS;
    private BaseDatos baseDatos;
    private BaseDatosFireBase baseDatosFireBase;
    private ActivityResultLauncher<String> contenido;
    private ServicioSucursales servicioSucursales;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Conexion de la interfaz o Layout al codigo de Java.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_form);

        //Conexion de las variables de Java a los objetos en la interfaz.
        BotonCrearSucursalNS = (Button) findViewById(R.id.BotonCrearSucursalNS);
        BotonConsultarSucursalNS = (Button) findViewById(R.id.BotonConsultarSucursalNS);
        BotonBorrarSucursalNS = (Button) findViewById(R.id.BotonBorrarSucursalNS);
        BotonActualizarSucursalNS = (Button) findViewById(R.id.BotonActualizarSucursalNS);
        EspacioNombreNS = (EditText) findViewById(R.id.EspacioNombreNS);
        EspacioUbicacionNS = (EditText) findViewById(R.id.EspacioUbicacionNS);
        EspacioIdSucursalNS = (EditText) findViewById(R.id.EspacioIdSucursalNS);
        ImagenSucursalNS = (ImageView) findViewById(R.id.ImagenSucursalNS);

        try
        {
            // Declaracion de las bases de datos locales y de FireBase, en conjunto con el servicio.
            baseDatos = new BaseDatos(this);
            baseDatosFireBase = new BaseDatosFireBase();
            servicioSucursales = new ServicioSucursales();

            contenido = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>()
                    {
                        @Override
                        public void onActivityResult(Uri result)
                        {
                            try
                            {
                                InputStream inputStream = getContentResolver().openInputStream(result);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                ImagenSucursalNS.setImageBitmap(bitmap);
                            }
                            catch (FileNotFoundException e)
                            {
                                Log.e("FileError", e.toString());
                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Log.e("BaseDeDatos",e.toString());
        }

        // Comando para que cuando den un click al icono de buscar imagen realice una accion.
        ImagenSucursalNS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            // Comando para acceder a la galeria de imagenes.
            public void onClick(View v)
            {
                contenido.launch("image/*");
            }
        });

        BotonCrearSucursalNS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Sucursal sucursal = new Sucursal
                        (
                        EspacioNombreNS.getText().toString(),
                        EspacioUbicacionNS.getText().toString(),
                        ""
                        );

                //Instruccion para insertar producto en base de datos local.
                //baseDatos.InsertarProducto(sucursal);
                //Instruccion para insertar producto en base de datos Firebase.
                baseDatosFireBase.InsertarSucursal(sucursal);


                Intent intent = new Intent(getApplicationContext(), Catalogo_Sucursales.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(),"El Producto ha sido creado",Toast.LENGTH_SHORT).show();
            }
        });

        BotonConsultarSucursalNS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = EspacioIdSucursalNS.getText().toString().trim();
                if (id.compareTo("")==0)
                {
                    Toast.makeText(getApplicationContext(),"Ingrese un ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Sucursal> SucursalDb = servicioSucursales.cursorToArray(baseDatos.getProductoporId(id));
                    if (SucursalDb.size() != 0)
                    {
                        Sucursal sucursal = SucursalDb.get(0);
                        EspacioNombreNS.setText(sucursal.getNombre());
                        EspacioUbicacionNS.setText(sucursal.getUbicacion());

                        //Bitmap bitmap = BitmapFactory.decodeByteArray(producto.getImagen(),0,producto.getImagen().length);
                        //ImagenProductoNP.setImageBitmap(bitmap);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Ese Producto No Existe",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        BotonBorrarSucursalNS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = EspacioIdSucursalNS.getText().toString().trim();
                if (id.compareTo("")==0)
                {
                    Toast.makeText(getApplicationContext(),"Ingrese un ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Sucursal> SucursalDb = servicioSucursales.cursorToArray(baseDatos.getProductoporId(id));
                    if (SucursalDb.size() != 0)
                    {
                        baseDatos.BorrarporId(id);
                        Toast.makeText(getApplicationContext(), "El producto " + id + " ha sido eliminado", Toast.LENGTH_SHORT).show();
                        Limpiar();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Ese Producto No Existe",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        BotonActualizarSucursalNS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = EspacioIdSucursalNS.getText().toString().trim();
                if (id.compareTo("")==0)
                {
                    Toast.makeText(getApplicationContext(),"Ingrese un ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Sucursal> SucursalDb = servicioSucursales.cursorToArray(baseDatos.getProductoporId(id));
                    if (SucursalDb.size() != 0)
                    {
                        //baseDatos.ActualizarSucursal(id,EspacioNombreNS.getText().toString(),EspacioUbicacionNS.getText().toString(), servicioSucursales.ImageViewtoByte(ImagenSucursalNS));

                        Toast.makeText(getApplicationContext(), "El producto " + id + " ha sido actualizado", Toast.LENGTH_SHORT).show();

                        Limpiar();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Ese Producto No Existe",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void Limpiar()
    {
        EspacioIdSucursalNS.setText("");
        EspacioUbicacionNS.setText("");
        ImagenSucursalNS.setImageResource(R.drawable.ic_busqueda_imagenes);
        EspacioIdSucursalNS.setText("");
    }
}