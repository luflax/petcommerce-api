package com.petcommerce.petcommerce.produto;

import com.petcommerce.petcommerce.admin.AdminService;
import com.petcommerce.petcommerce.autorizacao.AutorizacaoService;
import com.petcommerce.petcommerce.fotoProduto.FotoProduto;
import com.petcommerce.petcommerce.fotoProduto.FotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/produto", produces = {"application/json"})
public class ProdutoController {

    private final ProdutoService produtoService;
    private final AutorizacaoService autorizacaoService;
    private final AdminService adminService;
    private final FotoProdutoService fotoProdutoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService, FotoProdutoService fotoProdutoService, AutorizacaoService autorizacaoService, AdminService adminservice){
        this.produtoService = produtoService;
        this.fotoProdutoService = fotoProdutoService;
        this.autorizacaoService = autorizacaoService;
        this.adminService = adminservice;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll(){
        List<ProdutoDto> productsWithPhotos = new ArrayList<>();
        produtoService.findAll().forEach(produto -> {
            productsWithPhotos.add(
                    new ProdutoDto(produto).setPhotos(
                            fotoProdutoService.listAllByProductId(produto.getId())
                                    .stream()
                                    .map(FotoProduto::getPath).collect(Collectors.toList())
                    )

            );
        });
        return new ResponseEntity<>(productsWithPhotos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody ProdutoDto produto, @RequestHeader String Authorization){

        String idCliente = autorizacaoService.indetificarToken(Authorization);
        if(StringUtils.isEmpty(idCliente) ||
                (!StringUtils.isEmpty(idCliente) && !adminService.usuarioEhAdmin(Long.parseLong(idCliente)))
        )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(produtoService.save(produto), HttpStatus.OK);
    }
}
