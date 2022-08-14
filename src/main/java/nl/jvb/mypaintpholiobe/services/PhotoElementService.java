package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.PhotoElementDto;
import nl.jvb.mypaintpholiobe.domain.entities.PhotoElement;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.ArtProjectRepository;
import nl.jvb.mypaintpholiobe.repositories.PhotoElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoElementService {
    private final PhotoElementRepository photoElementRepository;
    private final ArtProjectRepository artProjectRepository;
    private final ArtProjectService artProjectService;

    @Autowired
    public PhotoElementService(
            PhotoElementRepository photoElementRepository,
            ArtProjectRepository artProjectRepository,
            ArtProjectService artProjectService) {
        this.photoElementRepository = photoElementRepository;
        this.artProjectRepository = artProjectRepository;
        this.artProjectService = artProjectService;
    }

    public List<PhotoElementDto> getAllPhotos() {
        List<PhotoElement> PhotoElementList = photoElementRepository.findAll();
        return transferPhotoListToDtoList(PhotoElementList);
    }

    public List<PhotoElementDto> getAllPhotosByProject(Long projectId) {
        List<PhotoElement> photoElementList = photoElementRepository.findAllPhotosByArtProjectId(projectId);
        return transferPhotoListToDtoList(photoElementList);
    }

    public List<PhotoElementDto> transferPhotoListToDtoList(List<PhotoElement> photoElements){
        List<PhotoElementDto> photoElementDtoList = new ArrayList<>();

        for(PhotoElement photoElement : photoElements) {
            PhotoElementDto dto = photoToDto(photoElement);
            if(photoElement.getArtProject() != null){
                dto.setArtProjectDto(artProjectService.artProjectToDto(photoElement.getArtProject()));
            }
            photoElementDtoList.add(dto);
        }
        return photoElementDtoList;
    }

    public PhotoElementDto getPhotoById(Long id) {
        if (photoElementRepository.findById(id).isPresent()) {
            PhotoElement photoElement = photoElementRepository.findById(id).get();
            PhotoElementDto dto = photoToDto(photoElement);
            if(photoElement.getArtProject() != null) {
                dto.setArtProjectDto(artProjectService.artProjectToDto(photoElement.getArtProject()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Foto is niet gevonden.");
        }
    }

    public PhotoElementDto createPhoto(PhotoElementDto photoElementDto) {
        PhotoElement photoElement = dtoToPhoto(photoElementDto);
        var project = artProjectRepository.findById(photoElement.getProjectId());
        photoElement.setArtProject(project.get());
        photoElementRepository.save(photoElement);
        PhotoElementDto dto = photoToDto(photoElement);
        dto.setArtProjectDto(artProjectService.artProjectToDto(photoElement.getArtProject()));
        return dto;
    }

    public PhotoElementDto updatePhoto(Long id, PhotoElementDto photoElementDto) {
        if (photoElementRepository.findById(id).isPresent()) {
            PhotoElement oldInfo = photoElementRepository.findById(id).get();
            PhotoElement newInfo = dtoToPhoto(photoElementDto);
            newInfo.setId(oldInfo.getId());
            photoElementRepository.save(newInfo);
            return photoToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public void deletePhotoById(@RequestBody Long id) {
        if (photoElementRepository.findById(id).isPresent()) {
            photoElementRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Bericht is niet gevonden.");
        }
    }

    public PhotoElement dtoToPhoto(PhotoElementDto dto) {
        PhotoElement photoElement = new PhotoElement();

        photoElement.setProjectId(dto.getProjectId());
        photoElement.setSequenceNumber(dto.getSequenceNumber());

        return photoElement;
    }

    public PhotoElementDto photoToDto(PhotoElement photoElement) {
        PhotoElementDto dto = new PhotoElementDto();

        dto.setId(photoElement.getProjectId());
        dto.setSequenceNumber(photoElement.getSequenceNumber());
        dto.setProjectId(photoElement.getProjectId());

        return dto;
    }
}
