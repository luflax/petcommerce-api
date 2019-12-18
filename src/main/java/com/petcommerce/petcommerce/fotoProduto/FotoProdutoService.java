package com.petcommerce.petcommerce.fotoProduto;

import com.petcommerce.petcommerce.produto.Produto;
import com.petcommerce.petcommerce.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FotoProdutoService {

    private final FotoProdutoRepository fotoprodutoRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public FotoProdutoService(FotoProdutoRepository fotoprodutoRepository, FileStorageService fileStorageService) {
        this.fotoprodutoRepository = fotoprodutoRepository;
        this.fileStorageService = fileStorageService;
    }

    public FotoProduto save(Long produtoId, MultipartFile file){

        String fileName = fileStorageService.storeFile(file);

        FotoProduto fotoProduto = FotoProduto.builder().path(fileName).produto(
                Produto.builder().id(produtoId).build()).build();

        return fotoprodutoRepository.save(fotoProduto);
    }

    public List<FotoProduto> listAllByProductId(Long productId){
        return fotoprodutoRepository.findAllByProdutoId(productId);
    }

}
