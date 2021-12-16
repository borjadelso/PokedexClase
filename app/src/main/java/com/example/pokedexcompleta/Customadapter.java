package com.example.pokedexcompleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Customadapter extends BaseAdapter{
    ArrayList<Pokemon> pokemons;
   Context txt;

    public Customadapter(ArrayList<Pokemon> pokemons, Context txt) {
        this.pokemons = pokemons;
        this.txt = txt;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewInflado = LayoutInflater.from(txt).inflate(R.layout.itm_pkm, null);
        TextView txtNombre = viewInflado.findViewById(R.id.nombrePkm);
        ImageView imgPkm = viewInflado.findViewById(R.id.imgpkm);
        txtNombre.setText(pokemons.get(position).getNombre());
        Picasso.get().load(MainActivity.urlsImg.get(position)).into(imgPkm);
        return viewInflado;
    }
}
