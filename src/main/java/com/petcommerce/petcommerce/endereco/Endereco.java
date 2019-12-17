package com.petcommerce.petcommerce.endereco;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.petcommerce.petcommerce.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String logradouro;

    public Integer numero;

    public String complemento;

    public String referencia;

    public String bairro;

    public String cidade;

    public String estado;

    public String cep;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    public Cliente cliente;

}
