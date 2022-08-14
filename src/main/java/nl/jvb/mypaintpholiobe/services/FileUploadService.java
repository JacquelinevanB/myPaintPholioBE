package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.repositories.FileUploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileUploadService {

    @Value("/Users/jacqu/IdeaProjects/myPaintPholioBE/uploads")
    private Path fileStoragePath;
    private final String fileStorageLocation;

    private final FileUploadRepository fileUploadRepository;

    public FileUploadService(@Value("/Users/jacqu/IdeaProjects/myPaintPholioBE/uploads")
                             String fileStorageLocation,
                             FileUploadRepository fileUploadRepository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
        this.fileStorageLocation = fileStorageLocation;
        this.fileUploadRepository = fileUploadRepository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Probleem met creëren van de file directory.", e);
        }
    }

    public String storeFile(MultipartFile file, String url) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Probleem met opslaan van het bestand.", e);
        }
        fileUploadRepository.save(new FileUploadResponse(fileName, file.getContentType(), url));
        return fileName;
    }

    public Resource downloadFile(String fileName) {
        Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Probleem met lezen van het bestand.", e);
        }
        if(resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Het bestand bestaat niet of kan niet gelezen worden.");
        }
    }
}
