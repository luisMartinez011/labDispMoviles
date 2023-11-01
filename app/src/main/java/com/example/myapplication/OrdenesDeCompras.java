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
import java.util.InputMismatchException;

import android.widget.SearchView;


public class OrdenesDeCompras extends AppCompatActivity {

    // on below line we are creating variables.
    private ListView languageLV;
    private Button addBtn;
    private EditText itemEdt;
    private ArrayList<String> lngList;
    private ArrayList<String> descripcionOrdenesList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordenes_de_compra);

        // on below line we are initializing our variables.
        languageLV = (ListView)findViewById(R.id.listaOrdenesCompra);
        searchView=findViewById(R.id.searchView);
        addBtn = findViewById(R.id.buttonAgregarOrdenes);

//        lngList = new ArrayList<>();
//        descripcionOrdenesList = new ArrayList<>();
//        // on below line we are adding items to our list
//        lngList.add("Orden #890");
//        lngList.add("Orden #320");
//
//        descripcionOrdenesList.add("Descripcion 1");
//        descripcionOrdenesList.add("Descripcion 2");

        if( getIntent().getStringArrayListExtra("numeroOrdenes") != null){
            lngList = getIntent().getStringArrayListExtra("numeroOrdenes");
            descripcionOrdenesList = getIntent().getStringArrayListExtra("descripcionOrdenes");
        }else{
            lngList = new ArrayList<>();
            descripcionOrdenesList = new ArrayList<>();
        }

//        lngList = new ArrayList<>();
//        descripcionOrdenesList = new ArrayList<>();
//        lngList.add("Orden #890");
//        lngList.add("Orden #320");
//
//        descripcionOrdenesList.add("Descripcion 1");
//        descripcionOrdenesList.add("Descripcion 2");
        // on the below line we are initializing the adapter for our list view.

        OrdenesDeComprasAdapter adapter=new OrdenesDeComprasAdapter(this, lngList, descripcionOrdenesList);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lngList);

        // on below line we are setting adapter for our list view.
        languageLV.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting text from edit text
                Intent intent = new Intent(OrdenesDeCompras.this, AgregarOrdenesCompras.class);
                intent.putStringArrayListExtra("numerosOrdenesExistentes",lngList);
                intent.putStringArrayListExtra("descripcionOrdenesExistentes",descripcionOrdenesList);
                startActivity(intent);

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        Button backButton = findViewById(R.id.returnButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

    }
}
