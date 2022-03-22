package com.fatec.shewbaca;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.fatec.Ranking.Enviar;



public class Jogomemoria1 extends ActionBarActivity {

    Random r = new Random();
    TextView lblNum, lblErros, lblResultadoFinal, lblAcertos, lblTime, lblMais1;
    Button btn1,btn2,btn3,btn4,btn5,btn6, iniciar, btnProximo, btPublicar;
    String numeros=null;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int erros = 0,acertou=0, secs=0;
    int aux=0,cont=0;
    int selec1=0, selec2=0, selec3=0, selec4=0, selec5=0, selec6=0, selecionado=0; //para saber se o botao foi clicado
    int num;//guardar numero randomizado
    private int vetor[] = new int[6];
    //Segundo timer
    private int contT=0;
    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();

    //--------------------------banco------------------
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    //----------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogomemoria1);
        iniciar = (Button) findViewById(R.id.btnIniciar);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btnProximo = (Button) findViewById(R.id.btnProximo);
        btPublicar = (Button) findViewById(R.id.btPublicar);
        lblErros = (TextView)findViewById(R.id.lblErros);
        lblResultadoFinal = (TextView)findViewById(R.id.lblResultadoFinal);
        lblTime = (TextView)findViewById(R.id.lblTime);
        lblAcertos = (TextView)findViewById(R.id.lblAcertos);
        lblMais1 = (TextView)findViewById(R.id.lblMais1);

        //------------------------Botão Iniciar - Randomiza os números e inicia o tempo
        iniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Travarbotao();
                cont=0;
                for(int i=0; i<6;i++){
                    do{
                        cont=0;
                        Randomizar();
                        vetor[i]=num;
                        for(int j=0;j<i;j++){
                            if(vetor[j] == num){
                                cont+=1;
                            }
                        }
                    }while(cont == 2);

                }

