package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.CommentElementDto;
import nl.jvb.mypaintpholiobe.domain.entities.CommentElement;
import nl.jvb.mypaintpholiobe.services.CommentElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentElementController {

    private final CommentElementService commentElementService;

    @Autowired
    public CommentElementController(CommentElementService commentElementService) {
        this.commentElementService = commentElementService;
    }

//    @GetMapping
//    public ResponseEntity<List<CommentElementDto>> getAllComments(
//            @RequestParam(value = "projectId", required = false) Optional<Long> projectId) {
//        List<CommentElementDto> dtos;
//
//        if (projectId.isEmpty()) {
//            dtos = commentElementService.getAllComments();
//        } else {
//            dtos = commentElementService.getAllCommentsByProject(projectId.get());
//        }
//        return ResponseEntity.ok().body(dtos);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentElementDto> getCommentById(@PathVariable("id") Long id) {
        CommentElementDto oneComment = commentElementService.getCommentById(id);
        return ResponseEntity.ok().body(oneComment);
    }

    @PostMapping
    public ResponseEntity<CommentElementDto> createComment(@RequestBody CommentElementDto dto) {
        final CommentElementDto newComment = commentElementService.createComment(dto);
        final URI location = URI.create("/comments/" + newComment.getId());
        return ResponseEntity.created(location).body(newComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable("id") Long id,
                                                @RequestBody CommentElementDto commentElementDto) {
        CommentElementDto comment = commentElementService.updateComment(id, commentElementDto);
        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentElement> deleteComment(@PathVariable("id") Long id) {
        commentElementService.deleteCommentById(id);
        return ResponseEntity.noContent().build();
    }
}
