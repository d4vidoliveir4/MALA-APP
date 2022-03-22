package com.fatec.Ranking;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import com.fatec.shewbaca.JSONParser;

public class Enviar{
	String acertos=null,secs=null, exercicio=null,usuario=null, chave=null;
	public Enviar(String a, String s, String e, String u, String c){
		this.acertos=a;
		this.secs=s;
		this.exercicio=e;
		this.usuario=u;
        this.chave = c;
	}
	JSONParser jsonParser = new JSONParser();
    private static String ranking = "http://<urlServidor>/ranking.php";
    private static final String TAG_SUCCESS = "success";
	
    
	public int ranking() {
		// TODO Auto-generated method stub
		String Usuario = usuario;
        String idExercicio = exercicio;
        String pontuacao = acertos;
        String tempo = secs;
        String key = chave;

        // Criando os parametros
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Usuario", Usuario));
        params.add(new BasicNameValuePair("Chave", key));
        params.add(new BasicNameValuePair("idExercicio", idExercicio));
        params.add(new BasicNameValuePair("pontuacao", pontuacao));
        params.add(new BasicNameValuePair("tempo", tempo));
        try {
        	JSONObject json = jsonParser.makeHttpRequest(ranking, "POST", params);
        	Log.d("Create Response", json.toString());          
            int success = json.getInt(TAG_SUCCESS);
            return success;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        } catch (Exception e) {
            return 0;
            
        }
	}
}
