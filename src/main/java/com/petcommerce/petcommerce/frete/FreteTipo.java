package com.petcommerce.petcommerce.frete;

import com.google.gson.annotations.SerializedName;

public enum FreteTipo {
    @SerializedName("4014")
    SEDEX(4014),
    @SerializedName("4510")
    PAC(4510);

    public Integer tipo;
    FreteTipo(Integer tipo){
        this.tipo = tipo;
    }
}
