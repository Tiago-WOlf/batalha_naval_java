package main.Ajuda;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.telaInicial.JFrameTelaInicial;

public class JFrameTelaAjuda extends javax.swing.JFrame {

    public JFrameTelaAjuda() {
        initComponents();
    }

    
    private void initComponents() {

        labelImagemBarcos = new javax.swing.JLabel();
        labelImagemBarcos1 = new javax.swing.JLabel();
        labelImagemSubmarino = new javax.swing.JLabel();
        labelImagemBomba = new javax.swing.JLabel();
        botaoVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(701, 596));
        setResizable(false);
        getContentPane().setLayout(null);

        // Adicionando textos acima das imagens
        
        JLabel labelTextoBarco1 = new JLabel("Barco 1:");
        labelTextoBarco1.setFont(new java.awt.Font("Arial", 1, 14));
        getContentPane().add(labelTextoBarco1);
        labelTextoBarco1.setBounds(60, 80, 100, 30);

        JLabel labelTextoBarco2 = new JLabel("Barco 2:");
        labelTextoBarco2.setFont(new java.awt.Font("Arial", 1, 14));
        getContentPane().add(labelTextoBarco2);
        labelTextoBarco2.setBounds(60, 170, 100, 30);

        JLabel labelTextoSubmarino = new JLabel("Submarino:");
        labelTextoSubmarino.setFont(new java.awt.Font("Arial", 1, 14));
        getContentPane().add(labelTextoSubmarino);
        labelTextoSubmarino.setBounds(60, 240, 100, 30);

        JLabel labelTextoBomba = new JLabel("Bomba:");
        labelTextoBomba.setFont(new java.awt.Font("Arial", 1, 14));
        getContentPane().add(labelTextoBomba);
        labelTextoBomba.setBounds(60, 300, 100, 30);

        //IMAGENS DOS COMPONENTES DO JOGO
        
        labelImagemBarcos1.setIcon(new ImageIcon(getClass().getResource("/main/Ajuda/barco1.png"))); // Certifique-se de que o caminho está correto
        getContentPane().add(labelImagemBarcos1);
        labelImagemBarcos1.setBounds(60, 110, 110, 60);

        labelImagemBarcos.setIcon(new ImageIcon(getClass().getResource("/main/Ajuda/barco2_int.png"))); // Certifique-se de que o caminho está correto
        getContentPane().add(labelImagemBarcos);
        labelImagemBarcos.setBounds(60, 190, 110, 60);

        labelImagemSubmarino.setIcon(new ImageIcon(getClass().getResource("/main/Ajuda/barco3int.png"))); // Certifique-se de que o caminho está correto
        getContentPane().add(labelImagemSubmarino);
        labelImagemSubmarino.setBounds(60, 255, 100, 60);

        labelImagemBomba.setIcon(new ImageIcon(getClass().getResource("/main/Ajuda/bomba.png"))); // Certifique-se de que o caminho está correto
        getContentPane().add(labelImagemBomba);
        labelImagemBomba.setBounds(60, 320, 100, 60);

        botaoVoltar.setFont(new java.awt.Font("Metal Lord", 0, 12)); // NOI18N
        botaoVoltar.setIcon(new ImageIcon(getClass().getResource("/main/Ajuda/voltar.png"))); // Certifique-se de que o caminho está correto
        botaoVoltar.setText("voltar");
        botaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(botaoVoltar);
        botaoVoltar.setBounds(480, 510, 120, 50);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText(" REGRAS DO JOGO\n\n Há um tabuleiro de 10x10, ou seja, 100 blocos. \n\n Será dada uma quantidade de enegia e cada barco\n consome uma quantidade de energia\n\n Você poderá escolher onde colocar suas embarcações até \nque consuma toda energia \n\n O objetivo do jogo é destruir todas as embarcações do navio inimigo\n antes que ele destrua as suas \n\n A cada tiro será consumido uma quantidade de energia\n podendo escolher 3 tipos de tiros. \n\n");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(200, 110, 430, 380); // Ajuste a posição e o tamanho conforme necessário

        jLabel1.setFont(new java.awt.Font("Metal Lord", 0, 48)); // NOI18N
        jLabel1.setText("AJUDA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(260, 10, 300, 50);

        pack();
        setLocationRelativeTo(null);
    }

    private void botaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        JFrameTelaInicial volta = new JFrameTelaInicial();
        volta.setVisible(true); // Use setVisible(true) em vez de show()
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameTelaAjuda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton botaoVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelImagemBarcos;
    private javax.swing.JLabel labelImagemBarcos1;
    private javax.swing.JLabel labelImagemBomba;
    private javax.swing.JLabel labelImagemSubmarino;
    // End of variables declaration
}
