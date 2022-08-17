package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.ProjectUpdateDto;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.domain.entities.ProjectUpdate;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.ArtProjectRepository;
import nl.jvb.mypaintpholiobe.repositories.FileUploadRepository;
import nl.jvb.mypaintpholiobe.repositories.ProjectUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectUpdateService {
    private final ProjectUpdateRepository projectUpdateRepository;
    private final ArtProjectRepository artProjectRepository;
    private final ArtProjectService artProjectService;
    private final FileUploadRepository fileUploadRepository;

    @Autowired
    public ProjectUpdateService(
            ProjectUpdateRepository projectUpdateRepository,
            ArtProjectRepository artProjectRepository,
            ArtProjectService artProjectService,
            FileUploadRepository fileUploadRepository) {
        this.projectUpdateRepository = projectUpdateRepository;
        this.artProjectRepository = artProjectRepository;
        this.artProjectService = artProjectService;
        this.fileUploadRepository = fileUploadRepository;
    }

    public List<ProjectUpdateDto> getAllUpdates() {
        List<ProjectUpdate> updateList = projectUpdateRepository.findAll();
        List<ProjectUpdateDto> updateDtoList = new ArrayList<>();
        for(ProjectUpdate update : updateList) {
            ProjectUpdateDto dto = updateToDto(update);
            if(update.getArtProject() != null) {
                dto.setArtProjectDto(artProjectService.artProjectToDto(update.getArtProject()));
            }
            updateDtoList.add(dto);
        }
        return updateDtoList;
    }

    public List<ProjectUpdateDto> getAllUpdatesByProjectId(Long projectId) {
        if (projectUpdateRepository.findById(projectId).isPresent()) {
            List<ProjectUpdate> updateList = projectUpdateRepository.findAllUpdatesByArtProjectId(projectId);
            List<ProjectUpdateDto> updateDtoList = new ArrayList<>();
            for(ProjectUpdate update : updateList) {
                ProjectUpdateDto dto = updateToDto(update);
                dto.setArtProjectDto(artProjectService.artProjectToDto(update.getArtProject()));
                updateDtoList.add(dto);
            }
            return updateDtoList;
        } else {
            throw new RecordNotFoundException("Project en/of projectupdates zijn niet gevonden.");
        }
    }

    public ProjectUpdateDto getUpdateById(Long id) {
        if (projectUpdateRepository.findById(id).isPresent()) {
            ProjectUpdate projectUpdate = projectUpdateRepository.findById(id).get();
            ProjectUpdateDto dto = updateToDto(projectUpdate);
            if(projectUpdate.getArtProject() != null) {
                dto.setArtProjectDto(artProjectService.artProjectToDto(projectUpdate.getArtProject()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

//    de projectId zou uit het actieve project gehaald moeten worden... ?
    public ProjectUpdateDto createProjectUpdate(ProjectUpdateDto projectUpdateDto, Long projectId) {
        if (artProjectRepository.findById(projectId).isPresent()) {
            ProjectUpdate update = dtoToUpdate(projectUpdateDto);
            update.setArtProject(artProjectRepository.findById(projectId).get());
            projectUpdateRepository.save(update);
            ProjectUpdateDto dto = updateToDto(update);
            dto.setArtProjectDto(artProjectService.artProjectToDto(update.getArtProject()));
            return dto;
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }

    }

    public ProjectUpdateDto updateProjectUpdate(Long id, ProjectUpdateDto projectUpdateDto) {
        if (projectUpdateRepository.findById(id).isPresent()) {
            ProjectUpdate oldInfo = projectUpdateRepository.findById(id).get();
            ProjectUpdate newInfo = dtoToUpdate(projectUpdateDto);
            newInfo.setId(oldInfo.getId());
            projectUpdateRepository.save(newInfo);
            return updateToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public void deleteUpdateById(Long id) {
        if (projectUpdateRepository.findById(id).isPresent()) {
            projectUpdateRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public ProjectUpdate dtoToUpdate(ProjectUpdateDto dto) {
        ProjectUpdate projectUpdate = new ProjectUpdate();

        projectUpdate.setId(dto.getId());
        projectUpdate.setReflectionText(dto.getReflectionText());
        projectUpdate.setDate(dto.getDate());
        projectUpdate.setSequenceNumber(dto.getSequenceNumber());

        return projectUpdate;
    }

    public ProjectUpdateDto updateToDto(ProjectUpdate projectUpdate) {
        ProjectUpdateDto dto = new ProjectUpdateDto();

        dto.setId(projectUpdate.getId());
        dto.setReflectionText(projectUpdate.getReflectionText());
        dto.setDate(projectUpdate.getDate());
        dto.setSequenceNumber(projectUpdate.getSequenceNumber());

        return dto;
    }

    public void assignPhotoToProjectUpdate(String name, Long elementId) {
        Optional<ProjectUpdate> optionalUpdate = projectUpdateRepository.findById(elementId);
        Optional<FileUploadResponse> optionalPhoto = fileUploadRepository.findByFileName(name);
        if (optionalUpdate.isPresent() && optionalPhoto.isPresent()) {
            FileUploadResponse photo = optionalPhoto.get();
            ProjectUpdate update = optionalUpdate.get();
            update.setFile(photo);
            projectUpdateRepository.save(update);
        }
    }
}
