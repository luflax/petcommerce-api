package com.petcommerce.petcommerce.venda;

import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.frete.FreteDto;
import com.petcommerce.petcommerce.frete.FreteService;
import com.petcommerce.petcommerce.produto.Produto;
import com.petcommerce.petcommerce.produto.ProdutoService;
import com.petcommerce.petcommerce.vendaProduto.VendaProduto;
import com.petcommerce.petcommerce.vendaProduto.VendaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final FreteService freteService;
    private final ProdutoService produtoService;
    private final VendaProdutoService vendaProdutoService;

    @Autowired
    public VendaService(VendaRepository vendaRepository, FreteService freteService,
                        ProdutoService produtoService, VendaProdutoService vendaProdutoService){
        this.vendaRepository = vendaRepository;
        this.freteService = freteService;
        this.produtoService = produtoService;
        this.vendaProdutoService = vendaProdutoService;
    }

    public Venda save(Venda venda){
        venda.setDataPedido(LocalDate.now());

        Double pesoTotalProdutos = venda.produtos.stream().map(VendaProduto::getProduto).collect(Collectors.toList())
                .stream().map(Produto::getWeight)
                .reduce(0.0, Double::sum);
        FreteDto frete = freteService.getShipInfo(venda.getEndereco().getCep(), pesoTotalProdutos, venda.getTipoFrete());
        venda.setValorFrete(Double.parseDouble(frete.getValor().replace(",", ".")));

        Venda novaVenda = vendaRepository.save(venda);
        venda.produtos.forEach(vendaProduto -> {
            Produto produto = vendaProduto.getProduto();
            if(produtoService.buy(produto.getId(), vendaProduto.getQuantidade()) > 0)
            {
                vendaProduto.setVenda(Venda.builder().id(novaVenda.getId()).build());
                vendaProduto.setPreco(produtoService.findById(produto.getId()).priceWithDiscount());
                vendaProdutoService.save(vendaProduto);
            }
        });
        return novaVenda;
    }
}
