package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.ArtProjectDto;
import nl.jvb.mypaintpholiobe.domain.entities.ArtProject;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.ArtProjectRepository;
import nl.jvb.mypaintpholiobe.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtProjectService {

    private final ArtProjectRepository artProjectRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Autowired
    public ArtProjectService(
            ArtProjectRepository artProjectRepository,
            StudentRepository studentRepository,
            StudentService studentService) {
        this.artProjectRepository = artProjectRepository;
        this.studentRepository = studentRepository;
        this.studentService =studentService;
    }

    public List<ArtProjectDto> getAllProjects() {
        List<ArtProject> projectList = artProjectRepository.findAll();
        List<ArtProjectDto> projectDtoList = new ArrayList<>();
        for(ArtProject artProject : projectList) {
            ArtProjectDto dto = artProjectToDto(artProject);
            if(artProject.getStudent() != null) {
                dto.setStudentDto(studentService.studentToDto(artProject.getStudent()));
            }
            projectDtoList.add(dto);
        }
        return projectDtoList;
    }

    public ArtProjectDto getArtProjectById(Long id) {
        if (artProjectRepository.findById(id).isPresent()) {
            ArtProject project = artProjectRepository.findById(id).get();
            ArtProjectDto dto = artProjectToDto(project);
            if(project.getStudent() != null) {
                dto.setStudentDto(studentService.studentToDto(project.getStudent()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    public ArtProjectDto createArtProject(ArtProjectDto projectDto) {
        ArtProject project = dtoToArtProject(projectDto);
        var student = studentRepository.findById(project.getStudentId());
        project.setStudent(student.get());
        artProjectRepository.save(project);
        ArtProjectDto dto = artProjectToDto(project);
        dto.setStudentDto(studentService.studentToDto(project.getStudent()));
        return dto;
    }

    public ArtProjectDto updateArtProject(Long id, ArtProjectDto projectDto) {
        if (artProjectRepository.findById(id).isPresent()) {
            ArtProject oldInfo = artProjectRepository.findById(id).get();
            ArtProject newInfo = dtoToArtProject(projectDto);
            newInfo.setId(oldInfo.getId());
            artProjectRepository.save(newInfo);
            return artProjectToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    public void deleteArtProjectById(@RequestBody Long id) {
        if (artProjectRepository.findById(id).isPresent()) {
            artProjectRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    public ArtProject dtoToArtProject(ArtProjectDto dto) {
        ArtProject project = new ArtProject();

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
        project.setStudentId(dto.getStudentId());

        return project;
    }

    public ArtProjectDto artProjectToDto(ArtProject project) {
        ArtProjectDto dto = new ArtProjectDto();

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
        dto.setFinished(project.getFinished());
        dto.setStudentId(project.getStudentId());

        return dto;
    }
}
