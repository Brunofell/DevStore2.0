package com.example.DevStore.DTO.produto;

import com.example.DevStore.DTO.produto.DadosListagemProduto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "produto-service", url = "http://localhost:8080/produtos")  // Supondo que o serviço de produtos está em outra porta
public interface ProdutoClient {

    @GetMapping("/{id}")
    Optional<DadosListagemProduto> buscarProdutoPorId(@PathVariable("id") Long id);
}
