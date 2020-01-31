package com.petcommerce.petcommerce.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.endereco.Endereco;
import com.petcommerce.petcommerce.frete.FreteTipo;
import com.petcommerce.petcommerce.vendaProduto.VendaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "venda")
    public List<VendaProduto> produtos;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataPedido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataEntrega;

    @ManyToOne
    @JoinColumn(name = "enderecoId", nullable = false)
    private Endereco endereco;

    private String status;

    private Double valorFrete;

    private FreteTipo tipoFrete;

    public Double valorTotal(){
        return produtos.stream().map(VendaProduto::getPreco).reduce(0.0, (a, b) -> a + b);
    }

    public Venda setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }
}
