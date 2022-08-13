package nl.jvb.mypaintpholiobe.repositories;

import nl.jvb.mypaintpholiobe.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
