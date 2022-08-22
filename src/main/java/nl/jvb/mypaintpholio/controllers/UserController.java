package nl.jvb.mypaintpholio.controllers;

import nl.jvb.mypaintpholio.domain.dtos.CreateUserDto;
import nl.jvb.mypaintpholio.domain.dtos.UserDto;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.exceptions.BadRequestException;
import nl.jvb.mypaintpholio.services.UserService;
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
public class UserController {

    private final UserService userService;
    private final FileUploadController fileUploadController;

    @Autowired
    public UserController(UserService userService,
                          FileUploadController fileUploadController) {
        this.userService = userService;
        this.fileUploadController = fileUploadController;
    }

    @GetMapping("/admin")
    @Transactional
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/{username}")
    @Transactional
    public ResponseEntity<UserDto> getUserById(@PathVariable("username") String username) {
        UserDto oneUser = userService.getUserById(username);
        return ResponseEntity.ok().body(oneUser);
    }

    @GetMapping("/{username}/authorities")
    @Transactional
    public ResponseEntity<UserDto> getUserAuthorities(@PathVariable("username") String username) {
        UserDto oneUser = userService.getUserById(username);
        return ResponseEntity.ok().body(oneUser);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        String newUserName = userService.createUser(createUserDto);
        userService.addAuthority(newUserName, "ROLE_USER");

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
            @RequestBody CreateUserDto createUserDto) {
        userService.updateUser(username, createUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUserById(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(
            @PathVariable("username") String username,
            @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
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
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{username}/image")
    public void assignPhotoToUser(
            @PathVariable("username") String username,
            @RequestBody MultipartFile file) {
        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        userService.assignPhotoToUser(photo.getFileName(), username);
    }
}