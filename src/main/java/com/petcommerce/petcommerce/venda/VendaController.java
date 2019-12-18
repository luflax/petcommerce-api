package com.petcommerce.petcommerce.venda;

import com.petcommerce.petcommerce.autorizacao.AutorizacaoService;
import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.produto.ProdutoService;
import com.petcommerce.petcommerce.vendaProduto.VendaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value="/venda", produces = {"application/json"})
public class VendaController {

    private final VendaService vendaService;
    private final VendaProdutoService vendaProdutoService;
    private final ProdutoService produtoService;
    private final AutorizacaoService autorizacaoService;

    @Autowired
    public VendaController(VendaService vendaService, VendaProdutoService vendaProdutoService,
                           ProdutoService produtoService, AutorizacaoService autorizacaoService){
        this.vendaService = vendaService;
        this.vendaProdutoService = vendaProdutoService;
        this.produtoService = produtoService;
        this.autorizacaoService = autorizacaoService;
    }

    @PostMapping
    public ResponseEntity<Venda> save(@RequestBody Venda venda, @RequestHeader String Authorization){

        String idCliente = autorizacaoService.indetificarToken(Authorization);
        if(StringUtils.isEmpty(idCliente))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        try {
            venda.setCliente(new Cliente(Long.parseLong(idCliente)));
            Venda novaVenda = vendaService.save(venda.setDataPedido(LocalDate.now()));
            venda.produtos.forEach(produto -> {
                if(produtoService.buy(produto.getProduto().getId(), produto.getQuantidade()) > 0)
                {
                    produto.setVenda(Venda.builder().id(novaVenda.getId()).build());
                    produto.setPreco(produtoService.findById(produto.getProduto().getId()).priceWithDiscount());
                    vendaProdutoService.save(produto);
                }
            });
            return new ResponseEntity<>(novaVenda, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
