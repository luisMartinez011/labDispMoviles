package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import android.widget.SearchView;

public class AgregarOrdenesCompras extends AppCompatActivity {
    private EditText numeroOrden;
    private EditText descripcionOrden;
    private ArrayList<String> numerosOrdenes;
    private ArrayList<String> descripcionOrdenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHandler dbHandler = new DBHandler(AgregarOrdenesCompras.this);
        setContentView(R.layout.agregar_ordenes_compra);

        // on below line we are initializing our variables.
        numeroOrden = findViewById(R.id.agregarNumeroOrden);
        descripcionOrden = findViewById(R.id.agregarDescripcionOrden);
        Button addBtn = findViewById(R.id.idBtnAddOrdenesCompra);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the order details from the EditText fields.
                String numero = "Orden #: " + numeroOrden.getText().toString();
                String descripcion = descripcionOrden.getText().toString();

                // Check if the order name is not empty.
                if (numeroOrden.getText().toString().length() > 1) {
                    // Add the order to the database using the DBHandler.
                    DBHandler dbHandler = new DBHandler(AgregarOrdenesCompras.this);
                    dbHandler.addNewOrder(numero, descripcion);

                    // Return to the OrdenesDeCompras activity.
                    Intent intent = new Intent(AgregarOrdenesCompras.this, OrdenesDeCompras.class);
                    startActivity(intent);
                }
            }
        });

    }
}
