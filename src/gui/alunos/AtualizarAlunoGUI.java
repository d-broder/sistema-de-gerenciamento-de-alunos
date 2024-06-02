package gui.alunos;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import gui.AlunoGUI;
import modelo.Aluno;

public class AtualizarAlunoGUI extends JFrame {
    private JTextField cpfTextField;
    private JTextField nomeTextField;
    private JTextField dataNascimentoTextField;
    private JTextField pesoTextField;
    private JTextField alturaTextField;
    private JButton atualizarButton;
    private JButton voltarButton; // Alteração do botão "Sair" para "Voltar"

    private AlunoDAO alunoDAO;

    public AtualizarAlunoGUI() {
        // Set up the frame
        setTitle("Atualizar Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(440, 240);
        setLocationRelativeTo(null);

        // Create labels
        JLabel cpfLabel = createLabel("CPF do Aluno:");
        JLabel nomeLabel = createLabel("Novo Nome:");
        JLabel dataNascimentoLabel = createLabel("Nova Data de Nascimento:");
        JLabel pesoLabel = createLabel("Novo Peso:");
        JLabel alturaLabel = createLabel("Nova Altura:");

        // Create text fields
        cpfTextField = createTextField();
        nomeTextField = createTextField();
        dataNascimentoTextField = createTextField();
        pesoTextField = createTextField();
        alturaTextField = createTextField();

        // Create buttons
        atualizarButton = new JButton("Atualizar");
        voltarButton = new JButton("Voltar");

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add components to main panel
        mainPanel.add(createPanel(cpfLabel, cpfTextField));
        mainPanel.add(createPanel(nomeLabel, nomeTextField));
        mainPanel.add(createPanel(dataNascimentoLabel, dataNascimentoTextField));
        mainPanel.add(createPanel(pesoLabel, pesoTextField));
        mainPanel.add(createPanel(alturaLabel, alturaTextField));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createButtonPanel());

        // Add main panel to the frame
        add(mainPanel);

        // Initialize AlunoDAO
        alunoDAO = new AlunoDAO();

        // Initialize buttons' action listeners
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAluno();
            }
        });

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
        label.setPreferredSize(new Dimension(150, 30));
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
        buttonPanel.add(atualizarButton);
        buttonPanel.add(voltarButton);
        return buttonPanel;
    }

    private void atualizarAluno() {
        String cpf = cpfTextField.getText();
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O CPF do aluno não pode ser vazio.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obter o aluno atual do banco de dados
        Aluno alunoAtual = alunoDAO.buscarPorCPF(cpf);
        if (alunoAtual == null) {
            JOptionPane.showMessageDialog(null, "Aluno com CPF " + cpf + " não encontrado.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Criar um objeto Aluno com os novos dados
        Aluno aluno = new Aluno(null, null, null, 0, 0);
        aluno.setNome(nomeTextField.getText().isEmpty() ? alunoAtual.getNome() : nomeTextField.getText());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date novaDataNascimento = formato.parse(dataNascimentoTextField.getText());
            aluno.setDataNascimento(novaDataNascimento);
        } catch (ParseException ex) {
            aluno.setDataNascimento(alunoAtual.getDataNascimento());
        }

        try {
            double novoPeso = pesoTextField.getText().isEmpty() ? alunoAtual.getPeso()
                    : Double.parseDouble(pesoTextField.getText());
            double novaAltura = alturaTextField.getText().isEmpty() ? alunoAtual.getAltura()
                    : Double.parseDouble(alturaTextField.getText());
            aluno.setPeso(novoPeso);
            aluno.setAltura(novaAltura);
        } catch (NumberFormatException ex) {
            aluno.setPeso(alunoAtual.getPeso());
            aluno.setAltura(alunoAtual.getAltura());
        }

        // Atualizar o aluno usando AlunoDAO
        alunoDAO.atualizar(aluno);

        // Exibindo uma mensagem de sucesso
        JOptionPane.showMessageDialog(null, "Aluno com CPF " + cpf + " atualizado com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void voltarParaMainGUI() {
        new AlunoGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de AtualizarAlunoGUI
    }

    public static void main(String[] args) {
        new AtualizarAlunoGUI();
    }
}
