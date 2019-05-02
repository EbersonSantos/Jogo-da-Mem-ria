package model;

import java.io.Serializable;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eberson
 */
public class Tabuleiro implements Serializable {

    private Celula[][] matriz;         //matriz do jogo
    private int abertos;

    public Tabuleiro(int n) {       //cria matriz do jogo com número par
        if (n % 2 == 0) {
            matriz = new Celula[n][n];
        } else {
            matriz = new Celula[4][4];
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = new Celula();
            }
        }

        this.abertos = 0;
        this.preencherMatriz();     //preenche a matriz randomicamente
        //this.printMatriz();
    }

    public Tabuleiro(String s) {
        this.abertos = 0;
        int tam = s.length();
        
        int n = (int) Math.sqrt(tam);
        
        if (n % 2 == 0) {
            matriz = new Celula[n][n];
        } else {
            matriz = new Celula[4][4];
        }
        
        int count = 0;
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                String letra = s.substring(count, count+1);
                int numero = Integer.valueOf(letra);
                count++;
                matriz[i][j] = new Celula(numero);
            }
        }
        
        //this.printMatriz();

    }

    public String toString(){
        String conversao = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                int numero = matriz[i][j].getValor();
                conversao = conversao + numero;
            }
        }
        return conversao;
    }
    
    private void preencherMatriz() {

        Random r = new Random();
        for (int i = 0; i < Math.pow(matriz.length, 2) / 2; ++i) {
            for (int j = 0; j < 2; j++) {
                boolean sorteado = false;
                while (!sorteado) {
                    int linha = r.nextInt(matriz.length);
                    int coluna = r.nextInt(matriz.length);

                    if (matriz[linha][coluna].getValor() != 0) {
                        sorteado = false;
                    } else {
                        sorteado = true;
                        matriz[linha][coluna].setValor(i + 1);
                    }
                }
            }

        }
    }

    public void printMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j].getValor() + " ");
            }
            System.out.println("");
        }
            System.out.println();
    }

    public boolean getEstado(int linha, int coluna) {
        return matriz[linha][coluna].getEstado();
    }

    public void setEstado(int linha, int coluna) {
        matriz[linha][coluna].setEstado();
    }

    public int getTamanho() {
        return matriz.length;
    }

    public boolean jogar(int linha1, int coluna1, int linha2, int coluna2) {
        if (matriz[linha1][coluna1].getValor() == matriz[linha2][coluna2].getValor()) {
            matriz[linha1][coluna1].setEstado();
            matriz[linha2][coluna2].setEstado();
            abertos = abertos + 2;
            return true;
        }
        return false;
    }

    public int getAbertos() {
        return abertos;
    }

    public int getValorCelula(int linha, int coluna) {
        return matriz[linha][coluna].getValor();
    }

}
