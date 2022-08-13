package nl.jvb.mypaintpholiobe.domain.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends User{

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ArtProject> artProjects;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public List<ArtProject> getArtProjects() { return artProjects; }

    public void setArtProjects(List<ArtProject> artProjects) {
        this.artProjects = artProjects;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
