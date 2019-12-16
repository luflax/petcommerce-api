package com.petcommerce.petcommerce.fotoProduto;

import com.petcommerce.petcommerce.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "fotoproduto", produces = {"application/json"})
public class FotoProdutoController {

    @Autowired
    private FotoProdutoService fotoProdutoService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/{produtoId}")
    public FotoProduto save(@PathVariable("produtoId") Long produtoId, @RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        return fotoProdutoService.save(
                FotoProdutoDto.builder().path(fileName).produtoId(produtoId).build()
        );
    }

    @GetMapping(value = "/{foto}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> find(@PathVariable("foto") String fotoName){
        return new ResponseEntity<Resource>(fileStorageService.loadFileAsResource(fotoName), HttpStatus.OK);
    }

}
