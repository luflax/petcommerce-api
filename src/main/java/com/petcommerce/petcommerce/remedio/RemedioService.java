package com.petcommerce.petcommerce.remedio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemedioService {

    @Autowired
    private RemedioRepository remedioRepository;

    public List<Remedio> findAll(){
        return remedioRepository.findAll();
    }

    public Remedio save(RemedioDto remedio){
        return remedioRepository.save(new Remedio(remedio));
    }
}
