package nl.jvb.mypaintpholiobe.domain.entities;


import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
