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
import javax.swing.JButton;
import javax.swing.JLabel;

class TelaMultiplayerServidor extends JPanel{
    
    private JanelaControl control;
    private JLabel icone;
    private JLabel labelIP;
    private JButton ok;
    private JLabel jogador1;
    private JLabel jogador2;
    private JLabel labelPontos;
    private JLabel labelPontos1;  
    private JLabel labelPontos2;  
    private JLabel pontos1;
    private JLabel pontos2;
    private JLabel ipServer;
    // End of variables declaration      
    
    public TelaMultiplayerServidor (JanelaControl control){
        this.control = control;
        this.setLayout(new BorderLayout());
       
	this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.createComponentes();
    }
    
    private void createComponentes(){
        labelIP = new JLabel();
        ipServer = new JLabel();
        ok = new JButton();
        ok.setActionCommand("conectarServidor");
        ok.addActionListener(control);
        jogador1 = new JLabel();
        jogador2 = new JLabel();
        pontos1 = new JLabel();
        pontos2 = new JLabel();
        labelPontos1 = new JLabel();
        labelPontos2 = new JLabel();
        labelPontos = new JLabel();
        
        
        ipServer.setText("000.000.000.000");
        
        jogador1.setText("JOGADOR:");

        jogador2.setText("JOGADOR:");

        pontos1.setText("0");

        pontos2.setText("0");

        labelPontos1.setText("Ponto(s)");

        labelPontos2.setText("Ponto(s)");

        labelPontos.setText("PONTUAÇÂO");
        
        labelIP.setText("INFORME O ENDEREÇO IP:");

        ok.setText("Iniciar Jogo");

        ImageIcon icon = new ImageIcon("hh.gif");
        icone = new JLabel(icon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jogador1)
                            .addComponent(jogador2))
                        .addGap(112, 112, 112)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pontos1)
                            .addComponent(pontos2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPontos2)
                            .addComponent(labelPontos1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelIP)
                        .addGap(18, 18, 18)
                        .addComponent(ipServer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addComponent(ok)
                .addContainerGap(53, Short.MAX_VALUE))
            .addComponent(icone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIP)
                    .addComponent(ok)
                    .addComponent(ipServer))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jogador1)
                    .addComponent(pontos1)
                    .addComponent(labelPontos1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jogador2)
                    .addComponent(pontos2)
                    .addComponent(labelPontos2))
                .addGap(17, 17, 17)
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
    
    public void setPontos2(int valor) {
        String msg = String.valueOf(valor);
        this.pontos2.setText(msg);
    }

    
    public void setJogador1(String nome) {
        this.jogador1.setText(nome);
    }
    
    public void setJogador2(String nome) {
        this.jogador2.setText(nome);
    }

    public void desativarBotao() {
        this.ok.setEnabled(false);
    }
    
    public void setIpServer(String ip) {
        this.ipServer.setText(ip);
    }
   
}

