package com.petcommerce.petcommerce.usuario;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petcommerce.petcommerce.endereco.Endereco;
import com.petcommerce.petcommerce.telefone.Telefone;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String primeiroNome;

    private String ultimoNome;

    private LocalDate dataNascimento;

    private String email;

    private String senhaHash;

    private String token;

    public void setId(Long id){
        this.id = id;
    }
}
