package com.fatec.shewbaca;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fatec.Ranking.Enviar;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class JogoPergunta extends ActionBarActivity {
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    public Button btconf;
    public TextView txtpergunta, txtponto;
    public RadioButton rda, rdb, rdc;
    public Random r = new Random();
    public int pontos,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    public int resp;
    public String explicacao;
    public int pergunta,secs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_pergunta);
        btconf=(Button) this.findViewById(R.id.btconfirmar);
        txtpergunta=(TextView) this.findViewById(R.id.txtpergunta);
        txtponto=(TextView) this.findViewById(R.id.txtpontopergunta);
        rda=(RadioButton) this.findViewById(R.id.rda);
        rdb=(RadioButton) this.findViewById(R.id.rdb);
        rdc=(RadioButton) this.findViewById(R.id.rdc);
        pontos = 0;
        p1=0;
        p2=0;
        p3=0;
        p4=0;
        p5=0;
        p6=0;
        p7=0;
        p8=0;
        p9=0;
        p10=0;
        secs=0;
        sortearpergunta();
           }

    public void pergunta1(){
txtpergunta.setText("Cinco competidores (A, B, C, D e E) disputam uma prova de natação que premia o 1º, 2º e 3º colocados com medalhas de ouro, prata e bronze, respectivamente. As seguintes conclusões sobre a prova são falsas, mas uma afirmação de cada uma delas (note que cada conclusão possui duas afirmações) pode ser verdadeira.\n" +
        "\n" +
        "A não ganhou o ouro e B não ganhou a prata;\n" +
        "D não ganhou a prata e B não ganhou o bronze;\n" +
        "C ganhou uma medalha, já D não ganhou nenhuma;\n" +
        "A ganhou uma medalha, já C não ganhou nenhuma;\n" +
        "D ganhou uma medalha e E também.");
        rda.setText("A ganhou a medalha de ouro, D levou a medalha de prata e C ganhou a medalha de bronze.");
        rdb.setText("B ganhou a medalha de ouro, A levou a medalha de prata e C ganhou a medalha de bronze.");
        rdc.setText("C ganhou a medalha de ouro, D levou a medalha de prata e E ganhou a medalha de bronze.");
        resp=1;
        p1=1;
    }
    public void pergunta2(){
        txtpergunta.setText("Você deseja construir chiqueiros. Porém, você deve construir 4 chiqueiros e distribuir 9 porcos entre eles, de forma que cada chiqueiro tenha um número ímpar de porcos. Como fazer isso?");
        rda.setText("Construa 2 chiqueiros e coloque 1 porco em cada um, construa 2 com 3 porcos e com o restante faça Bacon ");
        rdb.setText("Construa 1 chiqueiro e coloque 6 porcos, e faça 3 chiqueiros com 1 porco em cada");
        rdc.setText("Construa 3 chiqueiros e coloque 3 porcos em cada um. Depois, é só construir um quarto chiqueiro em volta desses três chiqueiros.");
        resp=3;
        p2=1;
    }
    public void pergunta3(){
        txtpergunta.setText("Três pessoas vão pescar: 2 pais e 2 filhos. Como isso é possível?");
        rda.setText("Isso não é possivel");
        rdb.setText("As 3 pessoas são: o avô, o pai e o filho.");
        rdc.setText("Tem 3 pessoas no barco e uma nadando.");
        resp=2;
        p3=1;
    }
    public void pergunta4(){
        txtpergunta.setText("Marcos está olhando a fotografia de alguém. Seu amigo pergunta quem é o homem do retrato. Marcos responde: “Irmãos e irmãs eu não tenho, mas o pai deste cara é filho do meu pai”. Quem está na fotografia?");
        rda.setText("O filho de Marcos.");
        rdb.setText("O sobrinho de Marcos.");
        rdc.setText("O meio irmão de Marcos.");
        resp=1;
        p4=1;
    }
    public void pergunta5(){
        txtpergunta.setText("Três pessoas vão pescar: 2 pais e 2 filhos. Como isso é possível?");
        rda.setText("Isso não é possivel");
        rdb.setText("As 3 pessoas são: o avô, o pai e o filho.");
        rdc.setText("Tem 3 pessoas no barco e uma nadando.");
        resp=2;
        p5=1;
    }
    public void pergunta6(){
        txtpergunta.setText("Todas as minhas flores, menos duas, são rosas. Todas as minhas flores, menos duas, são tulipas. Todas as minhas flores, menos duas, são margaridas. Quantas flores eu tenho?");
        rda.setText("2 flores: sendo que nenhuma delas é uma rosa, uma tulipa ou uma margarida.");
        rdb.setText("3 flores: uma rosa, uma tulipa e uma margarida.");
        rdc.setText("4 flores: 2 rosas, 1 tulipa e 1 margarida.");
        resp=2;
        p6=1;
    }
    public void pergunta7(){
        txtpergunta.setText("Você tem 8 rosquinhas doces. As rosquinhas têm o mesmo peso, com exceção de uma, que é mais pesada. Você tem uma balança como de pratos simples à sua disposição. Qual é o número mínimo de pesagens que deve ser feito para se descobrir qual das rosquinhas é a mais pesada?");
        rda.setText("5 pesagens");
        rdb.setText("3 pesagens");
        rdc.setText("2 pesagens");
        resp=2;
        p7=1;
        explicacao ="Coloque 3 rosquinhas de cada lado da balança. Caso a balança fique equilibrada, a rosquinha mais pesada é uma das duas que não estão na balança. Portanto é só colocar uma de cada lado da balança para descobrir qual é a mais pesada. Porém, caso a balança penda para um dos lados, retire as 3 rosquinhas do lado que ficou mais pesado e pese duas delas. Daí, caso a balança se equilibre, a rosquinha mais pesada é a que não está sendo pesada. Caso contrário, a balança revelará qual das duas é a mais pesada.";
    }
    public void pergunta8(){
        txtpergunta.setText("Sua gaveta de meias contém 10 pares de meias brancas e 10 pares de meias pretas. Suponha que você só possa pegar uma meia de cada vez e que você não possa ver a cor desta meia até que a retire da gaveta, quantas meias você terá que pegar até obter, no mínimo, um par de meias da mesma cor?");
        rda.setText("4 meias");
        rdb.setText("2 meias");
        rdc.setText("3 meias");
        resp=3;
        p8=1;
        explicacao="Na pior das hipóteses, você terá obtido, nas primeiras duas vezes, uma meia preta e outra branca. Na terceira é certo que você retirará uma meia que forme par com uma das anteriores.";
    }
    public void pergunta9(){
        txtpergunta.setText("Em uma reunião de família, estavam presentes as seguintes pessoas: Um avô, uma avó, dois pais, duas mães, três crianças, três netos (as), um irmão, duas irmãs, dois filhos, três filhas, um genro, uma sogra e uma nora. Porém, não estavam lá tantas pessoas como pode parecer. Quantas pessoas estavam presentes, e quem eram?");
        rda.setText("Estavam lá duas garotinhas, um garoto, seus pais e seus avós, totalizando sete pessoas.");
        rdb.setText("Estavam lá todos menconados no enunciado");
        rdc.setText("Nenhuma das anteriores");
        resp=1;
        p9=1;
    }
    public void pergunta10(){
        txtpergunta.setText("Isaac e Alberto contavam, muito empolgados, o resultado da Terceira Feira Anual Internacional de Ciências, realizada na Suécia. Na feira, havia 3 competidores: Luís, René e João. Isaac disse que Luís havia sido o vencedor, com René ficando em segundo lugar. Já Alberto, disse que João ganhou, com Luís em segundo.\n" +
                "\n" +
                "Na verdade, nenhum dos dois contou inteiramente a verdade sobre os resultados da feira de ciências. O que ocorreu foi que cada um deu uma afirmação verdadeira e outra falsa sobre a classificação. Diga: Qual foi a verdadeira classificação dos competidores?");
        rda.setText("1º João; 2° Luís; 3º René.");
        rdb.setText("1º Luís; 2° René; 3º João.");
        rdc.setText("1º João; 2° René; 3º Luís.");
        resp=3;
        p10=1;
    }
    public void sortearpergunta(){
        pergunta = r.nextInt(9)+1;

        if(pergunta ==1){
            if(p1==1){
                sortearpergunta();

            }
            else{
                pergunta1();
            }
        }
        if(pergunta ==2){
            if(p2==1){
                sortearpergunta();

            }
            else{
                pergunta2();
            }
        }
        if(pergunta ==3){
            if(p3==1){
                sortearpergunta();

            }
            else{
                pergunta3();
            }
        }
        if(pergunta ==4){
            if(p4==1){
                sortearpergunta();

            }
            else{
                pergunta4();
            }
        }
        if(pergunta ==5){
            if(p5==1){
                sortearpergunta();

            }
            else{
                pergunta5();
            }
        }
        if(pergunta ==6){
            if(p6==1){
                sortearpergunta();

            }
            else{
                pergunta6();
            }
        }
        if(pergunta ==7){
            if(p7==1){
                sortearpergunta();

            }
            else{
                pergunta7();
            }
        }
        if(pergunta ==8){
            if(p8==1){
                sortearpergunta();

            }
            else{
                pergunta8();
            }
        }
        if(pergunta ==9){
            if(p9==1){
                sortearpergunta();

            }
            else{
                pergunta9();
            }
        }
        if(pergunta ==10){
            if(p10==1){
                sortearpergunta();

            }
            else{
                pergunta10();
            }
        }
    }

