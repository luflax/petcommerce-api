package com.petcommerce.petcommerce.remedio;

import com.petcommerce.petcommerce.produto.ProdutoDto;
import com.petcommerce.petcommerce.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/remedio", produces = {"application/json"})
public class RemedioController {
    @Autowired
    private RemedioService remedioService;
    @GetMapping
    public List<Remedio> findAll(){
        return remedioService.findAll();
    }

    @PostMapping
    public Remedio save(@RequestBody RemedioDto remedio){
        return remedioService.save(remedio);
    }

}
