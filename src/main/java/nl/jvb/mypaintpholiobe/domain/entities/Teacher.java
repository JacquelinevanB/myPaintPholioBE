package nl.jvb.mypaintpholiobe.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends User{

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    @PreRemove
    public void deleteTeacher() {
        this.getStudents().forEach(student -> student.setTeacher(null));
    }

}
