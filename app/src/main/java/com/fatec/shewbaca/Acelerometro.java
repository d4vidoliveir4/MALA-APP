package com.fatec.shewbaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.fatec.Ranking.Enviar;

public class Acelerometro extends ActionBarActivity implements SensorEventListener {
	Random r = new Random();
	private SensorManager senSensorManager;
	private Sensor senAccelerometer;
	private float last_x, last_y;
	TextView lblResultado, lblErrosAcel;
	Button btIniciarAce,btPublicar;
	ImageView imgC,imgB,imgE,imgD;
	int num;
	int flag=0,flagt=0;;
	int erros=0,acertos=0;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	int secs=0;

//--------------------------banco------------------
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
//----------------------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acelerometro);
		lblResultado = (TextView)findViewById(R.id.lblResultadoAce);
		imgB = (ImageView)findViewById(R.id.imgBaixo);
		imgC = (ImageView)findViewById(R.id.imgCima);
		imgD = (ImageView)findViewById(R.id.imgDireita);
		imgE = (ImageView)findViewById(R.id.imgEsquerda);
		btIniciarAce = (Button)findViewById(R.id.btIniciarAce);
		btPublicar = (Button)findViewById(R.id.btPublicarAcel);
		lblErrosAcel =(TextView)findViewById(R.id.lblErrosAce);
		senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	    senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acelerometro, menu);
		return true;
	}
	protected void onPause() {
	    super.onPause();
	    senSensorManager.unregisterListener(this);
	}
	protected void onResume() {
	    super.onResume();
	    senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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
	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		Sensor mySensor = sensorEvent.sensor;
		 
	    if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	    	float x = sensorEvent.values[0];
	        float y = sensorEvent.values[1];
	        float z = sensorEvent.values[2];	       
	            last_x = x;
	            last_y = y;
	            if (flag==1 && (last_x>2.5||last_x<-2.5||last_y>2.5||last_y<-2.5)){
	            	flag=0;
	            	verifica(last_x,last_y);
	            }
	            
	        }
	    }
	 
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	 
	}
	public void verifica(double x, double y){
		
		//Caso seja para cima
    	if(num==0)
    		if(y<=-2){
	        	limpar();
	        	lblResultado.setText("Certo!");
	        	acertos++;
    		}else{
		        limpar();
		        lblResultado.setText("Errado!");
		        erros++;
    		}
    	//Caso seja para baixo
    	if(num==2)
    		if(y>=2){
	        	limpar();
	        	lblResultado.setText("Certo!");
	        	acertos++;
    		}else{
	        	limpar();
	        	lblResultado.setText("Errado!");
	        	erros++;
    		}
    	//Caso seja direita
    	if(num==1)
    		if(x<=-2){
	        	limpar();
	        	lblResultado.setText("Certo!");
	        	acertos++;
    		}else{
	        	limpar();
	        	lblResultado.setText("Errado!");
	        	erros++;
    		}
    	//Caso seja esquerda
    	if(num==3)
    		if(x>=2){
	        	limpar();
	        	lblResultado.setText("Certo!");
	        	acertos++;
    		}else{
	        	limpar();
	        	lblResultado.setText("Errado!");
	        	erros++;
    	}
    	lblErrosAcel.setText(String.valueOf(5-erros));
    	if(erros==5){
    		
    		stop();
    		flagt=0;
    		lblResultado.setText("Você cometeu 5 erros!" +
    							"\nVocê acertou: "+acertos+" em "+secs+"segundos.");
    		btPublicar.setEnabled(true);
    	}
	}
	public void btRandom(View view){
		if(flagt==0){
			btPublicar.setEnabled(false);
			flagt=1;
			erros=0;
    		acertos=0;
    		secs=0;
			startTime = SystemClock.uptimeMillis();
    	    customHandler.postDelayed(updateTimerThread, 0);
		}
		gerarDirecao();
		lblResultado.setText("Resultado...");
		flag=1;
	}
	public void limpar(){
        imgB.setImageResource(R.drawable.neutro);
		imgC.setImageResource(R.drawable.neutro);
		imgD.setImageResource(R.drawable.neutro);
		imgE.setImageResource(R.drawable.neutro);
	}
	public void gerarDirecao(){
		num = r.nextInt(4);
		if(num==0){
			imgB.setImageResource(R.drawable.neutro);
			imgC.setImageResource(R.drawable.setac);
			imgD.setImageResource(R.drawable.neutro);
			imgE.setImageResource(R.drawable.neutro);			
		}
		if(num==1){
			imgB.setImageResource(R.drawable.neutro);
			imgC.setImageResource(R.drawable.neutro);
			imgD.setImageResource(R.drawable.seta_d);
			imgE.setImageResource(R.drawable.neutro);		
		}
		if(num==2){
			imgB.setImageResource(R.drawable.setab);
			imgC.setImageResource(R.drawable.neutro);
			imgD.setImageResource(R.drawable.neutro);
			imgE.setImageResource(R.drawable.neutro);			
		}
		if(num==3){
			imgB.setImageResource(R.drawable.neutro);
			imgC.setImageResource(R.drawable.neutro);
			imgD.setImageResource(R.drawable.neutro);
			imgE.setImageResource(R.drawable.setae);		
		}
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
			//Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();  
		}
		
		//-----------------------Banco---------------------------------------------------------------------
		class ranking extends AsyncTask<String, String, String> {
			 
		    /**
		     * Mostra o dialogo antes de iniciar 
		     * */
		    @Override
		    protected void onPreExecute() {
		        super.onPreExecute();
		        pDialog = new ProgressDialog(Acelerometro.this);
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
						chave =dados[2];
                    }
                }catch(IOException e){
                    Log.e("Exception", "File read failed: " + e.toString());
                }

		    	Enviar e = new Enviar(String.valueOf(acertos),String.valueOf(secs),"2",nome,chave);
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
