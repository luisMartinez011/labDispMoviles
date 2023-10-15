package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.widget.SearchView;


public class OrdenesDeCompras extends AppCompatActivity {

    // on below line we are creating variables.
    private ListView languageLV;
    private Button addBtn;
    private EditText itemEdt;
    private ArrayList<String> lngList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordenes_de_compra);

        // on below line we are initializing our variables.
        languageLV = findViewById(R.id.idLVLanguages);
//        addBtn = findViewById(R.id.idBtnAdd);
//        itemEdt = findViewById(R.id.idEdtItemName);
        searchView=findViewById(R.id.searchView);


        lngList = new ArrayList<>();

        // on below line we are adding items to our list
        lngList.add("Orden #890");
        lngList.add("Orden #320");

        // on the below line we are initializing the adapter for our list view.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lngList);

        // on below line we are setting adapter for our list view.
        languageLV.setAdapter(adapter);
//        addBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // on below line we are getting text from edit text
//                String item = itemEdt.getText().toString();
//
//                // on below line we are checking if item is not empty
//                if (!item.isEmpty()) {
//
//                    // on below line we are adding item to our list.
//                    lngList.add(item);
//
//                    // on below line we are notifying adapter
//                    // that data in list is updated to
//                    // update our list view.
//                    adapter.notifyDataSetChanged();
//                }
//
//            }
//        });

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

    }
}
