package com.example.real_food;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.real_food.Vistas.Principal.Login;

public class pantalla_inicio extends AppCompatActivity
{
    private Button BotonIngreso;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_inicio);

        BotonIngreso = (Button) findViewById(R.id.BotonIngreso);

        BotonIngreso.setOnClickListener(new View.OnClickListener()
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