package com.example.real_food.Vistas.Principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity
{
    private Button BotonIngresarLogIn,BotoniralRegistro;
    private EditText UsuarioLogin, ClaveLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        BotonIngresarLogIn = (Button) findViewById(R.id.BotonIngresarLogIn);
        BotoniralRegistro = (Button) findViewById(R.id.BotoniralRegistro);
        UsuarioLogin = (EditText) findViewById(R.id.UsuarioLogin);
        ClaveLogin = (EditText) findViewById(R.id.ClaveLogin);
        mAuth = FirebaseAuth.getInstance();

        BotonIngresarLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Mail = UsuarioLogin.getText().toString().trim();
                String Clave = ClaveLogin.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(Mail,Clave)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                Intent intent = new Intent(getApplicationContext(), Opciones.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(getApplicationContext(),"Hubo un error en el Ingreso",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        BotoniralRegistro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);
            }
        });
    }
}