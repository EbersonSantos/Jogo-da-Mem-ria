/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import model.Jogador;
import model.Tabuleiro;

/**
 *
 * @author Eberson
 */
public class Controlador {
     
    //private Servidor server;
    private Tabuleiro board;
    private Jogador jogador1;
    private Jogador jogador2;
    private int tamanhoBoard;
    private boolean showAll;
    private static int servidores;
            
    public Controlador(int n, String nomeJogador){
        board = new Tabuleiro(n);
        
        this.jogador1 = new Jogador(nomeJogador);
              
        tamanhoBoard = n;
        servidores++;
//        if(servidores == 1){
//            System.out.println("criei outro servidor");
//            server = new Servidor();
//            new Thread(server).start();
//        }        
    }

    public void criarJogador2(String nome, int pontos) {
        this.jogador2 = new Jogador(nome, pontos);
    }
    
    public Controlador(int n){
        board = new Tabuleiro(n);
        
        this.jogador1 = new Jogador();
              
        tamanhoBoard = n;
        
        servidores++;
//        if(servidores == 1){
//            System.out.println("criei outro servidor");
//            server = new Servidor();
//            new Thread(server).start();
//        }        
    }
    
    public void novoTabuleiro(int n){
       board = new Tabuleiro(n);
       tamanhoBoard = n; 
    }

    public Tabuleiro getBoard() {
        return board;
    }
    
//    public Servidor getServer() {
//        return server;
//    }
    
    public int getValorCelula(int linha, int coluna){
        return board.getValorCelula(linha,coluna);
    }
    
    public boolean celulaIsOpen(int linha, int coluna){
        return board.getEstado(linha,coluna);
    }
    
//    public Tabuleiro getTabuleiro(){ //retorna o tabuleiro se alguem desistir
//       if(showAll){
//           return board;
//       }else{
//           return null;
//       }
//    }
    
    public int getTamanho(){
        return tamanhoBoard;
    }

    public Jogador getJogador1() {
        return jogador1;
    }
    
    
    public boolean jogar(int linha1, int coluna1, int linha2, int coluna2){
        boolean result = board.jogar(linha1,coluna1, linha2, coluna2);
//        if(result){
//            showAll = true;
//        }
        return result;
    }
   
    public boolean fimDeJogo(){
        int abertos = board.getAbertos();
        
        int tamanho = (int)Math.pow(board.getTamanho(),2);
        
        if(abertos == tamanho){
            return true;
        }else{
            return false;
        }
    }

    public void apagarTabuleiro() {
        this.board = null;
    }

    public void setBoard(String strRecebida) {
        this.board = new Tabuleiro(strRecebida);
    }

    public Jogador getJogador2() {
        return jogador2;
    }
    
}
