package gui.historico_peso;

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

import dao.HistoricoPesoDAO;
import gui.HistoricoPesoGUI;
import modelo.HistoricoPeso;

public class InserirHistoricoPesoGUI extends JFrame {
    private JTextField cpfAlunoTextField;
    private JTextField dataTextField;
    private JTextField pesoTextField;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton voltarButton;

    private HistoricoPesoDAO historicoPesoDAO;

    public InserirHistoricoPesoGUI() {
        setTitle("Cadastro de Histórico de Peso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(440, 240);
        setLocationRelativeTo(null);

        historicoPesoDAO = new HistoricoPesoDAO();

        JLabel cpfAlunoLabel = createLabel("CPF do Aluno:");
        JLabel dataLabel = createLabel("Data:");
        JLabel pesoLabel = createLabel("Peso:");

        cpfAlunoTextField = createTextField();
        dataTextField = createTextField();
        pesoTextField = createTextField();

        cadastrarButton = new JButton("Cadastrar");
        limparButton = new JButton("Limpar");
        voltarButton = new JButton("Voltar");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createPanel(cpfAlunoLabel, cpfAlunoTextField));
        mainPanel.add(createPanel(dataLabel, dataTextField));
        mainPanel.add(createPanel(pesoLabel, pesoTextField));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createButtonPanel());

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarHistoricoPeso();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaMainGUI();
            }
        });

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

    private void cadastrarHistoricoPeso() {
        if (camposVazios()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        HistoricoPeso historicoPeso = criarHistoricoPeso();
        historicoPesoDAO.inserir(historicoPeso);
        JOptionPane.showMessageDialog(null, "Histórico de peso cadastrado com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private boolean camposVazios() {
        return cpfAlunoTextField.getText().isEmpty() || dataTextField.getText().isEmpty()
                || pesoTextField.getText().isEmpty();
    }

    private HistoricoPeso criarHistoricoPeso() {
        String cpfAluno = cpfAlunoTextField.getText();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = formatoData.parse(dataTextField.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        double peso = Double.parseDouble(pesoTextField.getText());

        return new HistoricoPeso(cpfAluno, data, peso);
    }

    private void limparCampos() {
        cpfAlunoTextField.setText("");
        dataTextField.setText("");
        pesoTextField.setText("");
    }

    private void voltarParaMainGUI() {
        new HistoricoPesoGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de InserirHistoricoPesoGUI
    }

    public static void main(String[] args) {
        new InserirHistoricoPesoGUI();
    }
}
