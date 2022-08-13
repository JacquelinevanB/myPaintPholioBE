package nl.jvb.mypaintpholiobe.repositories;

import nl.jvb.mypaintpholiobe.domain.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
