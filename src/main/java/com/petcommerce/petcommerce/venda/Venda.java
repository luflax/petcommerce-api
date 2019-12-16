package com.petcommerce.petcommerce.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petcommerce.petcommerce.cliente.Cliente;
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

    @OneToMany(mappedBy = "venda")
    List<VendaProduto> produtos;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataPedido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataEntrega;

    private String status;

    public Double valorTotal(){
        return produtos.stream().map(VendaProduto::getPreco).reduce(0.0, (a, b) -> a + b);
    }

    public Venda setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }
}
