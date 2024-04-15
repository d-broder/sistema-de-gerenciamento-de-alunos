package gui;

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

public class AlunoGUI extends JFrame {
    private JTextField cpfTextField;
    private JTextField nomeTextField;
    private JTextField dataNascimentoTextField;
    private JTextField pesoTextField;
    private JTextField alturaTextField;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton sairButton;

    public AlunoGUI() {
        // Set up the frame
        setTitle("Cadastro de Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 220); // Adjusted height to fit the content
        setLocationRelativeTo(null);

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
        mainPanel.add(createPanel(new JLabel("CPF:"), cpfTextField));
        mainPanel.add(createPanel(new JLabel("Nome:"), nomeTextField));
        mainPanel.add(createPanel(new JLabel("Data de Nascimento:"), dataNascimentoTextField));
        mainPanel.add(createPanel(new JLabel("Peso:"), pesoTextField));
        mainPanel.add(createPanel(new JLabel("Altura:"), alturaTextField));

        // Criando um painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(sairButton);

        // Adicionando o painel de botões ao painel principal
        mainPanel.add(buttonPanel);

        // Add the panel to the frame
        add(mainPanel);

        // Add an action listener to the cadastrarButton
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cpfTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O CPF do aluno não pode ser vazio.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else if (nomeTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O nome do aluno não pode ser vazio.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else if (dataNascimentoTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "A data de nascimento do aluno não pode ser vazia.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else if (pesoTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "O peso do aluno não pode ser vazio.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else if (alturaTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "A altura do aluno não pode ser vazia.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Aluno aluno = new Aluno();
                    AlunoDAO alunoDAO = new AlunoDAO();

                    aluno.setCpf(cpfTextField.getText());
                    aluno.setNome(nomeTextField.getText());

                    String dataTexto = dataNascimentoTextField.getText();

                    // Criando um objeto SimpleDateFormat para formatar a data
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                    try {
                        // Convertendo a String para um objeto Date
                        Date dataNascimento = formato.parse(dataTexto);

                        // Setando a data de nascimento do aluno
                        aluno.setDataNascimento(dataNascimento);
                    } catch (ParseException ex) {
                        // Caso ocorra um erro na conversão da data
                        ex.printStackTrace();
                    }
                    aluno.setPeso(Double.parseDouble(pesoTextField.getText()));
                    aluno.setAltura(Double.parseDouble(alturaTextField.getText()));
                    alunoDAO.adiciona(aluno);
                    JOptionPane.showMessageDialog(null,
                            "Aluno " + nomeTextField.getText() + " cadastrado com sucesso.", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);

                }

                nomeTextField.setText("");
            }
        });

        // Add an action listener to the limparButton
        limparButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                nomeTextField.setText("");
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

    public static void main(String[] args) {
        new AlunoGUI();
    }
}
