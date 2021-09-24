package com.badulaque.badulaque.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.badulaque.badulaque.R;
import com.badulaque.badulaque.adapter.AdapterDrinks;
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


        //Listagem de drinks
        this.criarDrinks();

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
        Drink drink = new Drink ("Sex on the Beach", "R$3,89", "Ouro");
        this.listaDrinks.add(drink);
    }
}