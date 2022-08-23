package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.UpdateDto;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.domain.entities.Update;
import nl.jvb.mypaintpholio.services.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/updates")
public class UpdateController {

    private final UpdateService updateService;
    private final FileUploadController fileUploadController;

    @Autowired
    public UpdateController(UpdateService updateService,
                            FileUploadController fileUploadController) {
        this.updateService = updateService;
        this.fileUploadController = fileUploadController;
    }

    @Transactional
    @GetMapping("/admin")
    public ResponseEntity<List<UpdateDto>> getAllUpdates() {
        List<UpdateDto> allUpdates = updateService.getAllUpdates();
        return ResponseEntity.ok().body(allUpdates);
    }

    @Transactional
    @GetMapping("/project/{project_id}")
    public ResponseEntity<List<UpdateDto>> getAllUpdatesByProject(@PathVariable(value = "project_id") Long Id) {
        List<UpdateDto> updates;
        updates = updateService.getAllUpdatesByProjectId(Id);
        return ResponseEntity.ok().body(updates);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<UpdateDto> getUpdateById(@PathVariable("id") Long id) {
        UpdateDto oneUpdate = updateService.getUpdateById(id);
        return ResponseEntity.ok().body(oneUpdate);
    }

    @PostMapping("/add_update/{project_id}")
    public ResponseEntity<UpdateDto> createUpdate(@RequestBody UpdateDto updateDto,
                                                  @PathVariable ("project_id") Long projectId) {
        final UpdateDto newUpdate = updateService.createUpdate(updateDto, projectId);
        return ResponseEntity.ok().body(newUpdate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUpdate(@PathVariable("id") Long id,
                                              @RequestBody UpdateDto updateDto) {
        UpdateDto update = updateService.updateUpdate(id, updateDto);
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Update> deleteUpdate(@PathVariable("id") Long id) {
        updateService.deleteUpdateById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/image")
    public void assignPhotoToProjectUpdate(@PathVariable("id") Long updateId,
                                  @RequestBody MultipartFile file) {
        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        updateService.assignPhotoToUpdate(photo.getFileName(), updateId);
    }
}
