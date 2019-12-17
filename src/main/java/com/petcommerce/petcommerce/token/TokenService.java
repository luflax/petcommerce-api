package com.petcommerce.petcommerce.token;

import com.petcommerce.petcommerce.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class TokenService {
    private static final Long tempoExpiracao = 24 * 60 * 60 * 1000L;
    private String chave = "+MbQeThWmZq4t7w!z%C*F-J@NcRfUjXn2r5u8x/A?D(G+KbPdSgVkYp3s6v9y$B&";

    public String gerarToken(Usuario usuario){
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(usuario.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
                .signWith(SignatureAlgorithm.HS256, chave)
                .compact();

    }

    public Claims decodificarToken(String token){
        return Jwts.parser()
                .setSigningKey(chave)
                .parseClaimsJws(token)
                .getBody();
    }
}
