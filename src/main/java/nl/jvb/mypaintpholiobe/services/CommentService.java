package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.CommentDto;
import nl.jvb.mypaintpholiobe.domain.entities.Comment;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(
            CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto getCommentById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            Comment comment = commentRepository.findById(id).get();
            return commentToDto(comment);
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = commentRepository.save(dtoToComment(commentDto));
        return commentToDto(comment);
    }

    public CommentDto updateComment(Long id, CommentDto commentDto) {
        if (commentRepository.findById(id).isPresent()) {
            Comment oldInfo = commentRepository.findById(id).get();
            Comment newInfo = dtoToComment(commentDto);
            newInfo.setId(oldInfo.getId());
            commentRepository.save(newInfo);
            return commentToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public void deleteCommentById(@RequestBody Long id) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public Comment dtoToComment(CommentDto dto) {
        Comment comment = new Comment();

        comment.setDateOfComment(dto.getDateOfComment());
        comment.setCommentText(dto.getCommentText());
        comment.setAuthor(dto.getAuthor());

        return comment;
    }

    public CommentDto commentToDto(Comment comment) {
        CommentDto dto = new CommentDto();

        dto.setId(comment.getId());
        dto.setDateOfComment(comment.getDateOfComment());
        dto.setCommentText(comment.getCommentText());
        dto.setAuthor(comment.getAuthor());

        return dto;
    }
}
