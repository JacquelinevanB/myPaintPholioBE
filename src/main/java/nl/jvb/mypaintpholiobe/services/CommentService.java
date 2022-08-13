package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.CommentDto;
import nl.jvb.mypaintpholiobe.domain.entities.Comment;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.ArtProjectRepository;
import nl.jvb.mypaintpholiobe.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArtProjectRepository artProjectRepository;
    private final ArtProjectService artProjectService;

    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            ArtProjectRepository artProjectRepository,
            ArtProjectService artProjectService) {
        this.commentRepository = commentRepository;
        this.artProjectRepository = artProjectRepository;
        this.artProjectService = artProjectService;
    }

    public List<CommentDto> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        return transferCommentListToDtoList(commentList);
    }

    public List<CommentDto> getAllCommentsByProject(Long projectId) {
        List<Comment> commentList = commentRepository.findAllCommentsByArtProjectId(projectId);
        return transferCommentListToDtoList(commentList);
    }

    public List<CommentDto> transferCommentListToDtoList(List<Comment> comments){
        List<CommentDto> commentDtoList = new ArrayList<>();

        for(Comment comment : comments) {
            CommentDto dto = commentToDto(comment);
            if(comment.getArtProject() != null){
                dto.setArtProjectDto(artProjectService.artProjectToDto(comment.getArtProject()));
            }
            commentDtoList.add(dto);
        }
        return commentDtoList;
    }

    public CommentDto getCommentById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            Comment comment = commentRepository.findById(id).get();
            CommentDto dto = commentToDto(comment);
            if(comment.getArtProject() != null) {
                dto.setArtProjectDto(artProjectService.artProjectToDto(comment.getArtProject()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = dtoToComment(commentDto);
        var project = artProjectRepository.findById(comment.getProjectId());
        comment.setArtProject(project.get());
        commentRepository.save(comment);
        CommentDto dto = commentToDto(comment);
        dto.setArtProjectDto(artProjectService.artProjectToDto(comment.getArtProject()));
        return dto;
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
        comment.setProjectId(dto.getProjectId());

        return comment;
    }

    public CommentDto commentToDto(Comment comment) {
        CommentDto dto = new CommentDto();

        dto.setId(comment.getId());
        dto.setDateOfComment(comment.getDateOfComment());
        dto.setCommentText(comment.getCommentText());
        dto.setAuthor(comment.getAuthor());
        dto.setProjectId(comment.getProjectId());

        return dto;
    }
}
