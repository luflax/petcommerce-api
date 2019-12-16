package com.petcommerce.petcommerce.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Transactional
    @Modifying
    @Query("update Produto p set p.quantity = p.quantity - :quantity  where p.id = :id and p.quantity >= :quantity")
    int reduceProductQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);
}
