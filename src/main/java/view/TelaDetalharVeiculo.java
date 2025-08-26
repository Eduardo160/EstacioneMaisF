package view;

import javax.swing.*;
import java.awt.*;

public class TelaDetalharVeiculo extends JDialog {

    private static final Color COLOR_BG = new Color(30, 30, 30);
    private static final Color COLOR_TEXT = new Color(240, 240, 240);
    private static final Color COLOR_ACCENT = new Color(255, 153, 0);
    private static final Color COLOR_INATIVO = new Color(200, 0, 0);

    public TelaDetalharVeiculo(JFrame parent, String placa, String modelo, String entrou, String saiu, boolean ativo) {
        super(parent, "Placa", true);

        setSize(350, 280);
        setLocationRelativeTo(parent);
        setUndecorated(true);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(COLOR_BG);
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(content);

        // Top bar (Título + botão fechar)
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        JLabel titulo = new JLabel("Placa");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(COLOR_TEXT);
        topBar.add(titulo, BorderLayout.WEST);

        JButton btnClose = new JButton("✕");
        btnClose.setFocusPainted(false);
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setForeground(COLOR_TEXT);
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClose.addActionListener(e -> dispose());
        topBar.add(btnClose, BorderLayout.EAST);

        content.add(topBar, BorderLayout.NORTH);

        // Painel central com informações
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Campo da placa com botão editar
        JPanel placaPanel = new JPanel(new BorderLayout());
        placaPanel.setBackground(new Color(60, 60, 60));
        placaPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        JLabel lblPlaca = new JLabel(placa);
        lblPlaca.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPlaca.setForeground(COLOR_TEXT);
        placaPanel.add(lblPlaca, BorderLayout.CENTER);

        JButton btnEdit = new JButton("✎");
        btnEdit.setFocusPainted(false);
        btnEdit.setBorderPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setForeground(COLOR_TEXT);
        btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        placaPanel.add(btnEdit, BorderLayout.EAST);

        center.add(placaPanel);
        center.add(Box.createVerticalStrut(15));

        JLabel lblModelo = new JLabel("Modelo: " + modelo);
        lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblModelo.setForeground(COLOR_TEXT);
        center.add(lblModelo);

        JLabel lblEntrou = new JLabel("Entrou: " + entrou);
        lblEntrou.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblEntrou.setForeground(COLOR_TEXT);
        center.add(lblEntrou);

        JLabel lblSaiu = new JLabel("Saiu: " + saiu);
        lblSaiu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSaiu.setForeground(COLOR_TEXT);
        center.add(lblSaiu);

        JLabel lblStatus = new JLabel(ativo ? "Ativo" : "Inativo");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblStatus.setForeground(ativo ? COLOR_ACCENT : COLOR_INATIVO);
        center.add(Box.createVerticalStrut(10));
        center.add(lblStatus);

        content.add(center, BorderLayout.CENTER);
    }
}
