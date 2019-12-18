package com.petcommerce.petcommerce.fotoProduto;

import com.petcommerce.petcommerce.admin.AdminService;
import com.petcommerce.petcommerce.autorizacao.AutorizacaoService;
import com.petcommerce.petcommerce.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "fotoproduto", produces = {"application/json"})
public class FotoProdutoController {

    private final FotoProdutoService fotoProdutoService;

    private final AutorizacaoService autorizacaoService;
    private final AdminService adminService;

    @Autowired
    public FotoProdutoController(AutorizacaoService autorizacaoService, AdminService adminService, FotoProdutoService fotoProdutoService) {
        this.autorizacaoService = autorizacaoService;
        this.adminService = adminService;
        this.fotoProdutoService = fotoProdutoService;
    }

    @PostMapping("/{produtoId}")
    public ResponseEntity<FotoProduto> save(@PathVariable("produtoId") Long produtoId, @RequestParam("file") MultipartFile file,
                            @RequestHeader String Authorization){

        String idCliente = autorizacaoService.indetificarToken(Authorization);
        if(StringUtils.isEmpty(idCliente) ||
                (!StringUtils.isEmpty(idCliente) && !adminService.usuarioEhAdmin(Long.parseLong(idCliente)))
        )
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(fotoProdutoService.save(produtoId, file), HttpStatus.OK);
    }

}
