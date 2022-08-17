package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.CreateUserDto;
import nl.jvb.mypaintpholio.domain.dtos.UserDto;
import nl.jvb.mypaintpholio.domain.entities.Authority;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.domain.entities.User;
import nl.jvb.mypaintpholio.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholio.exceptions.UsernameAlreadyExistsException;
import nl.jvb.mypaintpholio.repositories.FileUploadRepository;
import nl.jvb.mypaintpholio.repositories.UserRepository;
import nl.jvb.mypaintpholio.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileUploadRepository fileUploadRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       FileUploadRepository fileUploadRepository) {
        this.userRepository = userRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList) {
            userDtoList.add(userToDto(user));
        }
        return userDtoList;
    }

    public UserDto getUserById(String username) {
        if (userRepository.findById(username).isPresent()) {
            User user = userRepository.findById(username).get();
            return userToDto(user);
        } else {
            throw new RecordNotFoundException("Geen gebruiker '" + username + "' gevonden.");
        }
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(CreateUserDto createUserDto) {
        if (userExists(createUserDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Gebruikersnaam bestaat al.");
        }
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        createUserDto.setApiKey(randomString);

        String encodedPassword = passwordEncoder.encode(createUserDto.getPassword());
        createUserDto.setPassword(encodedPassword);

        User user = userRepository.save(createNewUser(createUserDto));
        return user.getUsername();
    }

    public void updateUser(String username, CreateUserDto createUserDto) {
        if (userRepository.findById(username).isPresent()) {
            User updatedUser = createNewUser(createUserDto);
            userRepository.save(updatedUser);
        } else {
            throw new RecordNotFoundException("Geen gebruiker gevonden.");
        }
    }

    public void deleteUserById(String username) {
        if (userRepository.findById(username).isPresent()) {
            userRepository.deleteById(username);
        } else {
            throw new RecordNotFoundException("Geen gebruiker gevonden.");
        }
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = userToDto(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream()
                .filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public User createNewUser(CreateUserDto dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEnabled(dto.getEnabled());
        user.setApiKey(dto.getApiKey());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmailAddress(dto.getEmailAddress());

        return user;
    }

    public UserDto userToDto(User user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEnabled(user.isEnabled());
        dto.setApiKey(user.getApiKey());
        dto.setAuthorities(user.getAuthorities());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmailAddress(user.getEmailAddress());

        return dto;
    }

    public void assignPhotoToUser(String imagename, String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        Optional<FileUploadResponse> optionalPhoto = fileUploadRepository.findByFileName(imagename);
        if (optionalUser.isPresent() && optionalPhoto.isPresent()) {
            FileUploadResponse photo = optionalPhoto.get();
            User user = optionalUser.get();
            user.setFile(photo);
            userRepository.save(user);
        }
    }
}