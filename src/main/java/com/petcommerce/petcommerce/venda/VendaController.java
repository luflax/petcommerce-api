package com.petcommerce.petcommerce.venda;

import com.petcommerce.petcommerce.produto.ProdutoService;
import com.petcommerce.petcommerce.vendaProduto.VendaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value="/venda", produces = {"application/json"})
public class VendaController {

    private final VendaService vendaService;
    private final VendaProdutoService vendaProdutoService;
    private final ProdutoService produtoService;

    @Autowired
    public VendaController(VendaService vendaService, VendaProdutoService vendaProdutoService,
                           ProdutoService produtoService){
        this.vendaService = vendaService;
        this.vendaProdutoService = vendaProdutoService;
        this.produtoService = produtoService;
    }

    @PostMapping
    public Venda save(@RequestBody Venda venda){
        Venda novaVenda = vendaService.save(venda.setDataPedido(LocalDate.now()));
        venda.produtos.forEach(produto -> {
            if(produtoService.buy(produto.getProduto().getId(), produto.getQuantidade()) > 0)
            {
                produto.setVenda(Venda.builder().id(novaVenda.getId()).build());
                produto.setPreco(produtoService.findById(produto.getProduto().getId()).priceWithDiscount());
                vendaProdutoService.save(produto);
            }

        });
        return novaVenda;
    }
}
