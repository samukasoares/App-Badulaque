package com.badulaque.badulaque.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.badulaque.badulaque.R;
import com.badulaque.badulaque.model.Drink;

import java.util.List;

public class AdapterDrinks extends RecyclerView.Adapter<AdapterDrinks.MyViewHolder> {

    private List<Drink> listaDrinks;

    public AdapterDrinks(List<Drink> lista) {
        this.listaDrinks = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {            //Criar visualizações
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_drinks,parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {                   //Depois de criado exibe por esse método

        Drink drink = listaDrinks.get(position);
        holder.nomeDrink.setText(drink.getNomeDrink());
        holder.custoDrink.setText(drink.getCustoDrink());
        holder.pacote.setText(drink.getPacote());
    }

    @Override
    public int getItemCount() {
        return listaDrinks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeDrink;
        TextView custoDrink;
        TextView pacote;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeDrink = itemView.findViewById(R.id.textNomeDrink);
            custoDrink = itemView.findViewById(R.id.textCustoDrink);
            pacote = itemView.findViewById(R.id.textPacote);

        }
    }

}
