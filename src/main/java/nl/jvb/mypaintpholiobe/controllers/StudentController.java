package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateStudentDto;
import nl.jvb.mypaintpholiobe.domain.dtos.StudentDto;
import nl.jvb.mypaintpholiobe.domain.models.Student;
import nl.jvb.mypaintpholiobe.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok().body(allStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        StudentDto oneStudent = studentService.getStudentById(id);
        return ResponseEntity.ok().body(oneStudent);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(
            @RequestBody CreateStudentDto createStudentDto) {
        final StudentDto newStudent = studentService.createStudent(createStudentDto);
        final URI location = URI.create("/students/" + newStudent.getId());
        return ResponseEntity.created(location).body(newStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(
            @PathVariable("id") Long id,
            @RequestBody CreateStudentDto createStudentDto) {
        StudentDto student = studentService.updateStudent(id, createStudentDto);
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
}