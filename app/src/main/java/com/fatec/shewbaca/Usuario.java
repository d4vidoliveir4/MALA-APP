package com.fatec.shewbaca;

import com.fatec.banco.DataHelperLM;

/**
 * Created by David on 10/03/2015.
 */
public class Usuario {

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    private int idade=0;
    private String nome="Anon";
}
