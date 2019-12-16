package com.petcommerce.petcommerce.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String brand;

    private List<String> photos;

    private Double discount;

    private Integer quantity;

    public ProdutoDto(Produto produto){
        setName(produto.getName());
        setBrand(produto.getBrand());
        setDescription(produto.getDescription());
        setPrice(produto.getPrice());
        setId(produto.getId());
        setDiscount(produto.getDiscount());
        setQuantity(produto.getQuantity());
    }

    public ProdutoDto setPhotos(List<String> photos) {
        this.photos = photos;
        return this;
    }
}
