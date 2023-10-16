package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
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

        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (isCredentialsValid(username, password)) {
                Intent intent = new Intent(MainActivity.this, OrdenesDeCompras.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isCredentialsValid(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

}
