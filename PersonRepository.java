package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.Person;
import nl.jvb.mypaintpholio.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {

}
