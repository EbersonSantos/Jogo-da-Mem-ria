package gui;

import java.applet.AudioClip;
import control.Controlador;
import java.applet.Applet;
import javax.swing.JOptionPane;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import model.Jogador;

public class JanelaControl implements ActionListener {

    private Controlador app;
    private Janela janela;
    public AudioClip audioReacao;
    public AudioClip audioJogo;
    private int jogadas;
    private int jogadasCliente;
    private int jogadasServidor;
    private int linha1;
    private int coluna1;
    private ServerSocket servidor;
    private Socket conexao; //feito com os clientes
    private int turno; //0 cliente, 1 servidor
    private final int TURNO_CLIENTE = 0;
    private final int TURNO_SERVIDOR = 1;
    private boolean fim;

    private boolean souCliente;
    private boolean souServidor;

    private Socket socketCliente;
    private BufferedReader entradaServidor;
    private DataOutputStream saidaServidor;

    private BufferedReader entradaCliente;
    private DataOutputStream saidaCliente;

    public Controlador getApp() {
        return app;
    }

    public JanelaControl(Controlador app) {
        this.app = app;
        this.jogadasCliente = 0;
        this.janela = new Janela(this);
//        try {
//            servidor = new ServerSocket(6789);
//        } catch (IOException ex) {
//            Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.janela.setStatus("Turno do Jogador");
    }

    private void jogar(int linha1, int coluna1, int linha2, int coluna2) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {
                boolean acertou = app.jogar(linha1, coluna1, linha2, coluna2);

                boolean fim = app.fimDeJogo();
                if (fim) {
                    janela.setStatus("Parabéns, inicie um novo jogo!");
                }

                if (!acertou) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    janela.getTjm().setDefault(linha1, coluna1);
                    janela.getTjm().setDefault(linha2, coluna2);
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    app.getJogador1().setPontuacao();
                    janela.getTjm().enabledButton(linha1, coluna1);
                    janela.getTjm().enabledButton(linha2, coluna2);
                }
                janela.getTis().setPontos1(app.getJogador1().getPontuacao());

