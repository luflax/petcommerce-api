package com.petcommerce.petcommerce.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto save(ProdutoDto produto){
        return produtoRepository.save(new Produto(produto));
    }

    public Integer buy(Long produtoId, Integer quantity){
        return produtoRepository.reduceProductQuantity(produtoId, quantity);
    }
}
