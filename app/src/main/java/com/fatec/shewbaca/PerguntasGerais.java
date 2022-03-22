package com.fatec.shewbaca;

import com.fatec.banco.DataHelperLM;
import com.fatec.banco.DataHelperPerguntas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class PerguntasGerais extends Activity {
	private DataHelperPerguntas dh;
	TextView lblpergunta, respa, respb, respc, respd;
	Button bta, btb, btc, btd;
	
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
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perguntas);
		lblpergunta = (TextView) this.findViewById(R.id.pergunta);
		respa = (TextView) this.findViewById(R.id.respa);
		respb = (TextView) this.findViewById(R.id.respb);
		respc = (TextView) this.findViewById(R.id.respc);
		respd = (TextView) this.findViewById(R.id.respd);
		bta = (Button) this.findViewById(R.id.bta);
		btb = (Button) this.findViewById(R.id.btb);
		btc = (Button) this.findViewById(R.id.btc);
		btd = (Button) this.findViewById(R.id.btd);
		this.dh = new DataHelperPerguntas(this);
		carregaBanco();
	}
	
	public void carregaBanco(){
		//S� executar essa fun��o durante o desenvolvimento
		//Essa fun��o carrega as informa��es no sqlite
		dh.deleteAll();
		dh.insert("Qual dessas palavras nao faz sentido com as outras?","Macaco","Elefante","Computador","Leao","Computador");
		dh.insert("Qual dessas palavras nao faz sentido com as outras","Celular","Telefone","Orelhao","Perfume","Perfume");
		dh.insert("Qual dessas palavras nao faz sentido com as outras","Caderno","Ventilador","Livro","Caneta","Ventilador");
		dh.insert("Marcos tem 3 filhos, Joao, Antonio e o terceiro filho tem o nome composto dos dois primeiros irmaos, Qual eh o seu nome?","Jooo Antonio","Marcos Antonio","Marcos Joao","NDA","Joao Antonio");
	}
	
	
}
