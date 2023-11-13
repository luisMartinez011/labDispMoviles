package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ArticulosDeComprasAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;

    public ArticulosDeComprasAdapter(Activity context,ArrayList<String> maintitle,ArrayList<String> subtitle) {
        super(context, R.layout.interface_lista_ordenes_compra, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.interface_lista_articulos_compra, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.listaAlias);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.listaDescripcionArticulo);

        titleText.setText(maintitle.get(position));
        imageView.setImageResource(R.drawable.object);
        subtitleText.setText(subtitle.get(position));

        return rowView;

    };
}