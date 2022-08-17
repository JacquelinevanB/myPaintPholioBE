package nl.jvb.mypaintpholiobe.repositories;

import nl.jvb.mypaintpholiobe.domain.entities.QuoteText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteTextRepository extends JpaRepository<QuoteText, Long> {

}
