package com.example.badulaqueenterprise;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class metodos {
    public static double ArredondaCentena (double vlr){
        double arredondado;
        if (vlr>0){
            arredondado = Math.round(vlr/10)*10;
            arredondado = Math.round(vlr/100)*100;
        }else{
            arredondado = 0;
        }
        return arredondado;
    }

    public static void EditarTextView (TextView x){
        x.setTypeface(null, Typeface.BOLD);
    }

    public static String NomeDoMes(int i, int tipo) {
        String mes[] = {"jan.", "fev.", "mar.", "abr.", "maio", "jun.", "jul.", "ago.", "set.", "out.", "nov.", "dez."};
        if (tipo == 0) {
            return (mes[i - 1]);
        } else {
            return (mes[i - 1].substring(0, 3));
        }
    }

    public static String DiaDaSemana(int i, int tipo) {
        String diasem[] = {"domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sábado"};
        if (tipo == 0) {
            return (diasem[i - 1]);
        } else {
            return (diasem[i - 1].substring(0, 3));
        }
    }
}
