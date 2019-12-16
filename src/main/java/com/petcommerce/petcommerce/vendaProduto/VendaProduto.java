package com.petcommerce.petcommerce.vendaProduto;

import com.petcommerce.petcommerce.cliente.Cliente;
import com.petcommerce.petcommerce.produto.Produto;
import com.petcommerce.petcommerce.venda.Venda;
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
public class VendaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendaId", nullable = false)
    public Venda venda;

    @ManyToOne
    @JoinColumn(name = "produtoId", nullable = false)
    private Produto produto;

    private Integer quantidade;

    private Double preco;

    public VendaProduto setVenda(Venda venda) {
        this.venda = venda;
        return this;
    }
}
