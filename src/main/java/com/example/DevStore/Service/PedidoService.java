package com.example.DevStore.Service;


import com.example.DevStore.DTO.pedido.DadosCadastroPedido;
import com.example.DevStore.DTO.pedido.DadosUpdatePedido;
import com.example.DevStore.DTO.produto.DadosListagemProduto;
import com.example.DevStore.DTO.produto.ProdutoClient;
import com.example.DevStore.entity.Pedido;
import com.example.DevStore.entity.Produto;
import com.example.DevStore.rabbitMQ.RabbitMQConfig;
import com.example.DevStore.repository.PedidoRepository;
import com.example.DevStore.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoClient produtoClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public void cadastrarPedido(DadosCadastroPedido dados) {
        boolean estoqueDisponivel = true;

        for (var item : dados.produtos()) {
            var produtoResponse = produtoClient.buscarProdutoPorId(item.id_produto());
            if (produtoResponse.isEmpty()) {
                throw new RuntimeException("Produto não encontrado");
            }

            DadosListagemProduto produto = produtoResponse.get();
            if (produto.estoque() < item.quantidade()) {
                estoqueDisponivel = false;
                break;
            }
        }

        if (estoqueDisponivel) {
            for (var item : dados.produtos()) {
                Produto produto = produtoRepository.findById(item.id_produto()).get();
                produto.setEstoque(produto.getEstoque() - item.quantidade());
                produtoRepository.save(produto);
            }

            Pedido pedido = new Pedido(dados);
            pedidoRepository.save(pedido);

            // Enviar mensagem para o RabbitMQ após o pedido ser salvo
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, "Pedido cadastrado com sucesso: " + pedido.getId());
        } else {
            throw new RuntimeException("Estoque insuficiente para um ou mais produtos.");
        }
    }

    @Transactional
    public void excluir(@PathVariable Long id){
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public void atualizarPedido(@RequestBody @Valid DadosUpdatePedido dados){

        var pedido = pedidoRepository.getReferenceById(dados.id());

        pedido.atualizarInfos(dados);

        pedidoRepository.save(pedido);
    }
}