package com.example.real_food.Vistas.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.real_food.R;
import com.example.real_food.Vistas.Productos.Catalogo_Productos;
import com.example.real_food.Vistas.Sucursales.Catalogo_Sucursales;

public class Opciones extends AppCompatActivity {
    private Button BotonVolverLogin;
    private LinearLayout OpcionProducto, OpcionSucursal, OpcionServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_opciones);

        BotonVolverLogin = (Button) findViewById(R.id.BotonVolverLogin);
        OpcionProducto = (LinearLayout) findViewById(R.id.OpcionProducto);
        OpcionSucursal = (LinearLayout) findViewById(R.id.OpcionSucursal);
        OpcionServicio = (LinearLayout) findViewById(R.id.OpcionServicio);

        OpcionProducto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Catalogo_Productos.class);
                startActivity(intent);
            }
        });

        OpcionServicio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Catalogo_Productos.class);
                startActivity(intent);
            }
        });

        OpcionSucursal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Catalogo_Sucursales.class);
                startActivity(intent);
            }
        });


        BotonVolverLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}