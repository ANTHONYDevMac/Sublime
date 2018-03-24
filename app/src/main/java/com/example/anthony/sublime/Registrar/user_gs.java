package com.example.anthony.sublime.Registrar;

/**
 * Created by anthony on 10/03/18.
 */

public class user_gs {

    public String sobrenome, descricao, nome;

    public user_gs() {}

    public user_gs(String sobrenome, String descricao, String nome) {
        this.sobrenome = sobrenome;
        this.descricao = descricao;
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getNome() {
        return nome;
    }


}
