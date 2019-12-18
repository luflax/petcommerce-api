package com.petcommerce.petcommerce.cadastroCliente;

import com.petcommerce.petcommerce.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroClienteRepository extends JpaRepository<Cliente, Long> {

}
