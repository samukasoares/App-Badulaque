package com.badulaque.badulaque.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.badulaque.badulaque.R;
import com.badulaque.badulaque.adapter.AdapterDrinks;
import com.badulaque.badulaque.helper.RecyclerItemClickListener;
import com.badulaque.badulaque.model.Drink;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BardulaqueActivity extends AppCompatActivity {

    private RecyclerView recyclerDrinks;
    private List<Drink> listaDrinks = new ArrayList<>();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bardulaque);

        recyclerDrinks = findViewById(R.id.recyclerDrinks);
        fab = findViewById(R.id.btnAddDrink);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addDrink = new Intent (getApplicationContext(), AdicionarDrinkActivity.class);
                startActivity(addDrink);
            }
        });

        //Adicionar evento de clique no RV
        recyclerDrinks.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerDrinks, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                Log.i("clique", "onItemClick");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.i("clique", "onLongItemClick");
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }


    @Override
    protected void onStart() {
        carregarDrinks();
        super.onStart();
    }

    public void carregarDrinks(){

        Drink drink = new Drink();
        drink.setNomeDrink("Moscow Mule");
        drink.setPacote("Especial");
        drink.setCustoDrink("R$ 3,89");
        listaDrinks.add(drink);


        //Configuração do Adapter (recebe os dados, formata o layout e configura no recycler view)
        AdapterDrinks adapterDrinks =new AdapterDrinks(listaDrinks);

        //Configuração RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerDrinks.setLayoutManager(layoutManager);
        recyclerDrinks.setHasFixedSize(true);
        recyclerDrinks.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerDrinks.setAdapter(adapterDrinks);
    }


    public void criarDrinks(){

    }
}