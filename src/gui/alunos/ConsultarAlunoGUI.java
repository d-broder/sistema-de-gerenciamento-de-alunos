package gui.alunos;

import java.awt.BorderLayout;
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

import dao.AlunoDAO;
import modelo.Aluno;

public class ConsultarAlunoGUI extends JFrame {
    private JTextField consultaTextField;
    private JButton consultarButton;
    private JTable tabelaAlunos;
    private JScrollPane scrollPane;

    private AlunoDAO alunoDAO;

    public ConsultarAlunoGUI() {
        // Set up the frame
        setTitle("Consultar Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Ajustar conforme necessário
        setLocationRelativeTo(null);

        // Initialize AlunoDAO
        alunoDAO = new AlunoDAO();

        // Criando os componentes
        consultaTextField = new JTextField(20);
        consultarButton = new JButton("Consultar");
        tabelaAlunos = new JTable();
        scrollPane = new JScrollPane(tabelaAlunos);

        // Criando o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Criando o painel de consulta
        JPanel consultaPanel = new JPanel();
        consultaPanel.add(new JLabel("CPF ou Nome:"));
        consultaPanel.add(consultaTextField);
        consultaPanel.add(consultarButton);

        // Adicionando o painel de consulta ao painel principal
        mainPanel.add(consultaPanel, BorderLayout.NORTH);

        // Adicionando a tabela ao painel principal
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the panel to the frame
        add(mainPanel);

        // Add an action listener to the consultarButton
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarAlunos();
            }
        });

        // Display the frame
        setVisible(true);
    }

    private void consultarAlunos() {
        String termo = consultaTextField.getText();
        List<Aluno> alunos = alunoDAO.consultar(termo);
        popularTabela(alunos);
    }

    private void popularTabela(List<Aluno> alunos) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CPF");
        model.addColumn("Nome");
        model.addColumn("Data de Nascimento");
        model.addColumn("Peso");
        model.addColumn("Altura");

        for (Aluno aluno : alunos) {
            Object[] rowData = new Object[5];
            rowData[0] = aluno.getCpf();
            rowData[1] = aluno.getNome();
            rowData[2] = aluno.getDataNascimento();
            rowData[3] = aluno.getPeso();
            rowData[4] = aluno.getAltura();
            model.addRow(rowData);
        }

        tabelaAlunos.setModel(model);
    }

    public static void main(String[] args) {
        new ConsultarAlunoGUI();
    }
}
