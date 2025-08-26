package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TelaGerenciarVeiculos extends JFrame {

    private static final Color COLOR_BG = new Color(23, 23, 23);
    private static final Color COLOR_PANEL = new Color(36, 36, 36);
    private static final Color COLOR_HEADER = new Color(50, 50, 50);
    private static final Color COLOR_TEXT = new Color(240, 240, 240);
    private static final Color COLOR_BUTTON = new Color(200, 0, 0);

    private JTable tabela;

    public TelaGerenciarVeiculos() {
        setTitle("Veículos Estacionados");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_BG);

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(COLOR_PANEL);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Cabeçalho
        JLabel lblTitulo = new JLabel("🚗 Veículos Estacionados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(COLOR_TEXT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"Placa", "Modelo", "Cor", "Hr. Entrada", "Hr. Saída", "Ações"};
        Object[][] dados = {
                {"GBK7D05", "Chev/Onix", "Branca", "08:00", "xx:xx", "Saída"},
                {"", "", "", "", "", "Saída"},
                {"", "", "", "", "", "Saída"},
                {"", "", "", "", "", "Saída"},
                {"", "", "", "", "", "Saída"}
        };

        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Só permite editar (clicar) no botão
            }
        };

        tabela = new JTable(modelo);
        tabela.setRowHeight(40);
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        tabela.setForeground(COLOR_TEXT);
        tabela.setBackground(COLOR_PANEL);
        tabela.setGridColor(new Color(80, 80, 80));

        // Estilo do cabeçalho
        JTableHeader header = tabela.getTableHeader();
        header.setBackground(COLOR_HEADER);
        header.setForeground(COLOR_TEXT);
        header.setFont(new Font("Arial", Font.BOLD, 14));

        // Renderer para centralizar texto
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabela.getColumnCount() - 1; i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Renderer para botão de saída
        tabela.getColumn("Ações").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Ações").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.getViewport().setBackground(COLOR_PANEL);
        painelPrincipal.add(scroll, BorderLayout.CENTER);

        // Rodapé (paginação)
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.setBackground(COLOR_PANEL);
        JLabel lblPagina = new JLabel("⟨ Página 1 de xx ⟩");
        lblPagina.setForeground(COLOR_TEXT);
        lblPagina.setFont(new Font("Arial", Font.PLAIN, 12));
        rodape.add(lblPagina);
        painelPrincipal.add(rodape, BorderLayout.SOUTH);

        add(painelPrincipal, BorderLayout.CENTER);
    }

    // Renderer do botão
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
            setBackground(COLOR_BUTTON);
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 14));
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "Saída" : value.toString());
            return this;
        }
    }

    // Editor do botão
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(COLOR_BUTTON);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            label = (value == null) ? "Saída" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                JOptionPane.showMessageDialog(null,
                        "Saída registrada para o veículo da linha " + (tabela.getSelectedRow() + 1));
            }
            clicked = false;
            return label;
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new TelaGerenciarVeiculos().setVisible(true));
//    }
}
