package com.fatec.shewbaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import com.fatec.Ranking.Enviar;
import com.fatec.banco.DataHelperLM;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogicaMatematica extends ActionBarActivity {
	private DataHelperLM dh;
	TextView output;
	int max=1;
	int min=1;
	TextView lblPergunta;
	Button btAlt1,btAlt2,btAlt3,btAlt4,btIniciar,btPublicar,btProx;
	Random r = new Random();
	String certa;
	int acertos=0,erros=0;
	//Timer-------------------------------------
		private long startTime = 0L;
		private Handler customHandler = new Handler();
		long timeInMilliseconds = 0L;
		long timeSwapBuff = 0L;
		long updatedTime = 0L;
		int secs=0;
	//--------------------------banco------------------
			private ProgressDialog pDialog;
	//----------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logica_matematica);
		output = (TextView) this.findViewById(R.id.out_text);
		lblPergunta = (TextView) this.findViewById(R.id.lblPerguntaLM);
		btAlt1 = (Button) this.findViewById(R.id.btAlt1);
		btAlt2 = (Button) this.findViewById(R.id.btAlt2);
		btAlt3 = (Button) this.findViewById(R.id.btAlt3);
		btAlt4 = (Button) this.findViewById(R.id.btAlt4);
		btIniciar = (Button) this.findViewById(R.id.btIniciar);
		btPublicar = (Button) this.findViewById(R.id.btPublicarLM);
		btProx = (Button) this.findViewById(R.id.btProx);
		this.dh = new DataHelperLM(this);
		carregaBanco();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logica_matematica, menu);
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
	
	public void carregaBanco(){
		//S� executar essa fun��o durante o desenvolvimento
		//Essa fun��o carrega as informa��es no sqlite
		dh.deleteAll();
        dh.insert("1+1","1","2","3","4","2");
        dh.insert("1*1","1","2","3","4","1");
        dh.insert("1/1","1","2","3","4","1");
        dh.insert("1-1","1","2","0","4","0");
        dh.insert("(2+1)-4/2","1","2","5","4","1");
        dh.insert("(2*3)+(4/2)","10","8","5","4","8");
        dh.insert("5+(3*(8/2))","17","20","15","14","17");
        dh.insert("4*5","10","21","20","40","20");
        dh.insert("4/2","1","2","5","4","2");
        dh.insert("2+1","1","2","3","4","3");
        dh.insert("(2+1)-4","-1","-2","-5","-4","-1");
        dh.insert("2*3*2","11","12","15","14","12");
	}
	public void escolherPergunta(){
		DataHelperLM dh = new DataHelperLM(this);	
		max=dh.selectQtd();
		int num = r.nextInt(max - min) + min;
		List<String> names = this.dh.selectOne(num);
		
        StringBuilder sb = new StringBuilder();
        for (String name : names) {
           sb.append(name);
        }
        Log.d("EXAMPLE", "names size - " + names.size());
        this.output.setText(sb.toString());
        String texto=sb.toString();
        String[] parts = texto.split("_");
        lblPergunta.setText("Quanto é " +parts[0]+"?");
        certa=parts[5];
        //As alternativas
        btAlt1.setText(parts[1]);
        btAlt2.setText(parts[2]);
        btAlt3.setText(parts[3]);
        btAlt4.setText(parts[4]);
        btProx.setEnabled(true);
	}
	public void erro(){
		erros++;
		if (erros==5){
			stop();
			btIniciar.setEnabled(true);
			btProx.setEnabled(false);
			btPublicar.setEnabled(true);
			lblPergunta.setText("Você cometeu 5 erros!\nAcertou "+acertos+" em "+secs+"secs. Parabens!");
		}
	}
	public void btIniciar(View view){
		acertos=0;
		erros=0;
		secs=0;
		startTime = SystemClock.uptimeMillis();
	    customHandler.postDelayed(updateTimerThread, 0);
		btIniciar.setEnabled(false);
		btPublicar.setEnabled(false);
		gerar();
	}
	public void btProx(View view){
		btProx.setEnabled(false);
		gerar();
	}
	public void btPublicar(View view){
		new ranking().execute();
	}
	public void gerar(){
		int cor = getResources().getColor(R.color.roxo);
		btAlt1.setBackgroundColor(cor);
		btAlt2.setBackgroundColor(cor);
		btAlt3.setBackgroundColor(cor);
		btAlt4.setBackgroundColor(cor);
		btAlt1.setEnabled(true);
		btAlt2.setEnabled(true);
		btAlt3.setEnabled(true);
		btAlt4.setEnabled(true);
		escolherPergunta();
	}
	
	public void btAlt1(View view){
		btProx.setEnabled(true);
		btAlt1.setEnabled(false);
		btAlt2.setEnabled(false);
		btAlt3.setEnabled(false);
		btAlt4.setEnabled(false);
		if (btAlt1.getText().toString().equals(certa)){
			int cor = getResources().getColor(R.color.verde);
			btAlt1.setBackgroundColor(cor);
			acertos++;
		}else{
			int cor = getResources().getColor(R.color.vermelho);
			btAlt1.setBackgroundColor(cor);
			erro();
		}
	}
	public void btAlt2(View view){
		btProx.setEnabled(true);
		btAlt1.setEnabled(false);
		btAlt2.setEnabled(false);
		btAlt3.setEnabled(false);
		btAlt4.setEnabled(false);
		if (btAlt2.getText().toString().equals(certa)){
			int cor = getResources().getColor(R.color.verde);
			btAlt2.setBackgroundColor(cor);
			acertos++;
		}else{
			int cor = getResources().getColor(R.color.vermelho);
			btAlt2.setBackgroundColor(cor);
			erro();
		}
	}
	public void btAlt3(View view){
		btProx.setEnabled(true);
		btAlt1.setEnabled(false);
		btAlt2.setEnabled(false);
		btAlt3.setEnabled(false);
		btAlt4.setEnabled(false);
		if (btAlt3.getText().toString().equals(certa)){
			int cor = getResources().getColor(R.color.verde);
			btAlt3.setBackgroundColor(cor);
			acertos++;
		}else{
			int cor = getResources().getColor(R.color.vermelho);
			btAlt3.setBackgroundColor(cor);
			erro();
		}
	}
	public void btAlt4(View view){
		btProx.setEnabled(true);
		btAlt1.setEnabled(false);
		btAlt2.setEnabled(false);
		btAlt3.setEnabled(false);
		btAlt4.setEnabled(false);
		if (btAlt4.getText().toString().equals(certa)){
			int cor = getResources().getColor(R.color.verde);
			btAlt4.setBackgroundColor(cor);
			acertos++;
			erro();
		}else{
			int cor = getResources().getColor(R.color.vermelho);
			btAlt4.setBackgroundColor(cor);
			erro();
		}
	}
	//Timer----------------------------------------------------------------
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
	//-----------------------Banco---------------------------------------------------------------------
	class ranking extends AsyncTask<String, String, String> {
		 
	    /**
	     * Mostra o dialogo antes de iniciar 
	     * */
	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();
	        pDialog = new ProgressDialog(LogicaMatematica.this);
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
	    	Enviar e = new Enviar(String.valueOf(acertos),String.valueOf(secs),"4",nome,chave);
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
