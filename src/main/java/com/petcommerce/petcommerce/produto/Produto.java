package com.petcommerce.petcommerce.produto;

import com.petcommerce.petcommerce.produtoCategoria.ProdutoCategoria;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private String brand;

    private Double discount;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoriaId")
    private ProdutoCategoria category;

    public Produto(ProdutoDto produto){
        setName(produto.getName());
        setBrand(produto.getBrand());
        setDescription(produto.getDescription());
        setPrice(produto.getPrice());
        setId(produto.getId());
        setDiscount(produto.getDiscount());
        setQuantity(produto.getQuantity());
        setCategory(produto.getCategory());
    }

    public Double priceWithDiscount(){
        return discount > 0 ?
            price - (price * discount / 100) : price;
    }
}
