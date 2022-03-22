package com.fatec.shewbaca;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import android.os.CountDownTimer;

import com.fatec.Ranking.Enviar;

public class JogoMaior extends ActionBarActivity {

    public Button btesq, btdir, btjogar,btPublicar;
    public TextView txtesq, txtdir, txttempo,txtmensagem;
    public TextView d1,d2;
    public String a1,a2,a3,a4;
    private Handler handler;
    private int contT=0,secs=0,valor=19;
    private TimerTask task;
    private Timer timerAtual = new Timer();
    public Random r = new Random();
    public int esq, dir, totalesq, totaldir;
    public String r1,r2;
    public TextView txtpontuacao;
    public int pontos=0,qtd=4,vezes=0,fase=1;
    public int number;
    private boolean Running = false, primeira=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_maior);
        txtesq =(TextView)this.findViewById(R.id.tesq);
        txtdir =(TextView)this.findViewById(R.id.tdir);
        btesq =(Button)this.findViewById(R.id.btesq);
        btdir =(Button)this.findViewById(R.id.btdir);
        btjogar=(Button)this.findViewById(R.id.btjogarmaior);
        btPublicar = (Button) findViewById(R.id.btPublicar);
        txtpontuacao=(TextView)this.findViewById(R.id.txtpontomaior);
        txttempo= (TextView)this.findViewById(R.id.txttempoMaior);
        txtmensagem=(TextView)this.findViewById(R.id.textView13);
        handler = new Handler();
        pontos=0;
        btesq.setEnabled(false);
        btdir.setEnabled(false);
        totaldir = 0;
        totalesq = 0;

    }

    //--------------------------banco------------------
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    //----------------------------------------------------

    public void btPublicar(View view){
        new ranking().execute();
        //Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        contT++;
                        secs++;
                        if(contT == 3){
                            //faz a limpeza do campo apos 3 segundos
                            txtesq.setText("");
                            txtdir.setText("");
                            valor--;
                            vezes++;
                            fazer();
                            if(fase == 1)
                                if(vezes == 4){
                                    contT++;
                                    valor+=4;
                                    Responder();
                                }
                                else
                                    contT=0;
                            else {
                                if(fase ==2){
                                    if(vezes == 7) {
                                        contT++;
                                        valor+=7;
                                        Responder();
                                    }
                                    else
                                        contT=0;
                                }
                                else{
                                    if(fase==3){
                                        if(vezes == 10) {
                                            contT++;
                                            valor+=10;
                                            Responder();
                                        }
                                        else
                                            contT=0;
                                    }
                                    else{
                                        Fim();
                                    }
                                }
                            }
                        }
                        if(fase==4)
                            Fim();
                    }
                });
            }};

        timerAtual.schedule(task, 0, 1000);
        //contT=0;
    }
    //função fazer realizar o randomize dos valores, guarda no total
    public void fazer(){
        esq = r.nextInt(valor)+1;
        dir = r.nextInt(valor)+1;
        txtesq.setText(String.valueOf(esq));
        txtdir.setText(String.valueOf(dir));
        totalesq= totalesq + esq;
        totaldir= totaldir + dir;
        //ativaTimer();
        esq = 0;
        dir = 0;
        //contT = 0;
    }

    public void Bloquearb(){
        btdir.setEnabled(false);
        btesq.setEnabled(false);
    }

    public void Fim(){
        txtmensagem.setText("Parábens!!! Você acertou os 3 níveis");
        btjogar.setText("Jogar Novamente");
        btPublicar.setVisibility(View.VISIBLE);
        btPublicar.setEnabled(true);
        btjogar.setEnabled(true);
        fase=1;
        valor=19;
        vezes=0;
        totaldir = 0;
        totalesq = 0;
        txtesq.setText("0");
        txtdir.setText("0");
    }

    public void Responder(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Resposta!");
        alertDialog.setMessage("Responda apertando o botao esquerdo ou direito");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
            }
        });
        alertDialog.show();
        btdir.setEnabled(true);
        btesq.setEnabled(true);
    }

    public void iniciaMaior(View view){
        Bloquearb();
        btjogar.setText("Jogar");
        btPublicar.setVisibility(View.INVISIBLE);
        esq = r.nextInt(29)+1;
        dir = r.nextInt(29)+1;
        contT=0;
        txtesq.setText(String.valueOf(esq));
        txtdir.setText(String.valueOf(dir));
        totalesq= totalesq + esq;
        totaldir= totaldir + dir;
        //ativaTimer();
        esq = 0;
        dir = 0;
        // contT = 0;
        //fase1();
        btjogar.setEnabled(false);
        if(fase==1 && primeira) {
            primeira=false;
            ativaTimer();
        }
        if(fase==1){
            pontos=0;
            txtpontuacao.setText("0");
        }
    }

    public void respesq(View view){
        Bloquearb();
        if(totalesq >= totaldir){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Parabéns!");
            alertDialog.setMessage("Você acertou!");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                    Novo();
                }
            });
            alertDialog.show();
        }
        if(totaldir > totalesq){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Eita!");
            alertDialog.setMessage("Você errou!!");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions

                    Errou();

                }
            });
            alertDialog.show();
        }
    }
    public void respdir(View view){
        Bloquearb();
        if(totaldir >= totalesq) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Parabéns!");
            alertDialog.setMessage("Você acertou!");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                    Novo();
                }
            });
            alertDialog.show();
        }
        if(totalesq > totaldir){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Eita!");
            alertDialog.setMessage("Você errou!!");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                    Errou();

                }
            });
            alertDialog.show();
        }
    }

    public void Novo(){
        pontos = pontos + 1;
        valor=19;
        fase++;
        vezes=0;
        totaldir = 0;
        totalesq = 0;
        txtpontuacao.setText(String.valueOf(pontos));
        txtdir.setText("0");
        txtesq.setText("0");
        btjogar.setEnabled(true);
    }

    public void Errou(){
        totaldir = 0;
        totalesq = 0;
        vezes=0;
        valor=19;
        txtdir.setText("0");
        txtesq.setText("0");
        btjogar.setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jogo_maior, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    //Programar botão voltar do android
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            //Seu código aqui
            timerAtual.cancel();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }



    class ranking extends AsyncTask<String, String, String> {

        /**
         * Mostra o dialogo antes de iniciar
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(JogoMaior.this);
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
            String chave = "";
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
            Enviar e = new Enviar(String.valueOf(pontos),String.valueOf(secs),"5",nome,chave);
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



}
