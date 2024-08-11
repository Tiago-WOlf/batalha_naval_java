package main.telaInicial;

import main.BatalhaEnergiaJogo;
import main.Ajuda.JFrameTelaAjuda;

import java.awt.Color;

public class JFrameTelaInicial extends javax.swing.JFrame {

    public JFrameTelaInicial() {
        initComponents();
    }

    private void initComponents() {
        botaoJogar = new javax.swing.JButton();
        botaoajuda = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();
        fundolabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(801, 596));
        setResizable(false);
        getContentPane().setLayout(null);

        botaoJogar.setFont(new java.awt.Font("Nyala", 1, 18)); // NOI18N
        botaoJogar.setText("PLAY");
        botaoJogar.setContentAreaFilled(false);
        botaoJogar.setBorderPainted(false);
        botaoJogar.setForeground(Color.WHITE); // Texto branco
        botaoJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoJogarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoJogar);
        botaoJogar.setBounds(50, 350, 90, 40);

        botaoajuda.setFont(new java.awt.Font("Nyala", 1, 18)); // NOI18N
        botaoajuda.setText("AJUDA");
        botaoajuda.setContentAreaFilled(false);
        botaoajuda.setBorderPainted(false);
        botaoajuda.setForeground(Color.WHITE); // Texto branco
        botaoajuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoajudaActionPerformed(evt);
            }
        });
        getContentPane().add(botaoajuda);
        botaoajuda.setBounds(150, 350, 100, 40);

        botaoSair.setFont(new java.awt.Font("Nyala", 1, 18)); // NOI18N
        botaoSair.setText("SAIR");
        botaoSair.setContentAreaFilled(false);
        botaoSair.setBorderPainted(false);
        botaoSair.setForeground(Color.WHITE); // Texto branco
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });
        getContentPane().add(botaoSair);
        botaoSair.setBounds(250, 350, 90, 40);

        fundolabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/telaInicial/Batalha Naval.jpg"))); // Ajuste o caminho da imagem se necessário
        getContentPane().add(fundolabel);
        fundolabel.setBounds(-90, -0, 900, 570);

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    private void botaoJogarActionPerformed(java.awt.event.ActionEvent evt) {
        BatalhaEnergiaJogo novo = new BatalhaEnergiaJogo();
        novo.setVisible(true);
        this.dispose();
    }

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void botaoajudaActionPerformed(java.awt.event.ActionEvent evt) {
        JFrameTelaAjuda nova = new JFrameTelaAjuda();
        nova.setVisible(true);
        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameTelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton botaoJogar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JButton botaoajuda;
    private javax.swing.JLabel fundolabel;
    // End of variables declaration
}
