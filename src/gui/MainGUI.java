package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.alunos.AtualizarAlunoGUI;
import gui.alunos.ConsultarAlunoGUI;
import gui.alunos.ExcluirAlunoGUI;
import gui.alunos.InserirAlunoGUI;

public class MainGUI extends JFrame {
    private JButton btnAtualizar;
    private JButton btnConsultar;
    private JButton btnExcluir;
    private JButton btnInserir;

    public MainGUI() {
        setTitle("Gerenciamento de Alunos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        btnInserir = new JButton("Inserir Aluno");
        btnExcluir = new JButton("Excluir Aluno");
        btnAtualizar = new JButton("Atualizar Aluno");
        btnConsultar = new JButton("Consultar Aluno");

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

        panel.add(btnInserir);
        panel.add(btnExcluir);
        panel.add(btnAtualizar);
        panel.add(btnConsultar);

        add(panel);
        setVisible(true);
    }

    private void openInserirAlunoGUI() {
        InserirAlunoGUI inserirAlunoGUI = new InserirAlunoGUI();
        inserirAlunoGUI.setVisible(true);
    }

    private void openExcluirAlunoGUI() {
        ExcluirAlunoGUI excluirAlunoGUI = new ExcluirAlunoGUI();
        excluirAlunoGUI.setVisible(true);
    }

    private void openAtualizarAlunoGUI() {
        AtualizarAlunoGUI atualizarAlunoGUI = new AtualizarAlunoGUI();
        atualizarAlunoGUI.setVisible(true);
    }

    private void openConsultarAlunoGUI() {
        ConsultarAlunoGUI consultarAlunoGUI = new ConsultarAlunoGUI();
        consultarAlunoGUI.setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
