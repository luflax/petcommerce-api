package com.petcommerce.petcommerce.cliente;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petcommerce.petcommerce.endereco.Endereco;
import com.petcommerce.petcommerce.telefone.Telefone;
import com.petcommerce.petcommerce.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario {

    public Cliente(Long id){
        setId(id);
    }
    private String cpf;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    private String sexo;
}
