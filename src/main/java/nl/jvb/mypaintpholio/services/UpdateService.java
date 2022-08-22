package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.UpdateDto;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.domain.entities.Update;
import nl.jvb.mypaintpholio.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholio.repositories.ProjectRepository;
import nl.jvb.mypaintpholio.repositories.FileUploadRepository;
import nl.jvb.mypaintpholio.repositories.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateService {
    private final UpdateRepository updateRepository;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final FileUploadRepository fileUploadRepository;

    @Autowired
    public UpdateService(
            UpdateRepository updateRepository,
            ProjectRepository projectRepository,
            ProjectService projectService,
            FileUploadRepository fileUploadRepository) {
        this.updateRepository = updateRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.fileUploadRepository = fileUploadRepository;
    }

    public List<UpdateDto> getAllUpdates() {
        List<Update> updateList = updateRepository.findAll();
        List<UpdateDto> updateDtoList = new ArrayList<>();
        for(Update update : updateList) {
            UpdateDto dto = updateToDto(update);
            if(update.getProject() != null) {
                dto.setProjectDto(projectService.projectToDto(update.getProject()));
            }
            updateDtoList.add(dto);
        }
        return updateDtoList;
    }

    public List<UpdateDto> getAllUpdatesByProjectId(Long projectId) {
        if (updateRepository.findById(projectId).isPresent()) {
            List<Update> updateList = updateRepository.findAllUpdatesByProjectId(projectId);
            List<UpdateDto> updateDtoList = new ArrayList<>();
            for(Update update : updateList) {
                UpdateDto dto = updateToDto(update);
                dto.setProjectDto(projectService.projectToDto(update.getProject()));
                updateDtoList.add(dto);
            }
            return updateDtoList;
        } else {
            throw new RecordNotFoundException("Project en/of projectupdates zijn niet gevonden.");
        }
    }

    public UpdateDto getUpdateById(Long id) {
        if (updateRepository.findById(id).isPresent()) {
            Update update = updateRepository.findById(id).get();
            UpdateDto dto = updateToDto(update);
            if(update.getProject() != null) {
                dto.setProjectDto(projectService.projectToDto(update.getProject()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public UpdateDto createUpdate(UpdateDto updateDto, Long projectId) {
        if (projectRepository.findById(projectId).isPresent()) {
            Update update = dtoToUpdate(updateDto);
            update.setProject(projectRepository.findById(projectId).get());
            updateRepository.save(update);
            UpdateDto dto = updateToDto(update);
            dto.setProjectDto(projectService.projectToDto(update.getProject()));
            return dto;
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    public UpdateDto updateUpdate(Long id, UpdateDto updateDto) {
        if (updateRepository.findById(id).isPresent()) {
            Update oldInfo = updateRepository.findById(id).get();
            Update newInfo = dtoToUpdate(updateDto);
            newInfo.setId(oldInfo.getId());
            updateRepository.save(newInfo);
            return updateToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public void deleteUpdateById(Long id) {
        if (updateRepository.findById(id).isPresent()) {
            updateRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public Update dtoToUpdate(UpdateDto dto) {
        Update update = new Update();

        update.setId(dto.getId());
        update.setReflexionText(dto.getReflectionText());
        update.setDate(dto.getDate());
        update.setSequenceNumber(dto.getSequenceNumber());

        return update;
    }

    public UpdateDto updateToDto(Update update) {
        UpdateDto dto = new UpdateDto();

        dto.setId(update.getId());
        dto.setReflectionText(update.getReflexionText());
        dto.setDate(update.getDate());
        dto.setSequenceNumber(update.getSequenceNumber());

        return dto;
    }

    public void assignPhotoToUpdate(String name, Long elementId) {
        Optional<Update> optionalUpdate = updateRepository.findById(elementId);
        Optional<FileUploadResponse> optionalPhoto = fileUploadRepository.findByFileName(name);
        if (optionalUpdate.isPresent() && optionalPhoto.isPresent()) {
            FileUploadResponse photo = optionalPhoto.get();
            Update update = optionalUpdate.get();
            update.setFile(photo);
            updateRepository.save(update);
        }
    }
}
