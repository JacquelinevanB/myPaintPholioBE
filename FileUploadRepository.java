package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUploadResponse, String> {

    Optional<FileUploadResponse> findByFileName(String fileName);
}
