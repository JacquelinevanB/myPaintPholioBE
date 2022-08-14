package nl.jvb.mypaintpholiobe.repositories;

import nl.jvb.mypaintpholiobe.domain.entities.PhotoElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoElementRepository extends JpaRepository<PhotoElement, Long> {
    List<PhotoElement> findAllPhotosByArtProjectId(Long projectId);

}
