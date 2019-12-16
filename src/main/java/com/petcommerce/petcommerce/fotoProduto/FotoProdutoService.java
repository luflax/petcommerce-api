package com.petcommerce.petcommerce.fotoProduto;

import com.petcommerce.petcommerce.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FotoProdutoService {

    @Autowired
    private FotoProdutoRepository fotoprodutoRepository;

    public FotoProduto save(FotoProdutoDto fotoProduto){
        return fotoprodutoRepository.save(FotoProduto.builder()
                .path(fotoProduto.getPath())
                .produto(Produto.builder().id(fotoProduto.getProdutoId()).build())
                .build()
        );
    }

    public List<FotoProduto> listAllByProductId(Long productId){
        return fotoprodutoRepository.findAllByProdutoId(productId);
    }

}
