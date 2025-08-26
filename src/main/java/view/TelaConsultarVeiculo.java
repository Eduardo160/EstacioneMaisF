package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TelaConsultarVeiculo extends JDialog {

    private JTextField txtPlaca;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public TelaConsultarVeiculo(JFrame parent) {
        super(parent, "Consultar Veículo", true);

        // Cores base
        Color bgEscuro = new Color(23, 23, 23);
        Color bgPainel = new Color(40, 40, 40);
        Color corTexto = new Color(240, 240, 240);
        Color corLaranja = new Color(255, 140, 0);

        setLayout(new BorderLayout());
        getContentPane().setBackground(bgEscuro);

        // Painel título
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(new Color(60, 60, 60));
        painelTitulo.setBorder(new EmptyBorder(5, 10, 5, 10));

        JLabel lblIcon = new JLabel("\uD83D\uDD0D"); // ícone de lupa
        lblIcon.setForeground(corTexto);
        lblIcon.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel lblTitulo = new JLabel(" Consultar Veículo");
        lblTitulo.setForeground(corTexto);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));

        JButton btnFechar = new JButton("X");
        btnFechar.setForeground(corTexto);
        btnFechar.setBackground(new Color(90, 0, 0));
        btnFechar.setFocusPainted(false);
        btnFechar.addActionListener(e -> dispose());

        painelTitulo.add(lblIcon, BorderLayout.WEST);
        painelTitulo.add(lblTitulo, BorderLayout.CENTER);
        painelTitulo.add(btnFechar, BorderLayout.EAST);

        add(painelTitulo, BorderLayout.NORTH);

        // Painel de busca
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBusca.setBackground(bgPainel);
        painelBusca.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setForeground(corTexto);
        lblPlaca.setFont(new Font("SansSerif", Font.BOLD, 14));

        txtPlaca = new JTextField(15);
        txtPlaca.setBackground(new Color(70, 70, 70));
        txtPlaca.setForeground(corTexto);
        txtPlaca.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton btnConsultar = new JButton("Consultar \uD83D\uDD0D");
        btnConsultar.setBackground(corLaranja);
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFocusPainted(false);

        painelBusca.add(lblPlaca);
        painelBusca.add(txtPlaca);
        painelBusca.add(btnConsultar);

        add(painelBusca, BorderLayout.CENTER);

        // Tabela
        String[] colunas = {"Placa", "Modelo", "Cor", "Ações"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Apenas a coluna de ações
            }
        };

        tabela.setRowHeight(35);
        tabela.setBackground(new Color(50, 50, 50));
        tabela.setForeground(corTexto);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabela.getTableHeader().setBackground(bgPainel);
        tabela.getTableHeader().setForeground(corTexto);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        // Renderizar coluna de ações
        tabela.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
            JButton btnVer = new JButton("\uD83D\uDD0D");
            JButton btnExcluir = new JButton("\uD83D\uDDD1");

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
                painel.setBackground(new Color(50, 50, 50));

                btnVer.setFocusPainted(false);
                btnVer.setBackground(new Color(70, 70, 70));
                btnVer.setForeground(corTexto);

                btnExcluir.setFocusPainted(false);
                btnExcluir.setBackground(new Color(90, 30, 30));
                btnExcluir.setForeground(Color.WHITE);

                painel.add(btnVer);
                painel.add(btnExcluir);

                return painel;
            }
        });

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollTabela.getViewport().setBackground(bgEscuro);

        add(scrollTabela, BorderLayout.SOUTH);

        // Rodapé
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelRodape.setBackground(bgEscuro);

        JLabel lblPagina = new JLabel("Página 1 de xx");
        lblPagina.setForeground(corTexto);

        painelRodape.add(lblPagina);
        add(painelRodape, BorderLayout.PAGE_END);

        // Dados iniciais fictícios
        modeloTabela.addRow(new Object[]{"GBK7D05", "Chev/Onix", "Preto", ""});
        modeloTabela.addRow(new Object[]{"ABC1D23", "Fiat/Argo", "Branco", ""});
        modeloTabela.addRow(new Object[]{"XYZ9Z99", "VW/Gol", "Vermelho", ""});

        setSize(800, 500);
        setLocationRelativeTo(parent);
    }
}
