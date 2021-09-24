package com.badulaque.badulaque.model;

public class Drink {

    private String nomeDrink;
    private String custoDrink;
    private String pacote;

    public Drink(){

    }

    public Drink(String nomeDrink, String custoDrink, String pacote) {
        this.nomeDrink = nomeDrink;
        this.custoDrink = custoDrink;
        this.pacote = pacote;
    }

    public String getNomeDrink() {
        return nomeDrink;
    }

    public void setNomeDrink(String nomeDrink) {
        this.nomeDrink = nomeDrink;
    }

    public String getCustoDrink() {
        return custoDrink;
    }

    public void setCustoDrink(String custoDrink) {
        this.custoDrink = custoDrink;
    }

    public String getPacote() {
        return pacote;
    }

    public void setPacote(String pacote) {
        this.pacote = pacote;
    }
}
