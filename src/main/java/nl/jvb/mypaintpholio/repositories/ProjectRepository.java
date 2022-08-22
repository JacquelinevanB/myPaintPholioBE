package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByPerson(String username);
}
