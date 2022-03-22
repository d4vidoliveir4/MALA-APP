package com.fatec.shewbaca;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fatec.Ranking.Enviar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class Cores extends ActionBarActivity {
    //--------------------------banco------------------
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    //-------------------------------------------------
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    Button btIniciarCor,btCor1,btCor2,btPublicarCor;
    TextView txtCores;
    int flag=0,flagt=0;;
    int erros=0,acertos=0;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int secs=0;
    int correto=0;
    int corIgual=0;
    int corDiferente=0;
    int textoDiferente=0;
    Random r = new Random();
    int max=0;
    int min=0;
    String[] textos = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textos[0]="Azul";
        textos[1]="Verde";
        textos[2]="Vermelho";
        textos[3]="Roxo";
        textos[4]="Amarelo";
        textos[5]="Branco";
        textos[6]="Beje";
        textos[7]="-";
        textos[8]="-";
        textos[9]="-";
        setContentView(R.layout.activity_cores);
        btCor1= (Button) findViewById(R.id.btCor1);
        btCor2= (Button) findViewById(R.id.btCor2);
        btIniciarCor= (Button) findViewById(R.id.btIniciarCor);
        btPublicarCor= (Button) findViewById(R.id.btPublicarCor);
        txtCores= (TextView) findViewById(R.id.txtCores);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cores, menu);
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
    public void gerarCor(){
        max=3;min=0;
        corIgual = r.nextInt(max - min) + min;
        corDiferente=0;textoDiferente=0;
        while(corDiferente==textoDiferente){
            corDiferente=r.nextInt(max - min) + min;
            textoDiferente=r.nextInt(max - min) + min;
        }
    }
    public void iniciar(){
        correto = 0;
        btIniciarCor.setEnabled(false);
        btPublicarCor.setEnabled(false);
        max=10;min=0;
        correto = r.nextInt(max - min) + min;
        gerarCor();
        if(correto<=5){
            correto = 1;
            if(corIgual==0)
                btCor1.setBackgroundColor(getResources().getColor(R.color.cor1));
            if(corIgual==1)
                btCor1.setBackgroundColor(getResources().getColor(R.color.cor2));
            if(corIgual==2)
                btCor1.setBackgroundColor(getResources().getColor(R.color.cor3));
            btCor1.setText(textos[corIgual]);
            if(corDiferente==0)
                btCor2.setBackgroundColor(getResources().getColor(R.color.cor1));
            if(corDiferente==1)
                btCor2.setBackgroundColor(getResources().getColor(R.color.cor2));
            if(corDiferente==2)
                btCor2.setBackgroundColor(getResources().getColor(R.color.cor3));
            btCor2.setText(textos[textoDiferente]);
        }
        else{
            correto = 2;
            if(corDiferente==0)
                btCor1.setBackgroundColor(getResources().getColor(R.color.cor1));
            if(corDiferente==1)
                btCor1.setBackgroundColor(getResources().getColor(R.color.cor2));
            if(corDiferente==2)
                btCor1.setBackgroundColor(getResources().getColor(R.color.cor3));
            btCor1.setText(textos[textoDiferente]);
            if(corIgual==0)
                btCor2.setBackgroundColor(getResources().getColor(R.color.cor1));
            if(corIgual==1)
                btCor2.setBackgroundColor(getResources().getColor(R.color.cor2));
            if(corIgual==2)
                btCor2.setBackgroundColor(getResources().getColor(R.color.cor3));
            btCor2.setText(textos[corIgual]);
        }
    }
    public void verificar(int botao){
        if(erros == 5){
            txtCores.setText("Fim de jogo!");
        }
        if (botao==correto) {
            iniciar();
            acertos++;
            txtCores.setText("Acertos: "+acertos+".\nChances usadas: "+erros+"de 5.");

        }
        else{
            erros++;
            btPublicarCor.setEnabled(true);
            btIniciarCor.setEnabled(true);
        }
    }
    public void btCorr1 (View view) {
       verificar(1);
    }
    public void btCorr2 (View view) {
        verificar(2);
    }
    //----------------------------------------------------------------------------------------------
    public void btIniciar(View view){
        iniciar();
        btPublicarCor.setEnabled(false);
        flagt=1;
        erros=0;
        acertos=0;
        secs=0;
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
    }
    public void stop(){
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
            secs = (int) (updatedTime / 1000);

            customHandler.postDelayed(this, 0);
        }

    };
    public void btPublicar(View view){
        new ranking().execute();
    }
    //-----------------------Banco---------------------------------------------------------------------
    class ranking extends AsyncTask<String, String, String> {

        /**
         * Mostra o dialogo antes de iniciar
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Cores.this);
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
            String chave= "";
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
            Enviar e = new Enviar(String.valueOf(acertos),String.valueOf(secs),"3",nome,chave);
            if(e.ranking()==0){
                Intent i = new Intent(getApplicationContext(), Salvo.class);
                startActivity(i);
                finish();
            }else{
                Intent i = new Intent(getApplicationContext(), NaoSalvo.class);
                startActivity(i);
                finish();
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
}
