package com.petcommerce.petcommerce.fotoProduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoProdutoRepository extends JpaRepository<FotoProduto, Long> {
    public List<FotoProduto> findAllByProdutoId(Long produtoId);
}
