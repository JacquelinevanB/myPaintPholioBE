package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.Update;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpdateRepository extends JpaRepository<Update, Long> {

    List<Update> findAllUpdatesByProjectId(Long projectId);
}
