package com.petcommerce.petcommerce.fotoProduto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FotoProdutoDto {

    private String path;

    private Long produtoId;
}
