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
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBHandler dbHandler = new DBHandler(AgregarOrdenesCompras.this);
        setContentView(R.layout.agregar_ordenes_compra);

        // on below line we are initializing our variables.
        numeroOrden = findViewById(R.id.agregarNumeroOrden);
        descripcionOrden = findViewById(R.id.agregarDescripcionOrden);
        Button addBtn = findViewById(R.id.idBtnAddOrdenesCompra);

        if( getIntent().getStringArrayListExtra("numerosOrdenesExistentes") != null){
            numerosOrdenes = getIntent().getStringArrayListExtra("numerosOrdenesExistentes");
            descripcionOrdenes = getIntent().getStringArrayListExtra("descripcionOrdenesExistentes");
        }else{
            numerosOrdenes = new ArrayList<>();
            descripcionOrdenes = new ArrayList<>();
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting text from edit text
                String numero = "Orden #: " + numeroOrden.getText().toString();
                String descripcion = descripcionOrden.getText().toString();

                // on below line we are checking if item is not empty
                if (numeroOrden.getText().toString().length()>1) {
                    // on below line we are adding item to our list.
                    numerosOrdenes.add(numero);
                    descripcionOrdenes.add(descripcion);
                    Intent intent = new Intent(AgregarOrdenesCompras.this, OrdenesDeCompras.class);
                    intent.putStringArrayListExtra("numeroOrdenes",numerosOrdenes);
                    intent.putStringArrayListExtra("descripcionOrdenes",descripcionOrdenes);
                    startActivity(intent);
                }

            }
        });
    }
}
