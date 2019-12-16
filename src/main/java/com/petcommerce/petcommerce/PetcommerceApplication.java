package com.petcommerce.petcommerce;

import com.petcommerce.petcommerce.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class PetcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetcommerceApplication.class, args);
    }

}
