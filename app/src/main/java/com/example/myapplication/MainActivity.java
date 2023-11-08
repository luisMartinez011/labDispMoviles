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
                    int passwordColumnIndex = cursor.getColumnIndex(DBHandler.PASSWORD_COL_USERS);
                    // User with the provided email exists.
                    String storedPassword = cursor.getString(passwordColumnIndex);
                    cursor.close();

                    if (password.equals(storedPassword)) {
                        Intent intent = new Intent(MainActivity.this, OrdenesDeCompras.class);
                        startActivity(intent);
                    } else {
                        // Password does not match.
                        Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // User with the provided email does not exist.
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
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
}
