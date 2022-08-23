package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.ProjectDto;
import nl.jvb.mypaintpholio.domain.entities.Project;
import nl.jvb.mypaintpholio.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholio.repositories.ProjectRepository;
import nl.jvb.mypaintpholio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          PersonRepository personRepository,
                          PersonService personService) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.personService = personService;
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for(Project project : projectList) {
            ProjectDto dto = projectToDto(project);
            if(project.getPerson() != null) {
                dto.setUserDto(personService.personToDto(project.getPerson()));
            }
            projectDtoList.add(dto);
        }
        return projectDtoList;
    }

    public List<ProjectDto> getAllProjectsByPerson(String username) {
        List<Project> projectList = projectRepository.findAllProjectsByPerson(username);
        List<ProjectDto> projectDtoList = new ArrayList<>();
        for(Project project : projectList) {
            ProjectDto dto = projectToDto(project);
            dto.setUserDto(personService.personToDto(project.getPerson())); // ---> klopt dit?
            projectDtoList.add(dto);
        }
        return projectDtoList;

//        if (personRepository.findById(username).isPresent()) {
//
//        } else {
//            throw new RecordNotFoundException("Gebruiker en/of projecten zijn niet gevonden.");
//        }
    }

    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id).get();
        ProjectDto dto = projectToDto(project);
        if(project.getPerson() != null) {
            dto.setUserDto(personService.personToDto(project.getPerson()));
        }
        return dto;
//        if (artProjectRepository.findById(id).isPresent()) {
//
//        } else {
//            throw new RecordNotFoundException("Project is niet gevonden.");
//        }
    }

    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = dtoToProject(projectDto);
        projectRepository.save(project);
        return projectToDto(project);
    }

    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        if (projectRepository.findById(id).isPresent()) {
            Project oldInfo = projectRepository.findById(id).get();
            Project newInfo = dtoToProject(projectDto);
            newInfo.setId(oldInfo.getId());
            projectRepository.save(newInfo);
            return projectToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    public void deleteProjectById(Long id) {
        if (projectRepository.findById(id).isPresent()) {
            projectRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    public void assignProjectToPerson(Long id, String username) {
        var optionalProject = projectRepository.findById(id);
        var optionalPerson = personRepository.findById(username);

        if(optionalProject.isPresent() && optionalPerson.isPresent()) {
            var project = optionalProject.get();
            var person = optionalPerson.get();

            project.setPerson(person);
            projectRepository.save(project);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public Project dtoToProject(ProjectDto dto) {
        Project project = new Project();

        project.setTitle(dto.getTitle());
        project.setMediumType(dto.getMediumType());
        project.setDateStart(dto.getDateStart());
        project.setDateEnd(dto.getDateEnd());
        project.setInspiration(dto.getInspiration());
        project.setHeight(dto.getHeight());
        project.setWidth(dto.getWidth());
        project.setDescription(dto.getDescription());
        project.setSubject(dto.getSubject());
        project.setFinished(dto.getFinished());

        return project;
    }

    public ProjectDto projectToDto(Project project) {
        ProjectDto dto = new ProjectDto();

        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setMediumType(project.getMediumType());
        dto.setDateStart(project.getDateStart());
        dto.setDateEnd(project.getDateEnd());
        dto.setInspiration(project.getInspiration());
        dto.setHeight(project.getHeight());
        dto.setWidth(project.getWidth());
        dto.setDescription(project.getDescription());
        dto.setSubject(project.getSubject());
        dto.setIsFinished(project.isFinished());

        return dto;
    }
}
