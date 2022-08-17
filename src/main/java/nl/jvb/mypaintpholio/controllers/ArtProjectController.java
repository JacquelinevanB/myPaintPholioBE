package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.ArtProjectDto;
import nl.jvb.mypaintpholio.domain.entities.ArtProject;
import nl.jvb.mypaintpholio.services.ArtProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/projects")
public class ArtProjectController {

    private final ArtProjectService artProjectService;

    @Autowired
    public ArtProjectController(ArtProjectService artProjectService) {
        this.artProjectService = artProjectService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<ArtProjectDto>> getAllProjects() {
        List<ArtProjectDto> allProjects = artProjectService.getAllProjects();
        return ResponseEntity.ok().body(allProjects);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ArtProjectDto>> getAllProjectsByUserId(@RequestParam(value = "username") String username) {
        List<ArtProjectDto> userProjects;
        userProjects = artProjectService.getAllProjectsByUserId(username);
        return ResponseEntity.ok().body(userProjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtProjectDto> getArtProjectById(@PathVariable("id") Long id) {
        ArtProjectDto oneProject = artProjectService.getArtProjectById(id);
        return ResponseEntity.ok().body(oneProject);
    }

    @PostMapping("/add_project")
    public ResponseEntity<ArtProjectDto> createArtProject(@RequestBody ArtProjectDto artProjectDto) {
        ArtProjectDto newProject = artProjectService.createArtProject(artProjectDto);
//        final URI location = URI.create("/projects/" + newProject.getId());
//        final URI location = URI.create("/users/" + newProject.getUserId() + "/" + newProject.getId());
        return ResponseEntity.ok().body(newProject);
    }

    @PutMapping("/{id}/{username}")
    public void assignProjectToUser(@PathVariable("id") Long id, @PathVariable("username") String username) {
        artProjectService.assignProjectToUser(id, username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArtProject(@PathVariable("id") Long id,
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