public void confirmarresposta(View view){
    RadioGroup rg = (RadioGroup)
            findViewById(R.id.rgopcoes);
    int op = rg.getCheckedRadioButtonId();
    if(op==R.id.rda && resp==1){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Parabéns!");
        alertDialog.setMessage("Você acertou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=pontos+1;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rda && resp==2){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Errado!!");
        alertDialog.setMessage("Você errou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=0;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rda && resp==3){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Errado!!");
        alertDialog.setMessage("Você errou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=0;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rdb && resp==1){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Errado!!");
        alertDialog.setMessage("Você errou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=0;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rdb && resp==2){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Parabéns!");
        alertDialog.setMessage("Você acertou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=pontos+1;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rdb && resp==3){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Errado!!");
        alertDialog.setMessage("Você errou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=0;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rdc && resp==1){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Errado!!");
        alertDialog.setMessage("Você errou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=0;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rdc && resp==2){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Errado!!");
        alertDialog.setMessage("Você errou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=0;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }
    if(op==R.id.rdc && resp==3){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Parabéns!");
        alertDialog.setMessage("Você acertou");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
                pontos=pontos+1;
                txtponto.setText(String.valueOf(pontos));
                sortearpergunta();
            }
        });
        alertDialog.show();
    }

}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jogo_pergunta, menu);
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
    public void btPublicarPergunta(View view){
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
            pDialog = new ProgressDialog(JogoPergunta.this);
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
                    chave=dados[2];
                }
            }catch(IOException e){
                Log.e("Exception", "File read failed: " + e.toString());
            }
            Enviar e = new Enviar(String.valueOf(pontos),String.valueOf(secs),"7",nome,chave);
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
