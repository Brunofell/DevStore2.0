package com.example.DevStore.DTO.pedido;

import java.time.LocalDateTime;
import java.util.List;

public record DadosCadastroPedido(
        Long id,
        Long id_user,
        LocalDateTime data,
        Long total,
        List<ItemPedido> produtos // Adicionando lista de produtos no pedido
) {}
