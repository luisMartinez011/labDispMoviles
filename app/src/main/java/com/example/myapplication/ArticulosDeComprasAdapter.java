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

    private final ArrayList<String> codigo;

    public ArticulosDeComprasAdapter(Activity context,ArrayList<String> maintitle,ArrayList<String> subtitle, ArrayList<String> codigo) {
        super(context, R.layout.interface_lista_ordenes_compra, maintitle);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.codigo = codigo;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.interface_lista_articulos_compra, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.listaAlias);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.listaDescripcionArticulo);

        titleText.setText(maintitle.get(position) + ": " + codigo.get(position));
        String producto = maintitle.get(position);
        if( producto.equals("Martillo")){
            imageView.setImageResource(R.drawable.martillo);
        } else if (producto.equals("Taladro")) {
            imageView.setImageResource(R.drawable.taladro);
        }
        else if (producto.equals("Destornillador")) {
            imageView.setImageResource(R.drawable.destronillador);
        }
        else if (producto.equals("Clavos")) {
            imageView.setImageResource(R.drawable.clavos);
        }
        else if (producto.equals("Zerrucho")) {
            imageView.setImageResource(R.drawable.zerrucho);
        }else{
            imageView.setImageResource(R.drawable.object);
        }


        subtitleText.setText(subtitle.get(position));

        return rowView;

    };
}