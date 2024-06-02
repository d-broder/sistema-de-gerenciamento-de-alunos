// src/modelo/HistoricoPeso.java
package modelo;

import java.util.Date;

public class HistoricoPeso {
    private int id;
    private String cpfAluno;
    private Date data;
    private double peso;
    private String nomeAluno;

    // Construtor padrão
    public HistoricoPeso() {
    }

    // Construtor com parâmetros
    public HistoricoPeso(int id, String cpfAluno, Date data, double peso) {
        this.id = id;
        this.cpfAluno = cpfAluno;
        this.data = data;
        this.peso = peso;
    }

    // Construtor sem o ID (para inserções onde o ID é auto incrementado)
    public HistoricoPeso(String cpfAluno, Date data, double peso) {
        this.cpfAluno = cpfAluno;
        this.data = data;
        this.peso = peso;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    @Override
    public String toString() {
        return "HistoricoPeso{" +
                "id=" + id +
                ", cpfAluno='" + cpfAluno + '\'' +
                ", data=" + data +
                ", peso=" + peso +
                '}';
    }
}
