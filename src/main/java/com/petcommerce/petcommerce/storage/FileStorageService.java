package com.petcommerce.petcommerce.storage;

import com.petcommerce.petcommerce.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String storeFile(MultipartFile file){
        String fileName = String.format("%s.%s",
                UUID.randomUUID().toString(),
                StringUtils.getFilenameExtension(file.getOriginalFilename()));

        try{
            Files.copy(file.getInputStream(), fileStorageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (Exception e){
            //TODO: Create file error exception
            e.printStackTrace();
            return "";
        }
    }

    public Resource loadFileAsResource(String filename){
        try{
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists())
                return resource;
            else{
                throw new Exception();
            }
        }catch (Exception e){
            //TODO: Create file error exception
            e.printStackTrace();
            return null;
        }
    }

}
