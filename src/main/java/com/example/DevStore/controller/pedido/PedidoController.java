package com.example.DevStore.controller.pedido;

import com.example.DevStore.DTO.pedido.DadosCadastroPedido;
import com.example.DevStore.DTO.pedido.DadosListagemPedido;
import com.example.DevStore.DTO.pedido.DadosUpdatePedido;
import com.example.DevStore.DTO.produto.DadosListagemProduto;
import com.example.DevStore.DTO.produto.ProdutoClient;
import com.example.DevStore.Service.PedidoService;
import com.example.DevStore.entity.Pedido;
import com.example.DevStore.entity.Produto;
import com.example.DevStore.repository.PedidoRepository;
import com.example.DevStore.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoClient produtoClient; // Injetando o cliente Feign
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("cadastrar")
    public void cadastrar(@RequestBody DadosCadastroPedido dados) {
        pedidoService.cadastrarPedido(dados);
    }

    @GetMapping("listar")
    public List<DadosListagemPedido> listar(){
        return repository.findAll().stream().map(DadosListagemPedido::new).toList();
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        pedidoService.excluir(id);
    }

    @PutMapping("atualizar")
    public void atualizarPedido(@RequestBody @Valid DadosUpdatePedido dados){
        pedidoService.atualizarPedido(dados);
    }

}