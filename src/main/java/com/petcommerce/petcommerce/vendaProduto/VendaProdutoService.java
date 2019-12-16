package com.petcommerce.petcommerce.vendaProduto;

import com.petcommerce.petcommerce.venda.Venda;
import org.springframework.stereotype.Service;

@Service
public class VendaProdutoService {

    private final VendaProdutoRepository vendaProdutoRepository;

    public VendaProdutoService(VendaProdutoRepository vendaProdutoRepository){
        this.vendaProdutoRepository = vendaProdutoRepository;
    }

    public VendaProduto save(VendaProduto produto){
        return vendaProdutoRepository.save(produto);
    }
}
