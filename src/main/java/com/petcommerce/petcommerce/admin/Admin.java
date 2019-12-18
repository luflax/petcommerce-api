package com.petcommerce.petcommerce.admin;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.petcommerce.petcommerce.endereco.Endereco;
import com.petcommerce.petcommerce.telefone.Telefone;
import com.petcommerce.petcommerce.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@Builder
public class Admin extends Usuario {

}
