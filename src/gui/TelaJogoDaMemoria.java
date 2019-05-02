/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

class TelaJogoDaMemoria extends JPanel{
    
    private JanelaControl control;
    private JButton[][] grid;
    private boolean rede;
        
    public TelaJogoDaMemoria(JanelaControl control, boolean rede, boolean botoes){
        this.rede = rede;
        this.control = control;
        int size = control.getApp().getTamanho();
        this.setLayout(new GridLayout(size,size));
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.createComponentes(rede);
        if(botoes == false){
            this.desativarBotoes();
        }
    }
    
    private void createComponentes(boolean rede){                
        
        int size = control.getApp().getTamanho();
        grid = new JButton[size][size];
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){      
                JButton botao = new JButton(" "); //creates new button
                botao.setBackground(Color.DARK_GRAY);
                botao.setName(x+"-"+y);
                if(rede){
                    botao.setActionCommand("jogarRedes");
                }else{
                    botao.setActionCommand("jogar"); //jogar normal
                }
                botao.setFont(new Font("Arial", Font.PLAIN, 14));
                botao.addActionListener(control);
                grid[x][y] = botao;
                this.add(grid[x][y]);
            }
        }
    }
  
    public void setIcon(int linha, int coluna, int numeroImagem){ //mudar a imagem de um botao
        //System.out.println(linha+String.valueOf(coluna));
        String nome = String.valueOf(numeroImagem)+".png";
        ImageIcon icon = new ImageIcon(nome);
        grid[linha][coluna].setIcon(icon);
        grid[linha][coluna].setBackground(Color.LIGHT_GRAY);
        //grid[linha][coluna].setEnabled(false);
        
        
        //grid[linha][coluna].setText("");
    }
    
    public void setDefault(int linha, int coluna){
        grid[linha][coluna].setBackground(Color.DARK_GRAY);
        grid[linha][coluna].setIcon(null);
        grid[linha][coluna].setEnabled(true);
        //botao.setBackground(Color.YELLOW);
    }
    
    public int getLinhaButton(JButton b){       
        String s = b.getName();
        String[] result = s.split("-");
        return Integer.parseInt(result[0]);
    }
    
    public int getColunaButton(JButton b){
        
        String s = b.getName();
        String[] result = s.split("-");
        return Integer.parseInt(result[1]);
    }
    
    public void enabledButton(int linha, int coluna){ //desativar um botao e mudar seu texto
        grid[linha][coluna].setEnabled(false);
    }

    private void desativarBotoes() {
        int size = control.getApp().getTamanho();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){      
                grid[x][y].setEnabled(false);
            }
        }
    }

    void ativarBotoes() {
        int size = control.getApp().getTamanho();
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){      
                grid[x][y].setEnabled(true);
            }
        }
    }
}

