package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateTeacherDto;
import nl.jvb.mypaintpholiobe.domain.dtos.TeacherDto;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.domain.entities.Student;
import nl.jvb.mypaintpholiobe.domain.entities.Teacher;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.FileUploadRepository;
import nl.jvb.mypaintpholiobe.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final FileUploadRepository fileUploadRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,
                          FileUploadRepository fileUploadRepository) {
        this.teacherRepository = teacherRepository;
        this.fileUploadRepository = fileUploadRepository;
    }

    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teacherList = teacherRepository.findAll();
        List<TeacherDto> teacherDtoList = new ArrayList<>();
        for(Teacher teacher : teacherList) {
            TeacherDto dto = teacherToDto(teacher);
            teacherDtoList.add(dto);
        }
        return teacherDtoList;
    }

    public TeacherDto getTeacherById(Long id) {
        if (teacherRepository.findById(id).isPresent()) {
            Teacher teacher = teacherRepository.findById(id).get();
            return teacherToDto(teacher);
        } else {
            throw new RecordNotFoundException("Docent is niet gevonden.");
        }
    }

    public TeacherDto createTeacher(CreateTeacherDto createTeacherDto) {
        Teacher teacher = teacherRepository.save(createNewTeacher(createTeacherDto));
        return teacherToDto(teacher);
    }

    public TeacherDto updateTeacher(Long id, CreateTeacherDto createTeacherDto) {
        if (teacherRepository.findById(id).isPresent()) {
            Teacher oldInfo = teacherRepository.findById(id).get();
            Teacher newInfo = createNewTeacher(createTeacherDto);
            newInfo.setId(oldInfo.getId());
            teacherRepository.save(newInfo);
            return teacherToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Docent is niet gevonden.");
        }
    }

    public void deleteTeacherById(@RequestBody Long id) {
        if (teacherRepository.findById(id).isPresent()) {
            teacherRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Docent is niet gevonden.");
        }
    }

    public Teacher createNewTeacher(CreateTeacherDto dto) {
        var teacher = new Teacher();

        teacher.setUsername(dto.getUsername());
        teacher.setPassword(dto.getPassword());
        teacher.setEmailAddress(dto.getEmailAddress());
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());

        return teacher;
    }

    public static TeacherDto teacherToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();

        dto.setId(teacher.getId());
        dto.setUsername(teacher.getUsername());
        dto.setEmailAddress(teacher.getEmailAddress());
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());

        return dto;
    }

    public void assignPhotoToTeacher(String fileName, Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        Optional<FileUploadResponse> optionalPhoto = fileUploadRepository.findByFileName(fileName);
        if (optionalTeacher.isPresent() && optionalPhoto.isPresent()) {
            FileUploadResponse photo = optionalPhoto.get();
            Teacher teacher = optionalTeacher.get();
            teacher.setFile(photo);
            teacherRepository.save(teacher);
        }
    }
}