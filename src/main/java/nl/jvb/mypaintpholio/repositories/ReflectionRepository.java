package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReflectionRepository extends JpaRepository<Reflection, Long> {

    List<Reflection> findAllReflectionsByProjectId(Long projectId);
}
