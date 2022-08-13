package nl.jvb.mypaintpholiobe.services;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateStudentDto;
import nl.jvb.mypaintpholiobe.domain.dtos.StudentDto;
import nl.jvb.mypaintpholiobe.domain.entities.Student;
import nl.jvb.mypaintpholiobe.exceptions.RecordNotFoundException;
import nl.jvb.mypaintpholiobe.repositories.StudentRepository;
import nl.jvb.mypaintpholiobe.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;

    @Autowired
    public StudentService(
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            TeacherService teacherService
        ) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        for(Student student : studentList) {

            StudentDto dto = studentToDto(student);

            if(student.getTeacher() != null) {
                dto.setTeacherDto(teacherService.teacherToDto(student.getTeacher()));
            }
            studentDtoList.add(dto);
        }
        return studentDtoList;
    }

    public StudentDto getStudentById(Long id) {
        if (studentRepository.findById(id).isPresent()) {

            Student student = studentRepository.findById(id).get();

            StudentDto dto = studentToDto(student);

            if(student.getTeacher() != null) {
                dto.setTeacherDto(teacherService.teacherToDto(student.getTeacher()));
            }
            return dto;
        } else {
            throw new RecordNotFoundException("Geen student gevonden.");
        }
    }

    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        Student student = createNewStudent(createStudentDto);
        studentRepository.save(student);
        return studentToDto(student);
    }

    public StudentDto updateStudent(Long id, CreateStudentDto createStudentDto) {
        if (studentRepository.findById(id).isPresent()) {
            Student oldInfo = studentRepository.findById(id).get();
            Student newInfo = createNewStudent(createStudentDto);
            newInfo.setId(oldInfo.getId());
            studentRepository.save(newInfo);
            return studentToDto(newInfo);
        } else {
            throw new RecordNotFoundException("Geen student gevonden.");
        }
    }

    public void deleteStudentById(@RequestBody Long id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Geen student gevonden.");
        }
    }

    public Student createNewStudent(CreateStudentDto dto) {
        Student student = new Student();

        student.setUsername(dto.getUsername());
        student.setPassword(dto.getPassword());
        student.setEmailAddress(dto.getEmailAddress());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());

        return student;
    }

    public StudentDto studentToDto(Student student) {
        StudentDto dto = new StudentDto();

        dto.setId(student.getId());
        dto.setUsername(student.getUsername());
        dto.setEmailAddress(student.getEmailAddress());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());

        return dto;
    }

    public Student assignTeacherToStudent(Long id, Long teacherId) {
        var optionalStudent = studentRepository.findById(id);
        var optionalTeacher = teacherRepository.findById(teacherId);

        if(optionalStudent.isPresent() && optionalTeacher.isPresent()) {
            var student = optionalStudent.get();
            var teacher = optionalTeacher.get();

            student.setTeacher(teacher);
            studentRepository.save(student);

            return student;
        } else {
            throw new RecordNotFoundException();
        }
    }
}