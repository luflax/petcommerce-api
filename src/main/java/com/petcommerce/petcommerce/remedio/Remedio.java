package com.petcommerce.petcommerce.remedio;

import com.petcommerce.petcommerce.produto.Produto;
import com.petcommerce.petcommerce.produto.ProdutoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remedio extends Produto {
    public Remedio(RemedioDto remedio){
        super(remedio);
        this.laboratorio = remedio.getLaboratorio();
    }
    private String laboratorio;
}
