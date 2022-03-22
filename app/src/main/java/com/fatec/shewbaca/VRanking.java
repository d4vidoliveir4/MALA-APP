package com.fatec.shewbaca;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class VRanking extends ActionBarActivity {
    WebView webView;
    String chave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vranking);
        webView = (WebView) findViewById(R.id.webView);

        webView.loadUrl("http://www.nothing564.890m.com/consultaRanking.php");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vranking, menu);
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
    public void btMeuRanking(View view){
        String ret = "";
        String nome ="Anon";
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
        webView.loadUrl("http://www.nothing564.890m.com/MeuRanking.php?u="+chave);
    }
}
