package com.example.badulaqueenterprise;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;

public class Evento {

    int NumConvidados, MesesAteEvento;

    public Evento(int NumConvidados, int MesesAteEvento) {
        this.NumConvidados = NumConvidados;
        this.MesesAteEvento = NumConvidados;
    }

    public double fIPCA (){
        double fIPCA = 0;
        double Y = MesesAteEvento / MainActivity.K_PERIODO;
        fIPCA = (Math.pow(1 + MainActivity.K_IPCA / 100, Y ))- 1;

        if (fIPCA<0){
            fIPCA = 0;
        } else{
            fIPCA = fIPCA;
        }
        return fIPCA;
    }

    public double fMargemContr (){
        return 0.14 - NumConvidados * 0.0004;
    }

    public int fNumeroGarcom (){
        return NumConvidados/20;
    }

    public int fNumAuxCozinha (){
        int auxiliar;

        if (NumConvidados >= 120){
            auxiliar = 3;
        } else if (NumConvidados >= 80){
            auxiliar = 2;
        } else{
            auxiliar = 1;
        }
        return auxiliar;
    }

    public int fNumCozinheira(){
        int cozinheira;
        if(NumConvidados > 60){
            cozinheira = 2;
        }else{
            cozinheira = 1;
        }
        return cozinheira;
    }

    public double fCustoCozinha(){
        double v = 0;
        if(NumConvidados >= 120){
            v = CustoMoCozinha_120();
        } else if (NumConvidados >= 80){
            v = CustoMoCozinha_80();
        } else if (NumConvidados >= 60){
            v = CustoMoCozinha_60();
        } else{
            CustoMoCozinha_min();
        }
        return v;
    }

    public int CustoMoCozinha_min(){
        return MainActivity.K_CHEF + MainActivity.K_COZINHEIRA;
    }

    public int CustoMoCozinha_60(){return MainActivity.K_CHEF + MainActivity.K_COZINHEIRA + MainActivity.K_AuxCozinha;}

    public int CustoMoCozinha_80(){
        return MainActivity.K_CHEF + 2 * MainActivity.K_COZINHEIRA + 1 * MainActivity.K_AuxCozinha;
    }

    public int CustoMoCozinha_120(){
        return MainActivity.K_CHEF + 2 * MainActivity.K_COZINHEIRA + 3 * MainActivity.K_AuxCozinha;
    }

    public int fNumBarman (){
        int barman = 1;

        if(NumConvidados >=250){
            barman = 5;
        }else if (NumConvidados >=200){
            barman = 4;
        } else if(NumConvidados >= 150){
            barman = 3;
        }else if (NumConvidados >=100){
            barman = 2;
        }
        return barman;
    }

    public double fCustoMoBar(CheckBox bartender){
        double vCustoBar = 0;
        if(bartender.isChecked()){
            vCustoBar = fNumBarman() * MainActivity.K_BARMAN;
        }else{
            vCustoBar = 0;
        }
        return vCustoBar;
    }


    public double fCustoBar(CheckBox bartender, Spinner packBar){
        double custo = 0;
        String pacoteBar = packBar.getSelectedItem().toString();
        if(bartender.isChecked()){
            switch (pacoteBar) {
                case "Bronze":
                    custo = MainActivity.K_CUSTOBRONZE * NumConvidados;
                    break;
                case "Prata":
                    custo = MainActivity.K_CUSTOPRATA * NumConvidados;
                    break;
                case "Ouro":
                    custo = MainActivity.K_CUSTOOURO * NumConvidados;
                    break;
            }
        }else{
            custo = 0;
        }
        return custo;
    }

    public double fCustoCervejaChopp(CheckBox cerveja, CheckBox chopp, Spinner tipoCerveja) {
        double custo = 0;
        String tipoCervejaTexto = tipoCerveja.getSelectedItem().toString();

        if (cerveja.isChecked()) {
            switch (tipoCervejaTexto) {
                case "Brahma/Skol":
                    custo = MainActivity.K_CERVEJA1;
                    break;
                case "Original":
                    custo = MainActivity.K_CERVEJA2;
                    break;
                case "Heineken":
                    custo = MainActivity.K_CERVEJA3;
                    break;
                default:
                    throw new IllegalArgumentException("Cerveja Inválida");
            }
        } else if (chopp.isChecked()) {
            custo = MainActivity.K_CHOPP;
        } else{
            custo = 0;
        }

        return custo;
    }

    public double fCustoMensalMo(){
        return MainActivity.K_MO / MainActivity.K_EVENTOS;
    }

    public double fCustoPublicidade(){
        return MainActivity.K_PUBLICIDADE/MainActivity.K_EVENTOS;
    }

    public double fCustoEcad(){
        return MainActivity.K_ECAD / MainActivity.K_EVENTOS;
    }
    public double fCustoCPFL(){
        return MainActivity.K_CPFL / MainActivity.K_EVENTOS;
    }

    public double fCustoInformatica(){
        return MainActivity.K_INFORMATICA / MainActivity.K_EVENTOS;
    }

    public double fCustoContador(){
        return MainActivity.K_CONTADOR / MainActivity.K_EVENTOS;
    }

    public double fTotalFixo(){
        return  fCustoContador() + fCustoCPFL() + fCustoEcad() + fCustoInformatica() + fCustoMensalMo() + fCustoPublicidade();
    }

    public double fValorRetirada(){
        return Math.round(13.33 * NumConvidados + 1666.7);
    }

    public double fCustoVariavel(){
        return (MainActivity.K_Lavanderia + MainActivity.K_GLP);
    }

    public double fCustoCerimonia(CheckBox cerimonia){
        double custo = 0;

        if (cerimonia.isChecked()){
            custo = MainActivity.K_CUSTOCERIMONIA;
        } else{
            custo = 0;
        }
        return custo;
    }

    public double descontoDiaSemana (Calendar evento){
        double desconto = 0.8;
        String diaEvento = MainActivity.NomeDoMes(evento.DAY_OF_WEEK, 0);

        switch (diaEvento){
            case "sábado":
                desconto = 1;
                break;
            case "sexta":
                desconto = 0.9;
                break;
            case "domingo":
                desconto = 0.85;
                break;
        }
        return desconto;
    }



}
