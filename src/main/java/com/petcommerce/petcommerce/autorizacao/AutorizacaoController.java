package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.cliente.Cliente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "autorizacao", produces = {"application/json"})
public class AutorizacaoController {

    @PostMapping("/cadastrar")
    public Cliente cadastrar(@RequestBody Cliente cliente){

    }

}
