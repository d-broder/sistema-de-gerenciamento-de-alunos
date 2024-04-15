package gui.alunos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AlunoDAO;
import modelo.Aluno;

public class ExcluirAlunoGUI extends JFrame {
    private JTextField cpfTextField;
    private JButton excluirButton;
    private JButton sairButton;

    private AlunoDAO alunoDAO;

    public ExcluirAlunoGUI() {
        // Set up the frame
        setTitle("Excluir Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150); // Adjusted height to fit the content
        setLocationRelativeTo(null);

        // Create the components
        cpfTextField = new JTextField(20);
        excluirButton = new JButton("Excluir");
        sairButton = new JButton("Sair");

        // Criando o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Adicionando os componentes ao painel principal
        mainPanel.add(createPanel(new JLabel("CPF do Aluno:"), cpfTextField));

        // Criando um painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(excluirButton);
        buttonPanel.add(sairButton);

        // Adicionando o painel de botões ao painel principal
        mainPanel.add(buttonPanel);

        // Add the panel to the frame
        add(mainPanel);

        // Initialize AlunoDAO
        alunoDAO = new AlunoDAO();

        // Add an action listener to the excluirButton
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirAluno();
            }
        });

        // Add an action listener to the sairButton
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Display the frame
        setVisible(true);
    }

    // Método auxiliar para criar painéis com rótulo e campo de texto
    private static JPanel createPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private void excluirAluno() {
        String cpf = cpfTextField.getText();
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O CPF do aluno não pode ser vazio.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar um objeto Aluno com apenas o CPF preenchido
        Aluno aluno = new Aluno();
        aluno.setCpf(cpf);

        // Excluir o aluno usando AlunoDAO
        alunoDAO.excluir(aluno);

        // Exibindo uma mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Aluno com CPF " + cpf + " excluído com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new ExcluirAlunoGUI();
    }
}
