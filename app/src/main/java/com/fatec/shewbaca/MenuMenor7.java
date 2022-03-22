package com.fatec.shewbaca;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.fatec.shewbaca.util.NumCresc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MenuMenor7 extends ActionBarActivity {
    public Button btJogoMemo, btMemCurta, btAgilAten, btNunCresc, btCores, btSons, btLogiMat, btSumiu, btMaior, btracio;
    int idade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_menor7);
        getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
        //Ler dados do usuario e configurar---------------------------------------
        String ret = "";
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
                idade=Integer.parseInt(dados[1]);
            }
        }catch(IOException e){
            Log.e("Exception", "File read failed: " + e.toString());
        }
        btJogoMemo= (Button) findViewById(R.id.btExerc4);
        btSumiu =(Button)findViewById(R.id.btExerc9);
        btMemCurta= (Button) findViewById(R.id.btExerc1);
        btAgilAten= (Button) findViewById(R.id.btExer2);
        btNunCresc= (Button) findViewById(R.id.btExerc5);
        btCores= (Button) findViewById(R.id.btExerc7);
        btSons= (Button) findViewById(R.id.btExerc6);
        btLogiMat= (Button) findViewById(R.id.btExerc3);
        btMaior=(Button)findViewById(R.id.btexerc10);
        btracio=(Button) findViewById(R.id.btrl);
        if (idade<7){
            btLogiMat.setEnabled(false);
            btNunCresc.setEnabled(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_menor7, menu);
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
    public void btExerc17(View view){
        Intent it = new Intent(MenuMenor7.this, MemoriaCurta.class);
        startActivity(it);
    }
    public void btExerc27(View view){
        Intent it = new Intent(MenuMenor7.this, Acelerometro.class);
        startActivity(it);
    }
    public void btExerc37(View view){
        Intent it = new Intent(MenuMenor7.this, LogicaMatematica.class);
        startActivity(it);
    }
    public void btExerc47(View view){
        Intent it = new Intent(MenuMenor7.this, Jogomemoria1.class);
        startActivity(it);
    }

    public void btExerc57(View view){
        Intent it = new Intent(MenuMenor7.this, NumCresc.class);
        startActivity(it);
    }
    public void btExerc67(View view){
        Intent it = new Intent(MenuMenor7.this, JogoSons.class);
        startActivity(it);
    }
    public void btExerc77(View view){
        Intent it = new Intent(MenuMenor7.this, Cores.class);
        startActivity(it);
    }
    public void btExerc97(View view){
        Intent it = new Intent(MenuMenor7.this, JogoSumiu2.class);
        startActivity(it);
    }
    public void btExerc107(View view){
        Intent it = new Intent(MenuMenor7.this, JogoMaior.class);
        startActivity(it);
    }
    public void btExerc117(View view){
        Intent it = new Intent(MenuMenor7.this, JogoPergunta.class);
        startActivity(it);
    }
}
