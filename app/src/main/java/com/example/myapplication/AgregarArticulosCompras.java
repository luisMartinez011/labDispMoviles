package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AgregarArticulosCompras  extends AppCompatActivity {
    private Button addBtn;
    private EditText numeroArticulo;
    private EditText aliasArticulo;
    private EditText descripcionArticulo;
    private ArrayList<String> aliasNombreList;
    private ArrayList<String> descripcionArticulos;
    private int order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_articulos_compra_manual);

        // on below line we are initializing our variables.
        numeroArticulo = findViewById(R.id.agregarSerialArticulo);
        aliasArticulo = findViewById(R.id.agregarAliasArticulo);
        descripcionArticulo = findViewById(R.id.agregarDescripcionArticulo);
        addBtn = findViewById(R.id.btnAgregarArticulosCompra);

        DBHandler dbHandler = new DBHandler(AgregarArticulosCompras.this);

        aliasNombreList = new ArrayList<>();
        descripcionArticulos = new ArrayList<>();

        order_id = getIntent().getIntExtra("order_id2",1);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the order details from the EditText fields.
                String numero =  numeroArticulo.getText().toString();
                String alias = aliasArticulo.getText().toString();
                String descripcion = descripcionArticulo.getText().toString();

                // Check if the order name is not empty.
                if (numeroArticulo.getText().toString().length() > 1) {

                    // Add the order to the database using the DBHandler.
                    DBHandler dbHandler = new DBHandler(AgregarArticulosCompras.this);
                    dbHandler.addNewArticulo(numero, alias, descripcion, order_id);

                    // Return to the OrdenesDeCompras activity.
                    Intent intent = new Intent(AgregarArticulosCompras.this, ArticulosDeCompraMain.class);
                    intent.putExtra("order_id",order_id);
                    startActivity(intent);
                }
            }
        });

    }
}