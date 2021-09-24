package com.badulaque.badulaque.model;

import java.io.Serializable;

public class Drink implements Serializable {

    private Long id;
    private String nomeDrink;
    private String custoDrink;
    private String pacote;

    public Drink(){

    }

    public Drink(Long id,String nomeDrink, String custoDrink, String pacote) {
        this.nomeDrink = nomeDrink;
        this.custoDrink = custoDrink;
        this.pacote = pacote;
        this.id = id;
    }

    public Long getId() { return id;}

    public void setId(Long id) { this.id = id; }

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
