package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
