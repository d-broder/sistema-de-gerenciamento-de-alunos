package gui.historico_peso;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.HistoricoPesoDAO;
import gui.HistoricoPesoGUI;
import modelo.HistoricoPeso;

public class ConsultarHistoricoPesoGUI extends JFrame {
    private JTextField cpfAlunoTextField;
    private JButton consultarButton;
    private JButton limparFiltroButton;
    private JTable tabelaHistoricoPeso;
    private JScrollPane scrollPane;
    private JButton voltarButton;

    private HistoricoPesoDAO historicoPesoDAO;

    public ConsultarHistoricoPesoGUI() {
        setTitle("Consultar Histórico de Peso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        historicoPesoDAO = new HistoricoPesoDAO();

        cpfAlunoTextField = new JTextField(20);
        consultarButton = new JButton("Consultar");
        limparFiltroButton = new JButton("Limpar Filtro");
        tabelaHistoricoPeso = new JTable();
        scrollPane = new JScrollPane(tabelaHistoricoPeso);
        voltarButton = new JButton("Voltar");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel consultaPanel = new JPanel();
        consultaPanel.add(new JLabel("CPF do Aluno:"));
        consultaPanel.add(cpfAlunoTextField);
        consultaPanel.add(consultarButton);
        consultaPanel.add(limparFiltroButton);

        mainPanel.add(consultaPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(voltarButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarHistoricoPeso();
            }
        });

        limparFiltroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpfAlunoTextField.setText("");
                popularTabela(historicoPesoDAO.consultarTodos());
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaMainGUI();
            }
        });

        popularTabela(historicoPesoDAO.consultarTodos());

        setVisible(true);
    }

    private void consultarHistoricoPeso() {
        String cpfAluno = cpfAlunoTextField.getText();
        List<HistoricoPeso> historicosFiltrados = historicoPesoDAO.consultarPorCpf(cpfAluno);
        popularTabela(historicosFiltrados);
    }

    private void popularTabela(List<HistoricoPeso> historicos) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("CPF Aluno");
        model.addColumn("Data");
        model.addColumn("Peso");

        for (HistoricoPeso historico : historicos) {
            Object[] rowData = new Object[4];
            rowData[0] = historico.getId();
            rowData[1] = historico.getCpfAluno();
            rowData[2] = historico.getData();
            rowData[3] = historico.getPeso();
            model.addRow(rowData);
        }

        tabelaHistoricoPeso.setModel(model);
    }

    private void voltarParaMainGUI() {
        new HistoricoPesoGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de ConsultarHistoricoPesoGUI
    }

    public static void main(String[] args) {
        new ConsultarHistoricoPesoGUI();
    }
}
