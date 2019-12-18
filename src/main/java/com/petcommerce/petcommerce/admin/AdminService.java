package com.petcommerce.petcommerce.admin;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Boolean usuarioEhAdmin(Long id){
        Admin admin = adminRepository.findById(id).orElse(null);
        return admin != null;
    }
}
