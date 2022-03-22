package com.fatec.shewbaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import com.fatec.Ranking.Enviar;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MemoriaCurta extends ActionBarActivity {
	//Minhas variaveis---------------------------------
	Random r = new Random();
	int flag=0;
	TextView lblNum;
	EditText txtNum;
	Button btExec,btPublicar;
	String numeros=null;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	int acertos = 0;
	int max=9999999;
	int min=1000000;
	int tentativas=1;
	//--------------------------banco------------------
		private ProgressDialog pDialog;
		JSONParser jsonParser = new JSONParser();
	//-------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memoria_curta);
		getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
		lblNum = (TextView)findViewById(R.id.lblNumeros);
		txtNum = (EditText)findViewById(R.id.editText1);
		btExec = (Button)findViewById(R.id.btInicia);
		btPublicar = (Button)findViewById(R.id.btPublicarMemC);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.memoria_curta, menu);
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
	//-------------------meus metodos--------------------------------
	 /**
     * Controla o bot�o de iniciar o exercicio, gera os randons.
     * @param view
     */
    public void btRandom(View view){
    	if(acertos!=0&&acertos%5==0){
    		min=min*10;
    		max=(max*10)+9;
    	}
    	if(flag==0){
    		lblNum.setTextSize(100);
    		int num = r.nextInt(max - min) + min;
    		numeros = String.valueOf(num);
    	    lblNum.setText(numeros);
    	    flag=1;
    	    acertos++;
    	    tentativas=1;
    	    startTime = SystemClock.uptimeMillis();
    	    customHandler.postDelayed(updateTimerThread, 0);
    	    txtNum.setEnabled(false);
    	    btExec.setEnabled(false);
    	}
    	else{
    		btPublicar.setEnabled(true);
    		String resp=txtNum.getText().toString();
    		if(resp.equals(numeros)){
    			lblNum.setTextSize(50);
    			if (tentativas>1)
    				lblNum.setText("Parabens!\nForam só "+tentativas+" tentativas.\n");
    			else
    				lblNum.setText("Parabens!\nFoi só "+tentativas+" tentativa.\n");
    			flag=0;
    			btExec.setText("Iniciar");
    			txtNum.setText("");
    			txtNum.setEnabled(false);
    			
    			}
    		else{
    			lblNum.setTextSize(25);
    			lblNum.setText("Tente novamente!");
    			txtNum.setText("");
    			txtNum.requestFocus();
    			tentativas++;
    		}
    	}
    	
		}
    
    /**
     * Finaliza o contador.
     */
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
			int secs = (int) (updatedTime / 1000);
			if (secs<10){
				int cont=10-secs;
				btExec.setText(String.valueOf(cont));
			}
			if (secs==10){
				stop();
				btExec.setText("Confirma");
				lblNum.setTextSize(100);
				lblNum.setText("Digite os números.");
				txtNum.setEnabled(true);
				btExec.setEnabled(true);
				txtNum.requestFocus();
			}
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
		        pDialog = new ProgressDialog(MemoriaCurta.this);
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
		    	Enviar e = new Enviar(String.valueOf(acertos),"-","1",nome,chave);
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
