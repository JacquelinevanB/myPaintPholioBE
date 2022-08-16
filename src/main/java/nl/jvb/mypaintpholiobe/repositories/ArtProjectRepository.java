package nl.jvb.mypaintpholiobe.repositories;

import nl.jvb.mypaintpholiobe.domain.entities.ArtProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtProjectRepository extends JpaRepository<ArtProject, Long> {

    List<ArtProject> findArtProjectsByUserIsContaining(String username);
}
