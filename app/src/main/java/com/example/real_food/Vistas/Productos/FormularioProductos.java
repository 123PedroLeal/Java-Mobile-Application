package com.example.real_food.Vistas.Productos;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

import com.bumptech.glide.Glide;
import com.example.real_food.BaseDeDatos.BaseDatos;
import com.example.real_food.BaseDeDatos.BaseDatosFireBase;
import com.example.real_food.Entidades.Producto;
import com.example.real_food.R;
import com.example.real_food.Servicios.ServicioProductos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class FormularioProductos extends AppCompatActivity
{
    // Declaracion de las variables del formulario.
    private Button BotonCrearProductoNP,BotonConsultarProductoNP,BotonBorrarProductoNP,BotonActualizarProductoNP;
    private EditText EspacioNombreNP, EspacioDescripcionNP, EspacioPrecioNP, EspacioIdProductoNP;
    private ImageView ImagenProductoNP;
    private BaseDatos baseDatos;
    private BaseDatosFireBase baseDatosFireBase;
    private ActivityResultLauncher<String> contenido;
    private ServicioProductos servicioProductos;
    private StorageReference storageReference;
    private String UrlImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Conexion de la interfaz o Layout al codigo de Java.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prod_form);

        //Conexion de las variables de Java a los objetos en la interfaz.
        BotonCrearProductoNP = (Button) findViewById(R.id.BotonCrearProductoNP);
        BotonConsultarProductoNP = (Button) findViewById(R.id.BotonConsultarProductoNP);
        BotonBorrarProductoNP = (Button) findViewById(R.id.BotonBorrarProductoNP);
        BotonActualizarProductoNP = (Button) findViewById(R.id.BotonActualizarProductoNP);
        EspacioNombreNP = (EditText) findViewById(R.id.EspacioNombreNP);
        EspacioDescripcionNP = (EditText) findViewById(R.id.EspacioDescripcionNP);
        EspacioPrecioNP = (EditText) findViewById(R.id.EspacioPrecioNP);
        EspacioIdProductoNP = (EditText) findViewById(R.id.EspacioIdProductoNP);
        ImagenProductoNP = (ImageView) findViewById(R.id.ImagenProductoNP);
        UrlImagen = "";

        try
        {
            // Declaracion de las bases de datos locales y de FireBase, en conjunto con el servicio.
            baseDatos = new BaseDatos(this);
            baseDatosFireBase = new BaseDatosFireBase();
            servicioProductos = new ServicioProductos();
            storageReference = FirebaseStorage.getInstance().getReference();

            contenido = registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    new ActivityResultCallback<Uri>()
                    {
                        @Override
                        public void onActivityResult(Uri result)
                        {
                            Uri uri = result;
                            StorageReference filepath = storageReference.child("Imagen").child(uri.getLastPathSegment());
                            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                            {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    Toast.makeText(getApplicationContext(),"La imagen ha sido cargada",Toast.LENGTH_SHORT).show();
                                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                    {
                                        @Override
                                        public void onSuccess(Uri uri)
                                        {
                                            Uri UrlDescargada = uri;
                                            UrlImagen = UrlDescargada.toString();
                                            Glide.with(FormularioProductos.this)
                                                    .load(UrlDescargada)
                                                    .override(500, 500)
                                                    .into(ImagenProductoNP);
                                        }
                                    });
                                }
                            });
