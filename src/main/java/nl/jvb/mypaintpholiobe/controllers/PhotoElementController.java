package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.PhotoElementDto;
import nl.jvb.mypaintpholiobe.domain.entities.PhotoElement;
import nl.jvb.mypaintpholiobe.services.PhotoElementService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/photos")
public class PhotoElementController {

    private final PhotoElementService photoElementService;

    @Autowired
    public PhotoElementController(PhotoElementService photoElementService) {
        this.photoElementService = photoElementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoElementDto> getPhotoById(@PathVariable("id") Long id) {
        PhotoElementDto onePhoto = photoElementService.getPhotoById(id);
        return ResponseEntity.ok().body(onePhoto);
    }

    @PostMapping
    public ResponseEntity<PhotoElementDto> createPhoto(@RequestBody PhotoElementDto photoDto) {
        final PhotoElementDto newPhoto = photoElementService.createPhoto(photoDto);
        final URI location = URI.create("/photos/" + newPhoto.getId());
        return ResponseEntity.created(location).body(newPhoto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePhoto(@PathVariable("id") Long id,
                                              @RequestBody PhotoElementDto photoElementDto) {
        PhotoElementDto photo = photoElementService.updatePhoto(id, photoElementDto);
        return ResponseEntity.ok().body(photo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhotoElement> deletePhoto(@PathVariable("id") Long id) {
        photoElementService.deletePhotoById(id);
        return ResponseEntity.noContent().build();
    }
}
