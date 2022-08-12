package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateStudentDto;
import nl.jvb.mypaintpholiobe.domain.dtos.StudentDto;
import nl.jvb.mypaintpholiobe.domain.models.Student;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for(Student student : studentList) {
            StudentDto dto = newStudentToDto(student);
            studentDtoList.add(dto);
        }
        return studentDtoList;
    }

    public StudentDto getStudentById(Long id) {
        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            return newStudentToDto(student);
        }
        throw new RecordNotFoundException("ID '" + id + "' was not found.");
    }

    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        Student student = createNewStudent(createStudentDto);
        studentRepository.save(student);
        return newStudentToDto(student);
    }

    public StudentDto updateStudent(Long id, CreateStudentDto createStudentDto) {
        if (studentRepository.findById(id).isPresent()) {

            Student oldInfo = studentRepository.findById(id).get();
            Student newInfo = createNewStudent(createStudentDto);
            newInfo.setId(oldInfo.getId());

            studentRepository.save(newInfo);

            return newStudentToDto(newInfo);
        }
        throw new RecordNotFoundException("ID '" + id + "' was not found.");
    }

    public void deleteStudentById(@RequestBody Long id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
        }
        throw new RecordNotFoundException("ID '" + id + "' was not found.");
    }

    public Student createNewStudent(CreateStudentDto dto) {
        var student = new Student();

        student.setUsername(dto.getUsername());
        student.setPassword(dto.getPassword());
        student.setEmailAddress(dto.getEmailAddress());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());

        return student;
    }

    public StudentDto newStudentToDto(Student student) {
        StudentDto dto = new StudentDto();

        dto.setId(student.getId());
        dto.setUsername(student.getUsername());
        dto.setEmailAddress(student.getEmailAddress());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());

        return dto;
    }


}