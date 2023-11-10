package org.example;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long Id() {
        return id;
    }

    public String Nome() {
        return nome;
    }

    public String Email() {
        return email;
    }

    public String Senha() {
        return senha;
    }
}
