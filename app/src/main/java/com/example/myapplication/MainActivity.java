package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText usernameEditText = findViewById(R.id.usernameBtn);
        EditText passwordEditText = findViewById(R.id.passwordBtn);
        Button loginButton = findViewById(R.id.loginBtn);
        Button registerButton = findViewById(R.id.buttonRegistrarse);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                DBHandler dbHandler = new DBHandler(MainActivity.this);

                Cursor cursor = dbHandler.getUserByEmail(email);
                if (cursor.moveToFirst()) {
                    String storedPassword = cursor.getString(cursor.getColumnIndex(DBHandler.PASSWORD_COL_USERS));
                    // Check if the entered password matches the stored password
                    if (password.equals(storedPassword)) {
                        // Passwords match, login successful
                        Intent intent = new Intent(MainActivity.this, OrdenesDeCompras.class);
                        startActivity(intent);
                    } else {
                        // Passwords do not match, login failed
                        Toast.makeText(MainActivity.this, "Autenticacion falladas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User not found, login failed
                    Toast.makeText(MainActivity.this, "Autenticacion falladas", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, RegistroUsuarios.class);
                    startActivity(intent);
            }
        });
    }

    //admin@mail.com Administrador777
    private boolean isCredentialsValid(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

}
