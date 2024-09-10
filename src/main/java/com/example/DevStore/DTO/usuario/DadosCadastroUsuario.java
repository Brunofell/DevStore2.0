package com.example.DevStore.DTO.usuario;

public record DadosCadastroUsuario(
        String nome,
        String email,
        String senha,
        String telefone,
        String cpf,
        String cep,
        boolean ativo
) {
}
