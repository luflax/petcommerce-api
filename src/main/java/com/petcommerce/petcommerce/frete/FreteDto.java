package com.petcommerce.petcommerce.frete;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreteDto {
    @SerializedName("Valor")
    private String valor;
    @SerializedName("PrazoEntrega")
    private Integer prazo;
    @SerializedName("Codigo")
    private FreteTipo tipo;
    @SerializedName("obsFim")
    private String observacao;
}
