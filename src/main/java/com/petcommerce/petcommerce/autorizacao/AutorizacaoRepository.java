package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacaoRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
