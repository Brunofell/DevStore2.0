package com.example.DevStore.DTO.usuario;

public class DadosLoginUsuario {
    private String token;
    private String tipo;
    private Long id;

    public DadosLoginUsuario(String token, String usuario, Long id) {
        this.token = token;
        this.tipo = usuario;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
    public Long getId(){return id; }
}