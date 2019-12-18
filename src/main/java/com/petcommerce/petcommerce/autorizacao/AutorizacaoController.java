package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.admin.AdminService;
import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "autorizacao", produces = {"application/json"})
public class AutorizacaoController {

    private final AutorizacaoService autorizacaoService;
    private final AdminService adminService;

    @Autowired
    public AutorizacaoController(AutorizacaoService autorizacaoService, AdminService adminService){
        this.autorizacaoService = autorizacaoService;
        this.adminService = adminService;
    }

    @PostMapping("/logar")
    public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario, @RequestHeader(required = false) String Authorization){
        Usuario clienteLogado = autorizacaoService.logar(usuario, Authorization);
        return clienteLogado != null ?
                new ResponseEntity<>(clienteLogado, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

}
