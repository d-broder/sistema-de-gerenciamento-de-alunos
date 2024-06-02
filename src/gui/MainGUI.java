package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGUI extends JFrame {
    public MainGUI() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAluno = new JButton("Gerenciar Alunos");
        JButton btnHistoricoPeso = new JButton("Gerenciar Histórico de Peso");
        JButton btnSair = new JButton("Voltar");

        int width = 220;
        int height = 30;

        btnAluno.setPreferredSize(new Dimension(width, height));
        btnHistoricoPeso.setPreferredSize(new Dimension(width, height));
        btnSair.setPreferredSize(new Dimension(100, height));

        btnAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAlunoGUI();
            }
        });

        btnHistoricoPeso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHistoricoPesoGUI();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(btnAluno);
        panel.add(btnHistoricoPeso);
        panel.add(btnSair);

        add(panel);
        setVisible(true);
    }

    private void openAlunoGUI() {
        AlunoGUI alunoGUI = new AlunoGUI();
        alunoGUI.setVisible(true);
        dispose();
    }

    private void openHistoricoPesoGUI() {
        HistoricoPesoGUI historicoPesoGUI = new HistoricoPesoGUI();
        historicoPesoGUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
