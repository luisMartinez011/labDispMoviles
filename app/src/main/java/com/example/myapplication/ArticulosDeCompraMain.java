package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ArticulosDeCompraMain extends AppCompatActivity {
    private ArrayList<String> aliasNombreList;
    private ArrayList<String> descripcionArticulosList;


    private int order_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articulos_de_compra);

        ListView languageLV = (ListView) findViewById(R.id.listaArticulosCompra);
        SearchView searchView = findViewById(R.id.searchView);
        Button addBtn = findViewById(R.id.buttonAgregarArticulos);

        DBHandler dbHandler = new DBHandler(ArticulosDeCompraMain.this);

        aliasNombreList = new ArrayList<>();
        descripcionArticulosList = new ArrayList<>();

        order_id = getIntent().getIntExtra("order_id",1);
        Cursor cursor = dbHandler.getAllArticulosByOrder(order_id);

        if (cursor != null){
            int orderNameIndex = cursor.getColumnIndex(DBHandler.ALIAS_COL_ARTICULOS);
            int orderDescriptionIndex = cursor.getColumnIndex(DBHandler.SERIE_COL_ARTICULOS);
            while (cursor.moveToNext()){
                String orderName = cursor.getString(orderNameIndex);
                String orderDescription = cursor.getString(orderDescriptionIndex);
                aliasNombreList.add(orderName);
                descripcionArticulosList.add(orderDescription);
            }
        }

        ArticulosDeComprasAdapter adapter=new ArticulosDeComprasAdapter(this, aliasNombreList, descripcionArticulosList);

        languageLV.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticulosDeCompraMain.this, AgregarArticulosCompras.class);
                intent.putExtra("order_id2",order_id);
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
