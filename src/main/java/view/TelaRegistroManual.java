package view;

import javax.swing.*;
import java.awt.*;

public class TelaRegistroManual extends JDialog {

    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtPlaca;
    private JTextField txtHoraEntrada;
    private JButton btnCadastrar;

    public TelaRegistroManual(JFrame parent) {
        super(parent, "Registrar Manualmente", true);
        setSize(600, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(23, 23, 23));

        // Painel principal
        JPanel panel = new JPanel();
        panel.setBackground(new Color(23, 23, 23));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel lblTitulo = new JLabel("Registrar Manualmente");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Nome
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        panel.add(lblNome, gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(20);
        panel.add(txtNome, gbc);

        // Telefone
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblTelefone = new JLabel("Nº Telefone:");
        lblTelefone.setForeground(Color.WHITE);
        panel.add(lblTelefone, gbc);

        gbc.gridx = 1;
        txtTelefone = new JTextField(15);
        panel.add(txtTelefone, gbc);

        // Placa
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setForeground(Color.WHITE);
        panel.add(lblPlaca, gbc);

        gbc.gridx = 1;
        txtPlaca = new JTextField(10);
        panel.add(txtPlaca, gbc);

        // Hora Entrada
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblHoraEntrada = new JLabel("Hrs Entrada:");
        lblHoraEntrada.setForeground(Color.WHITE);
        panel.add(lblHoraEntrada, gbc);

        gbc.gridx = 1;
        txtHoraEntrada = new JTextField(10);
        panel.add(txtHoraEntrada, gbc);

        // Botão cadastrar
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnCadastrar = new JButton("Cadastrar Veículo");
        btnCadastrar.setBackground(new Color(255, 140, 0));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCadastrar.setPreferredSize(new Dimension(200, 40));
        panel.add(btnCadastrar, gbc);

        add(panel, BorderLayout.CENTER);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            TelaRegistroManual dialog = new TelaRegistroManual(null);
//            dialog.setVisible(true);
//        });
//    }
}
