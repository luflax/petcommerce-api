package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.token.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class AutorizacaoService {

    private final AutorizacaoRepository autorizacaoRepository;
    private final TokenService tokenService;

    @Autowired
    public AutorizacaoService(AutorizacaoRepository autorizacaoRepository, TokenService tokenService){
        this.autorizacaoRepository = autorizacaoRepository;
        this.tokenService = tokenService;
    }

    public Cliente cadastrar(Cliente cliente){
        cliente.getEnderecos().forEach(endereco -> {
            endereco.setCliente(cliente);
        });
        cliente.getTelefones().forEach(telefone -> {
            telefone.setCliente(cliente);
        });
        return autorizacaoRepository.save(cliente);
    }

    public Cliente logar(Cliente cliente, String token){
        if(!token.isEmpty() && validarToken(token, cliente.getEmail())){
            return autorizacaoRepository.findByEmail(cliente.getEmail());
        }else{
            Cliente usuario = autorizacaoRepository.findByEmail(cliente.getEmail());
            if(usuario != null && usuario.getSenhaHash().equals(cliente.getSenhaHash())){
                usuario.setToken(tokenService.gerarToken(cliente));
                return autorizacaoRepository.save(usuario);
            }
            return null;
        }
    }

    public Boolean validarToken(String tokenHeader, String subject){
        String token = tokenHeader.replace("Bearer ", "");
        Claims claims = tokenService.decodificarToken(token);
        return claims.getSubject().equals(subject) && claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }
}
