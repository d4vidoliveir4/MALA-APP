package com.fatec.shewbaca;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fatec.shewbaca.util.NumCresc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {
    Button botao;
    EditText nome;
    EditText idade;
    Button btGravar;
    Usuario us = new Usuario();
    String Key = "30A03";
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (EditText) this.findViewById(R.id.txtNome);
        idade = (EditText) this.findViewById(R.id.txtIdade);
        btGravar= (Button) this.findViewById(R.id.btGravar);
        getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);
        botao = (Button) findViewById(R.id.btExercicios);
        botao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_menu_exerc);
            }
        });

    }
    public void bt(View view){
        Intent it = new Intent(MainActivity.this, MainActivity.class);
        startActivity(it);
    }
    public void btVRanking(View view){
        Intent it = new Intent(MainActivity.this, VRanking.class);
        startActivity(it);
    }
    public void btVisitante(View view){
        botao.setEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    //---------------------------------------------ActivityExercicios-----------------------------------------------------
    /**
     * Controla o bot�o do primeiro exercicio no menu Exercicios.
     * @param view
     */
    public void btExerc1(View view){
        Intent it = new Intent(MainActivity.this, MemoriaCurta.class);
        startActivity(it);
    }
    public void btExerc2(View view){
        Intent it = new Intent(MainActivity.this, Acelerometro.class);
        startActivity(it);
    }
    public void btExerc3(View view){
        Intent it = new Intent(MainActivity.this, LogicaMatematica.class);
        startActivity(it);
    }
    public void btExerc4(View view){
        Intent it = new Intent(MainActivity.this, Jogomemoria1.class);
        startActivity(it);
    }

    public void btExerc5(View view){
        Intent it = new Intent(MainActivity.this, NumCresc.class);
        startActivity(it);
    }
    public void btExerc6(View view) {
        Intent it = new Intent(MainActivity.this, JogoSons.class);
        startActivity(it);
    }
    public void btExerc7(View view) {
        Intent it = new Intent(MainActivity.this, Cores.class);
        startActivity(it);
    }
    public void btExerc9(View view) {
        Intent it = new Intent(MainActivity.this, JogoSumiu2.class);
        startActivity(it);
    }
    public void btExerc10(View view) {
        Intent it = new Intent(MainActivity.this, JogoMaior.class);
        startActivity(it);
    }
    public void btExerc11(View view) {
        Intent it = new Intent(MainActivity.this, JogoPergunta.class);
        startActivity(it);
    }
    public void btsobre(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Informações");
        alertDialog.setMessage("Esse projeto foi criado para ajudar pessoas com deficiencia cognitiva a terem uma outra forma de realizarem treinamento. Contato: caiquehand@hotmail.com");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
            }
        });
        alertDialog.show();
    }
    public void btGravar(View view) {
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("user.config", Context.MODE_PRIVATE));
            outputStreamWriter.write(nome.getText().toString()+"-"+idade.getText().toString()+"-"+Key);
            outputStreamWriter.close();
        }catch(IOException e){
            Log.e("Exception", "File write failed: " + e.toString());
        }
        age=Integer.parseInt(idade.getText().toString());
        us.setIdade(Integer.parseInt(idade.getText().toString()));
        us.setNome(nome.getText().toString());
        nome.setEnabled(false);
        idade.setEnabled(false);
        btGravar.setEnabled(false);
        if (age<7)
        {setContentView(R.layout.activity_menu_menor7);}
        if(age>=7){
        setContentView(R.layout.activity_menu_exerc);}

    }
    //---------------------------------------------ActivityMemoriaCurta-----------------------------------------------------

}
