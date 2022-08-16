package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.ArtProjectDto;
import nl.jvb.mypaintpholiobe.domain.entities.ArtProject;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.ArtProjectRepository;
import nl.jvb.mypaintpholiobe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtProjectService {

    private final ArtProjectRepository artProjectRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public ArtProjectService(ArtProjectRepository artProjectRepository,
                             UserRepository userRepository,
                             UserService userService) {
        this.artProjectRepository = artProjectRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public List<ArtProjectDto> getAllProjects() {
        List<ArtProject> projectList = artProjectRepository.findAll();
        List<ArtProjectDto> projectDtoList = new ArrayList<>();
        for(ArtProject artProject : projectList) {
            ArtProjectDto dto = artProjectToDto(artProject);
            if(artProject.getUser() != null) {
                dto.setUserDto(userService.userToDto(artProject.getUser()));
            }
            projectDtoList.add(dto);
        }
        return projectDtoList;
    }

    public List<ArtProjectDto> getAllProjectsByUserId(String username) {
        if (userRepository.findById(username).isPresent()) {
            List<ArtProject> projectList = artProjectRepository.findArtProjectsByUserIsContaining(username);
            List<ArtProjectDto> projectDtoList = new ArrayList<>();
            for(ArtProject artProject : projectList) {
                ArtProjectDto dto = artProjectToDto(artProject);
                dto.setUserDto(userService.userToDto(artProject.getUser())); // ---> klopt dit?
                projectDtoList.add(dto);
            }
            return projectDtoList;
        } else {
            throw new RecordNotFoundException("Gebruiker en/of projecten zijn niet gevonden.");
        }
    }

    public ArtProjectDto getArtProjectById(Long id) {
        if (artProjectRepository.findById(id).isPresent()) {
            ArtProject project = artProjectRepository.findById(id).get();
            ArtProjectDto dto = artProjectToDto(project);
            if(project.getUser() != null) {
                dto.setUserDto(userService.userToDto(project.getUser()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    //        de studentId zou uit de JWT van de ingelogde gebruiker gehaald kunnen worden
    public ArtProjectDto createArtProject(ArtProjectDto projectDto, String username) {
        if (userRepository.findById(username).isPresent()) {
            ArtProject project = dtoToArtProject(projectDto);
            project.setUser(userRepository.findById(username).get());
            artProjectRepository.save(project);
            ArtProjectDto dto = artProjectToDto(project);
            dto.setUserDto(userService.userToDto(project.getUser()));
            return dto;
        } else {
            throw new RecordNotFoundException("Gebruiker is niet gevonden.");
        }
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

    public void deleteArtProjectById(Long id) {
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

        return dto;
    }
}
