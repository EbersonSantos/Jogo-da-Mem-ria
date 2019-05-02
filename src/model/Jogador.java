/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author Eberson
 */
public class Jogador implements Serializable{
    
    private String nome;
    private int pontuacao;
    public static final String NOME_DEFAULT = "JOGADOR";
    
    public Jogador(){
        this.nome = NOME_DEFAULT;
    }
    
    public Jogador(String nome){
        this.nome = nome;
    }
    
    public Jogador(String nome, int pontos){
        this.nome = nome;
        this.pontuacao = pontos;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao() {
        this.pontuacao++;
    }

    public void zerar() {
        this.pontuacao = 0;
    }
    
}
