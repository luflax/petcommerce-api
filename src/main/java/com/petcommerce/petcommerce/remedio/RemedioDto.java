package com.petcommerce.petcommerce.remedio;

import com.petcommerce.petcommerce.produto.ProdutoDto;
import lombok.Data;

@Data
public class RemedioDto extends ProdutoDto {
    private String laboratorio;
}
