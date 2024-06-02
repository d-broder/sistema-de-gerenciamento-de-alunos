package gui.alunos;

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

import dao.AlunoDAO;
import gui.AlunoGUI;
import modelo.Aluno;

public class ConsultarAlunoGUI extends JFrame {
    private JTextField consultaTextField;
    private JButton consultarButton;
    private JButton limparFiltroButton; // Botão para limpar o filtro
    private JTable tabelaAlunos;
    private JScrollPane scrollPane;
    private JButton voltarButton; // Botão para voltar

    private AlunoDAO alunoDAO;
    private List<Aluno> todosAlunos; // Lista de todos os alunos no banco de dados

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
        limparFiltroButton = new JButton("Limpar Filtro"); // Inicializar o botão
        tabelaAlunos = new JTable();
        scrollPane = new JScrollPane(tabelaAlunos);
        voltarButton = new JButton("Voltar"); // Inicializar o botão

        // Criando o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Criando o painel de consulta
        JPanel consultaPanel = new JPanel();
        consultaPanel.add(new JLabel("CPF ou Nome:"));
        consultaPanel.add(consultaTextField);
        consultaPanel.add(consultarButton);
        consultaPanel.add(limparFiltroButton); // Adicionar o botão no painel

        // Adicionando o painel de consulta ao painel principal
        mainPanel.add(consultaPanel, BorderLayout.NORTH);

        // Adicionando a tabela ao painel principal
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Criando o painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(voltarButton);

        // Adicionando o painel de botões ao painel principal na parte inferior
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(mainPanel);

        // Add action listeners
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarAlunos();
            }
        });

        // Adicionar um ActionListener para o botão de limpar filtro
        limparFiltroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpar o texto do campo de consulta
                consultaTextField.setText("");
                // Recarregar todos os alunos no banco de dados
                popularTabela(todosAlunos);
            }
        });

        // Adicionar um ActionListener para o botão "Voltar"
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaMainGUI();
            }
        });

        // Carregar todos os alunos ao abrir a GUI
        todosAlunos = alunoDAO.consultarTodos(); // Método fictício para obter todos os alunos
        popularTabela(todosAlunos);

        // Display the frame
        setVisible(true);
    }

    private void consultarAlunos() {
        String termo = consultaTextField.getText();
        List<Aluno> alunosFiltrados = alunoDAO.consultar(termo);
        popularTabela(alunosFiltrados);
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

    private void voltarParaMainGUI() {
        new AlunoGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de ConsultarAlunoGUI
    }

    public static void main(String[] args) {
        new ConsultarAlunoGUI();
    }
}
