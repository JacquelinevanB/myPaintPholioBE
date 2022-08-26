package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.ReflectionDto;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.domain.entities.Reflection;
import nl.jvb.mypaintpholio.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholio.repositories.ProjectRepository;
import nl.jvb.mypaintpholio.repositories.FileUploadRepository;
import nl.jvb.mypaintpholio.repositories.ReflectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReflectionService {
    private final ReflectionRepository reflectionRepository;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final FileUploadRepository fileUploadRepository;
    private final FileUploadService fileUploadService;

    @Autowired
    public ReflectionService(
            ReflectionRepository reflectionRepository,
            ProjectRepository projectRepository,
            ProjectService projectService,
            FileUploadRepository fileUploadRepository,
            FileUploadService fileUploadService) {
        this.reflectionRepository = reflectionRepository;
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadService = fileUploadService;
    }

    public List<ReflectionDto> getAllReflections() {
        List<Reflection> reflectionList = reflectionRepository.findAll();
        List<ReflectionDto> reflectionDtoList = new ArrayList<>();
        for(Reflection reflection : reflectionList) {
            ReflectionDto dto = reflectionToDto(reflection);
            if(reflection.getProject() != null) {
                dto.setProjectDto(projectService.projectToDto(reflection.getProject()));
            }
            if(reflection.getFile() != null) {
                dto.setFileUploadResponse(reflection.getFile());
            }
            reflectionDtoList.add(dto);
        }
        return reflectionDtoList;
    }

    public List<ReflectionDto> getAllReflectionsByProjectId(Long projectId) {
        if (reflectionRepository.findById(projectId).isPresent()) {
            List<Reflection> reflectionList = reflectionRepository.findAllReflectionsByProjectId(projectId);
            List<ReflectionDto> reflectionDtoList = new ArrayList<>();
            for(Reflection reflection : reflectionList) {
                ReflectionDto dto = reflectionToDto(reflection);
                if(reflection.getFile() != null) {
                    dto.setFileUploadResponse(reflection.getFile());
                }
                dto.setProjectDto(projectService.projectToDto(reflection.getProject()));
                reflectionDtoList.add(dto);
            }
            return reflectionDtoList;
        } else {
            throw new RecordNotFoundException("Project en/of projectupdates zijn niet gevonden.");
        }
    }

    public ReflectionDto getReflectionById(Long id) {
        if (reflectionRepository.findById(id).isPresent()) {
            Reflection reflection = reflectionRepository.findById(id).get();
            ReflectionDto dto = reflectionToDto(reflection);
            if(reflection.getProject() != null) {
                dto.setProjectDto(projectService.projectToDto(reflection.getProject()));
            }
            if(reflection.getFile() != null) {
                dto.setFileUploadResponse(reflection.getFile());
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public ReflectionDto createReflection(ReflectionDto reflectionDto, Long projectId) {
        if (projectRepository.findById(projectId).isPresent()) {
            Reflection reflection = dtoToReflection(reflectionDto);
            reflection.setProject(projectRepository.findById(projectId).get());
            reflectionRepository.save(reflection);
            ReflectionDto dto = reflectionToDto(reflection);
            dto.setProjectDto(projectService.projectToDto(reflection.getProject()));
            return dto;
        } else {
            throw new RecordNotFoundException("Project is niet gevonden.");
        }
    }

    public ReflectionDto updateReflection(Long id, ReflectionDto reflectionDto) {
        if (reflectionRepository.findById(id).isPresent()) {
            Reflection oldInfo = reflectionRepository.findById(id).get();
            Reflection newInfo = dtoToReflection(reflectionDto);
            newInfo.setId(oldInfo.getId());
            reflectionRepository.save(newInfo);
            return reflectionToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public void deleteReflectionById(Long id) {
        if (reflectionRepository.findById(id).isPresent()) {
            reflectionRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Projectupdate is niet gevonden.");
        }
    }

    public Reflection dtoToReflection(ReflectionDto dto) {
        Reflection reflection = new Reflection();

        reflection.setId(dto.getId());
        reflection.setReflexionText(dto.getReflectionText());
        reflection.setDateMade(dto.getDateMade());
        reflection.setSequenceNumber(dto.getSequenceNumber());

        return reflection;
    }

    public ReflectionDto reflectionToDto(Reflection reflection) {
        ReflectionDto dto = new ReflectionDto();

        dto.setId(reflection.getId());
        dto.setReflectionText(reflection.getReflexionText());
        dto.setDateMade(reflection.getDateMade());
        dto.setSequenceNumber(reflection.getSequenceNumber());

        return dto;
    }

    public void assignPhotoToReflection(String name, Long elementId) {
        Optional<Reflection> optionalReflection = reflectionRepository.findById(elementId);
        Optional<FileUploadResponse> optionalPhoto = fileUploadRepository.findByFileName(name);
        if (optionalReflection.isPresent() && optionalPhoto.isPresent()) {
            FileUploadResponse photo = optionalPhoto.get();
            Reflection reflection = optionalReflection.get();
            reflection.setFile(photo);
            reflectionRepository.save(reflection);
        }
    }
}
