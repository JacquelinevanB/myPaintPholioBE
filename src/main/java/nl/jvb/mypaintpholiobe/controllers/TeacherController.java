package nl.jvb.mypaintpholiobe.controllers;

import nl.jvb.mypaintpholiobe.domain.dtos.CreateTeacherDto;
import nl.jvb.mypaintpholiobe.domain.dtos.TeacherDto;
import nl.jvb.mypaintpholiobe.domain.models.Teacher;
import nl.jvb.mypaintpholiobe.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//import javax.validation.Valid;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> allTeachers = teacherService.getAllTeachers();
        return ResponseEntity.ok().body(allTeachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable("id") Long id) {
        TeacherDto oneTeacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok().body(oneTeacher);
    }

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(
            @RequestBody CreateTeacherDto createTeacherDto) {
        final TeacherDto newTeacher = teacherService.createTeacher(createTeacherDto);
        final URI location = URI.create("/teachers/" + newTeacher.getId());
        return ResponseEntity.created(location).body(newTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(
            @PathVariable("id") Long id,
            @RequestBody CreateTeacherDto createTeacherDto) {
        TeacherDto teacher = teacherService.updateTeacher(id, createTeacherDto);
        return ResponseEntity.ok().body(teacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.noContent().build();
    }
}