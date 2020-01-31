package com.petcommerce.petcommerce.frete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/frete", produces = {"application/json"})
public class FreteController {

    private final FreteService freteService;

    @Autowired
    public FreteController(FreteService freteService){
        this.freteService = freteService;
    }

    @GetMapping("/calcular")
    public List<FreteDto> calcularFrete(@RequestParam String cepDestino, Double peso){
        return Arrays.asList(
                freteService.getShipInfo(cepDestino, peso, FreteTipo.SEDEX),
                freteService.getShipInfo(cepDestino, peso, FreteTipo.PAC));
    }
}
