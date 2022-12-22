package com.example.badulaqueenterprise;

public class Cardapio {
    String nome;
    double custo;
    double preco;

    public Cardapio(String nome, double custo, double preco) {
        this.nome = nome;
        this.custo = custo;
        this.preco = preco;
    }

    public Cardapio(String nome, double custo){
        this.nome = nome;
        this.custo = custo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double fCustoBuffet(double custoVariavel, double totalCozinha, double custoCerveja, int numConvidados){
        return custoVariavel + totalCozinha + (custo + custoCerveja) * numConvidados;
    }

}
