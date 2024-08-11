package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class BatalhaEnergiaJogo extends JFrame {
    private static final int NUM_PARTES = 10;
    private static final int LINHA = 10;
    private static final int COLUNA = 10;
    private int energiaAtual = 10;
    private int energiaConsumida = 0;
    private boolean orientacaoHorizontal = true;
    private boolean modoLancarBombas = false;
    private int partesBarcosRestantes = 10; // 1+2+3 partes dos barcos
    private int partesBarcosRestantesMaquina = 10; // Partes dos barcos da máquina
    private final JButton[] botoesEnergia;
    private final JButton[][] tabuleiroBotoesJogador;
    private final JButton[][] tabuleiroBotoesMaquina;
    private final boolean[][] barcosPosicionadosJogador;
    private final boolean[][] barcosPosicionadosMaquina;
    private final ImageIcon[][] imagensBarcos;
    private final int[] tamanhosBarcos = {1, 2, 3};
    private final Random random = new Random();
    private JButton alternarModoButton = new JButton("Modo: Posicionar Barcos");

    public BatalhaEnergiaJogo() {
        setTitle("Batalha de Energia e Navios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);

        // Carregar as imagens dos barcos
        imagensBarcos = new ImageIcon[3][];
        imagensBarcos[0] = new ImageIcon[]{new ImageIcon(getClass().getResource("/resources/barco1.png"))};
        imagensBarcos[1] = new ImageIcon[]{
            new ImageIcon(getClass().getResource("/resources/barco2_parte1.png")),
            new ImageIcon(getClass().getResource("/resources/barco2_parte2.png"))
        };

        // Carregar as imagens do barco 3
        imagensBarcos[2] = new ImageIcon[]{
            new ImageIcon(getClass().getResource("/resources/barco3_parte1.png")),
            new ImageIcon(getClass().getResource("/resources/barco3_parte2.png")),
            new ImageIcon(getClass().getResource("/resources/barco3_parte3.png"))
        };

        // Configuração dos botões de energia
        botoesEnergia = new JButton[NUM_PARTES];
        for (int i = 0; i < NUM_PARTES; i++) {
            botoesEnergia[i] = new JButton("" + (i + 1));
            botoesEnergia[i].setEnabled(false);
            botoesEnergia[i].setBackground(Color.GRAY);
        }

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, NUM_PARTES));
        for (JButton botao : botoesEnergia) {
            painelBotoes.add(botao);
        }

        // Configuração dos tabuleiros
        tabuleiroBotoesJogador = new JButton[10][10];
        tabuleiroBotoesMaquina = new JButton[10][10];
        barcosPosicionadosJogador = new boolean[10][10];
        barcosPosicionadosMaquina = new boolean[10][10];

        JPanel tabuleirosPanel = new JPanel(new GridLayout(1, 2));

        JPanel tabuleiroJogadorPanel = new JPanel(new GridLayout(10, 10));
        configurarTabuleiro(tabuleiroBotoesJogador, barcosPosicionadosJogador, tabuleiroJogadorPanel);
        tabuleirosPanel.add(tabuleiroJogadorPanel);

        JPanel tabuleiroMaquinaPanel = new JPanel(new GridLayout(10, 10));
        configurarTabuleiro(tabuleiroBotoesMaquina, barcosPosicionadosMaquina, tabuleiroMaquinaPanel);
        tabuleirosPanel.add(tabuleiroMaquinaPanel);

        // Botões para selecionar o tipo de navio e a orientação
        JPanel selecaoNavioPanel = new JPanel();
        JButton navio1Button = new JButton("Navio 1 (1 de energia)");
        JButton navio2Button = new JButton("Navio 2 (2 de energia)");
        JButton navio3Button = new JButton("Navio 3 (3 de energia)");

        navio1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                energiaConsumida = tamanhosBarcos[0];
            }
        });

        navio2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                energiaConsumida = tamanhosBarcos[1];
            }
        });

        navio3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                energiaConsumida = tamanhosBarcos[2];
            }
        });

        selecaoNavioPanel.add(navio1Button);
        selecaoNavioPanel.add(navio2Button);
        selecaoNavioPanel.add(navio3Button);

        // Botão para selecionar a orientação dos barcos
        JButton orientacaoButton = new JButton("Orientação: Horizontal");
        orientacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orientacaoHorizontal = !orientacaoHorizontal;
                if (orientacaoHorizontal) {
                    orientacaoButton.setText("Orientação: Horizontal");
                } else {
                    orientacaoButton.setText("Orientação: Vertical");
                }
            }
        });

        // Botão para alternar entre o modo de lançamento de bombas e o modo de posicionamento de barcos
        JButton alternarModoButton = new JButton("Modo: Posicionar Barcos");
        alternarModoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modoLancarBombas = !modoLancarBombas;
                if (modoLancarBombas) {
                    alternarModoButton.setText("Modo: Lançar Bombas");
                    exibirPopup("Modo de Lançamento de Bombas Iniciado!");
                    esconderBarcos();
                } else {
                    alternarModoButton.setText("Modo: Posicionar Barcos");
                    exibirPopup("Modo de Posicionamento de Barcos Iniciado!");
                  
                }
            }
        });

        // Botão para resetar o jogo
        JButton resetButton = new JButton("Resetar Jogo");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        JPanel painelSul = new JPanel(new GridLayout(2, 1));
        painelSul.add(painelBotoes);
        painelSul.add(resetButton);

        add(selecaoNavioPanel, BorderLayout.NORTH);
        add(tabuleirosPanel, BorderLayout.CENTER);
        add(orientacaoButton, BorderLayout.WEST);
        add(alternarModoButton, BorderLayout.EAST);
        add(painelSul, BorderLayout.SOUTH);

        // Posicionar navios da máquina
        colocarBarcoMaquina();

        setLocationRelativeTo(null);
        setVisible(true);

        atualizarBotoesEnergia();
    }

    private void configurarTabuleiro(JButton[][] tabuleiroBotoes, boolean[][] barcosPosicionados, JPanel tabuleiroPanel) {
        for (int i = 0; i < LINHA; i++) {
            for (int j = 0; j < COLUNA; j++) {
                final int row = i;
                final int col = j;

                tabuleiroBotoes[i][j] = new JButton(" ");
                tabuleiroBotoes[i][j].setPreferredSize(new Dimension(50, 50));
                tabuleiroBotoes[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!modoLancarBombas) {
                            if (energiaConsumida > 0 && energiaAtual >= energiaConsumida) {
                                if (canPlaceShip(row, col, barcosPosicionados)) {
                                    energiaAtual -= energiaConsumida;
                                    atualizarBotoesEnergia();
                                    placeShip(row, col, tabuleiroBotoes, barcosPosicionados);
                                     
                                } else {
                                    exibirPopup("Não é possível colocar o navio aqui!");
                                }
                            } else {
                                exibirPopupEnergiaAcabou();
                            }
                        } else {
                            if (tabuleiroBotoes == tabuleiroBotoesMaquina) {
                                openActionSelectionWindow(row, col, tabuleiroBotoesMaquina, barcosPosicionadosMaquina);
                                maquinaLancaBomba();
                            }
                        }
                    }
                });
                tabuleiroPanel.add(tabuleiroBotoes[i][j]);
            }
        }
    }

    private void maquinaLancaBomba() {
        int row, col;
        do {
            row = random.nextInt(LINHA);
            col = random.nextInt(COLUNA);
        } while (tabuleiroBotoesJogador[row][col].getBackground().equals(Color.YELLOW) ||
                 tabuleiroBotoesJogador[row][col].getBackground().equals(Color.RED));

        if (barcosPosicionadosJogador[row][col]) {
            paintSingle(row, col, Color.RED, tabuleiroBotoesJogador, barcosPosicionadosJogador);
            partesBarcosRestantes--;
        } else {
            paintSingle(row, col, Color.WHITE, tabuleiroBotoesJogador, barcosPosicionadosJogador);
        }

        if (partesBarcosRestantes <= 0) {
            exibirPopupFimDeJogo("A máquina venceu! Todos os seus barcos foram destruídos.");

        }
    }

    private void atualizarBotoesEnergia() {
        for (int i = 0; i < NUM_PARTES; i++) {
            botoesEnergia[i].setEnabled(i < energiaAtual);
            botoesEnergia[i].setBackground(i < energiaAtual ? Color.GREEN : Color.GRAY);
        }
    }

    private void resetGame() {
    // Reset do tabuleiro do jogador
    for (int i = 0; i < LINHA; i++) {
        for (int j = 0; j < COLUNA; j++) {
            tabuleiroBotoesJogador[i][j].setIcon(null);
            tabuleiroBotoesJogador[i][j].setBackground(null);
            barcosPosicionadosJogador[i][j] = false;
        }
    }
    
    // Reset do tabuleiro da máquina
    for (int i = 0; i < LINHA; i++) {
        for (int j = 0; j < COLUNA; j++) {
            tabuleiroBotoesMaquina[i][j].setIcon(null);
            tabuleiroBotoesMaquina[i][j].setBackground(null);
            barcosPosicionadosMaquina[i][j] = false;
        }
    }

    // Reset dos parâmetros do jogo
    energiaAtual = 10;
    atualizarBotoesEnergia();
    partesBarcosRestantes = 10;
    partesBarcosRestantesMaquina = 10;
    
    // Posicionar os navios da máquina novamente
    colocarBarcoMaquina();

    // Garantir que os barcos da máquina fiquem ocultos
    esconderBarcos();

    exibirPopup("Jogo reiniciado! Posicione seus navios novamente.");
}

