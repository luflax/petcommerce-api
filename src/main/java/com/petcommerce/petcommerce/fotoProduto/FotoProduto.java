package com.petcommerce.petcommerce.fotoProduto;

import com.petcommerce.petcommerce.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FotoProduto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    @ManyToOne
    @JoinColumn(name="produtoId", nullable = false)
    private Produto produto;

}
