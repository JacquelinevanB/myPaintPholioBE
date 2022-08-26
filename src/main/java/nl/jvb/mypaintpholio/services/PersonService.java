package nl.jvb.mypaintpholio.services;

import nl.jvb.mypaintpholio.domain.dtos.CreatePersonDto;
import nl.jvb.mypaintpholio.domain.dtos.PersonDto;
import nl.jvb.mypaintpholio.domain.entities.Authority;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholio.domain.entities.Person;
import nl.jvb.mypaintpholio.domain.entities.User;
import nl.jvb.mypaintpholio.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholio.exceptions.UsernameAlreadyExistsException;
import nl.jvb.mypaintpholio.repositories.FileUploadRepository;
import nl.jvb.mypaintpholio.repositories.PersonRepository;
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
public class PersonService {

    private final PersonRepository personRepository;
    private final FileUploadRepository fileUploadRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository,
                         FileUploadRepository fileUploadRepository) {
        this.personRepository = personRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<PersonDto> getAllUsers() {
        List<Person> personList = personRepository.findAll();
        List<PersonDto> personDtoList = new ArrayList<>();
        for(Person person : personList) {
            PersonDto dto = personToDto(person);
            if (person.getFile() != null) {
                dto.setFileUploadResponse(person.getFile());
            }
            personDtoList.add(dto);
        }
        return personDtoList;
    }

    public PersonDto getUserById(String username) {
        if (personRepository.findById(username).isPresent()) {
            Person person = personRepository.findById(username).get();
            PersonDto dto = personToDto(person);
            if (person.getFile() != null) {
                dto.setFileUploadResponse(person.getFile());
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Geen gebruiker met deze gebruikersnaam gevonden.");
        }
    }

    public boolean userExists(String username) {
        return personRepository.existsById(username);
    }

    public String createPerson(CreatePersonDto createPersonDto) {
        if (userExists(createPersonDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Gebruikersnaam bestaat al.");
        }
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        createPersonDto.setApiKey(randomString);

        String encodedPassword = passwordEncoder.encode(createPersonDto.getPassword());
        createPersonDto.setPassword(encodedPassword);

        Person person = personRepository.save(createNewPerson(createPersonDto));
        return person.getUsername();
    }

    public void updatePassword(String username, CreatePersonDto createPersonDto) {
        if (!personRepository.existsById(username))throw new UsernameNotFoundException(username);
        Person person = personRepository.findById(username).get();
        person.setPassword(createPersonDto.getPassword());
        personRepository.save(person);
    }

    public void updatePerson(String username, CreatePersonDto createPersonDto) {
        if (!personRepository.existsById(username))throw new UsernameNotFoundException(username);
        Person person = personRepository.findById(username).get();
        person.setFirstName(createPersonDto.getFirstName());
        person.setLastName(createPersonDto.getLastName());
        person.setEmailAddress(createPersonDto.getEmailAddress());
        personRepository.save(person);
    }

    public void deletePersonById(String username) {
        if (personRepository.findById(username).isPresent()) {
            personRepository.deleteById(username);
        } else {
            throw new RecordNotFoundException("Geen gebruiker gevonden.");
        }
    }

    public Set<Authority> getAuthorities(String username) {
        if (!personRepository.existsById(username)) throw new UsernameNotFoundException(username);
        Person person = personRepository.findById(username).get();
        PersonDto personDto = personToDto(person);
        return personDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!personRepository.existsById(username)) throw new UsernameNotFoundException(username);
        Person person = personRepository.findById(username).get();
        person.addAuthority(new Authority(username, authority));
        personRepository.save(person);
    }

    public void removeAuthority(String username, String authority) {
        if (!personRepository.existsById(username)) throw new UsernameNotFoundException(username);
        Person person = personRepository.findById(username).get();
        Authority authorityToRemove = person.getAuthorities().stream()
                .filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        person.removeAuthority(authorityToRemove);
        personRepository.save(person);
    }

    public Person createNewPerson(CreatePersonDto dto) {
        Person person = new Person();

        person.setUsername(dto.getUsername());
        person.setPassword(dto.getPassword());
        person.setEnabled(dto.getEnabled());
        person.setApiKey(dto.getApiKey());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setEmailAddress(dto.getEmailAddress());

        return person;
    }

    public PersonDto personToDto(Person person) {
        PersonDto dto = new PersonDto();

        dto.setUsername(person.getUsername());
        dto.setPassword(person.getPassword());
        dto.setEnabled(person.isEnabled());
        dto.setApiKey(person.getApiKey());
        dto.setAuthorities(person.getAuthorities());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setEmailAddress(person.getEmailAddress());

        return dto;
    }

    public void assignPhotoToUser(String imagename, String username) {
        Optional<Person> optionalPerson = personRepository.findById(username);
        Optional<FileUploadResponse> optionalPhoto = fileUploadRepository.findByFileName(imagename);
        if (optionalPerson.isPresent() && optionalPhoto.isPresent()) {
            FileUploadResponse photo = optionalPhoto.get();
            Person person = optionalPerson.get();
            person.setFile(photo);
            personRepository.save(person);
        }
    }
}