package com.petcommerce.petcommerce.produto;

import com.petcommerce.petcommerce.fotoProduto.FotoProduto;
import com.petcommerce.petcommerce.fotoProduto.FotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/produto", produces = {"application/json"})
public class ProdutoController {

    private final ProdutoService produtoService;

    private final FotoProdutoService fotoProdutoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService, FotoProdutoService fotoProdutoService){
        this.produtoService = produtoService;
        this.fotoProdutoService = fotoProdutoService;
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
    public ResponseEntity<Produto> save(@RequestBody ProdutoDto produto){
        return new ResponseEntity<>(produtoService.save(produto), HttpStatus.OK);
    }

    @GetMapping("/{id}/comprar")
    public ResponseEntity<Produto> buy(@PathVariable("id") Long id, @RequestParam("quantity") Integer quantity){
        ResponseEntity<Produto> response;
        if(produtoService.buy(id, quantity) > 0){
            response = new ResponseEntity<>(produtoService.findById(id), HttpStatus.OK);
        }else{
            response =  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
