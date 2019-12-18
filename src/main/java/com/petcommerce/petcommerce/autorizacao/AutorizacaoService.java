package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.admin.AdminService;
import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.token.TokenService;
import com.petcommerce.petcommerce.usuario.Usuario;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class AutorizacaoService {

    private final AutorizacaoRepository autorizacaoRepository;
    private final TokenService tokenService;
    private final AdminService adminService;

    @Autowired
    public AutorizacaoService(AutorizacaoRepository autorizacaoRepository, TokenService tokenService,
                              AdminService adminService){
        this.autorizacaoRepository = autorizacaoRepository;
        this.tokenService = tokenService;
        this.adminService = adminService;
    }

    public Usuario logar(Usuario usuarioALogar, String token){
        if(!StringUtils.isEmpty(token)){
            String usuarioId = indetificarToken(token);
            if(!usuarioId.isEmpty())
                return autorizacaoRepository.findById(Long.parseLong(usuarioId)).orElse(null);
        }else{
            Usuario usuario = autorizacaoRepository.findByEmail(usuarioALogar.getEmail());
            if(usuario != null && usuario.getSenhaHash().equals(usuario.getSenhaHash())){
                if(StringUtils.isEmpty(usuario.getToken()) || StringUtils.isEmpty(indetificarToken(usuario.getToken())))
                    usuario.setToken(String.format("Bearer %s", tokenService.gerarToken(usuario)));
                return autorizacaoRepository.save(usuario);
            }
        }
        return null;
    }

    public String indetificarToken(String tokenHeader){
        try{
            String token = tokenHeader.replace("Bearer ", "");
            Claims claims = tokenService.decodificarToken(token);
            return claims.getExpiration().after(new Date(System.currentTimeMillis())) ? claims.getSubject() : "";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
