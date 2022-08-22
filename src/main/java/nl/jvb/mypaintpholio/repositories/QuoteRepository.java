package nl.jvb.mypaintpholio.repositories;

import nl.jvb.mypaintpholio.domain.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
