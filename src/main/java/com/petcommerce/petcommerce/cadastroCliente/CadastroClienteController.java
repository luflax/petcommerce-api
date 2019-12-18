package com.petcommerce.petcommerce.cadastroCliente;

import com.petcommerce.petcommerce.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "cadastrocliente", produces = {"application/json"})
public class CadastroClienteController {

    private final CadastroClienteService cadastroClienteService;
    @Autowired
    public CadastroClienteController(CadastroClienteService cadastroClienteService){
        this.cadastroClienteService = cadastroClienteService;
    }

    @PostMapping("/cadastrar")
    public Cliente cadastrar(@RequestBody Cliente cliente){
        return cadastroClienteService.cadastrar(cliente);
    }
}
