package com.petcommerce.petcommerce.produtoCategoria;

import com.petcommerce.petcommerce.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoCategoriaService {

    private final ProdutoCategoriaRepository produtoCategoriaRepository;

    @Autowired
    public ProdutoCategoriaService(ProdutoCategoriaRepository produtoCategoriaRepository) {
        this.produtoCategoriaRepository = produtoCategoriaRepository;
    }

    public ProdutoCategoria save(ProdutoCategoria produtoCategoria) {
        return produtoCategoriaRepository.save(produtoCategoria);
    }
}
