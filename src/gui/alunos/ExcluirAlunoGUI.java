package gui.alunos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AlunoDAO;
import gui.MainGUI;
import modelo.Aluno;

public class ExcluirAlunoGUI extends JFrame {
    private JTextField cpfTextField;
    private JButton excluirButton;
    private JButton voltarButton; // Alteração do botão "Sair" para "Voltar"

    private AlunoDAO alunoDAO;

    public ExcluirAlunoGUI() {
        // Set up the frame
        setTitle("Excluir Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 140); // Adjusted height to fit the content
        setLocationRelativeTo(null);

        // Create labels
        JLabel cpfLabel = createLabel("CPF do Aluno:");

        // Create text fields
        cpfTextField = createTextField();

        // Create buttons
        excluirButton = new JButton("Excluir");
        voltarButton = new JButton("Voltar");

        // Criate main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to main panel
        mainPanel.add(createPanel(cpfLabel, cpfTextField));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createButtonPanel());

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

        // Add an action listener to the voltarButton
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaMainGUI();
            }
        });

        // Display the frame
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setPreferredSize(new Dimension(100, 30));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        return textField;
    }

    private JPanel createPanel(Component component1, Component component2) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalStrut(10));
        panel.add(component1);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(component2);
        panel.add(Box.createHorizontalStrut(10));
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(excluirButton);
        buttonPanel.add(voltarButton);
        return buttonPanel;
    }

    private void excluirAluno() {
        String cpf = cpfTextField.getText();
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O CPF do aluno não pode ser vazio.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se o CPF existe no banco de dados
        if (!alunoDAO.verificarExistencia(cpf)) {
            JOptionPane.showMessageDialog(null, "O CPF informado não corresponde a nenhum aluno.", "Erro",
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

    private void voltarParaMainGUI() {
        new MainGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de ExcluirAlunoGUI
    }

    public static void main(String[] args) {
        new ExcluirAlunoGUI();
    }
}
