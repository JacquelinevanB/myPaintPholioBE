package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.ArtProjectDto;
import nl.jvb.mypaintpholio.domain.entities.ArtProject;
import nl.jvb.mypaintpholio.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholio.repositories.ArtProjectRepository;
import nl.jvb.mypaintpholio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ArtProject project = artProjectRepository.findById(id).get();
        ArtProjectDto dto = artProjectToDto(project);
        if(project.getUser() != null) {
            dto.setUserDto(userService.userToDto(project.getUser()));
        }
        return dto;
//        if (artProjectRepository.findById(id).isPresent()) {
//
//        } else {
//            throw new RecordNotFoundException("Project is niet gevonden.");
//        }
    }

    public ArtProjectDto createArtProject(ArtProjectDto projectDto) {
        ArtProject project = dtoToArtProject(projectDto);
        artProjectRepository.save(project);
        return artProjectToDto(project);
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

    public void assignProjectToUser(Long id, String username) {
        var optionalProject = artProjectRepository.findById(id);
        var optionalUser = userRepository.findById(username);

        if(optionalProject.isPresent() && optionalUser.isPresent()) {
            var project = optionalProject.get();
            var user = optionalUser.get();

            project.setUser(user);
            artProjectRepository.save(project);
        } else {
            throw new RecordNotFoundException();
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
        dto.setIsFinished(project.isFinished());

        return dto;
    }
}
