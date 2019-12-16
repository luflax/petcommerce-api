package com.petcommerce.petcommerce.produto;

import com.petcommerce.petcommerce.remedio.RemedioDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Produto(ProdutoDto produto){
        setName(produto.getName());
        setBrand(produto.getBrand());
        setDescription(produto.getDescription());
        setPrice(produto.getPrice());
        setId(produto.getId());
        setDiscount(produto.getDiscount());
        setQuantity(produto.getQuantity());
    }

    public Double priceWithDiscount(){
        return discount > 0 ?
            price - (price * discount / 100) : price;
    }
}
