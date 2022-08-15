package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateUserDto;
import nl.jvb.mypaintpholiobe.domain.dtos.UserDto;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

//import javax.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final FileUploadController fileUploadController;

    @Autowired
    public UserController(UserService userService,
                          FileUploadController fileUploadController) {
        this.userService = userService;
        this.fileUploadController = fileUploadController;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto oneUser = userService.getUserById(id);
        return ResponseEntity.ok().body(oneUser);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody CreateUserDto createUserDto) {
        final UserDto newUser = userService.createUser(createUserDto);
        final URI location = URI.create("/users/" + newUser.getId());
        return ResponseEntity.created(location).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(
            @PathVariable("id") Long id,
            @RequestBody CreateUserDto createUserDto) {
        UserDto user = userService.updateUser(id, createUserDto);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToUser(@PathVariable("id") Long userId,
                                     @RequestBody MultipartFile file) {
        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        userService.assignPhotoToUser(photo.getFileName(), userId);
    }
}