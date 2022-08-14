package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.CommentElementDto;
import nl.jvb.mypaintpholiobe.domain.entities.CommentElement;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.ArtProjectRepository;
import nl.jvb.mypaintpholiobe.repositories.CommentElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentElementService {

    private final CommentElementRepository commentElementRepository;
    private final ArtProjectRepository artProjectRepository;
    private final ArtProjectService artProjectService;

    @Autowired
    public CommentElementService(
            CommentElementRepository commentElementRepository,
            ArtProjectRepository artProjectRepository,
            ArtProjectService artProjectService) {
        this.commentElementRepository = commentElementRepository;
        this.artProjectRepository = artProjectRepository;
        this.artProjectService = artProjectService;
    }

    public List<CommentElementDto> getAllComments() {
        List<CommentElement> commentElementList = commentElementRepository.findAll();
        return transferCommentListToDtoList(commentElementList);
    }

    public List<CommentElementDto> getAllCommentsByProject(Long projectId) {
        List<CommentElement> commentElementList = commentElementRepository.findAllCommentsByArtProjectId(projectId);
        return transferCommentListToDtoList(commentElementList);
    }

    public List<CommentElementDto> transferCommentListToDtoList(List<CommentElement> commentElements){
        List<CommentElementDto> commentElementDtoList = new ArrayList<>();

        for(CommentElement commentElement : commentElements) {
            CommentElementDto dto = commentToDto(commentElement);
            if(commentElement.getArtProject() != null){
                dto.setArtProjectDto(artProjectService.artProjectToDto(commentElement.getArtProject()));
            }
            commentElementDtoList.add(dto);
        }
        return commentElementDtoList;
    }

    public CommentElementDto getCommentById(Long id) {
        if (commentElementRepository.findById(id).isPresent()) {
            CommentElement comment = commentElementRepository.findById(id).get();
            CommentElementDto dto = commentToDto(comment);
            if(comment.getArtProject() != null) {
                dto.setArtProjectDto(artProjectService.artProjectToDto(comment.getArtProject()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public CommentElementDto createComment(CommentElementDto commentElementDto) {
        CommentElement commentElement = dtoToComment(commentElementDto);
        var project = artProjectRepository.findById(commentElement.getProjectId());
        commentElement.setArtProject(project.get());
        commentElementRepository.save(commentElement);
        CommentElementDto dto = commentToDto(commentElement);
        dto.setArtProjectDto(artProjectService.artProjectToDto(commentElement.getArtProject()));
        return dto;
    }

    public CommentElementDto updateComment(Long id, CommentElementDto commentElementDto) {
        if (commentElementRepository.findById(id).isPresent()) {
            CommentElement oldInfo = commentElementRepository.findById(id).get();
            CommentElement newInfo = dtoToComment(commentElementDto);
            newInfo.setId(oldInfo.getId());
            commentElementRepository.save(newInfo);
            return commentToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public void deleteCommentById(@RequestBody Long id) {
        if (commentElementRepository.findById(id).isPresent()) {
            commentElementRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public CommentElement dtoToComment(CommentElementDto dto) {
        CommentElement commentElement = new CommentElement();

        commentElement.setDateOfComment(dto.getDateOfComment());
        commentElement.setCommentText(dto.getCommentText());
        commentElement.setAuthor(dto.getAuthor());
        commentElement.setProjectId(dto.getProjectId());

        return commentElement;
    }

    public CommentElementDto commentToDto(CommentElement commentElement) {
        CommentElementDto dto = new CommentElementDto();

        dto.setId(commentElement.getId());
        dto.setDateOfComment(commentElement.getDateOfComment());
        dto.setCommentText(commentElement.getCommentText());
        dto.setAuthor(commentElement.getAuthor());
        dto.setProjectId(commentElement.getProjectId());

        return dto;
    }
}
