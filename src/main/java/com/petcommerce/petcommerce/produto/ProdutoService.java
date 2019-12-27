package com.petcommerce.petcommerce.produto;

import com.petcommerce.petcommerce.fotoProduto.FotoProduto;
import com.petcommerce.petcommerce.fotoProduto.FotoProdutoService;
import com.petcommerce.petcommerce.produtoCategoria.ProdutoCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FotoProdutoService fotoProdutoService;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, FotoProdutoService fotoProdutoService) {
        this.produtoRepository = produtoRepository;
        this.fotoProdutoService = fotoProdutoService;
    }

    List<ProdutoDto> findAll(){
        return addImagesToProdutoList(produtoRepository.findAll());
    }

    List<ProdutoDto> findAllByCategory(Long idCategory){
        return addImagesToProdutoList(produtoRepository.findAllByCategory(ProdutoCategoria.builder().id(idCategory).build()));
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

    private List<ProdutoDto> addImagesToProdutoList(List<Produto> produtos){
        List<ProdutoDto> productsWithPhotos = new ArrayList<>();
        produtos.forEach(produto -> {
            productsWithPhotos.add(
                    new ProdutoDto(produto).setPhotos(
                            fotoProdutoService.listAllByProductId(produto.getId())
                                    .stream()
                                    .map(FotoProduto::getPath).collect(Collectors.toList())
                    )

            );
        });
        return productsWithPhotos;
    }
}
