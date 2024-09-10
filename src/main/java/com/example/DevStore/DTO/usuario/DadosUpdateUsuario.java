package com.example.DevStore.DTO.usuario;

public record DadosUpdateUsuario(
        Long id,
        String nome,
        String email,
        String senha,
        String telefone,
        String cpf,
        String cep
) {
}
