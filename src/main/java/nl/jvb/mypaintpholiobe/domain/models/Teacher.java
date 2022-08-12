package nl.jvb.mypaintpholiobe.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher extends User{

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    List<Student> students;

}
