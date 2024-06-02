package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.historico_peso.AtualizarHistoricoPesoGUI;
import gui.historico_peso.CalcularImcGUI;
import gui.historico_peso.ConsultarHistoricoPesoGUI;
import gui.historico_peso.ExcluirHistoricoPesoGUI;
import gui.historico_peso.InserirHistoricoPesoGUI;

public class HistoricoPesoGUI extends JFrame {
    private JButton btnAtualizar;
    private JButton btnConsultar;
    private JButton btnExcluir;
    private JButton btnInserir;
    private JButton btnCalcularImc;
    private JButton btnSair;

    public HistoricoPesoGUI() {
        setTitle("Gerenciamento de Alunos e Histórico de Peso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnInserir = new JButton("Inserir Histórico de Peso");
        btnExcluir = new JButton("Excluir Histórico de Peso");
        btnAtualizar = new JButton("Atualizar Histórico de Peso");
        btnConsultar = new JButton("Consultar Histórico de Peso");
        btnCalcularImc = new JButton("Calcular IMC");
        btnSair = new JButton("Voltar");

        int width = 220;
        int height = 30;

        btnInserir.setPreferredSize(new Dimension(width, height));
        btnExcluir.setPreferredSize(new Dimension(width, height));
        btnAtualizar.setPreferredSize(new Dimension(width, height));
        btnConsultar.setPreferredSize(new Dimension(width, height));
        btnCalcularImc.setPreferredSize(new Dimension(width, height));
        btnSair.setPreferredSize(new Dimension(100, height));

        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInserirHistoricoPesoGUI();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openExcluirHistoricoPesoGUI();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAtualizarHistoricoPesoGUI();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openConsultarHistoricoPesoGUI();
            }
        });

        btnCalcularImc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCalcularImcGUI();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainGUI();
            }
        });

        panel.add(btnInserir);
        panel.add(btnExcluir);
        panel.add(btnAtualizar);
        panel.add(btnConsultar);
        panel.add(btnCalcularImc);
        panel.add(btnSair);

        add(panel);
        setVisible(true);
    }

    private void openInserirHistoricoPesoGUI() {
        InserirHistoricoPesoGUI inserirHistoricoPesoGUI = new InserirHistoricoPesoGUI();
        inserirHistoricoPesoGUI.setVisible(true);
        dispose();
    }

    private void openExcluirHistoricoPesoGUI() {
        ExcluirHistoricoPesoGUI excluirHistoricoPesoGUI = new ExcluirHistoricoPesoGUI();
        excluirHistoricoPesoGUI.setVisible(true);
        dispose();
    }

    private void openAtualizarHistoricoPesoGUI() {
        AtualizarHistoricoPesoGUI atualizarHistoricoPesoGUI = new AtualizarHistoricoPesoGUI();
        atualizarHistoricoPesoGUI.setVisible(true);
        dispose();
    }

    private void openConsultarHistoricoPesoGUI() {
        ConsultarHistoricoPesoGUI consultarHistoricoPesoGUI = new ConsultarHistoricoPesoGUI();
        consultarHistoricoPesoGUI.setVisible(true);
        dispose();
    }

    private void openCalcularImcGUI() {
        CalcularImcGUI calcularImcGUI = new CalcularImcGUI();
        calcularImcGUI.setVisible(true);
        dispose();
    }

    private void openMainGUI() {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new HistoricoPesoGUI();
    }
}
