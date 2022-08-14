package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateStudentDto;
import nl.jvb.mypaintpholiobe.domain.dtos.StudentDto;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;
import nl.jvb.mypaintpholiobe.domain.entities.Student;
import nl.jvb.mypaintpholiobe.services.StudentService;
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
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final FileUploadController fileUploadController;

    @Autowired
    public StudentController(StudentService studentService,
                             FileUploadController fileUploadController) {
        this.studentService = studentService;
        this.fileUploadController = fileUploadController;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok().body(allStudents);
    }

    @GetMapping("/{id}")
    @Transactional
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

    @PutMapping("/{id}/{teacherId}")
    public void assignTeacherToStudent(
            @PathVariable("id") Long id,
            @PathVariable("teacherId")Long teacherId) {
        studentService.assignTeacherToStudent(id, teacherId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToStudent(@PathVariable("id") Long studentId,
                                     @RequestBody MultipartFile file) {
        FileUploadResponse photo = fileUploadController.singleFileUpload(file);
        studentService.assignPhotoToStudent(photo.getFileName(), studentId);
    }
}