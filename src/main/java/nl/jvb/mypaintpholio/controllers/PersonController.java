package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.CreatePersonDto;
import nl.jvb.mypaintpholio.domain.dtos.PersonDto;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.exceptions.BadRequestException;
import nl.jvb.mypaintpholio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class PersonController {

    private final PersonService personService;
    private final FileUploadController fileUploadController;

    @Autowired
    public PersonController(PersonService personService,
                            FileUploadController fileUploadController) {
        this.personService = personService;
        this.fileUploadController = fileUploadController;
    }

    @GetMapping("/admin")
    @Transactional
    public ResponseEntity<List<PersonDto>> getAllUsers() {
        List<PersonDto> allUsers = personService.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/{username}")
    @Transactional
    public ResponseEntity<PersonDto> getUserById(@PathVariable("username") String username) {
        PersonDto oneUser = personService.getUserById(username);
        return ResponseEntity.ok().body(oneUser);
    }

    @GetMapping("/{username}/authorities")
    @Transactional
    public ResponseEntity<PersonDto> getUserAuthorities(@PathVariable("username") String username) {
        PersonDto oneUser = personService.getUserById(username);
        return ResponseEntity.ok().body(oneUser);
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDto> createUser(@RequestBody CreatePersonDto createPersonDto) {
        String newUserName = personService.createPerson(createPersonDto);
        personService.addAuthority(newUserName, "ROLE_USER");

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUserName)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> updateUser(
            @PathVariable("username") String username,
            @RequestBody CreatePersonDto createPersonDto) {
        personService.updatePerson(username, createPersonDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        personService.deletePersonById(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(
            @PathVariable("username") String username,
            @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            personService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(
            @PathVariable("username") String username,
            @PathVariable("authority") String authority) {
        personService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{username}/image")
    public void assignPhotoToUser(
            @PathVariable("username") String username,
            @RequestBody MultipartFile file) {
        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        personService.assignPhotoToUser(photo.getFileName(), username);
    }
}