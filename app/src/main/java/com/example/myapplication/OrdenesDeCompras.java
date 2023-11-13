package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class OrdenesDeCompras extends AppCompatActivity {
    private ArrayList<String> lngList;
    private ArrayList<String> descripcionOrdenesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordenes_de_compra);

        ListView languageLV = (ListView) findViewById(R.id.listaOrdenesCompra);
        SearchView searchView = findViewById(R.id.searchView);
        Button addBtn = findViewById(R.id.buttonAgregarOrdenes);

        DBHandler dbHandler = new DBHandler(OrdenesDeCompras.this);

        lngList = new ArrayList<>();
        descripcionOrdenesList = new ArrayList<>();

        Cursor cursor = dbHandler.getAllOrders();

        if (cursor != null){
            int orderNameIndex = cursor.getColumnIndex(DBHandler.NAME_COL_ORDERS);
            int orderDescriptionIndex = cursor.getColumnIndex(DBHandler.DESCRIPTION_COL_ORDERS);

            while (cursor.moveToNext()){
                String orderName = cursor.getString(orderNameIndex);
                String orderDescription = cursor.getString(orderDescriptionIndex);
                lngList.add(orderName);
                descripcionOrdenesList.add(orderDescription);
            }
        }

        OrdenesDeComprasAdapter adapter=new OrdenesDeComprasAdapter(this, lngList, descripcionOrdenesList);

        languageLV.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdenesDeCompras.this, AgregarOrdenesCompras.class);
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

        languageLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(OrdenesDeCompras.this, ArticulosDeCompraMain.class);
                intent.putExtra("order_id",i+1);
                startActivity(intent);
            }
        });
    }
}
