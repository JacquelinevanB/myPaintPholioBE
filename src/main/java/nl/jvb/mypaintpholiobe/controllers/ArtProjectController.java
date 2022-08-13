package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.ArtProjectDto;
import nl.jvb.mypaintpholiobe.domain.entities.ArtProject;
import nl.jvb.mypaintpholiobe.services.ArtProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/artprojects")
public class ArtProjectController {

    private final ArtProjectService artProjectService;

    @Autowired
    public ArtProjectController(ArtProjectService artProjectService) {
        this.artProjectService = artProjectService;
    }

    @GetMapping
    public ResponseEntity<List<ArtProjectDto>> getAllProjects() {
        List<ArtProjectDto> allProjects = artProjectService.getAllProjects();
        return ResponseEntity.ok().body(allProjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtProjectDto> getArtProjectById(@PathVariable("id") Long id) {
        ArtProjectDto oneProject = artProjectService.getArtProjectById(id);
        return ResponseEntity.ok().body(oneProject);
    }

    @PostMapping
    public ResponseEntity<ArtProjectDto> createArtProject(
            @RequestBody ArtProjectDto artProjectDto) {
        final ArtProjectDto newProject = artProjectService.createArtProject(artProjectDto);
        final URI location = URI.create("/artprojects/" + newProject.getId());
        return ResponseEntity.created(location).body(newProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArtProject(
            @PathVariable("id") Long id,
            @RequestBody ArtProjectDto artProjectDto) {
        ArtProjectDto projectDto = artProjectService.updateArtProject(id, artProjectDto);
        return ResponseEntity.ok().body(projectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArtProject> deleteArtProject(@PathVariable("id") Long id) {
        artProjectService.deleteArtProjectById(id);
        return ResponseEntity.noContent().build();
    }
}
