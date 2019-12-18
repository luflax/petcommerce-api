package com.petcommerce.petcommerce.produtoCategoria;

import com.petcommerce.petcommerce.admin.AdminService;
import com.petcommerce.petcommerce.autorizacao.AutorizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/produtocategoria", produces = {"application/json"})
public class ProdutoCategoriaController {

    private final ProdutoCategoriaService produtoCategoriaService;
    private final AutorizacaoService autorizacaoService;
    private final AdminService adminService;

    @Autowired
    public ProdutoCategoriaController(ProdutoCategoriaService produtoCategoriaService,
                                      AutorizacaoService autorizacaoService, AdminService adminService) {
        this.produtoCategoriaService = produtoCategoriaService;
        this.autorizacaoService = autorizacaoService;
        this.adminService = adminService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoCategoria> save(@RequestBody ProdutoCategoria produtoCategoria, @RequestParam String Authorization){
        String idCliente = autorizacaoService.indetificarToken(Authorization);
        if(StringUtils.isEmpty(idCliente) ||
                (!StringUtils.isEmpty(idCliente) && !adminService.usuarioEhAdmin(Long.parseLong(idCliente)))
        )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(produtoCategoriaService.save(produtoCategoria), HttpStatus.OK);
    }
}
