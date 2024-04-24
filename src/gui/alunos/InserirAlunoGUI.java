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
import gui.MainGUI;
import modelo.Aluno;

public class InserirAlunoGUI extends JFrame {
    private JTextField cpfTextField;
    private JTextField nomeTextField;
    private JTextField dataNascimentoTextField;
    private JTextField pesoTextField;
    private JTextField alturaTextField;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton voltarButton; // Adicionando o botão "Voltar"

    private AlunoDAO alunoDAO;

    public InserirAlunoGUI() {
        // Set up the frame
        setTitle("Cadastro de Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(440, 240);
        setLocationRelativeTo(null);

        // Initialize AlunoDAO
        alunoDAO = new AlunoDAO();

        // Create labels
        JLabel cpfLabel = createLabel("CPF do Aluno:");
        JLabel nomeLabel = createLabel("Nome:");
        JLabel dataNascimentoLabel = createLabel("Data de Nascimento:");
        JLabel pesoLabel = createLabel("Peso:");
        JLabel alturaLabel = createLabel("Altura:");

        // Create text fields
        cpfTextField = createTextField();
        nomeTextField = createTextField();
        dataNascimentoTextField = createTextField();
        pesoTextField = createTextField();
        alturaTextField = createTextField();

        // Create buttons
        cadastrarButton = new JButton("Cadastrar");
        limparButton = new JButton("Limpar");
        voltarButton = new JButton("Voltar");

        // Criando o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adicionando os componentes ao painel principal
        mainPanel.add(createPanel(cpfLabel, cpfTextField));
        mainPanel.add(createPanel(nomeLabel, nomeTextField));
        mainPanel.add(createPanel(dataNascimentoLabel, dataNascimentoTextField));
        mainPanel.add(createPanel(pesoLabel, pesoTextField));
        mainPanel.add(createPanel(alturaLabel, alturaTextField));
        mainPanel.add(Box.createVerticalStrut(10));

        // Adicionando botões ao painel principal
        mainPanel.add(createButtonPanel());

        // Add action listeners to buttons
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarAluno();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        // Add action listener to "Voltar" button
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaMainGUI();
            }
        });

        // Display the frame
        add(mainPanel);
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
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(voltarButton);
        return buttonPanel;
    }

    private void cadastrarAluno() {
        if (camposVazios()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Aluno aluno = criarAluno();
        alunoDAO.inserir(aluno);
        JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private boolean camposVazios() {
        return cpfTextField.getText().isEmpty() || nomeTextField.getText().isEmpty() ||
                dataNascimentoTextField.getText().isEmpty() || pesoTextField.getText().isEmpty() ||
                alturaTextField.getText().isEmpty();
    }

    private Aluno criarAluno() {
        Aluno aluno = new Aluno();
        aluno.setCpf(cpfTextField.getText());
        aluno.setNome(nomeTextField.getText());

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dataNascimento = formatoData.parse(dataNascimentoTextField.getText());
            aluno.setDataNascimento(dataNascimento);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        aluno.setPeso(Double.parseDouble(pesoTextField.getText()));
        aluno.setAltura(Double.parseDouble(alturaTextField.getText()));
        return aluno;
    }

    private void limparCampos() {
        cpfTextField.setText("");
        nomeTextField.setText("");
        dataNascimentoTextField.setText("");
        pesoTextField.setText("");
        alturaTextField.setText("");
    }

    private void voltarParaMainGUI() {
        new MainGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de InserirAlunoGUI
    }

    public static void main(String[] args) {
        new InserirAlunoGUI();
    }
}
