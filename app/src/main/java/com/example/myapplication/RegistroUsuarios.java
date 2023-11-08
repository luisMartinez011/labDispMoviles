package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroUsuarios extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);

        EditText usernameEditText = findViewById(R.id.usernameBtn);
        EditText passwordEditText = findViewById(R.id.passwordBtn);
        Button loginButton = findViewById(R.id.intentoRegistroBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                DBHandler dbHandler = new DBHandler(RegistroUsuarios.this);

                dbHandler.addNewUser(email,password,email);
                Toast.makeText(RegistroUsuarios.this, "User successfully created!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroUsuarios.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
