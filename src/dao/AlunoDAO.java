package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import factory.ConnectionFactory;
import modelo.Aluno;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Aluno aluno) {
        String sql = "INSERT INTO aluno (cpf, nome, data_nascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
            stmt.setDouble(4, aluno.getPeso());
            stmt.setDouble(5, aluno.getAltura());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}