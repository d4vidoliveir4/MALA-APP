package com.fatec.shewbaca;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import android.content.DialogInterface;
import android.app.AlertDialog;
import java.util.Timer;
import java.util.TimerTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.fatec.Ranking.Enviar;

public class JogoSons extends ActionBarActivity {
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    public Button btsol, btla, btsi, btdo, btjogar, btvoltar, btfinal;
    public TextView txtponto;
    public int ponto;
    public Random r = new Random();
    public MediaPlayer sol = null;
    public MediaPlayer la = null;
    public MediaPlayer si = null;
    public MediaPlayer doo = null;
    public int pri, seg, ter, qua, qui, sex,set, oit, non, dec;
    public int nivel =1;
    public int resppri, respseg, respter, respqua, respqui, respsex,respset, respoit, respnon, respdec;
    public int contclique;
    public int sscs=0;
    private int contT=0;
    private Timer timerAtual = new Timer();
    private TimerTask task;
    private long startTime = 0L;
    private Handler handler;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int secs=0;
    int aux=0;
    int selecionado=0;
    private int number;






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogosons);
        btsol = (Button) this.findViewById(R.id.btsol);
        btla = (Button) this.findViewById(R.id.btla);
        btsi = (Button) this.findViewById(R.id.btsi);
        btdo = (Button) this.findViewById(R.id.btdo);
        btvoltar = (Button) this.findViewById(R.id.btvoltar);
        btjogar = (Button) this.findViewById(R.id.btjogar);
        btfinal = (Button) this.findViewById(R.id.btfinal);
        txtponto = (TextView) this.findViewById(R.id.txtponto);
        btjogar.setVisibility(View.INVISIBLE);
        btfinal.setEnabled(false);
        contclique=0;
        ponto =0;
        handler = new Handler();
        fase1();
    }
    private void ativaTimersol(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        contT++;
                        if(contT == 3){
                            tocarsol();
                        }
                    }
                });
            }};

        timerAtual.schedule(task, 0, 1000);
        contT=0;
    }
    private void ativaTimerla(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        contT++;
                        if(contT == 3){
                            tocarla();
                        }
                    }
                });
            }};


        timerAtual.schedule(task, 0, 1000);
        contT=0;
    }
    private void ativaTimersi(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        contT++;
                        if(contT == 3){
                            tocarsi();
                        }
                    }
                });
            }};

        timerAtual.schedule(task, 0, 1000);
        contT=0;
    }
    private void ativaTimerdo(){
        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        contT++;
                        if(contT == 3){
                            tocardo();
                        }
                    }
                });
            }};

        timerAtual.schedule(task, 0, 1000);
        contT=0;
    }

    //funçoes para tocar nota
    public void msgerro(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Errou!");
        alertDialog.setMessage("Você errou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                fase1();
            }
        });
        alertDialog.show();


    }
    public void msgfinal(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Parabens");
        alertDialog.setMessage("Você venceu todos os niveis");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                fase1();
            }
        });
        alertDialog.show();


    }
    public void tocarsol(){

        sol = MediaPlayer.create(this, R.raw.sol);
        sol.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer sol) {
                sol.stop();
                sol.release();
                sol= null;
            }
        });
        sol.start();
    }

    public void tocarla(){

        la = MediaPlayer.create(this, R.raw.la);
        la.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer la) {
                la.stop();
                la.release();
                la= null;
            }
        });
        la.start();

    }
    public void tocarsi(){

        si = MediaPlayer.create(this, R.raw.si);
        si.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer si) {
                si.stop();
                si.release();
                si= null;
            }
        });
        si.start();

    }
    public void tocardo(){

        doo = MediaPlayer.create(this, R.raw.doooo);
        doo.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer doo) {
                doo.stop();
                doo.release();
                doo= null;
            }
        });
        doo.start();

    }
    //inicio fases
    //fase1--------------------------------------------------------------------------------------------
    public void fase1(){
        nivel =1;
        contclique=0;
        pri = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4){
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }

        btjogar.setVisibility(View.VISIBLE);

    }
    //fase2--------------------------------------------------------------------------------------------
    public void fase2(){
        seg = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE); }

    //fase3--------------------------------------------------------------------------------------------
    public void fase3(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        ter = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);

            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);

    }
    //fase4--------------------------------------------------------------------------------------------
    public void fase4(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        qua = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);;
            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qua == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qua == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qua == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qua == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);
    }
    //fase5--------------------------------------------------------------------------------------------
    public void fase5(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        qui = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qua == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qua == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qua == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qua == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qui == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qui == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qui == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qui == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);
    }
    //fase6--------------------------------------------------------------------------------------------
    public void fase6(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        sex = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qua == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qua == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qua == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qua == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qui == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qui == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qui == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qui == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (sex == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (sex == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (sex == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (sex == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);
    }
    //fase7--------------------------------------------------------------------------------------------
    public void fase7(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        set = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qua == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qua == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qua == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qua == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qui == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qui == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qui == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qui == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (sex == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (sex == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (sex == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (sex == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (set == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (set == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (set == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (set == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);
    }
    //fase8--------------------------------------------------------------------------------------------
    public void fase8(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        oit = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qua == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qua == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qua == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qua == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qui == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qui == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qui == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qui == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (sex == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (sex == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (sex == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (sex == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (set == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (set == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (set == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (set == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (oit == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (oit == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (oit == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (oit == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);
    }
    //fase9--------------------------------------------------------------------------------------------
    public void fase9(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        non = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qua == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qua == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qua == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qua == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qui == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qui == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qui == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qui == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (sex == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (sex == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (sex == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (sex == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (set == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (set == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (set == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (set == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (oit == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (oit == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (oit == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (oit == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (non == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (non == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (non == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (non == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);
    }
    //fase10-------------------------------------------------------------------------------------------
    public void fase10(){
        contclique=0;
        btjogar.setVisibility(View.INVISIBLE);
        dec = r.nextInt(3)+1;
        if(pri ==1){
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if(pri ==2){
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if(pri ==3){
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if(pri ==4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (seg == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (seg == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (seg == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (seg == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (ter == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (ter == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (ter == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (ter == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qua == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qua == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qua == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qua == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (qui == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (qui == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (qui == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (qui == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (sex == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (sex == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (sex == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (sex == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (set == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (set == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (set == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (set == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (oit == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (oit == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (oit == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (oit == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (non == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (non == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (non == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (non == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        if (dec == 1) {
            //backgroud muda
            btsol.setBackgroundResource(R.drawable.solamareloes);
            ativaTimersol();
            btsol.setBackgroundResource(R.drawable.solamarelo);
            //backgroud normal
        }
        if (dec == 2) {
            //backgroud muda
            btla.setBackgroundResource(R.drawable.lavermelhoes);
            ativaTimerla();
            btla.setBackgroundResource(R.drawable.lavermelho);
            //backgroud normal
        }
        if (dec == 3) {
            //backgroud muda
            btsi.setBackgroundResource(R.drawable.siazules);
            ativaTimersi();
            btsi.setBackgroundResource(R.drawable.siazul);
            //backgroud normal
        }
        if (dec == 4) {
            //backgroud muda
            btdo.setBackgroundResource(R.drawable.doverdeesc);
            ativaTimerdo();
            btdo.setBackgroundResource(R.drawable.doverde);
            //backgroud normal
        }
        btjogar.setVisibility(View.VISIBLE);
    }
    public void btsol(View view){
        if(nivel ==1){
            resppri=1;
            if (pri == resppri){
                nivel++;
                ponto++;
                txtponto.setText(String.valueOf(ponto));
                fase2();}
            else
                msgerro();

        }

        if(nivel ==2){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respseg=1;
                if(seg == respseg){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase3();}
                else
                    msgerro();
            }
        }

        if(nivel ==3){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            else {
                respter = 1;
                if(ter == respter){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase4();
                }
                else
                    msgerro();


            }
        }

        if(nivel==4){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=1;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqua=1;
                if(qua == respqua){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase5();}
                else
                    msgerro();

            }
        }

        if(nivel == 5){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=1;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=1;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqui=1;
                if(qui == respqui){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase6();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 6){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=1;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=1;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=1;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respsex=1;
                if(sex == respsex   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase7();
                }
                else
                    msgerro();

            }

        }

        if(nivel == 7){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=1;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=1;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=1;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=1;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respset=1;
                if(set == respset   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase8();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 8){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=1;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=1;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=1;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=1;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =1;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respoit=1;
                if(oit == respoit   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase9();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 9){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=1;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=1;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=1;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=1;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =1;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=1;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respnon=1;
                if(non == respnon   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase10();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 10){
            if(contclique ==0){
                resppri =1;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =1;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=1;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=1;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=1;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=1;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =1;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=1;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==8){
                respnon=1;
                if(non==respnon)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respdec=1;
                if(dec == respdec   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    msgfinal();

                }
                else
                    msgerro();

            }

        }
    }

    public void btla(View view) {
        if (nivel == 1) {
            resppri = 2;
            if (pri == resppri){
                nivel++;
                ponto++;
                txtponto.setText(String.valueOf(ponto));
                fase2();}
            else
                fase1();
        }

        if(nivel ==2){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respseg=2;
                if(seg == respseg){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase3();}
                else
                    msgerro();
            }
        }

        if(nivel ==3){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            else {
                respter = 2;
                if(ter == respter){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase4();}
                else
                    msgerro();


            }
        }

        if(nivel==4){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=2;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqua=2;
                if(qua == respqua){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase5();}
                else
                    msgerro();

            }
        }
        if(nivel == 5){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=2;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=2;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqui=2;
                if(qui == respqui){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase6();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 6){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=2;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=2;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=2;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respsex=2;
                if(sex == respsex   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase7();
                }
                else
                    msgerro();

            }

        }

        if(nivel == 7){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=2;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=2;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=2;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=2;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respset=2;
                if(set == respset   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase8();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 8){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=2;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=2;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=2;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=2;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =2;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respoit=2;
                if(oit == respoit   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase9();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 9){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=2;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=2;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=2;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=2;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =2;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=2;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respnon=2;
                if(non == respnon   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase10();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 10){
            if(contclique ==0){
                resppri =2;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =2;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=2;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=2;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=2;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=2;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =2;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=2;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==8){
                respnon=2;
                if(non==respnon)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respdec=2;
                if(dec == respdec   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    msgfinal();

                }
                else
                    msgerro();

            }

        }
    }
    public void btsi(View view) {
        if (nivel == 1) {
            resppri = 3;
            if (pri == resppri) {
                nivel++;
                ponto++;
                txtponto.setText(String.valueOf(ponto));
                fase2();
            } else
                fase1();
        }

        if(nivel ==2){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respseg=3;
                if(seg == respseg){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase3();}
                else
                    msgerro();
            }
        }

        if(nivel ==3){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            else {
                respter = 3;
                if(ter == respter){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase4();}
                else
                    msgerro();


            }
        }

        if(nivel==4){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=3;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqua=3;
                if(qua == respqua){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase5();}
                else
                    msgerro();

            }
        }
        if(nivel == 5){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=3;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=3;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqui=3;
                if(qui == respqui){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase6();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 6){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=3;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=3;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=3;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respsex=3;
                if(sex == respsex   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase7();
                }
                else
                    msgerro();

            }

        }

        if(nivel == 7){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=3;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=3;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=3;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=3;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respset=3;
                if(set == respset   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase8();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 8){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=3;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=3;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=3;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=3;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =3;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respoit=3;
                if(oit == respoit   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase9();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 9){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=3;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=3;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=3;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=3;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =3;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=3;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respnon=3;
                if(non == respnon   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase10();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 10){
            if(contclique ==0){
                resppri =3;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =3;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=3;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=3;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=3;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=3;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =3;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=3;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==8){
                respnon=3;
                if(non==respnon)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respdec=3;
                if(dec == respdec   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    msgfinal();

                }
                else
                    msgerro();

            }

        }
    }
    public void btdo(View view) {
        if (nivel == 1) {
            resppri = 4;
            if (pri == resppri) {
                nivel++;
                ponto++;
                txtponto.setText(String.valueOf(ponto));
                fase2();
            } else
                fase1();
        }

        if(nivel ==2){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respseg=4;
                if(seg == respseg){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase3();}
                else
                    msgerro();
            }
        }

        if(nivel ==3){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            else {
                respter = 4;
                if(ter == respter){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase4();}
                else
                    msgerro();


            }
        }

        if(nivel==4){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=4;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqua=4;
                if(qua == respqua){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase5();}
                else
                    msgerro();

            }
        }
        if(nivel == 5){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=4;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=4;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respqui=4;
                if(qui == respqui){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase6();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 6){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=4;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=4;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=4;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respsex=4;
                if(sex == respsex   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase7();
                }
                else
                    msgerro();

            }

        }

        if(nivel == 7){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=4;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=4;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=4;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=4;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respset=4;
                if(set == respset   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase8();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 8){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=4;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=4;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=4;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=4;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =4;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respoit=4;
                if(oit == respoit   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase9();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 9){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=4;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=4;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=4;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=4;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =4;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=4;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respnon=4;
                if(non == respnon   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    fase10();
                }
                else
                    msgerro();

            }

        }
        if(nivel == 10){
            if(contclique ==0){
                resppri =4;
                if(pri == resppri)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==1){
                respseg =4;
                if(seg == respseg)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==2){
                respter=4;
                if(ter == respter)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==3){
                respqua=4;
                if(qua == respqua)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==4){
                respqui=4;
                if(qui == respqui)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==5){
                respsex=4;
                if(sex == respsex)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique ==6){
                respset =4;
                if(set ==respset)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==7){
                respoit=4;
                if(oit == respoit)
                    contclique++;
                else
                    msgerro();

            }
            if(contclique==8){
                respnon=4;
                if(non==respnon)
                    contclique++;
                else
                    msgerro();

            }
            else{
                respdec=4;
                if(dec == respdec   ){
                    nivel++;
                    ponto++;
                    txtponto.setText(String.valueOf(ponto));
                    msgfinal();

                }
                else
                    msgerro();

            }

        }
    }
    public void btPublicarSon(View view){
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
            pDialog = new ProgressDialog(JogoSons.this);
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
                    chave = dados[2];
                }
            }catch(IOException e){
                Log.e("Exception", "File read failed: " + e.toString());
            }
            Enviar e = new Enviar(String.valueOf(ponto),String.valueOf(sscs),"8",nome,chave);
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
