package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateUserDto;
import nl.jvb.mypaintpholiobe.domain.dtos.UserDto;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.domain.entities.User;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.FileUploadRepository;
import nl.jvb.mypaintpholiobe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileUploadRepository fileUploadRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       FileUploadRepository fileUploadRepository) {
        this.userRepository = userRepository;
        this.fileUploadRepository = fileUploadRepository;
    }

    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userList) {
            UserDto dto = userToDto(user);
//            if(user.getProfilePhoto() != null)
            userDtoList.add(dto);
        }
        return userDtoList;
    }

    public UserDto getUserById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            UserDto dto = userToDto(user);
            return dto;
        } else {
            throw new RecordNotFoundException("Geen gebruiker gevonden.");
        }
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        User user = userRepository.save(createNewUser(createUserDto));
        return userToDto(user);
    }

    public UserDto updateUser(Long id, CreateUserDto createUserDto) {
        if (userRepository.findById(id).isPresent()) {
            User oldInfo = userRepository.findById(id).get();
            User newInfo = createNewUser(createUserDto);
            newInfo.setId(oldInfo.getId());
            userRepository.save(newInfo);
            return userToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Geen gebruiker gevonden.");
        }
    }

    public void deleteUserById(@RequestBody Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Geen gebruiker gevonden.");
        }
    }

    public User createNewUser(CreateUserDto dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmailAddress(dto.getEmailAddress());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        return user;
    }

    public UserDto userToDto(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmailAddress(user.getEmailAddress());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());

        return dto;
    }

    public void assignPhotoToUser(String name, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<FileUploadResponse> optionalPhoto = fileUploadRepository.findByFileName(name);
        if (optionalUser.isPresent() && optionalPhoto.isPresent()) {
            FileUploadResponse photo = optionalPhoto.get();
            User user = optionalUser.get();
            user.setFile(photo);
            userRepository.save(user);
        }
    }
}