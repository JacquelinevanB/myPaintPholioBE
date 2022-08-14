package nl.jvb.mypaintpholiobe.repositories;

import nl.jvb.mypaintpholiobe.domain.entities.CommentElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentElementRepository extends JpaRepository<CommentElement, Long> {
    List<CommentElement> findAllCommentsByArtProjectId(Long projectId);

}
