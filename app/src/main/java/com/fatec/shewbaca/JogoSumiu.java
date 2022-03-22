package com.fatec.shewbaca;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Caique on 27/04/2015.
 */
public class JogoSumiu extends ActionBarActivity {
    public Button b1,b2;
    public TextView txt1, txt2, txt3;
    public TextView d1,d2;
    public String a1,a2,a3,a4;
    private Handler handler;
    private int contT=0;
    private TimerTask task;
    private Timer timerAtual = new Timer();
    public Random r = new Random();
    public int an1,an2,an3,an4,retira;
    public String r1,r2;
    public TextView txtpontuacao;
    public int pontos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_jogosumiu);
        b1 =(Button)this.findViewById(R.id.b1);
        b2=(Button)this.findViewById(R.id.b2);
        txt1 =(TextView)this.findViewById(R.id.txt1);
        txt2 =(TextView)this.findViewById(R.id.txt2);
        txt3 =(TextView)this.findViewById(R.id.txt3);
        d1 =(TextView)this.findViewById(R.id.d1);
        d2 =(TextView)this.findViewById(R.id.d2);
        txtpontuacao=(TextView)this.findViewById(R.id.tponto);
        handler = new Handler();
        pontos=0;
        fase1();

    }
    private void ativaTimer(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        contT++;
                        if(contT == 3){
                            //ação
                        }
                    }
                });
            }};

        timerAtual.schedule(task, 0, 1000);
        contT=0;
    }

    public void fase1(){

        //obtem valores
        an1 = r.nextInt(18)+1;
        an2 = r.nextInt(18)+1;
        an3 = r.nextInt(18)+1;
        an4 = r.nextInt(18)+1;
        //valida valor 4 diferente dos outros
        while(an1==an4 || an2==an4 || an3==an4){
            an4 = r.nextInt(18)+1;
        }
//descobre valor da primeira info
        if(an1==1){
            a1= "A";
        }
        if(an1==2){
            a1= "C";
        }
        if(an1==3){
            a1= "G";
        }
        if(an1==4){
            a1= "H";
        }
        if(an1==5){
            a1= "J";
        }
        if(an1==6){
            a1= "F";
        }
        if(an1==7){
            a1= "O";
        }
        if(an1==8){
            a1= "P";
        }
        if(an1==9){
            a1= "Q";
        }
        if(an1==10){
            a1= "W";
        }
        if(an1==11){
            a1= "Y";
        }
        if(an1==12){
            a1= "X";
        }
        if(an1==13){
            a1= "Z";
        }
        if(an1==14){
            a1= "1";
        }
        if(an1==15){
            a1= "3";
        }
        if(an1==16){
            a1= "4";
        }
        if(an1==17){
            a1= "7";
        }
        if(an1==18){
            a1= "9";
        }
        if(an1==19){
            a1= "0";
        }
        //descobre valor segunda info

        if(an2==1){
            a2= "A";
        }
        if(an2==2){
            a2= "C";
        }
        if(an2==3){
            a2= "G";
        }
        if(an2==4){
            a2= "H";
        }
        if(an2==5){
            a2= "J";
        }
        if(an2==6){
            a2= "F";
        }
        if(an2==7){
            a2= "O";
        }
        if(an2==8){
            a2= "P";
        }
        if(an2==9){
            a2= "Q";
        }
        if(an2==10){
            a2= "W";
        }
        if(an2==11){
            a2= "Y";
        }
        if(an2==12){
            a2= "X";
        }
        if(an2==13){
            a2= "Z";
        }
        if(an2==14){
            a2= "1";
        }
        if(an2==15){
            a2= "3";
        }
        if(an2==16){
            a2= "4";
        }
        if(an2==17){
            a2= "7";
        }
        if(an2==18){
            a2= "9";
        }
        if(an2==19){
            a2= "0";
        }
        //descobre valor terceiro info

        if(an3==1){
            a3= "A";
        }
        if(an3==2){
            a3= "C";
        }
        if(an3==3){
            a3= "G";
        }
        if(an3==4){
            a3= "H";
        }
        if(an3==5){
            a3= "J";
        }
        if(an3==6){
            a3= "F";
        }
        if(an3==7){
            a3= "O";
        }
        if(an3==8){
            a3= "P";
        }
        if(an3==9){
            a3= "Q";
        }
        if(an3==10){
            a3= "W";
        }
        if(an3==11){
            a3= "Y";
        }
        if(an3==12){
            a3= "X";
        }
        if(an3==13){
            a3= "Z";
        }
        if(an3==14){
            a3= "1";
        }
        if(an3==15){
            a3= "3";
        }
        if(an3==16){
            a3= "4";
        }
        if(an3==17){
            a3= "7";
        }
        if(an3==18){
            a3= "9";
        }
        if(an3==19){
            a3= "0";
        }

//descobre valor quarta info

        if(an4==1){
            a4= "A";
        }
        if(an4==2){
            a4= "C";
        }
        if(an4==3){
            a4= "G";
        }
        if(an4==4){
            a4= "H";
        }
        if(an4==5){
            a4= "J";
        }
        if(an4==6){
            a4= "F";
        }
        if(an4==7){
            a4= "O";
        }
        if(an4==8){
            a4= "P";
        }
        if(an4==9){
            a4= "Q";
        }
        if(an4==10){
            a4= "W";
        }
        if(an4==11){
            a4= "Y";
        }
        if(an4==12){
            a4= "X";
        }
        if(an4==13){
            a4= "Z";
        }
        if(an4==14){
            a4= "1";
        }
        if(an4==15){
            a4= "3";
        }
        if(an4==16){
            a4= "4";
        }
        if(an4==17){
            a4= "7";
        }
        if(an4==18){
            a4= "9";
        }
        if(an4==19){
            a4= "0";
        }

        txt1.setText(a1);
        txt2.setText(a2);
        txt3.setText(a3);
        ativaTimer();
        retira = r.nextInt(2)+1;
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        if(retira==1){
            d1.setText(a2);
            d2.setText(a3);
            b1.setText(a1);
            b2.setText(a4);
        }
        if(retira==2){
            d1.setText(a1);
            d2.setText(a3);
            b2.setText(a1);
            b1.setText(a4);
        }
        if(retira==3){
            d1.setText(a1);
            d2.setText(a2);
            b2.setText(a1);
            b1.setText(a4);
        }

    }
    public void b1(View view){

       if(retira == 1){
           AlertDialog alertDialog = new AlertDialog.Builder(this).create();
           alertDialog.setTitle("Parabéns!");
           alertDialog.setMessage("Você acertou");
           alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                   pontos=pontos+1;
                   txtpontuacao.setText(String.valueOf(pontos));
                   fase1();
               }
           });
           alertDialog.show();
       }
       if(retira == 2){
           AlertDialog alertDialog = new AlertDialog.Builder(this).create();
           alertDialog.setTitle("Que Pena!");
           alertDialog.setMessage("Você errou");
           alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                   pontos =0;
                   fase1();
               }
           });
           alertDialog.show();
       }
       if(retira == 3){
           AlertDialog alertDialog = new AlertDialog.Builder(this).create();
           alertDialog.setTitle("Que Pena!");
           alertDialog.setMessage("Você errou");
           alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                   pontos =0;
                   fase1();
               }
           });
           alertDialog.show();
       }
    }
    public void b2(View view){

        if(retira == 1){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Que Pena!");
            alertDialog.setMessage("Você errou");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                    pontos =0;
                    fase1();
                }
            });
            alertDialog.show();
        }
        if(retira == 2){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Parabéns!");
            alertDialog.setMessage("Você acertou");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                    pontos=pontos+1;
                    txtpontuacao.setText(String.valueOf(pontos));
                    fase1();
                }
            });
            alertDialog.show();
        }
        if(retira == 3){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Parabéns!");
            alertDialog.setMessage("Você acertou");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                    pontos=pontos+1;
                    txtpontuacao.setText(String.valueOf(pontos));
                    fase1();
                }
            });
            alertDialog.show();
        }
    }

}
