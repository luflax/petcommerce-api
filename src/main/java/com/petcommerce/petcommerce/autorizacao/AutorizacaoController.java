package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.admin.AdminService;
import com.petcommerce.petcommerce.frete.FreteService;
import com.petcommerce.petcommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "autorizacao", produces = {"application/json"})
public class AutorizacaoController {

    private final AutorizacaoService autorizacaoService;
    private final AdminService adminService;
    private final FreteService freteService;

    @Autowired
    public AutorizacaoController(AutorizacaoService autorizacaoService, AdminService adminService, FreteService freteService){
        this.autorizacaoService = autorizacaoService;
        this.adminService = adminService;
        this.freteService = freteService;
    }

    @PostMapping("/logar")
    public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario, @RequestHeader(required = false) String Authorization){
        Usuario clienteLogado = autorizacaoService.logar(usuario, Authorization);
        return clienteLogado != null ?
                new ResponseEntity<>(clienteLogado, HttpStatus.ACCEPTED) :
                new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

}
