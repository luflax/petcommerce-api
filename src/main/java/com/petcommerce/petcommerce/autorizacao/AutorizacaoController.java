package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.cliente.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "autorizacao", produces = {"application/json"})
public class AutorizacaoController {

    private final AutorizacaoService autorizacaoService;
    public AutorizacaoController(AutorizacaoService autorizacaoService){
        this.autorizacaoService = autorizacaoService;
    }

    @PostMapping("/cadastrar")
    public Cliente cadastrar(@RequestBody Cliente cliente){
        return autorizacaoService.cadastrar(cliente);
    }

    @PostMapping("/logar")
    public ResponseEntity<Cliente> logar(@RequestBody Cliente cliente, @RequestHeader String Authorization){
        Cliente clienteLogado = autorizacaoService.logar(cliente, Authorization);

        return clienteLogado != null ?
                new ResponseEntity<>(clienteLogado, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

}