//                            Instrucciones para guardar las imagenes en local.
//                            try
//                            {
////                                InputStream inputStream = getContentResolver().openInputStream(result);
////                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
////                                ImagenProductoNP.setImageBitmap(bitmap);
//                            }
//                            catch (FileNotFoundException e)
//                            {
//                                Log.e("FileError", e.toString());
//                            }
                        }
                    });
        }
        catch (Exception e)
        {
            Log.e("BaseDeDatos",e.toString());
        }

        Intent intentIN = getIntent();
        EspacioNombreNP.setText(intentIN.getStringExtra("nombre"));
        EspacioDescripcionNP.setText(intentIN.getStringExtra("descripcion"));
        EspacioPrecioNP.setText(String.valueOf(intentIN.getIntExtra("precio",0)));

        // Comando para que cuando den un click al icono de buscar imagen realice una accion.
        ImagenProductoNP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            // Comando para acceder a la galeria de imagenes.
            public void onClick(View v)
            {
                contenido.launch("image/*");
            }
        });

        BotonCrearProductoNP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (intentIN.getBooleanExtra("edit",false))
                {
                    String id = intentIN.getStringExtra("id");

                    Producto producto = new Producto
                            (
                                    id,
                                    EspacioNombreNP.getText().toString(),
                                    EspacioDescripcionNP.getText().toString(),
                                    Integer.parseInt(EspacioPrecioNP.getText().toString()),
                                    UrlImagen
                            );
                    baseDatosFireBase.ActualizarProducto(producto);

                    Toast.makeText(getApplicationContext(),"El Producto ha sido Actualizado",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Producto producto = new Producto
                            (
                                    EspacioNombreNP.getText().toString(),
                                    EspacioDescripcionNP.getText().toString(),
                                    Integer.parseInt(EspacioPrecioNP.getText().toString()),
                                    UrlImagen
                            );

                    //Instruccion para insertar producto en base de datos local.
                    //baseDatos.InsertarProducto(producto);
                    //Instruccion para insertar producto en base de datos Firebase.
                    baseDatosFireBase.InsertarProducto(producto);
                    Toast.makeText(getApplicationContext(),"El Producto ha sido creado",Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getApplicationContext(), Catalogo_Productos.class);
                startActivity(intent);


            }
        });

        BotonConsultarProductoNP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = EspacioIdProductoNP.getText().toString().trim();
                if (id.compareTo("")==0)
                {
                    Toast.makeText(getApplicationContext(),"Ingrese un ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Producto> ProductoDb = servicioProductos.cursorToArray(baseDatos.getProductoporId(id));
                    if (ProductoDb.size() != 0)
                    {
                        Producto producto = ProductoDb.get(0);
                        EspacioNombreNP.setText(producto.getNombre());
                        EspacioDescripcionNP.setText(producto.getDescription());
                        EspacioPrecioNP.setText(String.valueOf(producto.getPrecio()));

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
        BotonBorrarProductoNP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                String id = EspacioIdProductoNP.getText().toString().trim();
//                if (id.compareTo("")==0)
//                {
//                    Toast.makeText(getApplicationContext(),"Ingrese un ID",Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    ArrayList<Producto> ProductoDb = servicioProductos.cursorToArray(baseDatos.getProductoporId(id));
//                    if (ProductoDb.size() != 0)
//                    {
//                        baseDatos.BorrarporId(id);
//                        Toast.makeText(getApplicationContext(), "El producto " + id + " ha sido eliminado", Toast.LENGTH_SHORT).show();
//                        Limpiar();
//                    }
//                    else
//                    {
//                        Toast.makeText(getApplicationContext(),"Ese Producto No Existe",Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });

        BotonActualizarProductoNP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = EspacioIdProductoNP.getText().toString().trim();
                if (id.compareTo("")==0)
                {
                    Toast.makeText(getApplicationContext(),"Ingrese un ID",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Producto> ProductoDb = servicioProductos.cursorToArray(baseDatos.getProductoporId(id));
                    if (ProductoDb.size() != 0)
                    {
                        baseDatos.ActualizarProducto(
                                id,
                                EspacioNombreNP.getText().toString(),
                                EspacioDescripcionNP.getText().toString(),
                                EspacioPrecioNP.getText().toString(),
                                servicioProductos.ImageViewtoByte(ImagenProductoNP));

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
        EspacioNombreNP.setText("");
        EspacioDescripcionNP.setText("");
        EspacioPrecioNP.setText("");
        ImagenProductoNP.setImageResource(R.drawable.ic_busqueda_imagenes);
        EspacioIdProductoNP.setText("");
    }
}