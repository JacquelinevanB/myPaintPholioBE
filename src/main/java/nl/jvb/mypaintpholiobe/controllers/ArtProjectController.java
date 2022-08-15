package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.ArtProjectDto;
import nl.jvb.mypaintpholiobe.domain.entities.ArtProject;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.services.ArtProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
public class ArtProjectController {

    private final ArtProjectService artProjectService;

    @Autowired
    public ArtProjectController(ArtProjectService artProjectService) {
        this.artProjectService = artProjectService;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ArtProjectDto>> getAllProjects() {
        List<ArtProjectDto> allProjects = artProjectService.getAllProjects();
        return ResponseEntity.ok().body(allProjects);
    }

    @GetMapping("/{user_id}/projects")
    public ResponseEntity<List<ArtProjectDto>> getAllProjectsByUserId(@RequestParam(value = "userId") Long userId) {
        List<ArtProjectDto> userProjects;
        userProjects = artProjectService.getAllProjectsByUserId(userId);
        return ResponseEntity.ok().body(userProjects);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ArtProjectDto> getArtProjectById(@PathVariable("id") Long id) {
        ArtProjectDto oneProject = artProjectService.getArtProjectById(id);
        return ResponseEntity.ok().body(oneProject);
    }

    @PostMapping("/add_project/{user_id}")
    public ResponseEntity<ArtProjectDto> createArtProject(@RequestBody ArtProjectDto artProjectDto,
                                                          @PathVariable Long userId) {
        final ArtProjectDto newProject = artProjectService.createArtProject(artProjectDto, userId);
        final URI location = URI.create("/projects/" + newProject.getId());
//        final URI location = URI.create("/users/" + newProject.getUserId() + "/" + newProject.getId());
        return ResponseEntity.created(location).body(newProject);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<Object> updateArtProject(@PathVariable("id") Long id,
                                                   @RequestBody ArtProjectDto artProjectDto) {
        ArtProjectDto projectDto = artProjectService.updateArtProject(id, artProjectDto);
        return ResponseEntity.ok().body(projectDto);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<ArtProject> deleteArtProject(@PathVariable("id") Long id) {
        artProjectService.deleteArtProjectById(id);
        return ResponseEntity.noContent().build();
    }
}
