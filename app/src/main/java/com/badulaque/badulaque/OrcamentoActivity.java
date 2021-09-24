package com.badulaque.badulaque;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class OrcamentoActivity extends AppCompatActivity {

    private EditText textDataEvento, textMeses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);


        textDataEvento = findViewById(R.id.dataText);
        textMeses = findViewById(R.id.textNM);

        //Recuperar dados enviados
        Bundle dados = getIntent().getExtras();
        String dataEvento = dados.getString("DataEvento");
        long meses = dados.getLong("NumeroMeses");

        //Configurar valores recuperados
        textDataEvento.setText(dataEvento);
        textMeses.setText(String.valueOf(meses));



    }
}