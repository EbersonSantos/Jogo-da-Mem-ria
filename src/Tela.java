
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eberson
 */
public class Tela extends javax.swing.JPanel {

    /**
     * Creates new form Tela
     */
    public Tela() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelIP = new javax.swing.JLabel();
        ip = new javax.swing.JTextField();
        ok = new javax.swing.JButton();
        icone = new javax.swing.JLabel();
        jogador1 = new javax.swing.JLabel();
        jogador2 = new javax.swing.JLabel();
        pontos1 = new javax.swing.JLabel();
        pontos2 = new javax.swing.JLabel();
        labelPontos1 = new javax.swing.JLabel();
        labelPontos2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        labelIP.setText("INFORME O ENDEREÇO IP:");

        ip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipActionPerformed(evt);
            }
        });

        ok.setText("Iniciar Jogo");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        jogador1.setText("JOGADOR 1:");

        jogador2.setText("JOGADOR 2:");

        pontos1.setText("0");

        pontos2.setText("0");

        labelPontos1.setText("PONTO(S)");

        labelPontos2.setText("PONTO(S)");

        jLabel2.setText("000.000.000.000");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ip, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
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
                    .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ok))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
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
                .addComponent(icone, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okActionPerformed

    private void ipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icone;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jogador1;
    private javax.swing.JLabel jogador2;
    private javax.swing.JLabel labelIP;
    private javax.swing.JLabel labelPontos1;
    private javax.swing.JLabel labelPontos2;
    private javax.swing.JButton ok;
    private javax.swing.JLabel pontos1;
    private javax.swing.JLabel pontos2;
    // End of variables declaration//GEN-END:variables
}
