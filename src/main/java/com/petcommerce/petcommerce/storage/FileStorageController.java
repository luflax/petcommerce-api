package com.petcommerce.petcommerce.storage;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/arquivo", produces = {"application/json"})
public class FileStorageController {

    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping(value = "/imagem/{imagem}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<Resource> find(@PathVariable("imagem") String imagem){
        return new ResponseEntity<>(fileStorageService.loadFileAsResource(imagem), HttpStatus.OK);
    }
}
