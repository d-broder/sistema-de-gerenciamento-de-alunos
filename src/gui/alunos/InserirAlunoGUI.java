package gui.alunos;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AlunoDAO;
import modelo.Aluno;

public class InserirAlunoGUI extends JFrame {
    private JTextField cpfTextField;
    private JTextField nomeTextField;
    private JTextField dataNascimentoTextField;
    private JTextField pesoTextField;
    private JTextField alturaTextField;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton sairButton;

    private AlunoDAO alunoDAO;

    public InserirAlunoGUI() {
        // Set up the frame
        setTitle("Cadastro de Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 240); // Ajusted height to fit the content
        setLocationRelativeTo(null);

        // Initialize AlunoDAO
        alunoDAO = new AlunoDAO();

        // Create the components
        cpfTextField = new JTextField(20);
        nomeTextField = new JTextField(20);
        dataNascimentoTextField = new JTextField(20);
        pesoTextField = new JTextField(20);
        alturaTextField = new JTextField(20);
        cadastrarButton = new JButton("Cadastrar");
        limparButton = new JButton("Limpar");
        sairButton = new JButton("Sair");

        // Criando o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Adicionando os componentes ao painel principal
        mainPanel.add(createPanel("CPF:", cpfTextField));
        mainPanel.add(createPanel("Nome:", nomeTextField));
        mainPanel.add(createPanel("Data de Nascimento:", dataNascimentoTextField));
        mainPanel.add(createPanel("Peso:", pesoTextField));
        mainPanel.add(createPanel("Altura:", alturaTextField));

        // Adicionando botões ao painel principal
        mainPanel.add(createButtonPanel());

        // Add the panel to the frame
        add(mainPanel);

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

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Display the frame
        setVisible(true);
    }

    private JPanel createPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        panel.add(textField);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(sairButton);
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

    public static void main(String[] args) {
        new InserirAlunoGUI();
    }
}
