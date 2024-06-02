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

public class AtualizarHistoricoPesoGUI extends JFrame {
    private JTextField idTextField;
    private JTextField cpfAlunoTextField;
    private JTextField dataTextField;
    private JTextField pesoTextField;
    private JButton atualizarButton;
    private JButton voltarButton;

    private HistoricoPesoDAO historicoPesoDAO;

    public AtualizarHistoricoPesoGUI() {
        setTitle("Atualizar Histórico de Peso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(440, 240);
        setLocationRelativeTo(null);

        historicoPesoDAO = new HistoricoPesoDAO();

        JLabel idLabel = createLabel("ID:");
        JLabel cpfAlunoLabel = createLabel("CPF do Aluno:");
        JLabel dataLabel = createLabel("Data:");
        JLabel pesoLabel = createLabel("Peso:");

        idTextField = createTextField();
        cpfAlunoTextField = createTextField();
        dataTextField = createTextField();
        pesoTextField = createTextField();

        atualizarButton = new JButton("Atualizar");
        voltarButton = new JButton("Voltar");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createPanel(idLabel, idTextField));
        mainPanel.add(createPanel(cpfAlunoLabel, cpfAlunoTextField));
        mainPanel.add(createPanel(dataLabel, dataTextField));
        mainPanel.add(createPanel(pesoLabel, pesoTextField));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createButtonPanel());

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarHistoricoPeso();
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
        buttonPanel.add(atualizarButton);
        buttonPanel.add(voltarButton);
        return buttonPanel;
    }

    private void atualizarHistoricoPeso() {
        if (idTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O ID do histórico não pode ser vazio.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idTextField.getText());

        HistoricoPeso historicoPesoAtual = historicoPesoDAO.consultar(id);
        if (historicoPesoAtual == null) {
            JOptionPane.showMessageDialog(null, "Histórico com ID " + id + " não encontrado.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        HistoricoPeso historicoPeso = new HistoricoPeso(id, null, null, 0);
        historicoPeso.setCpfAluno(
                cpfAlunoTextField.getText().isEmpty() ? historicoPesoAtual.getCpfAluno() : cpfAlunoTextField.getText());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date novaData = formato.parse(dataTextField.getText());
            historicoPeso.setData(novaData);
        } catch (ParseException ex) {
            historicoPeso.setData(historicoPesoAtual.getData());
        }

        try {
            double novoPeso = pesoTextField.getText().isEmpty() ? historicoPesoAtual.getPeso()
                    : Double.parseDouble(pesoTextField.getText());
            historicoPeso.setPeso(novoPeso);
        } catch (NumberFormatException ex) {
            historicoPeso.setPeso(historicoPesoAtual.getPeso());
        }

        historicoPesoDAO.atualizar(historicoPeso);

        JOptionPane.showMessageDialog(null, "Histórico com ID " + id + " atualizado com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void voltarParaMainGUI() {
        new HistoricoPesoGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de AtualizarHistoricoPesoGUI
    }

    public static void main(String[] args) {
        new AtualizarHistoricoPesoGUI();
    }
}
