/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class TelaInformacoesSingleplayer extends JPanel{
    
    private JanelaControl control;
    private JLabel icone;

    private JLabel jogador1;
    private JLabel labelPontos1;
    private JLabel pontos1;
    // End of variables declaration      
    
    public TelaInformacoesSingleplayer(JanelaControl control){
        this.control = control;
        this.setLayout(new BorderLayout());
       
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.createComponentes();
    }
    
    private void createComponentes(){

        jogador1 = new JLabel();

        pontos1 = new JLabel();

        labelPontos1 = new JLabel();

        jogador1.setText("JOGADOR:");

        pontos1.setText("0");

        labelPontos1.setText("Ponto(s)");

        ImageIcon icon = new ImageIcon("gatinho.jpg");
        icone = new JLabel(icon);

      
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(icone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jogador1)
                .addGap(112, 112, 112)
                .addComponent(pontos1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPontos1)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jogador1)
                    .addComponent(pontos1)
                    .addComponent(labelPontos1))
                .addGap(139, 139, 139)
                .addComponent(icone, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

    }
    
    
    
//    public void jogadorGanhou(){
//        this.remove(imagem);
//        ImageIcon icon = new ImageIcon("vitoria.gif");
//        imagem = new JLabel(icon);
//        this.add(imagem, BorderLayout.CENTER);
//    }
//    
//    public void jogadorPerdeu(){
//        this.remove(imagem);
//        ImageIcon icon = new ImageIcon("perda.gif");
//        imagem = new JLabel(icon);
//        this.add(imagem, BorderLayout.CENTER);
//    }
//    
//    public void houveEmpate(){
//        this.remove(imagem);
//        ImageIcon icon = new ImageIcon("empate.gif");
//        imagem = new JLabel(icon);
//        this.add(imagem, BorderLayout.CENTER);
//    }
//    
//    public void setPontos1(int valor) {
//        String msg = String.valueOf(valor);
//        this.pontos1.setText(msg);
//    }
//    
//    public void setPontos2(int valor) {
//        String msg = String.valueOf(valor);
//        this.pontos2.setText(msg);
//    }

    
    public void setPontos1(int valor) {
        String msg = String.valueOf(valor);
        this.pontos1.setText(msg);
    }
    
    public void setJogador1(String nome) {
        this.jogador1.setText(nome);
    }
   
}

