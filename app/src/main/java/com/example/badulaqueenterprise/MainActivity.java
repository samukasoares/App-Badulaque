package com.example.badulaqueenterprise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    //CUSTOS
    final public static int faxina = 120;

    //ESTIMATIVAS
    final public static int K_EVENTOS = 35;
    final public static int K_CONVIDADOS = 140;

    //CUSTOS FIXOS ANO
    final public static int K_SINAL = 2000;
    final public static int K_CONTADOR = 2160;
    final public static int K_MO = 31200;
    final public static int K_PUBLICIDADE = 6000;
    final public static int K_ECAD = 9600;
    final public static int K_INFORMATICA = 2400;
    final public static int K_CPFL = 28000;

    //BUFFET
    final public static int K_COZINHEIRA = 300;
    final public static int K_CHEF = 350;
    final public static int K_AuxCozinha = 180;
    final public static int K_Precozinha = 150;
    final public static int K_PERDAS = 10;
    final public static int K_CERVEJA = 6;

    final public static double custoCardapioBronze = 39.34;
    final public static double custoCardapioPrata = 52.82;
    final public static double custoCardapioOuro = 62.72;
    final public static double custoCardapioChurrasco = 54.76;
    final public static double custoCardapioMineira = 47.83;
    final public static double custoCardapioCoquetel = 57.31;
    final public static double custoCardapioBoteco = 35.43;

    final public static double K_CERVEJA1 = 9.911;
    final public static double K_CERVEJA2 = 11.339;
    final public static double K_CERVEJA3 = 14.161;
    final public static double K_CHOPP = 12.113;

    //ESPAÇO
    final public static int K_ESPACO = 6400;
    final public static int K_GARCOM = 150;
    final public static int K_FAXINEIRA = 150;
    final public static int K_AJUDANTE = 100;
    final public static int K_BANHEIRO = 100;
    final public static int K_SUPERVISAO = 350;
    final public static int K_CUSTOCABINE = 800;
    final public static int K_CUSTOCERIMONIA = 800;
    final public static int K_KIDS = 370;
    final public static int K_HE = 15; //Hora Extra por pessoa
    final public static double K_Lavanderia = 1.2; //Por Pessoa
    final public static double K_GLP = 1.2;
    final public static int K_FAXINA = 200;
    final public static int K_AuxCozMin = 1;
    final public static int K_CozinheiraMin = 1;

    //BAR
    final public static int K_SEMALCOOL = 16;
    final public static double K_PERSONAL = 4.5;
    final public static int K_PACKSEMALCOOL = 15;
    final public static int K_BARMAN = 150;
    final public static double K_CUSTOBRONZE = 20;
    final public static double K_CUSTOPRATA = 24;
    final public static double K_CUSTOOURO = 28;


    //FINANCEIRO
    final public static int K_FUTURA = 25;
    final public static int K_PERIODO = 12;
    final public static double K_IPCA = 9;

    //FORMATAÇÕES DE DATA
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Date dataHoraAtual = new Date();
    private long nm; //Numero de meses até o evento
    int ds;  //Dia da Semana do Evento (0-6)

    DatePickerDialog dpd;
    Calendar c;
    ImageView btnCal;
    public TextView textoData, dataExtenso , meses, valorEstrutura, valorBronze, valorPrata, valorOuro, valorMineira, valorCoquetel, valorChurrasco, valorBoteco, valorBar, valorCabine, valorProposta;
    EditText numConvidados;
    Date dt;
    String dataEvento;
    public CheckBox cerveja, chopp, bartender, cerimonia, cabine;
    Spinner spinner, spinnerPackBar;
    Evento evento;

    double valorIPCA = 0;

    //VARIÁVEIS PARA CÁLCULO DA PROPOSTA TOTAL
    double[] cardapioPreco = new double[7];
    double vEspacoFinal =0;
    double valoroBar = 0;
    double vlrCabine =0;

    public TextView lblBronze, lblPrata, lblOuro, lblChurrasco, lblMineira, lblCoquetel, lblBoteco;

    public void iniciarVariaveis(){
        textoData = (TextView) findViewById(R.id.textDataEvento);
        btnCal = (ImageView) findViewById(R.id.btnCalendar);
        dataExtenso = (TextView) findViewById(R.id.textDataExtenso);
        numConvidados = (EditText) findViewById(R.id.editTextConvidados);
        meses = (TextView) findViewById(R.id.editTextMeses);
        valorProposta = (TextView) findViewById(R.id.textValorTotalProposta);

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
        bartender = (CheckBox) findViewById(R.id.checkBoxBartender);
        cerimonia = (CheckBox) findViewById(R.id.checkBoxCerimonia);
        cabine = (CheckBox) findViewById(R.id.checkBoxCabine);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapterCerveja = ArrayAdapter.createFromResource(this, R.array.tiposCerveja, android.R.layout.simple_spinner_item);
        popularComboBox(spinner,adapterCerveja);

        spinnerPackBar = findViewById(R.id.spinnerBar);
        ArrayAdapter<CharSequence> adapterPackBar = ArrayAdapter.createFromResource(this,R.array.packsBar, android.R.layout.simple_spinner_item);
        popularComboBox(spinnerPackBar,adapterPackBar);

        valorBar = (TextView) findViewById(R.id.editTextValorBar);
        valorCabine = (TextView) findViewById(R.id.editTextValorCabine);

        lblBronze = (TextView) findViewById(R.id.textBronze);
        lblPrata = (TextView) findViewById(R.id.textPrata);
        lblOuro = (TextView) findViewById(R.id.textOuro);
        lblChurrasco = (TextView) findViewById(R.id.textChurrasco);
        lblMineira = (TextView) findViewById(R.id.textMineira);
        lblBoteco = (TextView) findViewById(R.id.textBoteco);
        lblCoquetel = (TextView) findViewById(R.id.textCoquetel);
    }

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
        valorBar.setText("");
        valorCabine.setText("");
        valorProposta.setText("");
        resetCampos();
        cerveja.setChecked(false);
        chopp.setChecked(false);
        bartender.setChecked(false);
        cerimonia.setChecked(false);
        cabine.setChecked(false);
        spinnerPackBar.setEnabled(false);
        spinner.setEnabled(false);
    }

    public void btnLimparCampos (View view){
        limparCampos();
    }

    public void resetCampos(){
        valorBronze.setTypeface(null, Typeface.NORMAL);
        lblBronze.setTypeface(null, Typeface.NORMAL);
        lblBronze.setTextColor(getResources().getColor(R.color.black));
        valorBronze.setTextColor(getResources().getColor(R.color.cinzaEscuro));

        valorPrata.setTypeface(null, Typeface.NORMAL);
        lblPrata.setTypeface(null, Typeface.NORMAL);
        lblPrata.setTextColor(getResources().getColor(R.color.black));
        valorPrata.setTextColor(getResources().getColor(R.color.cinzaEscuro));


        valorOuro.setTypeface(null, Typeface.NORMAL);
        lblOuro.setTypeface(null, Typeface.NORMAL);
        lblOuro.setTextColor(getResources().getColor(R.color.black));
        valorOuro.setTextColor(getResources().getColor(R.color.cinzaEscuro));


        valorChurrasco.setTypeface(null, Typeface.NORMAL);
        lblChurrasco.setTypeface(null, Typeface.NORMAL);
        lblChurrasco.setTextColor(getResources().getColor(R.color.black));
        valorOuro.setTextColor(getResources().getColor(R.color.cinzaEscuro));


        valorMineira.setTypeface(null, Typeface.NORMAL);
        lblMineira.setTypeface(null, Typeface.NORMAL);
        lblMineira.setTextColor(getResources().getColor(R.color.black));
        valorMineira.setTextColor(getResources().getColor(R.color.cinzaEscuro));


        valorCoquetel.setTypeface(null, Typeface.NORMAL);
        lblCoquetel.setTypeface(null, Typeface.NORMAL);
        lblCoquetel.setTextColor(getResources().getColor(R.color.black));
        valorCoquetel.setTextColor(getResources().getColor(R.color.cinzaEscuro));


        valorBoteco.setTypeface(null, Typeface.NORMAL);
        lblBoteco.setTypeface(null, Typeface.NORMAL);
        lblBoteco.setTextColor(getResources().getColor(R.color.black));
        valorBoteco.setTextColor(getResources().getColor(R.color.cinzaEscuro));

        valorProposta.setText("");
    }

    public void lblClick (TextView x , int posicao ){
        try{
            resetCampos();
            valorProposta.setText(evento.CalcularProposta(vEspacoFinal,cardapioPreco,vlrCabine,valoroBar,posicao));
            metodos.EditarTextView(x);
            metodos.EditarTextView(x);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Nenhum valor para calcular!", Toast.LENGTH_SHORT).show();
        }
    }

    public String dataPorExtenso(String data) throws ParseException {
        dt = formatoData.parse(data);
        int d = dt.getDate();
        int m = dt.getMonth() + 1;
        int a = dt.getYear() + 1900;

        Calendar date = new GregorianCalendar(a, m - 1, d);
        ds = date.get(Calendar.DAY_OF_WEEK);

        return (metodos.DiaDaSemana(ds, 0) + ", " + d + " de " + metodos.NomeDoMes(m, 0) + " de " + a);
    }

    public boolean validarCampos(){
        //Validar se campo de data e convidados estão preenchidos
        boolean camposValidados = true;
        String textConvidados = numConvidados.getText().toString();

        if (textConvidados.equals("")){
            camposValidados = false;
        }
        return camposValidados;
    }

    public boolean validarMeses(String data){
        //Validar se número de meses é menor que FUTURA
        boolean mesesValidados = true;

        if (data.equals("")){
            Toast.makeText(getApplicationContext(),"Preencher data",Toast.LENGTH_SHORT);
        }else{
            LocalDate dataF = LocalDate.parse(data, dtf);
            LocalDate dataAtual = LocalDate.now();
            nm = ChronoUnit.MONTHS.between(dataAtual,dataF);
        }
        if(nm > K_FUTURA){
            mesesValidados = false;
        }
        return mesesValidados;
    }

    public void calculaTudo(){
        double nc = Integer.parseInt(numConvidados.getText().toString()); //Numero Convidados
        double meses = (double) nm;

        evento = new Evento(nc,meses);
        double valorMargemContr = evento.fMargemContr();
        double valorLimpeza = faxina;
        double valorSupervisão = K_SUPERVISAO;
        double valorAjudante = K_AJUDANTE;
        double numGarcom = evento.fNumeroGarcom();
        double valorGarcom = numGarcom * K_GARCOM;
        int valorKids = K_KIDS;

        double valorTotalFixo = evento.fTotalFixo();

        double valorCustoEspacoMO = valorSupervisão + valorGarcom + valorLimpeza + valorAjudante + valorKids;
        double valorCustoEspaco =  valorTotalFixo + valorCustoEspacoMO;

        double valorTotalCozinha = evento.fCustoCozinha();

        double valorRetirada = evento.fValorRetirada();
        double valorCustoCervejaChopp = evento.fCustoCervejaChopp(cerveja,chopp,spinner);
        double valorCustoVariavel = evento.fCustoVariavel();
        double valorCerimonia = evento.fCustoCerimonia(cerimonia);

        valoroBar = (metodos.ArredondaCentena(evento.fCustoBar(bartender, spinnerPackBar)/(1-valorIPCA)));
        double[] cardapioCusto = {custoCardapioBronze,custoCardapioPrata,custoCardapioOuro,custoCardapioChurrasco,custoCardapioMineira,custoCardapioCoquetel,custoCardapioBoteco};
        double valorPPFinal;


        //Calcula preço Por Pessoa de Todos os Cardapios
        for (int i = 0; i<cardapioCusto.length; i++){
            double valorCustoBuffet = (valorCustoVariavel + valorTotalCozinha + (cardapioCusto[i] + valorCustoCervejaChopp) * nc)/(1-valorMargemContr);
            //double valorEspacoInicial = valorCustoEspaco / margc;
            double valorBuffetInicial = valorCustoBuffet + valorRetirada;
            double vppInicial = valorBuffetInicial / nc;
            double valorEspaco =0;
            double vEspaco = 0;

            valorIPCA = evento.fIPCA();

            double vpp_inc;
            if(meses>0){
                vpp_inc = (vppInicial / (1-valorIPCA)) - vppInicial;
            }else{
                vpp_inc = 0;
            }
            double desconto = evento.descontoDiaSemana(ds);


            if(nc >= 150){
                valorEspaco = K_ESPACO;
            }else if(nc>=100){
                valorEspaco = 5800;
            }else if(nc<100){
                valorEspaco =5000;
            }

            vEspaco = Math.round(valorEspaco * desconto); //Considerando Desconto de Dia
            double vpp_dec = (vEspaco - valorCustoEspaco) / nc;

            valorPPFinal = Math.ceil(vppInicial + vpp_inc - vpp_dec);

            vEspacoFinal = vEspaco + valorCerimonia;
            cardapioPreco[i] = valorPPFinal;
            switch (i){
                case 0:
                    valorBronze.setText("R$" + Double.toString(valorPPFinal) + "0");
                    break;
                case 1:
                    valorPrata.setText("R$" + Double.toString(valorPPFinal) + "0");
                    break;
                case 2:
                    valorOuro.setText("R$" + Double.toString(valorPPFinal) + "0");
                    break;
                case 3:
                    valorChurrasco.setText("R$" + Double.toString(valorPPFinal) + "0");
                    break;
                case 4:
                    valorMineira.setText("R$" + Double.toString(valorPPFinal) + "0");
                    break;
                case 5:
                    valorCoquetel.setText("R$" + Double.toString(valorPPFinal) + "0");
                    break;
                case 6:
                    valorBoteco.setText("R$" + Double.toString(valorPPFinal) + "0");
                    break;
            }
        }
        valorEstrutura.setText("R$" + Double.toString(vEspacoFinal) + "0"); //Valor Estrutura
        valorBar.setText("R$" + valoroBar + "0"); //Valor Bar
        //Valor Cabine

    }

    public void popularComboBox (Spinner x, ArrayAdapter y){
        y.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        x.setAdapter(y);
        x.setEnabled(false);
    }

    public void definirListeners(){
        btnCal.setOnClickListener(new View.OnClickListener() {
            //Listener para botão do calendário
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
                                             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                                             @Override
                                             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                                             @Override
                                             public void afterTextChanged(Editable editable) {
                                                 dataEvento = textoData.getText().toString();
                                                 try {
                                                     Date date = formatoData.parse(dataEvento);

                                                     if(!validarMeses(dataEvento)){
                                                         dataExtenso.setText("");
                                                         meses.setText("");
                                                         textoData.setText("");
                                                         throw new Exception("Número de meses acima de " + K_FUTURA + "!");
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
                                         });

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
                    spinner.setEnabled(false );
                }

            }
        });

        bartender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bartender.isChecked()){
                    spinnerPackBar.setEnabled(true);
                }else{
                    spinnerPackBar.setEnabled(false);
                }
            }
        });

        lblBronze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblClick(valorBronze,0);
            }
        });

        lblPrata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblClick(valorPrata,1);

            }
        });

        lblOuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblClick(valorOuro,2);
            }
        });

        lblChurrasco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblClick(valorChurrasco,3);
            }
        });

        lblMineira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblClick(valorMineira,4);
            }
        });

        lblCoquetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblClick(valorCoquetel,5);
            }
        });

        lblBoteco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblClick(valorBoteco,6);
            }
        });

        chopp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!valorBronze.getText().toString().equals("")){
                    calculaTudo();
                }
            }
        });

        bartender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!valorBronze.getText().toString().equals("")){
                    calculaTudo();
                }
            }
        });

        cerveja.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!valorBronze.getText().toString().equals("")){
                    calculaTudo();
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!valorBronze.getText().toString().equals("")){
                    calculaTudo();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerPackBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!valorBronze.getText().toString().equals("")){
                    calculaTudo();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void botaoConfirmar (View view){
        //Ação do botão confirmar
        boolean camposValidados = validarCampos();
        if(camposValidados){
            calculaTudo();
        }else{
            Toast.makeText(getApplicationContext(), "Preencher Campos!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarVariaveis();
        definirListeners();
    }
}


