package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.ReflectionDto;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.domain.entities.Reflection;
import nl.jvb.mypaintpholio.services.ReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/reflections")
public class ReflectionController {

    private final ReflectionService reflectionService;
    private final FileUploadController fileUploadController;

    @Autowired
    public ReflectionController(ReflectionService reflectionService,
                                FileUploadController fileUploadController) {
        this.reflectionService = reflectionService;
        this.fileUploadController = fileUploadController;
    }

    @Transactional
    @GetMapping("/admin")
    public ResponseEntity<List<ReflectionDto>> getAllReflections() {
        List<ReflectionDto> allReflections = reflectionService.getAllReflections();
        return ResponseEntity.ok().body(allReflections);
    }

    @Transactional
    @GetMapping("/project/{project_id}")
    public ResponseEntity<List<ReflectionDto>> getAllReflectionsByProject(@PathVariable(value = "project_id") Long Id) {
        List<ReflectionDto> reflections;
        reflections = reflectionService.getAllReflectionsByProjectId(Id);
        return ResponseEntity.ok().body(reflections);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ReflectionDto> getReflectionById(@PathVariable("id") Long id) {
        ReflectionDto oneReflection = reflectionService.getReflectionById(id);
        return ResponseEntity.ok().body(oneReflection);
    }

    @PostMapping("/add_reflection/{project_id}")
    public ResponseEntity<ReflectionDto> createReflection(@RequestBody ReflectionDto reflectionDto,
                                                      @PathVariable ("project_id") Long projectId) {
        final ReflectionDto newReflections = reflectionService.createReflection(reflectionDto, projectId);
        return ResponseEntity.ok().body(newReflections);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReflection(@PathVariable("id") Long id,
                                              @RequestBody ReflectionDto reflectionDto) {
        ReflectionDto reflection = reflectionService.updateReflection(id, reflectionDto);
        return ResponseEntity.ok().body(reflection);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reflection> deleteReflection(@PathVariable("id") Long id) {
        reflectionService.deleteReflectionById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/image")
    public void assignPhotoToReflection(@PathVariable("id") Long reflectionId,
                                  @RequestBody MultipartFile file) {
        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        reflectionService.assignPhotoToReflection(photo.getFileName(), reflectionId);
    }
}
