// src/dao/HistoricoPesoDAO.java
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import modelo.HistoricoPeso;

public class HistoricoPesoDAO {
    private Connection connection;

    public HistoricoPesoDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void inserir(HistoricoPeso historicoPeso) {
        String sql = "INSERT INTO historico_peso (cpf_aluno, data, peso) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, historicoPeso.getCpfAluno());
            stmt.setDate(2, new java.sql.Date(historicoPeso.getData().getTime()));
            stmt.setDouble(3, historicoPeso.getPeso());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM historico_peso WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(HistoricoPeso historicoPeso) {
        String sql = "UPDATE historico_peso SET cpf_aluno = ?, data = ?, peso = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, historicoPeso.getCpfAluno());
            stmt.setDate(2, new java.sql.Date(historicoPeso.getData().getTime()));
            stmt.setDouble(3, historicoPeso.getPeso());
            stmt.setInt(4, historicoPeso.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public HistoricoPeso consultar(int id) {
        String sql = "SELECT hp.*, a.nome FROM historico_peso hp INNER JOIN aluno a ON hp.cpf_aluno = a.cpf WHERE hp.id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HistoricoPeso historicoPeso = new HistoricoPeso(
                        rs.getInt("id"),
                        rs.getString("cpf_aluno"),
                        rs.getDate("data"),
                        rs.getDouble("peso"));

                historicoPeso.setNomeAluno(rs.getString("nome")); // Setar o nome do aluno

                rs.close();
                stmt.close();
                return historicoPeso;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<HistoricoPeso> consultarPorCpf(String cpfAluno) {
        List<HistoricoPeso> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico_peso WHERE cpf_aluno = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpfAluno);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistoricoPeso historicoPeso = new HistoricoPeso(
                        rs.getInt("id"),
                        rs.getString("cpf_aluno"),
                        rs.getDate("data"),
                        rs.getDouble("peso"));
                historicos.add(historicoPeso);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }

    public List<HistoricoPeso> consultarTodos() {
        List<HistoricoPeso> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico_peso";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistoricoPeso historicoPeso = new HistoricoPeso(
                        rs.getInt("id"),
                        rs.getString("cpf_aluno"),
                        rs.getDate("data"),
                        rs.getDouble("peso"));
                historicos.add(historicoPeso);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historicos;
    }
}
