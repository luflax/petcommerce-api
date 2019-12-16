package com.petcommerce.petcommerce.cliente;

import com.petcommerce.petcommerce.endereco.Endereco;
import com.petcommerce.petcommerce.telefone.Telefone;
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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String primeiroNome;

    public String ultimoNome;

    public String cpf;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    public List<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    public List<Telefone> telefones;

    public Character sexo;

    public LocalDate dataNascimento;

    public String email;

    public String senhaHash;
}
