package gui.historico_peso;

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

import dao.HistoricoPesoDAO;
import gui.HistoricoPesoGUI;

public class ExcluirHistoricoPesoGUI extends JFrame {
    private JTextField idTextField;
    private JButton excluirButton;
    private JButton voltarButton;

    private HistoricoPesoDAO historicoPesoDAO;

    public ExcluirHistoricoPesoGUI() {
        setTitle("Excluir Histórico de Peso");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        historicoPesoDAO = new HistoricoPesoDAO();

        JLabel idLabel = createLabel("ID do Histórico:");

        idTextField = createTextField();

        excluirButton = new JButton("Excluir");
        voltarButton = new JButton("Voltar");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createPanel(idLabel, idTextField));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createButtonPanel());

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirHistoricoPeso();
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

    private void excluirHistoricoPeso() {
        String idText = idTextField.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O ID do histórico não pode ser vazio.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idText);

        historicoPesoDAO.excluir(id);

        JOptionPane.showMessageDialog(null, "Histórico com ID " + id + " excluído com sucesso.", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void voltarParaMainGUI() {
        new HistoricoPesoGUI(); // Abre uma nova instância de MainGUI
        dispose(); // Fecha a instância atual de ExcluirHistoricoPesoGUI
    }

    public static void main(String[] args) {
        new ExcluirHistoricoPesoGUI();
    }
}
