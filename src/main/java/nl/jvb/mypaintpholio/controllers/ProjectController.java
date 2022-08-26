package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.ProjectDto;
import nl.jvb.mypaintpholio.domain.entities.Person;
import nl.jvb.mypaintpholio.domain.entities.Project;
import nl.jvb.mypaintpholio.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Transactional
    @GetMapping("/all")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> allProjects = projectService.getAllProjects();
        return ResponseEntity.ok().body(allProjects);
    }

    @Transactional
    @GetMapping("/user/{username}")
    public ResponseEntity<List<ProjectDto>> getAllProjectsByPersonId(@PathVariable("username")Person person) {
        List<ProjectDto> userProjects;
        userProjects = projectService.getAllProjectsByPerson(person);
        return ResponseEntity.ok().body(userProjects);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Long id) {
        ProjectDto oneProject = projectService.getProjectById(id);
        return ResponseEntity.ok().body(oneProject);
    }

    @PostMapping("/add_project")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto newProject = projectService.createProject(projectDto);
        return ResponseEntity.ok().body(newProject);
    }

    @PostMapping("/add_project/{username}")
    public ResponseEntity<ProjectDto> createProjectForPerson(@RequestBody ProjectDto projectDto,
                                                    @PathVariable ("username") String username) {
        ProjectDto newProject = projectService.createProjectForPerson(projectDto, username);
        return ResponseEntity.ok().body(newProject);
    }

    @PutMapping("/{id}/{username}")
    public void assignProjectToPerson(@PathVariable("id") Long id, @PathVariable("username") String username) {
        projectService.assignProjectToPerson(id, username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable("id") Long id,
                                                   @RequestBody ProjectDto projectDto) {
        ProjectDto dto = projectService.updateProject(id, projectDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }
}
