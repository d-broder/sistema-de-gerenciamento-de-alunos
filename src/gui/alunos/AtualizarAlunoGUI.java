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

public class AtualizarAlunoGUI extends JFrame {
    private JTextField cpfTextField;
    private JTextField nomeTextField;
    private JTextField dataNascimentoTextField;
    private JTextField pesoTextField;
    private JTextField alturaTextField;
    private JButton atualizarButton;
    private JButton sairButton;

    private AlunoDAO alunoDAO;

    public AtualizarAlunoGUI() {
        // Set up the frame
        setTitle("Atualizar Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 240); // Adjusted height to fit the content
        setLocationRelativeTo(null);

        // Create the components
        cpfTextField = new JTextField(20);
        nomeTextField = new JTextField(20);
        dataNascimentoTextField = new JTextField(20);
        pesoTextField = new JTextField(20);
        alturaTextField = new JTextField(20);
        atualizarButton = new JButton("Atualizar");
        sairButton = new JButton("Sair");

        // Criando o painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Adicionando os componentes ao painel principal
        mainPanel.add(createPanel(new JLabel("CPF do Aluno:"), cpfTextField));
        mainPanel.add(createPanel(new JLabel("Novo Nome:"), nomeTextField));
        mainPanel.add(createPanel(new JLabel("Nova Data de Nascimento:"), dataNascimentoTextField));
        mainPanel.add(createPanel(new JLabel("Novo Peso:"), pesoTextField));
        mainPanel.add(createPanel(new JLabel("Nova Altura:"), alturaTextField));

        // Criando um painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(atualizarButton);
        buttonPanel.add(sairButton);

        // Adicionando o painel de botões ao painel principal
        mainPanel.add(buttonPanel);

        // Add the panel to the frame
        add(mainPanel);

        // Initialize AlunoDAO
        alunoDAO = new AlunoDAO();

        // Add an action listener to the atualizarButton
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAluno();
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
        Aluno aluno = new Aluno();
        aluno.setCpf(cpf);
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

    public static void main(String[] args) {
        new AtualizarAlunoGUI();
    }
}
