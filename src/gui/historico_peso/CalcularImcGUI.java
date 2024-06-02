package gui.historico_peso;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
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

public class CalcularImcGUI extends JFrame {
    private JTextField idTextField;
    private JButton calcularButton;
    private JButton voltarButton; // Botão de voltar
    private HistoricoPesoDAO historicoPesoDAO;

    public CalcularImcGUI() {
        setTitle("Calcular IMC");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        historicoPesoDAO = new HistoricoPesoDAO();

        JLabel idLabel = createLabel("ID do Histórico:");

        idTextField = createTextField();

        calcularButton = new JButton("Calcular");
        voltarButton = new JButton("Voltar");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createPanel(idLabel, idTextField));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(createButtonPanel());

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHistoricoPesoGUI(); // Método para abrir a interface principal
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
        buttonPanel.add(calcularButton);
        buttonPanel.add(voltarButton);
        return buttonPanel;
    }

    private void calcularIMC() {
        try {
            int id = Integer.parseInt(idTextField.getText());

            // Consultar o histórico de peso pelo ID
            HistoricoPeso historicoPeso = historicoPesoDAO.consultar(id);

            if (historicoPeso == null) {
                JOptionPane.showMessageDialog(null, "ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double peso = historicoPeso.getPeso();

            // Calcular IMC
            double altura = 1.75; // Altura fixa para fins de exemplo
            double imc = peso / (altura * altura);
            String interpretacao = interpretarIMC(imc);

            // Gravar informações em um arquivo
            gravarArquivo(historicoPeso, imc, interpretacao);

            // Exibir mensagem de sucesso
            JOptionPane.showMessageDialog(null, "IMC calculado e gravado com sucesso.", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Fechar a janela
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String interpretarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 25) {
            return "Peso normal";
        } else if (imc < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }

    private void gravarArquivo(HistoricoPeso historicoPeso, double imc, String interpretacao) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("historico_imc.txt", true))) {
            Date data = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = dateFormat.format(data);

            writer.println("Data: " + dataFormatada);
            writer.println("ID: " + historicoPeso.getId());
            writer.println("CPF: " + historicoPeso.getCpfAluno());
            writer.println("Nome: " + historicoPeso.getNomeAluno());
            writer.println("Peso: " + historicoPeso.getPeso() + " kg");
            writer.println("IMC: " + String.format("%.2f", imc));
            writer.println(interpretacao);
            writer.println(); // Adiciona uma linha em branco entre cada registro
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para abrir a interface principal
    private void openHistoricoPesoGUI() {
        HistoricoPesoGUI historicoPesoGUI = new HistoricoPesoGUI();
        historicoPesoGUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new CalcularImcGUI();
    }
}
