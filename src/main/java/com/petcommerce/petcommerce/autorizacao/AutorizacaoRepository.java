package com.petcommerce.petcommerce.autorizacao;

import com.petcommerce.petcommerce.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacaoRepository extends JpaRepository<Cliente, Long> {

    public Cliente findByEmail(String email);
}