                jogadas = 0;
                //System.out.println("Pontuação: " + );
                return null;
            }

        };
        worker.execute();
    }

    private void jogarRedes(int linha1, int coluna1, int linha2, int coluna2, boolean label1) {
        SwingWorker worker;
        worker = new SwingWorker() {

            @Override
            protected Void doInBackground() throws Exception {
                boolean acertou = app.jogar(linha1, coluna1, linha2, coluna2);

                //turno = getTurno();
                if (souCliente) {
                    jogadasServidor = 0;
                }
                if (souServidor) {
                    jogadasCliente = 0;
                }
                if (souServidor && turno == TURNO_CLIENTE) {
                    janela.setStatus("Esperando jogada do cliente...");
                }

                if (souCliente && turno == TURNO_SERVIDOR) {
                    janela.setStatus("Esperando jogada do servidor...");
                }
                fim = app.fimDeJogo();
                
                
                if (!acertou) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    janela.getTjm().setDefault(linha1, coluna1);
                    janela.getTjm().setDefault(linha2, coluna2);
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (label1) {
                        app.getJogador1().setPontuacao();
                    } else {
                        app.getJogador2().setPontuacao();
                    }

                    janela.getTjm().enabledButton(linha1, coluna1);
                    janela.getTjm().enabledButton(linha2, coluna2);
                }
                
                if (fim) {
                    janela.setStatus("Fim de jogo, inicie um novo!");
                    int pontos1 = app.getJogador1().getPontuacao();
                    int pontos2 = app.getJogador2().getPontuacao();
                    
                    if(pontos1 > pontos2){
                        JOptionPane.showMessageDialog(null, "Parabéns, você ganhou!!!", "PARABÈNS", JOptionPane.INFORMATION_MESSAGE);
                    }else if(pontos2 > pontos1){
                        JOptionPane.showMessageDialog(null, "Que pena, você perdeu!!!", "PERDA", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Houve um empate!!!", "EMPATE", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                janela.getTmc().setPontos1(app.getJogador1().getPontuacao());
                janela.getTms().setPontos1(app.getJogador1().getPontuacao());
                janela.getTmc().setPontos2(app.getJogador2().getPontuacao());
                janela.getTms().setPontos2(app.getJogador2().getPontuacao());
                //System.out.println("Pontuação: " + );

                return null;
            }

        };
        worker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "jogar":
                //System.out.println(jogadas);
                if (jogadas < 2) {
                    jogadas++;
                    if (jogadas == 1) {
                        this.linha1 = this.janela.getTjm().getLinhaButton((JButton) e.getSource()); //linha do botao
                        this.coluna1 = this.janela.getTjm().getColunaButton((JButton) e.getSource()); //coluna do botao
                        //System.out.println("linha: " + linha1 + ", coluna: " + coluna1);

                        this.janela.getTjm().setIcon(linha1, coluna1, this.app.getValorCelula(linha1, coluna1));

                    } else if (jogadas > 1) {
                        int linha2 = this.janela.getTjm().getLinhaButton((JButton) e.getSource()); //linha do botao
                        int coluna2 = this.janela.getTjm().getColunaButton((JButton) e.getSource()); //coluna do botao
                        if (linha2 == linha1 && coluna2 == coluna1) {
                            jogadas--;
                        } else {
                            this.janela.getTjm().setIcon(linha2, coluna2, this.app.getValorCelula(linha2, coluna2));

                            this.jogar(linha1, coluna1, linha2, coluna2);
                        }

                    }
                }
                break;
            case "jogarRedes":
                if (jogadasCliente < 2 || jogadasServidor < 2) {
                    if ((souServidor && turno == TURNO_SERVIDOR) || (souCliente && turno == TURNO_CLIENTE)) {
                        if (souCliente) {
                            jogadasCliente++;
                        }
                        if (souServidor) {
                            jogadasServidor++;
                        }
                        //System.out.println("jogadasCliente " + jogadasCliente);
                        //System.out.println("jogadaServidor " + jogadasServidor);
                        if (jogadasCliente == 1 || jogadasServidor == 1) {
//                            if (souServidor) {
//                                System.out.println("Servidor jogou uma vez");
//                            }
//                            if (souCliente) {
//                                System.out.println("Cliente jogou uma vez");
//                            }

                            this.linha1 = this.janela.getTjm().getLinhaButton((JButton) e.getSource()); //linha do botao
                            this.coluna1 = this.janela.getTjm().getColunaButton((JButton) e.getSource()); //coluna do botao

                            String s = "";
                            s = this.app.getBoard().toString() + "@";
                            s = s + linha1 + "@";
                            s = s + coluna1 + "@";
                            s = s + this.app.getJogador1().getNome() + "@";
                            s = s + this.app.getJogador1().getPontuacao() + "@";
                            s = s + this.getTurno();
                            this.enviarDados(s);

                            this.janela.getTjm().setIcon(linha1, coluna1, this.app.getValorCelula(linha1, coluna1));

                        } else if (jogadasCliente > 1 || jogadasServidor > 1) {
//                        if (souServidor) {
//                            System.out.println("Servidor jogou duas vezes");
//                        }
//                        if (souCliente) {
//                            System.out.println("Cliente jogou duas vezes");
//                        }
                            int linha2 = this.janela.getTjm().getLinhaButton((JButton) e.getSource()); //linha do botao
                            int coluna2 = this.janela.getTjm().getColunaButton((JButton) e.getSource()); //coluna do botao

                            if (linha2 == linha1 && coluna2 == coluna1) {
                                if (souCliente) {
                                    jogadasCliente--;
                                }
                                if (souServidor) {
                                    jogadasServidor--;
                                }
                            } else {

                                this.janela.getTjm().setIcon(linha2, coluna2, this.app.getValorCelula(linha2, coluna2));

                                this.jogarRedes(linha1, coluna1, linha2, coluna2, true);

                                String s = "";
                                s = this.app.getBoard().toString() + "@";
                                s = s + linha2 + "@";
                                s = s + coluna2 + "@";
                                s = s + this.app.getJogador1().getNome() + "@";
                                s = s + this.app.getJogador1().getPontuacao() + "@";
                                s = s + this.getTurno();
                                this.enviarDados(s);

                                if (fim) {
                                    janela.setStatus("Fim de jogo, inicie um novo!");
                                } else {
                                    
                                    janela.setStatus("Sua vez de jogar...");
                                    
                                    for (int i = 0; i < 2; i++) {
                                        try {
                                            esperaEntrada();
                                        } catch (IOException ex) {
                                            Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    
                                }

                            }
                        }
                    }
                }
                break;
            case "inicio":
                this.janela.resetJanela2(false, false);
                break;
            case "conectarCliente":
                final String host = this.janela.getTmc().getIp();
                this.conectarCliente(host);
                break;
            case "conectarServidor":
                this.conectarSevidor();
                janela.getTms().desativarBotao();
                break;
            case "singleplayer":
                janela.setStatus("");
                if (servidor != null) {
                    servidor = null;
                }
                if (conexao != null) {
                    conexao = null;
                }

                this.janela.getTjm().removeAll();
                String nomeJogador = this.app.getJogador1().getNome();
                this.app = new Controlador(4, nomeJogador);
                this.janela.resetJanela(false, false, true);
                this.souCliente = false;
                this.souServidor = false;
                if (!nomeJogador.equals(Jogador.NOME_DEFAULT)) {
                    this.setNomeJogador();
                }
                break;
            case "servidor":
                if (servidor != null) {
                    servidor = null;
                }
                if (conexao != null) {
                    conexao = null;
                }
                jogadasCliente = 0;

                turno = TURNO_SERVIDOR;

                this.janela.getTjm().removeAll();
                String name = this.app.getJogador1().getNome();
                this.app = new Controlador(4, name);
                this.janela.resetJanela(true, true, false);
                this.souCliente = false;
                this.souServidor = true;
                if (!name.equals(Jogador.NOME_DEFAULT)) {
                    this.setNomeJogador();
                }
                break;
            case "cliente":
                if (servidor != null && conexao != null) {
                    try {
                        servidor.close();
                        conexao.close();
                        servidor = null;
                        conexao = null;
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                janela.setStatus("Conecte-se a um servidor...");
                jogadasCliente = 0;
                turno = TURNO_SERVIDOR;
                if (servidor != null) {
                    try {
                        servidor.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    servidor = null;
                }
                this.janela.getTjm().removeAll();
                String nome = this.app.getJogador1().getNome();
                this.app.apagarTabuleiro();
                this.janela.resetJanela(true, false, false);
                this.souCliente = true;
                this.souServidor = false;
                if (!nome.equals(Jogador.NOME_DEFAULT)) {
                    this.setNomeJogador();
                }
                break;
            case "nome": //
                String alias = JOptionPane.showInputDialog(null, "Informe seu nome: ", "NOME DO JOGADOR", JOptionPane.QUESTION_MESSAGE);
                this.app.getJogador1().setNome(alias);
                this.setNomeJogador();
                break;
            case "sobre":
                JOptionPane.showMessageDialog(null, "        JOGO DA MEMÒRIA - DISCIPLINA REDES\n        DESENVOLVEDORES: \n        ALYSSON MANSO E EBERSON SANTOS\n", "SOBRE", 1);

                break;
            case "sair": //
                System.exit(0);
                break;
            case "ativar": //
                if (audioJogo != null) {
                    audioJogo.stop();
                    audioJogo = null;
                } else {
                    URL url = getClass().getResource("8bitSound.wav");
                    audioJogo = Applet.newAudioClip(url);
                    audioJogo.play();
                    audioJogo.loop();
                }
        }
    }

    private void sons(String som) {
        URL url = getClass().getResource(som);
        this.audioReacao = (AudioClip) Applet.newAudioClip(url);
    }

    private void setNomeJogador() {
        String nome = this.app.getJogador1().getNome();
        this.janela.getTis().setJogador1(nome);
        this.janela.getTms().setJogador1(nome);
        this.janela.getTmc().setJogador1(nome);
    }

    private int getTurno() { //0 cliente, 1 servidor
        if (this.jogadasCliente == 2) {
            this.jogadasCliente = 0;
            if (souServidor && turno == TURNO_SERVIDOR) {
                return turno = TURNO_CLIENTE;
            } else if (souServidor && turno == TURNO_CLIENTE) {
                return turno = TURNO_SERVIDOR;
            }

            if (souCliente && turno == TURNO_CLIENTE) {
                return turno = TURNO_SERVIDOR;
            } else if (souCliente && turno == TURNO_SERVIDOR) {
                return turno = TURNO_CLIENTE;
            }

        }

        if (this.jogadasServidor == 2) {
            this.jogadasServidor = 0;
            if (souCliente && turno == TURNO_CLIENTE) {
                return turno = TURNO_SERVIDOR;
            } else if (souCliente && turno == TURNO_SERVIDOR) {
                return turno = TURNO_CLIENTE;
            }

            if (souServidor && turno == TURNO_SERVIDOR) {
                return turno = TURNO_CLIENTE;
            } else if (souServidor && turno == TURNO_CLIENTE) {
                return turno = TURNO_SERVIDOR;
            }

        }

        return turno;

    }

    private void conectarCliente(String host) {

        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    socketCliente = new Socket(host, 6789);
                    janela.getTmc().desativarBotao();

                    entradaServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                    saidaServidor = new DataOutputStream(socketCliente.getOutputStream());

                    janela.setStatus("Turno do Servidor");
                    janela.getTjm().ativarBotoes();

                    for (int i = 0; i < 2; i++) {
                        esperaEntrada();
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível conectar!", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
        worker.execute();

    }

    private void conectarSevidor() {
        SwingWorker worker2 = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                conexao = servidor.accept();

                entradaCliente = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                saidaCliente = new DataOutputStream(conexao.getOutputStream());

//                String tabuleiro;
//                tabuleiro = app.getBoard().toString();
//                saidaCliente.writeBytes(tabuleiro + "\n");
                janela.getTjm().ativarBotoes();
                janela.setStatus("Sua vez de Jogar...");

                return null;
            }
        };
        try {
            servidor = new ServerSocket(6789);
            String ip = InetAddress.getLocalHost().getHostAddress();
            janela.setStatus("Esperando por cliente...");
            this.janela.getTms().setIpServer(ip);
            worker2.execute();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Servidor já conectado!!!", null, JOptionPane.PLAIN_MESSAGE);
        }

    }

    private void clienteParaServidor(String s) {
        try {
            //System.out.println("Enviei: " + s);
            saidaServidor.writeBytes(s + "\n");
        } catch (IOException ex) {
            Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void servidorParaCliente(String s) {
        try {
            //System.out.println("Enviei: " + s);
            saidaCliente.writeBytes(s + "\n");
        } catch (IOException ex) {
            Logger.getLogger(JanelaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void esperaEntrada() throws IOException {

        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                String strRecebida = "";
                if (souCliente) {
                    janela.setStatus("Esperando jogada do servidor...");
                    strRecebida = entradaServidor.readLine();;
                    jogadasServidor++;
                }
                if (souServidor) {
                    janela.setStatus("Esperando jogada do cliente...");
                    strRecebida = entradaCliente.readLine();
                    jogadasCliente++;
                }

                //System.out.println("Recebi " + strRecebida);
                //tabuleiro@linha@coluna@nome@pontos@turno
                String[] msg = strRecebida.split("@");

                if (app.getBoard() == null) {
                    app.setBoard(msg[0]);
                }
                int linha = Integer.parseInt(msg[1]);
                int coluna = Integer.parseInt(msg[2]);
                String jogador2 = msg[3];
                int pontos = Integer.parseInt(msg[4]);
                if (app.getJogador2() == null) {
                    app.criarJogador2(jogador2, pontos);
                }
                janela.getTmc().setJogador2(jogador2);
                janela.getTms().setJogador2(jogador2);
                janela.getTmc().setPontos2(pontos);
                janela.getTms().setPontos2(pontos);
                turno = Integer.parseInt(msg[5]);

                if (souCliente) {
                    if (jogadasServidor == 1) {
                        linha1 = linha;
                        coluna1 = coluna;
                        janela.getTjm().setIcon(linha1, coluna1, app.getValorCelula(linha1, coluna1));
                    }
                    if (jogadasServidor == 2) {
                        if (linha == linha1 && coluna == coluna1) {
                            if (souCliente) {
                                jogadasCliente--;
                            }
                            if (souServidor) {
                                jogadasServidor--;
                            }
                        } else {

                            janela.getTjm().setIcon(linha, coluna, app.getValorCelula(linha, coluna));

                            jogarRedes(linha1, coluna1, linha, coluna, false);

                            if (fim) {
                                janela.setStatus("Fim de jogo, inicie um novo!");
                            } else {
                                janela.setStatus("Sua vez de jogar...");
                            }

                        }

                    }
                }

                if (souServidor) {
                    if (jogadasCliente == 1) {
                        linha1 = linha;
                        coluna1 = coluna;
                        janela.getTjm().setIcon(linha1, coluna1, app.getValorCelula(linha1, coluna1));
                    }
                    if (jogadasCliente == 2) {
                        if (linha == linha1 && coluna == coluna1) {
                            if (souCliente) {
                                jogadasCliente--;
                            }
                            if (souServidor) {
                                jogadasServidor--;
                            }
                        } else {

                            janela.getTjm().setIcon(linha, coluna, app.getValorCelula(linha, coluna));

                            jogarRedes(linha1, coluna1, linha, coluna, false);

                            if (fim) {
                                janela.setStatus("Fim de jogo, inicie um novo!");
                            } else {
                                janela.setStatus("Sua vez de jogar...");
                            }
                        }

                    }

                }
                return null;
            }
        };

        worker.execute();
    }

    private void enviarDados(String s) {
        if (souServidor) {
            servidorParaCliente(s);
        }
        if (souCliente) {
            clienteParaServidor(s);
        }
    }
}
