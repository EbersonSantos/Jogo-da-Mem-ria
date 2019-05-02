/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class Janela extends JFrame {

    private JanelaControl control;
    private GridLayout cardLayout;
    private JLabel status;
    ;
    private JPanel cards;
    private TelaJogoDaMemoria tjm;
    private TelaMultiplayerServidor tms;
    private TelaMultiplayerCliente tmc;
    private TelaInformacoesSingleplayer tis;
    private TelaInicial ti;

    public Janela(JanelaControl control) {
        this.control = control;
        this.createComponentes();
        //this.createTela(false, false, true);
        this.createTela2(false, false);
        this.setTitle("JOGO DA MEMÒRIA");
        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setMinimumSize(new Dimension(1300, 900));

    }

    public JanelaControl getJanelaControl() {
        return this.control;
    }
    
    public void createTela2(boolean rede, boolean botoes) {
        this.cardLayout = new GridLayout();
        this.cards = new JPanel(cardLayout);
        this.getContentPane().add(cards, BorderLayout.CENTER);
        
        this.tis = new TelaInformacoesSingleplayer(control);
        this.tms = new TelaMultiplayerServidor(control);
        this.tmc = new TelaMultiplayerCliente(control);
        this.tjm = new TelaJogoDaMemoria(control, rede, botoes);
        this.ti = new TelaInicial();
            
        this.cards.add(ti, "ti");
        
    }

    private void createTela(boolean rede, boolean servidor,  boolean botoes) {

        this.cardLayout = new GridLayout();
        this.cards = new JPanel(cardLayout);
        this.getContentPane().add(cards, BorderLayout.CENTER);
        
        
        this.tis = new TelaInformacoesSingleplayer(control);
        this.tms = new TelaMultiplayerServidor(control);
        this.tmc = new TelaMultiplayerCliente(control);
        
        if(rede == false){
            
            this.cards.add(tis, "tis");
        }else{
            if(servidor){
        
                this.cards.add(tms, "tms");
            }else{
                
                this.cards.add(tmc, "tmc");
            }
        }

        this.tjm = new TelaJogoDaMemoria(control, rede, botoes);
        this.cards.add(tjm, "tjmr");

    }

    public TelaJogoDaMemoria getTjm(){
        return tjm;
    }

    public TelaMultiplayerServidor getTms() {
        return tms;
    }
    
    public TelaMultiplayerCliente getTmc() {
        return tmc;
    }

    public TelaInformacoesSingleplayer getTis() {
        return tis;
    }
    
    public void resetJanela2(boolean rede, boolean botoes) {
        this.remove(cards);
        this.createTela2(rede, botoes);
        this.setVisible(true);
    }
    
    public void resetJanela(boolean rede, boolean servidor,  boolean botoes) {
        this.remove(cards);
        this.createTela(rede, servidor, botoes);
        this.setVisible(true);
    }

    private void createComponentes() {
        JMenuBar barra = new JMenuBar();
        this.getContentPane().add(barra, BorderLayout.NORTH);

        JMenu menu;

        JMenuItem item;

        menu = new JMenu("Novo Jogo");
        barra.add(menu);

//        item = new JMenuItem("Médio");
//        item.setActionCommand("medio");
//        item.addActionListener(control);
//        menu.add(item);
//
//        item = new JMenuItem("Difícil");
//        item.setActionCommand("dificil");
//        item.addActionListener(control);
//        menu.add(item);
        
        item = new JMenuItem("Início");
        item.setActionCommand("inicio");
        item.addActionListener(control);
        menu.add(item);
        
        item = new JMenuItem("Singleplayer");
        item.setActionCommand("singleplayer");
        item.addActionListener(control);
        menu.add(item);
        
        JMenu multi = new JMenu("Multiplayer");
        //multi.setActionCommand("multiplayer");
        //multi.addActionListener(control);
        menu.add(multi);
        
        item = new JMenuItem("Servidor");
        item.setActionCommand("servidor");
        item.addActionListener(control);
        multi.add(item);
        
        item = new JMenuItem("Cliente");
        item.setActionCommand("cliente");
        item.addActionListener(control);
        multi.add(item);

        menu = new JMenu("Configurações");
        barra.add(menu);

        item = new JMenuItem("Som");
        item.setActionCommand("ativar");
        item.addActionListener(control);
        menu.add(item);

        item = new JMenuItem("Nome do Jogador");
        item.setActionCommand("nome");
        item.addActionListener(control);
        menu.add(item);

        menu = new JMenu("Sair");
        barra.add(menu);

        item = new JMenuItem("Sair");
        item.setActionCommand("sair");
        item.addActionListener(control);
        menu.add(item);

        item = new JMenuItem("Sobre");
        item.setActionCommand("sobre");
        item.addActionListener(control);
        menu.add(item);

        this.status = new JLabel("");
        status.setFont(new Font("Arial", Font.PLAIN, 15));
        this.getContentPane().add(status, BorderLayout.SOUTH);
    }

    public void setStatus(String msg) {
        this.status.setText(msg);
    }

}
