package com.fatec.shewbaca.util;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fatec.Ranking.Enviar;
import com.fatec.shewbaca.JSONParser;
import com.fatec.shewbaca.NaoSalvo;
import com.fatec.shewbaca.R;
import com.fatec.shewbaca.Salvo;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class NumCresc extends ActionBarActivity {
    //Variáveis
    Button iniciar,btPublicar,lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl15,lbl16,lbl17,lbl18,lbl19,lbl20,lbl21,lbl22,lbl23,lbl24,lbl25,lbl26,lbl27,lbl28,lbl29,lbl30;
    TextView lblAcertos, lblErros,lblResultado,lblIntro;
    private int num=0, numP=0, qtde=3,cont=0,qtdeN=0,exibi=0,tempo=2,qtdAcerto=0,qtdErro=0,vezes=0;
    private Boolean errou=false;
    private Random r = new Random();
    private int vetorP[] = new int[30];
    private String vetorS[] = new String[30];
    //Timer
    private int contT=0, secs=0;
    private long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private Handler customHandler = new Handler();
    private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();

    //--------------------------banco------------------
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    //----------------------------------------------------

    //Fim variáveis.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_cresc);
        iniciar = (Button) findViewById(R.id.BtIniciar);
        lbl1 = (Button) findViewById(R.id.lbl1);
        lbl2 = (Button) findViewById(R.id.lbl2);
        lbl3 = (Button) findViewById(R.id.lbl3);
        lbl4 = (Button) findViewById(R.id.lbl4);
        lbl5 = (Button) findViewById(R.id.lbl5);
        lbl6 = (Button) findViewById(R.id.lbl6);
        lbl7 = (Button) findViewById(R.id.lbl7);
        lbl8 = (Button) findViewById(R.id.lbl8);
        lbl9 = (Button) findViewById(R.id.lbl9);
        lbl10 = (Button) findViewById(R.id.lbl10);
        lbl11 = (Button) findViewById(R.id.lbl11);
        lbl12 = (Button) findViewById(R.id.lbl12);
        lbl13 = (Button) findViewById(R.id.lbl13);
        lbl14 = (Button) findViewById(R.id.lbl14);
        lbl15 = (Button) findViewById(R.id.lbl15);
        lbl16 = (Button) findViewById(R.id.lbl16);
        lbl17 = (Button) findViewById(R.id.lbl17);
        lbl18 = (Button) findViewById(R.id.lbl18);
        lbl19 = (Button) findViewById(R.id.lbl19);
        lbl20 = (Button) findViewById(R.id.lbl20);
        lbl21 = (Button) findViewById(R.id.lbl21);
        lbl22 = (Button) findViewById(R.id.lbl22);
        lbl23 = (Button) findViewById(R.id.lbl23);
        lbl24 = (Button) findViewById(R.id.lbl24);
        lbl25 = (Button) findViewById(R.id.lbl25);
        lbl26 = (Button) findViewById(R.id.lbl26);
        lbl27 = (Button) findViewById(R.id.lbl27);
        lbl28 = (Button) findViewById(R.id.lbl28);
        lbl29 = (Button) findViewById(R.id.lbl29);
        lbl30 = (Button) findViewById(R.id.lbl30);
        btPublicar = (Button) findViewById(R.id.Publicar);
        lblAcertos = (TextView) findViewById(R.id.lblAcertos);
        lblErros = (TextView) findViewById(R.id.lblErros);
        lblResultado = (TextView) findViewById(R.id.lblResultado);
        lblIntro = (TextView) findViewById(R.id.lblIntro);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_num_cresc, menu);
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


    //Publicar
    public void btPublicar(View view){
        new ranking().execute();
        //Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    class ranking extends AsyncTask<String, String, String> {

        /**
         * Mostra o dialogo antes de iniciar
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(NumCresc.this);
            pDialog.setMessage("Aguarde! Enviando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Criando ranking
         * */
        protected String doInBackground(String... args) {
            Enviar e = new Enviar(String.valueOf(qtdAcerto),String.valueOf(secs),"7","0","30A03");
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


    //Timer
    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        contT++;
                        if(contT == tempo){
                            exibi=1;
                            AtribuirVal();
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
            customHandler.postDelayed(this, 0);
        }

    };





    //Voltar Vetores
    public void VoltarVetores(){
        for(int i=0;i<30;i++){
            vetorP[i]=100;
        }
        contT=0;

        lbl1.setText("");lbl2.setText("");lbl3.setText("");lbl4.setText("");lbl5.setText("");lbl6.setText("");lbl7.setText("");lbl8.setText("");
        lbl9.setText("");lbl10.setText("");lbl11.setText("");lbl12.setText("");lbl13.setText("");lbl14.setText("");lbl15.setText("");lbl16.setText("");
        lbl17.setText("");lbl18.setText("");lbl19.setText("");lbl20.setText("");lbl21.setText("");lbl22.setText("");lbl23.setText("");
        lbl24.setText("");lbl25.setText("");lbl26.setText("");lbl27.setText("");lbl28.setText("");lbl29.setText("");lbl30.setText("");

        lbl1.setEnabled(true);lbl2.setEnabled(true);lbl3.setEnabled(true);lbl4.setEnabled(true);lbl5.setEnabled(true);lbl6.setEnabled(true);
        lbl7.setEnabled(true);lbl8.setEnabled(true);lbl9.setEnabled(true);lbl10.setEnabled(true);lbl11.setEnabled(true);lbl12.setEnabled(true);
        lbl13.setEnabled(true);lbl14.setEnabled(true);lbl15.setEnabled(true);lbl16.setEnabled(true);lbl17.setEnabled(true);lbl18.setEnabled(true);
        lbl19.setEnabled(true);lbl20.setEnabled(true);lbl21.setEnabled(true);lbl22.setEnabled(true);lbl23.setEnabled(true);lbl24.setEnabled(true);
        lbl25.setEnabled(true);lbl26.setEnabled(true);lbl27.setEnabled(true);lbl28.setEnabled(true);lbl29.setEnabled(true);lbl30.setEnabled(true);
    }

    public void Exibicao(){
        //aux=0;
        //novo=1;
        for(int i=0;i<30;i++){
            if(vetorP[i] == 100){
                vetorS[i]=" ";
            }
            else
                vetorS[i]=String.valueOf(vetorP[i]);
        }
    }

    public void Exibicao2(){
        for(int i=0;i<30;i++){
            if(vetorP[i] == 100){
                vetorS[i]=" ";
            }
            else
                vetorS[i]="?";
        }
    }


    //Iniciar
    public void BtIniciar(View view){
        lblIntro.setVisibility(View.INVISIBLE);
        VoltarVetores();
        vezes++;
        btPublicar.setVisibility(View.INVISIBLE);
        lblResultado.setText(" ");
        for (int j=0;j<qtde;j++){
            cont=0;
            do{
                cont=0;
                RandomizarNumero(qtde);
                RandomizarBotao(qtde);
                if(vetorP[numP] == 100){
                    vetorP[numP] = num;
                    for (int k = 0; k < numP; k++) {
                        if (vetorP[numP] == vetorP[k]) {
                            cont++;
                            vetorP[numP] = 100;
                        }
                    }
                    for (int l = numP+1; l > numP && l<30; l++) {
                        if (vetorP[numP] == vetorP[l]) {
                            cont++;
                            vetorP[numP] = 100;
                        }
                    }
                }
                else {
                    cont=1;
                }
            }while(cont >= 1);
        }
        exibi=0;
        AtribuirVal();
        iniciar.setEnabled(false);
        contT=0;
        if(vezes==1) {
            ativaTimer();
            startTime = SystemClock.uptimeMillis();//iniciar tempo
            customHandler.postDelayed(updateTimerThread, 0);
        }
    }





    //Atribuir valores para os botoes
    public void AtribuirVal(){
        if(exibi==1){
            Exibicao2();
        }
        else {
            Exibicao();

        }
        lbl1.setText(vetorS[0]);lbl2.setText(vetorS[1]);lbl3.setText(vetorS[2]);
        lbl4.setText(vetorS[3]);lbl5.setText(vetorS[4]);lbl6.setText(vetorS[5]);
        lbl7.setText(vetorS[6]);lbl8.setText(vetorS[7]);lbl9.setText(vetorS[8]);
        lbl10.setText(vetorS[9]);lbl11.setText(vetorS[10]);lbl12.setText(vetorS[11]);
        lbl13.setText(vetorS[12]);lbl14.setText(vetorS[13]);lbl15.setText(vetorS[14]);
        lbl16.setText(vetorS[15]);lbl17.setText(vetorS[16]);lbl18.setText(vetorS[17]);
        lbl19.setText(vetorS[18]);lbl20.setText(vetorS[19]);lbl21.setText(vetorS[20]);
        lbl22.setText(vetorS[21]);lbl23.setText(vetorS[22]);lbl24.setText(vetorS[23]);
        lbl25.setText(vetorS[24]);lbl26.setText(vetorS[25]);lbl27.setText(vetorS[26]);
        lbl28.setText(vetorS[27]);lbl29.setText(vetorS[28]);lbl30.setText(vetorS[29]);

    }


    //Randomização
    public void RandomizarNumero(int i){
            num = r.nextInt(i) + 1;
    }

    public void RandomizarBotao(int i){
        numP = r.nextInt(29);
    }



    //Resultados
    public void Fim(){
        qtde++;
        qtdAcerto++;
        qtdeN=0;
        tempo=tempo+1;
        lblAcertos.setText(String.valueOf(qtdAcerto));
        lblResultado.setText("Parabéns! Inicie o próximo nível!");
        btPublicar.setVisibility(View.VISIBLE);
        if(qtdAcerto==21){
            Final();
        }
        else
            iniciar.setEnabled(true);
    }

    public void FimErrou(){
        qtdErro++;
        qtdeN=0;
        VoltarVetores();//novo
        iniciar.setEnabled(true);
        lblErros.setText(String.valueOf(qtdErro));
        if(qtdErro==10){//NOVO
            lblResultado.setText("Você atingiu 10 erros. Tente novamente do início!");
            qtde=3;
            tempo=2;
            qtdAcerto=0;
            qtdErro=0;
            lblErros.setText("0");
            lblAcertos.setText("0");
        }
        else
            lblResultado.setText("Errou! Tente Novamente!");
    }

    public void Final(){
        iniciar.setEnabled(false);
        VoltarVetores();
        lblResultado.setText("Parabéns! Você concluiu todos os níveis!");
        stop();
        btPublicar.setVisibility(View.VISIBLE);
    }





    public Boolean Verificar(int n){
        errou=false;
        for(int i=0; i < 30;i++){
            if(vetorP[n]>vetorP[i]){
                errou=true;
            }
        }
        if(errou==true){
            FimErrou();
            return false;
        }
        else {
            vetorP[n]=100;//exclui esse q ja foi
            qtdeN++;
            if(qtdeN==qtde){
                Fim();
            }
            return true;
        }

    }


    //Botoes
    public void Btlbl1(View view){
        if(vetorP[0]==100){
            lbl1.setText(" ");
        }
        else {
            if(lbl1.getText().equals("?")) {
                lbl1.setText(String.valueOf(vetorP[0]));

                if (Verificar(0) == true) {
                    lbl1.setEnabled(false);
                } else {
                    lbl1.setText("X");
                }
            }

        }

    }

    public void Btlbl2(View view){

        if(vetorP[1]==100){
            lbl2.setText(" ");
        }
        else {
            if(lbl2.getText().equals("?")) {
                lbl2.setText(String.valueOf(vetorP[1]));

                if (Verificar(1) == true) {
                    lbl2.setEnabled(false);
                } else {
                    lbl2.setText("X");
                }
            }

        }
    }

    public void Btlbl3(View view){

        if(vetorP[2]==100){
            lbl3.setText(" ");
        }
        else {
            if(lbl3.getText().equals("?")) {
                lbl3.setText(String.valueOf(vetorP[2]));

                if (Verificar(2) == true) {
                    lbl3.setEnabled(false);
                } else {
                    lbl3.setText("X");
                }
            }

        }
    }

    public void Btlbl4(View view){

        if(vetorP[3]==100){
            lbl4.setText(" ");
        }
        else {
            if(lbl4.getText().equals("?")) {
                lbl4.setText(String.valueOf(vetorP[3]));

                if (Verificar(3) == true) {
                    lbl4.setEnabled(false);
                } else {
                    lbl4.setText("X");
                }
            }

        }
    }

    public void Btlbl5(View view){

        if(vetorP[4]==100){
            lbl5.setText(" ");
        }
        else {
            if(lbl5.getText().equals("?")) {
                lbl5.setText(String.valueOf(vetorP[4]));

                if (Verificar(4) == true) {
                    lbl5.setEnabled(false);
                } else {
                    lbl5.setText("X");
                }
            }

        }
    }

    public void Btlbl6(View view){

        if(vetorP[5]==100){
            lbl6.setText(" ");
        }
        else {
            if(lbl6.getText().equals("?")) {
                lbl6.setText(String.valueOf(vetorP[5]));

                if (Verificar(5) == true) {
                    lbl6.setEnabled(false);
                } else {
                    lbl6.setText("X");
                }
            }

        }
    }

    public void Btlbl7(View view){

        if(vetorP[6]==100){
            lbl7.setText(" ");
        }
        else {
            if(lbl7.getText().equals("?")) {
                lbl7.setText(String.valueOf(vetorP[6]));

                if (Verificar(6) == true) {
                    lbl7.setEnabled(false);
                } else {
                    lbl7.setText("X");
                }
            }

        }
    }

    public void Btlbl8(View view){

        if(vetorP[7]==100){
            lbl8.setText(" ");
        }
        else {
            if(lbl8.getText().equals("?")) {
                lbl8.setText(String.valueOf(vetorP[7]));

                if (Verificar(7) == true) {
                    lbl8.setEnabled(false);
                } else {
                    lbl8.setText("X");
                }
            }

        }
    }

    public void Btlbl9(View view){

        if(vetorP[8]==100){
            lbl9.setText(" ");
        }
        else {
            if(lbl9.getText().equals("?")) {
                lbl9.setText(String.valueOf(vetorP[8]));

                if (Verificar(8) == true) {
                    lbl9.setEnabled(false);
                } else {
                    lbl9.setText("X");
                }
            }

        }
    }

    public void Btlbl10(View view){

        if(vetorP[9]==100){
            lbl10.setText(" ");
        }
        else {
            if(lbl10.getText().equals("?")) {
                lbl10.setText(String.valueOf(vetorP[9]));

                if (Verificar(9) == true) {
                    lbl10.setEnabled(false);
                } else {
                    lbl10.setText("X");
                }
            }

        }
    }

    public void Btlbl11(View view){

        if(vetorP[10]==100){
            lbl11.setText(" ");
        }
        else {
            if(lbl11.getText().equals("?")) {
                lbl11.setText(String.valueOf(vetorP[10]));

                if (Verificar(10) == true) {
                    lbl11.setEnabled(false);
                } else {
                    lbl11.setText("X");
                }
            }

        }
    }

    public void Btlbl12(View view){

        if(vetorP[11]==100){
            lbl12.setText(" ");
        }
        else {
            if(lbl12.getText().equals("?")) {
                lbl12.setText(String.valueOf(vetorP[11]));

                if (Verificar(11) == true) {
                    lbl12.setEnabled(false);
                } else {
                    lbl12.setText("X");
                }
            }

        }
    }

    public void Btlbl13(View view){

        if(vetorP[12]==100){
            lbl13.setText(" ");
        }
        else {
            if(lbl13.getText().equals("?")) {
                lbl13.setText(String.valueOf(vetorP[12]));

                if (Verificar(12) == true) {
                    lbl13.setEnabled(false);
                } else {
                    lbl13.setText("X");
                }
            }

        }
    }

    public void Btlbl14(View view){

        if(vetorP[13]==100){
            lbl14.setText(" ");
        }
        else {
            if(lbl14.getText().equals("?")) {
                lbl14.setText(String.valueOf(vetorP[13]));

                if (Verificar(13) == true) {
                    lbl14.setEnabled(false);
                } else {
                    lbl14.setText("X");
                }
            }

        }
    }

    public void Btlbl15(View view){

        if(vetorP[14]==100){
            lbl15.setText(" ");
        }
        else {
            if(lbl15.getText().equals("?")) {
                lbl15.setText(String.valueOf(vetorP[14]));

                if (Verificar(14) == true) {
                    lbl15.setEnabled(false);
                } else {
                    lbl15.setText("X");
                }
            }

        }
    }

    public void Btlbl16(View view){

        if(vetorP[15]==100){
            lbl16.setText(" ");
        }
        else {
            if(lbl16.getText().equals("?")) {
                lbl16.setText(String.valueOf(vetorP[15]));

                if (Verificar(15) == true) {
                    lbl16.setEnabled(false);
                } else {
                    lbl16.setText("X");
                }
            }

        }
    }

    public void Btlbl17(View view){

        if(vetorP[16]==100){
            lbl17.setText(" ");
        }
        else {
            if(lbl17.getText().equals("?")) {
                lbl17.setText(String.valueOf(vetorP[16]));

                if (Verificar(16) == true) {
                    lbl17.setEnabled(false);
                } else {
                    lbl17.setText("X");
                }
            }

        }
    }

    public void Btlbl18(View view){

        if(vetorP[17]==100){
            lbl18.setText(" ");
        }
        else {
            if(lbl18.getText().equals("?")) {
                lbl18.setText(String.valueOf(vetorP[17]));

                if (Verificar(17) == true) {
                    lbl18.setEnabled(false);
                } else {
                    lbl18.setText("X");
                }
            }

        }
    }

    public void Btlbl19(View view){

        if(vetorP[18]==100){
            lbl19.setText(" ");
        }
        else {
            if(lbl19.getText().equals("?")) {
                lbl19.setText(String.valueOf(vetorP[18]));

                if (Verificar(18) == true) {
                    lbl19.setEnabled(false);
                } else {
                    lbl19.setText("X");
                }
            }

        }
    }

    public void Btlbl20(View view){

        if(vetorP[19]==100){
            lbl20.setText(" ");
        }
        else {
            if(lbl20.getText().equals("?")) {
                lbl20.setText(String.valueOf(vetorP[19]));

                if (Verificar(19) == true) {
                    lbl20.setEnabled(false);
                } else {
                    lbl20.setText("X");
                }
            }

        }
    }

    public void Btlbl21(View view){

        if(vetorP[20]==100){
            lbl21.setText(" ");
        }
        else {
            if(lbl21.getText().equals("?")) {
                lbl21.setText(String.valueOf(vetorP[20]));

                if (Verificar(20) == true) {
                    lbl21.setEnabled(false);
                } else {
                    lbl21.setText("X");
                }
            }

        }
    }

    public void Btlbl22(View view){

        if(vetorP[21]==100){
            lbl22.setText(" ");
        }
        else {
            if(lbl22.getText().equals("?")) {
                lbl22.setText(String.valueOf(vetorP[21]));

                if (Verificar(21) == true) {
                    lbl22.setEnabled(false);
                } else {
                    lbl22.setText("X");
                }
            }

        }
    }

    public void Btlbl23(View view){

        if(vetorP[22]==100){
            lbl23.setText(" ");
        }
        else {
            if(lbl23.getText().equals("?")) {
                lbl23.setText(String.valueOf(vetorP[22]));

                if (Verificar(22) == true) {
                    lbl23.setEnabled(false);
                } else {
                    lbl23.setText("X");
                }
            }

        }
    }

    public void Btlbl24(View view){

        if(vetorP[23]==100){
            lbl24.setText(" ");
        }
        else {
            if(lbl24.getText().equals("?")) {
                lbl24.setText(String.valueOf(vetorP[23]));

                if (Verificar(23) == true) {
                    lbl24.setEnabled(false);
                } else {
                    lbl24.setText("X");
                }
            }
        }
    }

    public void Btlbl25(View view){

        if(vetorP[24]==100){
            lbl25.setText(" ");
        }
        else {
            if(lbl25.getText().equals("?")) {
                lbl25.setText(String.valueOf(vetorP[24]));

                if (Verificar(24) == true) {
                    lbl25.setEnabled(false);
                } else {
                    lbl25.setText("X");
                }
            }
        }
    }

    public void Btlbl26(View view){

        if(vetorP[25]==100){
            lbl26.setText(" ");
        }
        else {
            if(lbl26.getText().equals("?")) {
                lbl26.setText(String.valueOf(vetorP[25]));

                if (Verificar(25) == true) {
                    lbl26.setEnabled(false);
                } else {
                    lbl26.setText("X");
                }
            }
        }
    }

    public void Btlbl27(View view){

        if(vetorP[26]==100){
            lbl27.setText(" ");
        }
        else {
            if(lbl27.getText().equals("?")) {
                lbl27.setText(String.valueOf(vetorP[26]));

                if (Verificar(26) == true) {
                    lbl27.setEnabled(false);
                } else {
                    lbl27.setText("X");
                }
            }
        }
    }

    public void Btlbl28(View view){

        if(vetorP[27]==100){
            lbl28.setText(" ");
        }
        else {
            if(lbl28.getText().equals("?")) {
                lbl28.setText(String.valueOf(vetorP[27]));

                if (Verificar(27) == true) {
                    lbl28.setEnabled(false);
                } else {
                    lbl28.setText("X");
                }
            }
        }
    }

    public void Btlbl29(View view){

        if(vetorP[28]==100){
            lbl29.setText(" ");
        }
        else {
            if(lbl29.getText().equals("?")) {
                lbl29.setText(String.valueOf(vetorP[28]));

                if (Verificar(28) == true) {
                    lbl29.setEnabled(false);
                } else {
                    lbl29.setText("X");
                }
            }
        }
    }

    public void Btlbl30(View view){

        if(vetorP[29]==100){
            lbl30.setText(" ");
        }
        else {
            if(lbl30.getText().equals("?")) {
                lbl30.setText(String.valueOf(vetorP[29]));

                if (Verificar(29) == true) {
                    lbl30.setEnabled(false);
                } else {
                    lbl30.setText("X");
                }
            }
        }
    }
}
