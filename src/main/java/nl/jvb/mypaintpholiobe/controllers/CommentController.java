package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.CommentDto;
import nl.jvb.mypaintpholiobe.domain.entities.Comment;
import nl.jvb.mypaintpholiobe.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//    @GetMapping
//    public ResponseEntity<List<CommentDto>> getAllComments(
//            @RequestParam(value = "projectId", required = false) Optional<Long> projectId) {
//        List<CommentDto> dtos;
//
//        if (projectId.isEmpty()) {
//            dtos = commentService.getAllComments();
//        } else {
//            dtos = commentService.getAllCommentsByProject(projectId.get());
//        }
//        return ResponseEntity.ok().body(dtos);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") Long id) {
        CommentDto oneComment = commentService.getCommentById(id);
        return ResponseEntity.ok().body(oneComment);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto) {
        final CommentDto newComment = commentService.createComment(dto);
        final URI location = URI.create("/comments/" + newComment.getId());
        return ResponseEntity.created(location).body(newComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateComment(
            @PathVariable("id") Long id,
            @RequestBody CommentDto commentDto) {
        CommentDto comment = commentService.updateComment(id, commentDto);
        return ResponseEntity.ok().body(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.noContent().build();
    }
}
