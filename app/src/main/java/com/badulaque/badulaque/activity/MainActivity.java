package com.badulaque.badulaque.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.badulaque.badulaque.R;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import br.com.sapereaude.maskedEditText.MaskedEditText;

public class MainActivity extends AppCompatActivity{

    // Constantes
    final public static int K_PERIODO = 25;

    private EditText textData, textNumConvidados;
    private CheckBox opcBar, opcDecor, opcCabine, opcCerimonial;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private long nm; // Numero de meses até o evento
    private ImageButton btnCal;
    private MaskedEditText editData;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        textNumConvidados = findViewById(R.id.editTextConv);
        opcBar = findViewById(R.id.opcBar);
        opcDecor = findViewById(R.id.opcDecor);
        opcCabine = findViewById(R.id.opcCabine);
        opcCerimonial = findViewById(R.id.opcCerimonial);
        btnCal = findViewById(R.id.btnCal);
        editData = findViewById(R.id.maskedDate);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.NavigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.bar:
                        item.setChecked(true);
                        Intent bar = new Intent(getApplicationContext(), BardulaqueActivity.class);
                        startActivity(bar);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();

                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int ano = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(MainActivity.this,  new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        int mes = mMonth+1;
                        if (mes < 10 && mDay <10){
                            editData.setText("0" + mDay + "0" + mes + "" + mYear);
                        }else if(mes > 9 && mDay > 9){
                            editData.setText(mDay + "" + mes + "" + mYear);
                        }else if (mes < 10 && mDay > 9){
                            editData.setText(mDay + "0" + mes + "" + mYear);
                        }else if (mes > 9 && mDay<10){
                            editData.setText("0" + mDay + "" + mes + "" + mYear);
                        }

                    }
                }, ano,mes,dia);
                dpd.show();
            }
        });

    }

    public void DisplayMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }


    public void botaoClicado(View view){
        String textDataEvento = editData.getText().toString();
        String textConvidados = textNumConvidados.getText().toString();

        boolean camposValidados = validarCampos();
        boolean mesesValidados = validarMeses();

        if(!camposValidados){
            Toast.makeText(getApplicationContext(), "Preencher Lacunas!", Toast.LENGTH_SHORT).show();
        }else if(!mesesValidados){
            Toast.makeText(getApplicationContext(), "Número de meses acima de "+ K_PERIODO, Toast.LENGTH_SHORT).show();
            }else{
            Intent intent = new Intent (getApplicationContext(), OrcamentoActivity.class);

            //Passando os dados
            intent.putExtra("DataEvento",textDataEvento);
            intent.putExtra("NumeroMeses",nm);

            startActivity(intent);
        }



        }


    public boolean validarCampos(){
        boolean camposValidados = true;
        String textDataEvento = editData.getText().toString();
        String textConvidados = textNumConvidados.getText().toString();

        if (textDataEvento.equals("") || textConvidados.equals("")){
            camposValidados = false;
        }
        return camposValidados;
    }


    public boolean validarMeses(){
        boolean mesesValidados = true;
        String textDataEvento = editData.getText().toString();

        if (textDataEvento.equals("")){
            Toast.makeText(getApplicationContext(),"Preencher data",Toast.LENGTH_SHORT);
        }else{
            LocalDate dataF = LocalDate.parse(textDataEvento, dtf);
            LocalDate dataAtual = LocalDate.now();
            nm = ChronoUnit.MONTHS.between(dataAtual,dataF);
        }

        if(nm > K_PERIODO){
            mesesValidados = false;
        }
        return mesesValidados;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}