// Método para esconder os barcos da máquina após o reset
private void esconderBarcos() {
    for (int i = 0; i < LINHA; i++) {
        for (int j = 0; j < COLUNA; j++) {
            if (barcosPosicionadosMaquina[i][j]) {
                tabuleiroBotoesMaquina[i][j].setBackground(null);
            }
        }
    }
}

    private void exibirPopup(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    private void exibirPopupEnergiaAcabou() {
        JOptionPane.showMessageDialog(this, "Energia Insuficiente!");
    }

    private void openActionSelectionWindow(int row, int col, JButton[][] tabuleiroBotoes, boolean[][] barcosPosicionados) {
        if (barcosPosicionados[row][col]) {
            paintSingle(row, col, Color.RED, tabuleiroBotoes, barcosPosicionados);
            partesBarcosRestantesMaquina--;
        } else {
            paintSingle(row, col, Color.WHITE, tabuleiroBotoes, barcosPosicionados);
        }

        if (partesBarcosRestantesMaquina <= 0) {
            exibirPopupFimDeJogo("Você venceu! Todos os barcos inimigos foram destruídos.");
            
        }
    }

    private void paintSingle(int row, int col, Color color, JButton[][] tabuleiroBotoes, boolean[][] barcosPosicionados) {
        tabuleiroBotoes[row][col].setBackground(color);
        if (color == Color.RED) {
            barcosPosicionados[row][col] = false;
        }
    }

    private boolean canPlaceShip(int row, int col, boolean[][] barcosPosicionados) {
        if (orientacaoHorizontal) {
            for (int i = 0; i < energiaConsumida; i++) {
                if (col + i >= COLUNA || barcosPosicionados[row][col + i]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < energiaConsumida; i++) {
                if (row + i >= LINHA || barcosPosicionados[row + i][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeShip(int row, int col, JButton[][] tabuleiroBotoes, boolean[][] barcosPosicionados) {
        if (orientacaoHorizontal) {
            for (int i = 0; i < energiaConsumida; i++) {
                tabuleiroBotoes[row][col + i].setIcon(imagensBarcos[energiaConsumida - 1][i]);
                barcosPosicionados[row][col + i] = true;
            }
        } else {
            for (int i = 0; i < energiaConsumida; i++) {
                tabuleiroBotoes[row + i][col].setIcon(imagensBarcos[energiaConsumida - 1][i]);
                barcosPosicionados[row + i][col] = true;
            }
        }
    }


   private void exibirPopupFimDeJogo(String mensagem) {
    int resposta = JOptionPane.showOptionDialog(this, mensagem + "\nDeseja jogar novamente?", "Fim de Jogo",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
    if (resposta == JOptionPane.YES_OPTION) {
        resetGame();
        
        // Garantir que o modo volte para posicionar barcos
        modoLancarBombas = false;
        alternarModoButton.setText("Modo: Posicionar Barcos");
        exibirPopup("Jogo terminado! Voltando ao modo de Posicionamento de Barcos.");
        
    } else {
        System.exit(0);
    }
}

 private void colocarBarcoMaquina() {
    int energiaAtualMaquina = 10;  // Energia inicial da máquina
    int[] tamanhosBarcosDisponiveis = {1, 2, 3};  // Tamanhos dos barcos

    while (energiaAtualMaquina > 0) {
        boolean barcoPosicionado = false;
        while (!barcoPosicionado && energiaAtualMaquina > 0) {
            // Escolhe um tamanho de barco aleatoriamente com base na energia disponível
            int tamanho = tamanhosBarcosDisponiveis[random.nextInt(tamanhosBarcosDisponiveis.length)];
            if (tamanho > energiaAtualMaquina) {
                continue;
            }

            boolean orientacaoHorizontal = random.nextBoolean();
            int row = random.nextInt(LINHA);
            int col = random.nextInt(COLUNA);

            if (canPlaceShip(row, col, tamanho, orientacaoHorizontal, barcosPosicionadosMaquina)) {
                placeShip(row, col, tamanho, orientacaoHorizontal, tabuleiroBotoesMaquina, barcosPosicionadosMaquina);
                energiaAtualMaquina -= tamanho;
                barcoPosicionado = true;
            }
        }
    }
}
private boolean canPlaceShip(int row, int col, int tamanho, boolean orientacaoHorizontal, boolean[][] barcosPosicionados) {
    if (orientacaoHorizontal) {
        if (col + tamanho > COLUNA) return false;
        for (int i = 0; i < tamanho; i++) {
            if (barcosPosicionados[row][col + i]) return false;
        }
    } else {
        if (row + tamanho > LINHA) return false;
        for (int i = 0; i < tamanho; i++) {
            if (barcosPosicionados[row + i][col]) return false;
        }
    }
    return true;
}

private void placeShip(int row, int col, int tamanho, boolean orientacaoHorizontal, JButton[][] tabuleiroBotoes, boolean[][] barcosPosicionados) {
    for (int i = 0; i < tamanho; i++) {
        if (orientacaoHorizontal) {
            tabuleiroBotoes[row][col + i].setIcon(imagensBarcos[tamanho - 1][i]);
            barcosPosicionados[row][col + i] = true;
            if (tabuleiroBotoes == tabuleiroBotoesMaquina) {
                tabuleiroBotoes[row][col + i].setIcon(null); // Esconde o barco da máquina
            }
        } else {
            tabuleiroBotoes[row + i][col].setIcon(imagensBarcos[tamanho - 1][i]);
            barcosPosicionados[row + i][col] = true;
            if (tabuleiroBotoes == tabuleiroBotoesMaquina) {
                tabuleiroBotoes[row + i][col].setIcon(null); // Esconde o barco da máquina
            }
        }
    }
}
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BatalhaEnergiaJogo());
    }
}