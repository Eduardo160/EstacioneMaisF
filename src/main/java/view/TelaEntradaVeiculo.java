package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaEntradaVeiculo extends JPanel {

    private static final Color COLOR_BG_PANEL  = new Color(32, 32, 32);
    private static final Color COLOR_TEXT      = new Color(240, 240, 240);
    private static final Color COLOR_HOVER_BG  = new Color(255, 157, 8);
    private static final Color COLOR_SHADOW    = new Color(0, 0, 0, 80);

    public TelaEntradaVeiculo() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.insets = new Insets(10, 10, 10, 10);

        // ====== Painel central com sombra ======
        JPanel centralPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Sombra
                g2.setColor(COLOR_SHADOW);
                g2.fillRoundRect(4, 4, getWidth() - 4, getHeight() - 4, 20, 20);

                // Fundo
                g2.setColor(COLOR_BG_PANEL);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                g2.dispose();
                super.paintComponent(g);
            }
        };
        centralPanel.setOpaque(false);
        centralPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ====== Botão fechar "X" ======
        JButton btnClose = new JButton("X");
        btnClose.setForeground(Color.WHITE);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnClose.setContentAreaFilled(false);
        btnClose.setBorder(null);
        btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClose.addActionListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) window.setVisible(false);
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        centralPanel.add(btnClose, gbc);

        // ====== Foto / Câmera ======
        JLabel cameraLabel = new JLabel(); // Futuramente será a câmera
        cameraLabel.setPreferredSize(new Dimension(350, 200));
        cameraLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        centralPanel.add(cameraLabel, gbc);

        // ====== Campos do veículo ======
        String[][] campos = {
            {"Placa", "GBK7D05"},
            {"Ano", "2024"},
            {"Cor", "Branca"},
            {"Modelo", "Sedan"},
            {"Hrs. Entrada", "8:00"}
        };

        gbc.gridheight = 1;
        for (int i = 0; i < campos.length; i++) {
            JLabel label = new JLabel(campos[i][0] + ":");
            label.setForeground(COLOR_TEXT);
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            gbc.gridx = 1;
            gbc.gridy = i + 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            centralPanel.add(label, gbc);

            JLabel valor = new JLabel(campos[i][1]);
            valor.setForeground(COLOR_TEXT);
            valor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gbc.gridx = 2;
            gbc.gridy = i + 1;
            gbc.anchor = GridBagConstraints.WEST;
            centralPanel.add(valor, gbc);
        }

        // ====== Botões ======
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonsPanel.setOpaque(false);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        RoundedButton btnManual = new RoundedButton("Cadastrar Manualmente");
        RoundedButton btnVeiculo = new RoundedButton("Cadastrar Veículo");
        buttonsPanel.add(btnManual);
        buttonsPanel.add(btnVeiculo);

        centralPanel.add(buttonsPanel, gbc);
        add(centralPanel, gbcMain);

        // ====== Ação do botão Manual ======
        btnManual.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            TelaRegistroManual dialog = new TelaRegistroManual(parentFrame);
            dialog.setVisible(true);
        });
    }

    // Botão arredondado com hover
    private class RoundedButton extends JButton {
        private Color baseBg = COLOR_HOVER_BG;
        private Color baseFg = Color.WHITE;

        public RoundedButton(String text) {
            super(text);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setForeground(baseFg);
            setBackground(baseBg);
            setFocusPainted(false);
            setBorder(new EmptyBorder(8, 16, 8, 16));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setContentAreaFilled(false);

            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(255, 180, 60));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(baseBg);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        public void setContentAreaFilled(boolean b) {
            super.setContentAreaFilled(false);
        }
    }

//    // ====== Teste rápido da tela ======
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Entrada de Veículo");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(800, 500);
//            frame.setLocationRelativeTo(null);
//            frame.setContentPane(new TelaEntradaVeiculo());
//            frame.setVisible(true);
//        });
//    }
}
