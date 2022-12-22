package com.example.badulaqueenterprise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    //Data Atual
    Date dataHoraAtual = new Date();

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
    final public static int K_SAM = 12000;



    //BUFFET
    final public static int K_COZINHEIRA = 300;
    final public static int K_CHEF = 350;
    final public static int K_AuxCozinha = 180;
    final public static int K_Precozinha = 150;
    final public static int K_PERDAS = 10;
    final public static int K_CERVEJA = 6;

    final public static double custoCardapioBronze = 60;
    final public static double custoCardapioPrata = 120;
    final public static double custoCardapioOuro = 0;
    final public static double custoCardapioChurrasco = 0;
    final public static double custoCardapioMineira = 0;
    final public static double custoCardapioCoquetel = 0;
    final public static double custoCardapioBoteco = 0;


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
    final public static int K_BRONZE = 20;
    final public static int K_PRATA = 24;
    final public static int K_OURO = 28;
    final public static int K_SEMALCOOL = 16;
    final public static double K_PERSONAL = 4.5;
    final public static int K_PACKSEMALCOOL = 15;
    final public static int K_DRINKPP = 2;
    final public static int K_BARMAN = 150;
    final public static double K_CUSTOBRONZE = 7.64;
    final public static double K_CUSTOPRATA = 9.27;
    final public static double K_CUSTOOURO = 11.76;
    final public static double K_CERVEJA1 = 9.911;
    final public static double K_CERVEJA2 = 11.339;
    final public static double K_CERVEJA3 = 14.161;
    final public static double K_CHOPP = 12.113;

    //Financeiro
    final public static int K_FUTURA = 25;
    final public static int K_PERIODO = 12;
    final public static int K_IPCA = 9;

    //Formatações de Data
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    DatePickerDialog dpd;
    Calendar c;
    ImageView btnCal;
    public TextView textoData, dataExtenso , meses, valorEstrutura, valorBronze, valorPrata, valorOuro, valorMineira, valorCoquetel, valorChurrasco, valorBoteco ;
    EditText numConvidados;
    Date dt;
    String dataEvento;
    public CheckBox cerveja, chopp, bartender, cerimonia;
    Spinner spinner, spinnerPackBar;

    public TextView lblBronze, lblPrata;


    private long nm; //Numero de meses até o evento

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

    //Validar se número de meses é menor que FUTURA
    public boolean validarMeses(String data){
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

    //Ação do botão confirmar
    @SuppressLint("SetTextI18n")
    public void botaoConfirmar (View view){
        boolean camposValidados = validarCampos();

        if(camposValidados){
            int nc = Integer.parseInt(numConvidados.getText().toString()); //Numero Convidados
            int meses = (int) nm;

            Evento evento = new Evento(nc,meses);
            double valorIPCA = evento.fIPCA();
            double valorMargemContr = evento.fMargemContr();
            double valorLimpeza = faxina;
            double valorSupervisão = K_SUPERVISAO;
            double valorAjudante = K_AJUDANTE;
            int numGarcom = evento.fNumeroGarcom();
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


            //Cardapio bronze = new Cardapio("Bronze", custoCardapioBronze);
           //bronze.fCustoBuffet(valorCustoVariavel,valorTotalCozinha,valorCustoCervejaChopp,nc);

            String[] cardapioNome = {"Bronze", "Prata", "Ouro", "Churrasco", "Mineira", "Coquetel", "Boteco"};
            double[] cardapioPreco = {custoCardapioBronze,custoCardapioPrata,custoCardapioOuro,custoCardapioChurrasco,custoCardapioMineira,custoCardapioCoquetel,custoCardapioBoteco};

            double valorEspaco = 0;
            double valorPPFinal;
            for (int i = 0; i<cardapioPreco.length; i++){
                double valorCustoBuffet = valorCustoVariavel + valorTotalCozinha + (cardapioPreco[i] + valorCustoCervejaChopp) * nc;
                double valorCustoProposta = (valorCustoEspaco + cardapioPreco[i])/1-valorIPCA;
                double valorCustoBar = ((evento.fCustoBar(bartender, spinnerPackBar) + evento.fCustoMoBar(bartender))/(1-valorIPCA));
                double valorCustoContrato = (valorCustoBar + valorCustoProposta)/(1-valorIPCA);
                double margc = 1 - valorMargemContr;
                double valorEspacoInicial = valorCustoEspaco / margc;
                double valorBuffetInicial = valorCustoBuffet / margc + valorRetirada;
                double valorPPInicial = valorBuffetInicial/nc;
                double vppInicial = valorBuffetInicial / nc;
                double vpp_inc;
                if(meses>0){
                    vpp_inc = vppInicial / (1-valorIPCA) - vppInicial;
                }else{
                    vpp_inc = 0;
                }
                double desconto = evento.descontoDiaSemana(c);

                if(nc >= 150){
                    valorEspaco = K_ESPACO;
                }else if(nc>=100){
                    valorEspaco = 5800;
                }else{
                    valorEspaco = 5000;
                }
                double ved = Math.round(valorEspaco * desconto);
                double vpp_dec = (ved - valorCustoEspaco) / nc;
                valorPPFinal = Math.round(valorPPInicial + vpp_inc - vpp_dec);

                switch (i){
                    case 0:
                        valorBronze.setText("R$" + Double.toString(valorPPFinal));
                        break;
                    case 1:
                        valorPrata.setText("R$" + Double.toString(valorPPFinal));
                        break;
                    case 2:
                        valorOuro.setText("R$" + Double.toString(valorPPFinal));
                        break;
                    case 3:
                        valorChurrasco.setText("R$" + Double.toString(valorPPFinal));
                        break;
                    case 4:
                        valorMineira.setText("R$" + Double.toString(valorPPFinal));
                        break;
                    case 5:
                        valorCoquetel.setText("R$" + Double.toString(valorPPFinal));
                        break;
                    case 6:
                        valorBoteco.setText("R$" + Double.toString(valorPPFinal));
                        break;
                }
            }
            valorEstrutura.setText(Double.toString(valorEspaco));

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
        bartender = (CheckBox) findViewById(R.id.checkBoxBartender);
        cerimonia = (CheckBox) findViewById(R.id.checkBoxCerimonia);

        lblBronze = (TextView) findViewById(R.id.textBronze);
        lblPrata = (TextView) findViewById(R.id.textPrata);

        //Popular ComboBox(Spinner) Cerveja
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tiposCerveja, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setEnabled(false);

        //Popular ComboBox(Spinner) Pacotes Bar
        spinnerPackBar = findViewById(R.id.spinnerBar);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.packsBar, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPackBar.setAdapter(adapter1);
        spinnerPackBar.setEnabled(false);



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





    }
}


