package com.example.real_food.Vistas.Principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.real_food.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity
{
    private Button BotonRegistrar, BotonVolverLogin;
    private EditText UsuarioRegistro, ClaveRegistro, ConfirmarClaveRegistro;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_registro);

        BotonRegistrar = (Button) findViewById(R.id.BotonRegistrar);
        BotonVolverLogin = (Button) findViewById(R.id.BotonVolverLogin);
        UsuarioRegistro = (EditText) findViewById(R.id.UsuarioRegistro);
        ClaveRegistro = (EditText) findViewById(R.id.ClaveRegistro);
        ConfirmarClaveRegistro = (EditText) findViewById(R.id.ConfirmarClaveRegistro);
        mAuth = FirebaseAuth.getInstance();

        BotonRegistrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Logica del Registro
                String Mail = UsuarioRegistro.getText().toString().trim();
                String Clave = ClaveRegistro.getText().toString().trim();
                String Confirmar = ConfirmarClaveRegistro.getText().toString().trim();

                if(Clave.compareTo(Confirmar) == 0)
                {
                    mAuth.createUserWithEmailAndPassword(Mail,Clave)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(getApplicationContext(),"Usuario Creado exitosamente",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                    }
                                }
                            })

                            .addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),"Hubo un Error en el registro",Toast.LENGTH_SHORT).show();
                                    Log.e("Error","Error" + e.getMessage());
                                }
                            });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"La Contrasena no coincide",Toast.LENGTH_SHORT);
                }

            }
        });

        BotonVolverLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }


}