                MostrarValores();
                ativaTimer();
                iniciar.setEnabled(false);

            }
        });

        btnProximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Jogomemoria1.this, Jogomemoria2.class);
                startActivity(it);
                finish();
            }
        });

        //------------------------Botões
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lblMais1.setVisibility(View.INVISIBLE);
                selecionado++;
                aux=0;
                btn1.setText(String.valueOf(vetor[0]));
                if(selecionado==2){
                    if(Verificar(vetor[0]) == true){
                        btn1.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    btn1.setEnabled(false);
                    selec1=1;

                }
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lblMais1.setVisibility(View.INVISIBLE);
                selecionado++;
                aux=0;
                btn2.setText(String.valueOf(vetor[1]));
                if(selecionado==2){
                    if(Verificar(vetor[1]) == true){
                        btn2.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    btn2.setEnabled(false);
                    selec2=1;

                }
            }//fim 2
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lblMais1.setVisibility(View.INVISIBLE);
                selecionado++;
                aux=0;
                btn3.setText(String.valueOf(vetor[2]));
                if(selecionado==2){
                    if(Verificar(vetor[2]) == true){
                        btn3.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    btn3.setEnabled(false);
                    selec3=1;

                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lblMais1.setVisibility(View.INVISIBLE);
                selecionado++;
                aux=0;
                btn4.setText(String.valueOf(vetor[3]));
                if(selecionado==2){
                    if(Verificar(vetor[3]) == true){
                        btn4.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    btn4.setEnabled(false);
                    selec4=1;

                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lblMais1.setVisibility(View.INVISIBLE);
                selecionado++;
                aux=0;
                btn5.setText(String.valueOf(vetor[4]));
                if(selecionado==2){
                    if(Verificar(vetor[4]) == true){
                        btn5.setVisibility(View.INVISIBLE);
                    }

                }
                else{
                    btn5.setEnabled(false);
                    selec5=1;

                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lblMais1.setVisibility(View.INVISIBLE);
                selecionado++;
                aux=0;
                btn6.setText(String.valueOf(vetor[5]));
                if(selecionado==2){
                    if(Verificar(vetor[5]) == true){
                        btn6.setVisibility(View.INVISIBLE);
                    }

                }
                else{
                    btn6.setEnabled(false);
                    selec6=1;
                }
            }
        });
    }

    public void btPublicar(View view){
        new ranking().execute();
        //Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void Acabou(){
        if(acertou==3){
            lblResultadoFinal.setText("Parabéns!!! Você venceu!");
            btnProximo.setVisibility(View.VISIBLE);
            btPublicar.setVisibility(View.VISIBLE);
            btPublicar.setEnabled(true);
            stop();
        }
    }


    public Boolean Verificar(int resp){   // Verifica se acertou
        Travarbotao();
        if(selec1 != 0){   //Verificar qual botão foi o primeiro a ser selecionado
            if(vetor[0] == resp){   //Verifica se acertou
                btn1.setVisibility(View.INVISIBLE);
                Acertou();
                return true;
            }
            erros++;
            NenhumSelecionado();
            return false;
        }
        else{
            if(selec2 != 0){
                if(vetor[1] == resp){
                    btn2.setVisibility(View.INVISIBLE);
                    Acertou();
                    return true;
                }
                erros++;
                NenhumSelecionado();
                return false;
            }
            else{
                if(selec3 != 0){
                    if(vetor[2] == resp){
                        btn3.setVisibility(View.INVISIBLE);
                        Acertou();
                        return true;
                    }
                    erros++;
                    NenhumSelecionado();
                    return false;
                }
                else{
                    if(selec4 != 0){
                        if(vetor[3] == resp){
                            btn4.setVisibility(View.INVISIBLE);
                            Acertou();
                            return true;
                        }
                        erros++;
                        NenhumSelecionado();
                        return false;
                    }
                    else{
                        if(selec5 != 0){
                            if(vetor[4] == resp){
                                btn5.setVisibility(View.INVISIBLE);
                                Acertou();
                                return true;
                            }
                            erros++;
                            NenhumSelecionado();
                            return false;
                        }
                        else{
                            if (vetor[5] == resp){
                                btn6.setVisibility(View.INVISIBLE);
                                Acertou();
                                return true;
                            }
                            else{
                                erros++;
                                NenhumSelecionado();
                                return false;
                            }
                        }
                    }
                }
            }
        }
    }


    public void Acertou(){
        aux=30;
        acertou++;
        lblMais1.setVisibility(View.VISIBLE);
        NenhumSelecionado();
    }

    public void Randomizar(){
        num = r.nextInt(3) + 1;
        numeros = String.valueOf(num);
    }


    public void NenhumSelecionado(){
        selec1=0;
        selec2=0;
        selec3=0;
        selec4=0;
        selec5=0;
        selec6=0;
        lblAcertos.setText(String.valueOf(acertou));
        Acabou();
    }


    public void Voltarbotao(){
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn1.setText(String.valueOf(""));
        btn2.setText(String.valueOf(""));
        btn3.setText(String.valueOf(""));
        btn4.setText(String.valueOf(""));
        btn5.setText(String.valueOf(""));
        btn6.setText(String.valueOf(""));
        lblErros.setText(String.valueOf(erros));//exibir número de erros
        selecionado=0;
    }

    public void Travarbotao(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
    }

    public void MostrarValores(){
        btn1.setText(String.valueOf(vetor[0]));
        btn2.setText(String.valueOf(vetor[1]));
        btn3.setText(String.valueOf(vetor[2]));
        btn4.setText(String.valueOf(vetor[3]));
        btn5.setText(String.valueOf(vetor[4]));
        btn6.setText(String.valueOf(vetor[5]));
    }


    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        //Log.d("Debug", "Timer ativado");
                        contT++;
                        // lblTime.setText(String.valueOf(contT));
                        if(contT == 4){
                            Voltarbotao();
                            startTime = SystemClock.uptimeMillis();//iniciar tempo
                            customHandler.postDelayed(updateTimerThread, 0);
                            timerAtual.cancel();
                        }
                    }
                });
            }};

        timerAtual.schedule(task, 0, 1000);
    }


    public void stop(){//Parar tempo
        timeSwapBuff += timeInMilliseconds;
        customHandler.removeCallbacks(updateTimerThread);
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;
    }



    private Runnable updateTimerThread = new Runnable() {
        /**
         * cria e executa um contador.
         */
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            secs = (int) ((updatedTime / 1000));
            lblTime.setText(String.valueOf(secs));

            if(aux==30 && selecionado==2){
                Voltarbotao();
                aux=0;
            }
            else
            if(aux>=30){
                aux=0;
            }
            else{
                aux++;
            }
            customHandler.postDelayed(this, 0);
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jogomemoria1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class ranking extends AsyncTask<String, String, String> {

        /**
         * Mostra o dialogo antes de iniciar
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Jogomemoria1.this);
            pDialog.setMessage("Aguarde! Enviando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Criando ranking
         * */
        protected String doInBackground(String... args) {
            String ret = "";
            String nome ="Anon";
            String chave ="";
            try{
                InputStream inputStream = openFileInput("user.config");
                if(inputStream!=null){
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receivedString ="";
                    StringBuilder stringBuilder= new StringBuilder();
                    while((receivedString= bufferedReader.readLine())!=null){
                        stringBuilder.append(receivedString);
                    }
                    inputStream.close();
                    ret=stringBuilder.toString();
                    String[] dados = ret.split("-");
                    nome=dados[0];
                    chave = dados[2];
                }
            }catch(IOException e){
                Log.e("Exception", "File read failed: " + e.toString());
            }
            Enviar e = new Enviar(String.valueOf(acertou),String.valueOf(secs),"6",nome,chave);
            if(e.ranking()==0){
                Intent i = new Intent(getApplicationContext(), Salvo.class);
                startActivity(i);
                //finish();
            }else{
                Intent i = new Intent(getApplicationContext(), NaoSalvo.class);
                startActivity(i);
                //finish();
            }
            return null;
        }
        /**
         * Fecha o dialogo depois que terminar
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }

    }

//----------------------------------------------------------------------------------------

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            //Seu código aqui
            timerAtual.cancel();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }


}
