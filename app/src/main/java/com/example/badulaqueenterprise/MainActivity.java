package com.example.badulaqueenterprise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    final public static int K_PERIODO = 25;

    //Data Atual
    Date dataHoraAtual = new Date();

    // Numero de meses até o evento
    private long nm;

    //Formatações de Data
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    DatePickerDialog dpd;
    Calendar c;
    ImageView btnCal;
    TextView textoData, dataExtenso , meses, valorEstrutura, valorBronze, valorPrata, valorOuro, valorMineira, valorCoquetel, valorChurrasco, valorBoteco ;
    EditText numConvidados;
    Date dt;
    String dataEvento;
    CheckBox cerveja, chopp;

    public void limparCampos(){
        textoData.setText("");
        dataExtenso.setText("");
        numConvidados.setText("");
        meses.setText("");
        valorEstrutura.setText("");
        valorBronze.setText("");
        valorPrata.setText("");
        valorOuro.setText("");
        valorMineira.setText("");
        valorCoquetel.setText("");
        valorChurrasco.setText("");
        valorBoteco.setText("");
    }

    public void btnLimparCampos (View view){
        limparCampos();
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

    public String dataPorExtenso(String data) throws ParseException {
        dt = formatoData.parse(data);
        int d = dt.getDate();
        int m = dt.getMonth() + 1;
        int a = dt.getYear() + 1900;

        Calendar date = new GregorianCalendar(a, m - 1, d);
        int ds = date.get(Calendar.DAY_OF_WEEK);

        return (DiaDaSemana(ds, 0) + ", " + d + " de " + NomeDoMes(m, 0) + " de " + a);
    }

    //Validar se campo de data e convidados estão preenchidos
    public boolean validarCampos(){
        boolean camposValidados = true;
        String textConvidados = numConvidados.getText().toString();

        if (textConvidados.equals("")){
            camposValidados = false;
        }
        return camposValidados;
    }

    //Validar se número de meses é menor que K_PERIODO
    public boolean validarMeses(String data){
        boolean mesesValidados = true;

        if (data.equals("")){
            Toast.makeText(getApplicationContext(),"Preencher data",Toast.LENGTH_SHORT);
        }else{
            LocalDate dataF = LocalDate.parse(data, dtf);
            LocalDate dataAtual = LocalDate.now();
            nm = ChronoUnit.MONTHS.between(dataAtual,dataF);
        }
        if(nm > K_PERIODO){
            mesesValidados = false;
        }
        return mesesValidados;
    }

    //Ação do botão confirmar

    public void botaoConfirmar (View view){
        boolean camposValidados = validarCampos();

        if(camposValidados){
            Toast.makeText(getApplicationContext(), "Rodar Código!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Preencher Campos!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Iniciar variáveis
        textoData = (TextView) findViewById(R.id.textDataEvento);
        btnCal = (ImageView) findViewById(R.id.btnCalendar);
        dataExtenso = (TextView) findViewById(R.id.textDataExtenso);
        numConvidados = (EditText) findViewById(R.id.editTextConvidados);
        meses = (TextView) findViewById(R.id.editTextMeses);
        valorEstrutura = (TextView) findViewById(R.id.editTextValorEstrutura);
        valorBronze = (TextView) findViewById(R.id.editTextValorBronze);
        valorPrata = (TextView) findViewById(R.id.editTextValorPrata);
        valorOuro = (TextView) findViewById(R.id.editTextValorOuro);
        valorBoteco = (TextView) findViewById(R.id.editTextValorBoteco);
        valorChurrasco = (TextView) findViewById(R.id.editTextValorChurrasco);
        valorCoquetel = (TextView) findViewById(R.id.editTextValorCoquetel);
        valorMineira = (TextView) findViewById(R.id.editTextValorMineira);
        cerveja = (CheckBox) findViewById(R.id.checkBoxCerveja);
        chopp = (CheckBox) findViewById(R.id.checkBoxChopp);

        //Popular ComboBox (Spinner)
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tiposCerveja, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setEnabled(false);


        //Listener para botão do calendário
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c = Calendar.getInstance();

                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int ano = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(MainActivity.this,  new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        int mes = mMonth+1;
                        if (mes < 10 && mDay <10){
                            textoData.setText("0" + mDay + "/0" + mes + "/" + mYear);
                        }else if(mes > 9 && mDay > 9){
                            textoData.setText(mDay + "/" + mes + "/" + mYear);
                        }else if (mes < 10 && mDay > 9){
                            textoData.setText(mDay + "/0" + mes + "/" + mYear);
                        }else if (mes > 9 && mDay<10){
                            textoData.setText("0" + mDay + "/" + mes + "/" + mYear);
                        }
                    }
                }, ano,mes,dia);
                dpd.show();
            }
        });

        textoData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dataEvento = textoData.getText().toString();
                try {
                    Date date = formatoData.parse(dataEvento);

                    if(!validarMeses(dataEvento)){
                        dataExtenso.setText("");
                        meses.setText("");
                        textoData.setText("");
                        throw new Exception("Número de meses acima de " + K_PERIODO + "!");
                    }else if (date.before(dataHoraAtual)){
                        dataExtenso.setText("");
                        meses.setText("");
                        textoData.setText("");
                        throw new Exception("Data já passou!");
                    }else{
                        dataExtenso.setText(dataPorExtenso(dataEvento));
                        meses.setText(String.valueOf(nm));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        );

        cerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cerveja.isChecked()){
                    spinner.setEnabled(true);
                    chopp.setEnabled(false);
                }else{
                    chopp.setEnabled(true);
                    spinner.setEnabled(false);
                }


            }
        });

        chopp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chopp.isChecked()){
                    spinner.setEnabled(false);
                    cerveja.setEnabled(false);
                }else{
                    cerveja.setEnabled(true);
                    spinner.setEnabled(true);
                }
            }
        });

    }
}


