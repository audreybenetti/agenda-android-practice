package br.com.alura.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id;
    private String nome;

    public Aluno() {
    }

    private String telefone;
    private String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }


    @NonNull
    @Override
    public String toString() {
        return  "Aluno: " + nome + '\n' +
                "Telefone: " + telefone + '\n' +
                "E-mail: " + email + '\n';
    }

    public boolean idValido() {
        return id > 0;
    }
}
