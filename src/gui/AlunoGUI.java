package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.alunos.AtualizarAlunoGUI;
import gui.alunos.ConsultarAlunoGUI;
import gui.alunos.ExcluirAlunoGUI;
import gui.alunos.InserirAlunoGUI;

public class AlunoGUI extends JFrame {
    private JButton btnAtualizar;
    private JButton btnConsultar;
    private JButton btnExcluir;
    private JButton btnInserir;
    private JButton btnSair;

    public AlunoGUI() {
        setTitle("Gerenciamento de Alunos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnInserir = new JButton("Inserir Aluno");
        btnExcluir = new JButton("Excluir Aluno");
        btnAtualizar = new JButton("Atualizar Aluno");
        btnConsultar = new JButton("Consultar Aluno");
        btnSair = new JButton("Voltar");

        int width = 220;
        int height = 30;

        btnInserir.setPreferredSize(new Dimension(width, height));
        btnExcluir.setPreferredSize(new Dimension(width, height));
        btnAtualizar.setPreferredSize(new Dimension(width, height));
        btnConsultar.setPreferredSize(new Dimension(width, height));
        btnSair.setPreferredSize(new Dimension(100, height));

        btnInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openInserirAlunoGUI();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openExcluirAlunoGUI();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAtualizarAlunoGUI();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openConsultarAlunoGUI();
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
        panel.add(btnSair);

        add(panel);
        setVisible(true);
    }

    private void openInserirAlunoGUI() {
        InserirAlunoGUI inserirAlunoGUI = new InserirAlunoGUI();
        inserirAlunoGUI.setVisible(true);
        dispose();
    }

    private void openExcluirAlunoGUI() {
        ExcluirAlunoGUI excluirAlunoGUI = new ExcluirAlunoGUI();
        excluirAlunoGUI.setVisible(true);
        dispose();
    }

    private void openAtualizarAlunoGUI() {
        AtualizarAlunoGUI atualizarAlunoGUI = new AtualizarAlunoGUI();
        atualizarAlunoGUI.setVisible(true);
        dispose();
    }

    private void openConsultarAlunoGUI() {
        ConsultarAlunoGUI consultarAlunoGUI = new ConsultarAlunoGUI();
        consultarAlunoGUI.setVisible(true);
        dispose();
    }

    private void openMainGUI() {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new AlunoGUI();
    }
}
