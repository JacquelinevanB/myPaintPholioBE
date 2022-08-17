package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.ProjectUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectUpdateRepository extends JpaRepository<ProjectUpdate, Long> {

    List<ProjectUpdate> findAllUpdatesByArtProjectId(Long projectId);
}
