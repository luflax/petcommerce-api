package com.petcommerce.petcommerce.telefone;

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
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ddd;

    private String numero;

    private TipoTelefone tipo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    public Cliente cliente;
}
