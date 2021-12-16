package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Jsoup ---> JSON
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    static ArrayList<String> urlsImg = new ArrayList<>();
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listadopokemon);
        //Customadapter adapter = new Customadapter(pokemons,this);

        //pre ejecucion------
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Document resCompl = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
                    nombres = (ArrayList<String>) resCompl.select("[href^=/es/pokedex/]").eachText();
                    nombres.remove(0);
                            //selectXpath("/html/body/div[4]/section[5]/ul"); Con esto funciona pero no con esa ruta
                    for (int i = 0; i < nombres.size(); i++) {
                        String numPkm = String.format("%03d", i + 1);
                        urlsImg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numPkm + ".png");
                        pokemons.add(new Pokemon(nombres.get(i)));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //post ejecucion --------
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Customadapter adapter = new Customadapter(pokemons, MainActivity.this);
                        listview.setAdapter(adapter);
                    }
                });
            }
        }).start();

    }
}