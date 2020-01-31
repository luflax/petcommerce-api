package com.petcommerce.petcommerce.venda;

import com.petcommerce.petcommerce.autorizacao.AutorizacaoService;
import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.frete.FreteDto;
import com.petcommerce.petcommerce.frete.FreteService;
import com.petcommerce.petcommerce.produto.Produto;
import com.petcommerce.petcommerce.produto.ProdutoService;
import com.petcommerce.petcommerce.vendaProduto.VendaProduto;
import com.petcommerce.petcommerce.vendaProduto.VendaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/venda", produces = {"application/json"})
public class VendaController {

    private final VendaService vendaService;
    private final VendaProdutoService vendaProdutoService;
    private final ProdutoService produtoService;
    private final AutorizacaoService autorizacaoService;
    private final FreteService freteService;

    @Autowired
    public VendaController(VendaService vendaService, VendaProdutoService vendaProdutoService,
                           ProdutoService produtoService, AutorizacaoService autorizacaoService,
                           FreteService freteService){
        this.vendaService = vendaService;
        this.vendaProdutoService = vendaProdutoService;
        this.produtoService = produtoService;
        this.autorizacaoService = autorizacaoService;
        this.freteService = freteService;
    }

    @PostMapping
    public ResponseEntity<Venda> save(@RequestBody Venda venda, @RequestHeader String Authorization){

        String idCliente = autorizacaoService.indetificarToken(Authorization);
        if(StringUtils.isEmpty(idCliente))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        try {
            venda.setCliente(new Cliente(Long.parseLong(idCliente)));
            return new ResponseEntity<>(vendaService.save(venda), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
