package com.petcommerce.petcommerce.cadastroCliente;

import com.petcommerce.petcommerce.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    private final CadastroClienteRepository cadastroClienteRepository;

    @Autowired
    public CadastroClienteService(CadastroClienteRepository cadastroClienteRepository) {
        this.cadastroClienteRepository = cadastroClienteRepository;
    }

    public Cliente cadastrar(Cliente cliente){
        cliente.getEnderecos().forEach(endereco -> {
            endereco.setCliente(cliente);
        });
        cliente.getTelefones().forEach(telefone -> {
            telefone.setCliente(cliente);
        });
        return cadastroClienteRepository.save(cliente);
    }
}
