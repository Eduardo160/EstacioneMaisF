package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class TelaPrincipal extends JFrame {

    private static final Color COLOR_BG_CENTER = new Color(23, 23, 23);
    private static final Color COLOR_LEFT_BAR = new Color(10, 10, 10);
    private static final Color COLOR_TOP_BAR = new Color(18, 18, 18);
    private static final Color COLOR_SUB_BAR = new Color(32, 32, 32);
    private static final Color COLOR_TEXT = new Color(240, 240, 240);
    private static final Color COLOR_HOVER_BG = new Color(255, 157, 8);
    private static final Color COLOR_HOVER_TEXT = new Color(255, 255, 255);
    private static final Color COLOR_SHADOW = new Color(0, 0, 0, 80);

    // Tornar o painel central acessível para os ActionListeners
    private JPanel centerWrapper;

    public TelaPrincipal() {
        setTitle("Estacione+");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BG_CENTER);

        // ====== LATERAL ESQUERDA ======
        JPanel left = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(COLOR_LEFT_BAR);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        left.setPreferredSize(new Dimension(100, 0));

        JLabel logoLeft = new JLabel(loadIcon("src/imagens/LogoEstacione+.png", 60, 50));
        logoLeft.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        logoPanel.setOpaque(false);
        logoPanel.add(logoLeft);
        left.add(logoPanel, BorderLayout.NORTH);
        add(left, BorderLayout.WEST);

        // ====== TOPO ======
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(COLOR_TOP_BAR);

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(COLOR_TOP_BAR);
        topBar.setPreferredSize(new Dimension(0, 54));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(45, 45, 45)));

        JPanel leftHead = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        leftHead.setOpaque(false);
        JLabel lbInicio = new JLabel("Início");
        lbInicio.setIcon(loadIcon("src/imagens/casa.png", 24, 24));
        lbInicio.setIconTextGap(8);
        lbInicio.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbInicio.setForeground(COLOR_TEXT);
        leftHead.add(lbInicio);
        topBar.add(leftHead, BorderLayout.WEST);

        JPanel rightHead = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 15));
        rightHead.setOpaque(false);
        JLabel lbCfg = new JLabel();
        lbCfg.setIcon(loadIcon("src/imagens/configurações.png", 24, 24));
        rightHead.add(lbCfg);
        topBar.add(rightHead, BorderLayout.EAST);

        // ====== Barra de menus centralizada ======
        JPanel subBar = new JPanel(new GridBagLayout());
        subBar.setBackground(COLOR_SUB_BAR);
        subBar.setPreferredSize(new Dimension(0, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10); // espaço entre os botões

        // Criar botões
        RoundedButton btnRegistrarVeic = new RoundedButton("Registrar Veíc.");
        RoundedButton btnGerenciar = new RoundedButton("Gerenciar");
        RoundedButton btnConsultar = new RoundedButton("Consultar");
        RoundedButton btnRelatorios = new RoundedButton("Relatórios");

        RoundedButton[] buttons = {btnRegistrarVeic, btnGerenciar, btnConsultar, btnRelatorios};
        for (int i = 0; i < buttons.length; i++) {
            gbc.gridx = i;
            subBar.add(buttons[i], gbc);
        }

        // ====== ActionListener para trocar o painel central ======
        btnRegistrarVeic.addActionListener(e -> {
            TelaEntradaVeiculo painel = new TelaEntradaVeiculo();
            centerWrapper.removeAll();
            centerWrapper.add(painel);
            centerWrapper.revalidate();
            centerWrapper.repaint();
        });
        
        // ação do botão
        btnGerenciar.addActionListener(e -> {
            TelaGerenciarVeiculos tela = new TelaGerenciarVeiculos();
            tela.setVisible(true);
        });
        
        btnConsultar.addActionListener(e -> {
            TelaConsultarVeiculo tela = new TelaConsultarVeiculo(this);
            tela.setVisible(true); // agora é JPanel

            centerWrapper.removeAll();

            // 🔹 Como centerWrapper usa GridBagLayout, precisamos usar GridBagConstraints
            GridBagConstraints gbcCenter = new GridBagConstraints();
            gbcCenter.gridx = 0;
            gbcCenter.gridy = 0;
            gbcCenter.anchor = GridBagConstraints.CENTER;

            centerWrapper.add(tela, gbcCenter);
            centerWrapper.revalidate();
            centerWrapper.repaint();
        });

        topWrapper.add(topBar, BorderLayout.NORTH);
        topWrapper.add(subBar, BorderLayout.CENTER);
        add(topWrapper, BorderLayout.NORTH);

        // ====== CENTRO CENTRALIZADO ======
        centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(COLOR_BG_CENTER);
        JLabel logoCenter = new JLabel(loadIcon("src/imagens/LogoEstacione+Escrita.png", 250, 250));
        GridBagConstraints gbcCenter = new GridBagConstraints();
        gbcCenter.gridx = 0;
        gbcCenter.gridy = 0;
        gbcCenter.anchor = GridBagConstraints.CENTER;
        centerWrapper.add(logoCenter, gbcCenter);
        add(centerWrapper, BorderLayout.CENTER);
    }

    // JButton arredondado com hover animado e efeito pressionado
    private class RoundedButton extends JButton {
        private Color baseBg = COLOR_SUB_BAR;
        private Color baseFg = COLOR_TEXT;
        private Color targetBg, targetFg;
        private Timer timer;
        private int steps = 10;
        private int currentStep;
        private boolean pressed = false;

        public RoundedButton(String text) {
            super(text);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setForeground(baseFg);
            setBackground(baseBg);
            setFocusPainted(false);
            setBorder(new EmptyBorder(8, 16, 8, 16));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setContentAreaFilled(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!pressed) startAnimation(COLOR_HOVER_BG, COLOR_HOVER_TEXT);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    if (!pressed) startAnimation(baseBg, baseFg);
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    pressed = true;
                    setBackground(blendColor(getBackground(), COLOR_HOVER_BG.darker(), 0.3f));
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                    startAnimation(COLOR_HOVER_BG, COLOR_HOVER_TEXT);
                }
            });
        }

        private void startAnimation(Color bg, Color fg) {
            if (timer != null) timer.stop();
            targetBg = bg;
            targetFg = fg;
            currentStep = 0;
            Color startBg = getBackground();
            Color startFg = getForeground();
            timer = new Timer(20, e -> {
                float ratio = (float) currentStep / steps;
                setBackground(blendColor(startBg, targetBg, ratio));
                setForeground(blendColor(startFg, targetFg, ratio));
                currentStep++;
                if (currentStep > steps) ((Timer)e.getSource()).stop();
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // Sombra
            g2.setColor(COLOR_SHADOW);
            g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 16, 16);
            // Fundo arredondado
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

    private Color blendColor(Color c1, Color c2, float ratio) {
        int r = (int)(c1.getRed() + (c2.getRed() - c1.getRed()) * ratio);
        int g = (int)(c1.getGreen() + (c2.getGreen() - c1.getGreen()) * ratio);
        int b = (int)(c1.getBlue() + (c2.getBlue() - c1.getBlue()) * ratio);
        return new Color(r, g, b);
    }

    private ImageIcon loadIcon(String path, int width, int height) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Imagem não encontrada: " + path);
            return new ImageIcon();
        }
        ImageIcon icon = new ImageIcon(path);
        if (width > 0 && height > 0) {
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
        }
        return icon;
    }
}
