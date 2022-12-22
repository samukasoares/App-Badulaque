package com.example.badulaqueenterprise;

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
}
